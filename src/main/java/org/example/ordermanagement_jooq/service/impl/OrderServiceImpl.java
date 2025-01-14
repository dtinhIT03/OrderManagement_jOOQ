package org.example.ordermanagement_jooq.service.impl;

import generated_sources.tables.pojos.Order;
import generated_sources.tables.pojos.OrderProduct;
import generated_sources.tables.pojos.Product;
import generated_sources.tables.pojos.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
import org.example.ordermanagement_jooq.data.mapper.OrderMapper;
import org.example.ordermanagement_jooq.data.mapper.OrderProductMapper;
import org.example.ordermanagement_jooq.data.mapper.ProductMapper;
import org.example.ordermanagement_jooq.data.mapper.UserMapper;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.OrderProductRequest;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.*;
import org.example.ordermanagement_jooq.exception.ResourceNotFoundException;
import org.example.ordermanagement_jooq.repository.OrderProductRepository;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.example.ordermanagement_jooq.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.ordermanagement_jooq.util.PageResponseUtils.toPageResponse;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderMapper orderMapper;
    OrderRepository orderRepository;
    OrderProductRepository orderProductRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    ProductMapper productMapper;
    UserMapper userMapper;
    OrderProductMapper orderProductMapper;
    @Override
    public Void save(OrderRequest request) {
        Order order = orderMapper.toOrder(request);
        Order order1 = orderRepository.save(order);
        List<OrderProductRequest> orderProducts = request.getItems().stream().peek(req -> req.setOrderId(order1.getId())).toList();
        List<OrderProduct> orderProductList =  orderProducts.stream().map(orderProductMapper::toOrderProduct).collect(Collectors.toList());
        orderProductRepository.save(orderProductList);
        return null;
    }

    @Override
    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        //find user by user_id trong order
        User user = userRepository.findById(order.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        UserResponse userResponse = userMapper.toUserReponse(user);

        List<OrderProduct> orderProductList = orderProductRepository.getAllByOrderId(id);

        //find product by productId trong orderProduct -> lấy list productId trong List<OrderProduct> trước sau đó map ra list product chẳng hạn
        List<Long> listProductId = orderProductList.stream().map(OrderProduct::getProductId).collect(Collectors.toList());
        Map<Long, ProductResponse> ProductReponseMap = productRepository.findAllByListId(listProductId).stream()
                .collect(Collectors.toMap(product -> product.getId(),productMapper::toProductResponse));
        // -> map sang List<OrderProductResponse>
        List<OrderProductResponse> orderProductResponses = orderProductList.stream()
                .map(orderProduct -> {
                    ProductResponse productResponse = ProductReponseMap.get(orderProduct.getProductId());
                    return OrderProductResponse.builder()
                            .productResponse(productResponse)
                            .totalPrice(orderProduct.getTotalPrice())
                            .quantity(orderProduct.getQuantity())
                            .build();
                }).collect(Collectors.toList());
        return toResponse(order,userResponse,orderProductResponses);
    }



    @Override
    public PageResponse<OrderResponse> searchOrders(List<FilterCondition> filterConditions, Pageable pageable) {
        Page<Order> orderPage = orderRepository.searchOrders(filterConditions,pageable);
        List<Order> orders = orderPage.getContent();

        //lấy ra list userId
        List<Long> userIds = orders.stream().map(Order::getUserId).toList();
        //query ra list user cho vào 1 map<id,userResponse>
        Map<Long,UserResponse> userResponseMap = userRepository.findAllByListId(userIds).stream().collect(Collectors.toMap(User::getId,userMapper::toUserReponse));
        //lấy ra list orderId
        List<Long> orderIds = orders.stream().map(Order::getId).toList();
        //lấy ra list orderProduct
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderIds(orderIds);
        //lấy ra list productId
        List<Long> productIds = orderProducts.stream().map(OrderProduct::getProductId).toList();
        //lấy ra list product
        Map<Long,ProductResponse> productResponseMap = productRepository.findAllByListId(productIds).stream().collect(Collectors.toMap(Product::getId,productMapper::toProductResponse));
        //map OrderProduct sang OrderProductResponse
        Map<Long,OrderProductResponse> orderProductResponseMap = orderProducts.stream().collect(Collectors.toMap(OrderProduct::getId,
                orderProduct -> OrderProductResponse.builder()
                        .productResponse(productResponseMap.get(orderProduct.getProductId()))
                        .quantity(orderProduct.getQuantity())
                        .totalPrice(orderProduct.getTotalPrice())
                        .build()));

        //map Order sang OrderResponse
        List<OrderResponse> orderResponses = orderMapper.toListOrderResponse(orders,userResponseMap,orderProducts,orderProductResponseMap);

        return toPageResponse(pageable,orderResponses,orderPage);
    }

    @Override
    public void delete(Long id) {
           boolean valid =  orderRepository.delete(id);
           if(!valid){
               throw new ResourceNotFoundException("Order not found!");
           }
    }

    @Override
    public Order changeStatusOrder(Long id, StatusOrder status) {
        Order order1= orderRepository.update(id,status);
        return order1;
    }

    private OrderResponse toResponse(Order order,UserResponse userResponse,List<OrderProductResponse> orderProductResponses){
        return  orderMapper.toOrderResponse(order,userResponse,orderProductResponses);

    }



}

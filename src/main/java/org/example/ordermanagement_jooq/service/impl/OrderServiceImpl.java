package org.example.ordermanagement_jooq.service.impl;

import generated_sources.tables.pojos.Order;
import generated_sources.tables.pojos.OrderProduct;
import generated_sources.tables.pojos.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.mapper.OrderMapper;
import org.example.ordermanagement_jooq.data.mapper.ProductMapper;
import org.example.ordermanagement_jooq.data.mapper.UserMapper;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderProductResponse;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.example.ordermanagement_jooq.data.response.UserResponse;
import org.example.ordermanagement_jooq.repository.OrderProductRepository;
import org.example.ordermanagement_jooq.repository.OrderRepository;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.example.ordermanagement_jooq.repository.UserRepository;
import org.example.ordermanagement_jooq.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Override
    public Void save(OrderRequest request) {
        Order order = orderMapper.toOrder(request);
        orderRepository.save(order);
        orderProductRepository.save(request.getItems());
        return null;
    }

    @Override
    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id);
        //find user by user_id trong order
        User user = userRepository.findById(order.getUserId());
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
    public OrderResponse toResponse(Order order,UserResponse userResponse,List<OrderProductResponse> orderProductResponses){
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        orderResponse.setUserResponse(userResponse);
        orderResponse.setItems(orderProductResponses);
        return orderResponse;

    }
}

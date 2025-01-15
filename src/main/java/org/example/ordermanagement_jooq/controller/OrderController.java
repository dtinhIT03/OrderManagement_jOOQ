package org.example.ordermanagement_jooq.controller;

import generated_sources.tables.pojos.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.enums.StatusOrder;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.example.ordermanagement_jooq.data.response.ResponseData;
import org.example.ordermanagement_jooq.data.response.ResponseError;
import org.example.ordermanagement_jooq.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {
    OrderService orderService;

    @GetMapping("/{id}")
    public ResponseData<OrderResponse> getById(@PathVariable @Min(1) Long id){
        try{
            return new ResponseData(HttpStatus.OK.value(),"get Order successfully!",orderService.getById(id));

        }catch(Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());

        }
    }

    @PostMapping
    public ResponseData<Void> save(@RequestBody @Valid OrderRequest request){
        try{
            orderService.save(request);
            return new ResponseData(HttpStatus.OK.value(),"save Order successfully!");
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    //API update PATCH
    @PatchMapping("/{id}")
    public ResponseData<Order> changeStatusOrder(@PathVariable @Min(1) Long id, @RequestParam(name = "status") StatusOrder status){
        try{
            Order order =  orderService.changeStatusOrder(id,status);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Update status order successfully !",order);
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    //API delete
    @DeleteMapping("/{id}")
    public ResponseData<Void> delete(@PathVariable @Min(1) Long id){
        try{
            orderService.delete(id);
            return new ResponseData<>(HttpStatus.OK.value(), "delete successfully!");
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseData<PageResponse<OrderResponse>> searchOrders(@RequestBody @Valid List<FilterCondition> filterConditions,
                                                                    Pageable pageable){
        return new ResponseData( HttpStatus.OK.value(), "search Order Successfully!",orderService.searchOrders(filterConditions,pageable));
    }

}

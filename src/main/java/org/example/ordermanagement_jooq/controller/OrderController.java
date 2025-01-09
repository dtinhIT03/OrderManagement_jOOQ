package org.example.ordermanagement_jooq.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.OrderRequest;
import org.example.ordermanagement_jooq.data.response.OrderResponse;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.example.ordermanagement_jooq.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderController {
    OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.save(request));
    }

    @GetMapping("/list-order")
    public ResponseEntity<Page<OrderResponse>> getOrders(Pageable pageable){
        return ResponseEntity.ok(orderService.getOrders(pageable));
    }

    @GetMapping("/page-order")
    public ResponseEntity<PageResponse<OrderResponse>> getPageOrders(Pageable pageable){
        return ResponseEntity.ok(orderService.getPageOrders( pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<OrderResponse>> searchOrders(@RequestBody List<FilterCondition> filterConditions, Pageable pageable){
        return ResponseEntity.ok(orderService.searchOrders(filterConditions,pageable));
    }

}

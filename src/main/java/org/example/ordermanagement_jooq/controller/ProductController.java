package org.example.ordermanagement_jooq.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.ProductRequest;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.example.ordermanagement_jooq.data.response.ResponseData;
import org.example.ordermanagement_jooq.data.response.ResponseError;
import org.example.ordermanagement_jooq.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    @GetMapping("/{id}")
    public ResponseData<ProductResponse> getById(@PathVariable @Min(1) Long id){
        try{

            return new ResponseData<>(HttpStatus.OK.value(),"Get Product successfully!",productService.getById(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @PostMapping
    public ResponseData<ProductResponse> save(@Valid @RequestBody ProductRequest request){
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "save Product successfully!",productService.add(request));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseData<Boolean> deleteById(@PathVariable @Min(1) Long id){
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "delete Product successfully!",productService.delete(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseData<PageResponse<ProductResponse>> search(@RequestBody List<FilterCondition> filters, Pageable pageable){
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "search Product successfully!",productService.search(filters,pageable));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}

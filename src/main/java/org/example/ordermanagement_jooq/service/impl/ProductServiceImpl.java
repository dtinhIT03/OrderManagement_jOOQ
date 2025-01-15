package org.example.ordermanagement_jooq.service.impl;

import generated_sources.tables.pojos.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ordermanagement_jooq.data.mapper.ProductMapper;
import org.example.ordermanagement_jooq.data.request.FilterCondition;
import org.example.ordermanagement_jooq.data.request.ProductRequest;
import org.example.ordermanagement_jooq.data.response.PageResponse;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.example.ordermanagement_jooq.repository.ProductRepository;
import org.example.ordermanagement_jooq.service.ProductService;
import org.example.ordermanagement_jooq.util.PageResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id);
        ProductResponse response = productMapper.toProductResponse(product);
        return response;
    }

    @Override
    public ProductResponse add(ProductRequest request) {
         Product product = productMapper.toProduct(request);
         Product product1 = productRepository.save(product);

        return productMapper.toProductResponse(product1);
    }

    @Override
    public boolean delete(Long id) {
        return productRepository.deleteById(id);
    }

    @Override
    public PageResponse<ProductResponse> search(List<FilterCondition> filters, Pageable pageable) {
        Page<Product> page = productRepository.searchProduct(filters,pageable);
        List<Product> products = page.getContent();
        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.toProductResponse(product)).toList();
        return PageResponseUtils.toPageResponse(pageable,productResponses,page);
    }

}

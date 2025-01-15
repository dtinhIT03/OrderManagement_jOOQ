package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.Product;
import org.example.ordermanagement_jooq.data.request.ProductRequest;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductRequest request);
}

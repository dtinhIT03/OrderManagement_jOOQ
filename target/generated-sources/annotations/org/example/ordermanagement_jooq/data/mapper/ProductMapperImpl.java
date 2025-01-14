package org.example.ordermanagement_jooq.data.mapper;

import generated_sources.tables.pojos.Product;
import javax.annotation.processing.Generated;
import org.example.ordermanagement_jooq.data.response.ProductResponse;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-14T14:24:09+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.sellingPrice( product.getSellingPrice() );
        productResponse.name( product.getName() );

        return productResponse.build();
    }
}

package br.com.lufecrx.crudexercise.api.model.dto;

import java.util.Set;

import br.com.lufecrx.crudexercise.api.model.Product;

public record ProductDTO(
    String name,
    Double price,
    Set<CategoryDTO> categories
) {
    public static ProductDTO from(Product product)
    {
        return new ProductDTO(product.getProductName(), product.getPrice(), CategoryDTO.from(product.getCategories()));
    }

    public static Set<ProductDTO> from(Set<Product> products)
    {
        return products.stream().map(ProductDTO::from).collect(java.util.stream.Collectors.toSet());
    }
} 
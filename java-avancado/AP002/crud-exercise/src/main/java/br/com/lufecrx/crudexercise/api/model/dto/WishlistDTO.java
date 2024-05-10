package br.com.lufecrx.crudexercise.api.model.dto;

import java.util.Set;

import br.com.lufecrx.crudexercise.api.model.Wishlist;

public record WishlistDTO(
    String name,
    Set<ProductDTO> products
) {
    public static WishlistDTO from(Wishlist wishlist) {
        return new WishlistDTO(wishlist.getName(), ProductDTO.from(wishlist.getProducts()));
    }
} 


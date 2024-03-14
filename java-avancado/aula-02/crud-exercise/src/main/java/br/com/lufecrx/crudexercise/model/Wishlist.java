package br.com.lufecrx.crudexercise.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlist")
public class Wishlist {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Wishlist name cannot be blank")
    private String name;

    @OneToMany (mappedBy = "wishlist")
    private List<ProductModel> products;

    public void addToWishlist(ProductModel product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }
}

package br.com.lufecrx.crudexercise.api.model;

import java.util.HashSet;
import java.util.Set;

import br.com.lufecrx.crudexercise.auth.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Set<Product> products;   

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addToWishlist(Product product) {
        // Initialize the products set if it's null
        if (this.products == null) {
            this.products = new HashSet<>();
        }
        this.products.add(product);
    }

    public void removeFromWishlist(Product product) {
        if (this.products != null) {
            this.products.remove(product);
        }
    }
}

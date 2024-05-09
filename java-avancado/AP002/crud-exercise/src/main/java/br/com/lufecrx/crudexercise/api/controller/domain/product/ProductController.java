package br.com.lufecrx.crudexercise.api.controller.domain.product;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.services.domain.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@ApiResponse(responseCode = "403", description = "You are not authorized to access this resource")
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("standard") ProductService productService) {
        this.productService = productService;
    }

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    // @GetMapping
    // public ResponseEntity<Iterable<Product>> findAll() {
    //     Iterable<Product> products = productService.getAllProducts();
    //     return ResponseEntity.ok(products);
    // }

    @Operation(summary = "Create a new product", description = "Create a new product with the given data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product created"),
        @ApiResponse(responseCode = "409", description = "Product already exists")        
    })
    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Product dto) {
        productService.createProduct(dto);
        return ResponseEntity.ok(bundle.getString("product.successfully_created"));
    }

    @Operation(summary = "Find a product by its ID", description = "Find a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Update a product", description = "Update a product with the given data")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid Product dto) {
        productService.updateProduct(id, dto);
        return ResponseEntity.ok(bundle.getString("product.successfully_updated"));
    }

    @Operation(summary = "Delete a product", description = "Delete a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(bundle.getString("product.successfully_deleted"));
    }
}

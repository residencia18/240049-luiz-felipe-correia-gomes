package br.com.lufecrx.crudexercise.api.controller.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.services.domain.product.ProductServicePaginable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/paginable/products")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Products found"),
    @ApiResponse(responseCode = "404", description = "Products not found")
})
public class ProductControllerPaginable {

    private ProductServicePaginable productService;

    @Autowired
    public ProductControllerPaginable(@Qualifier("paginable") ProductServicePaginable productService) {
        this.productService = productService;
    }

    @Operation(summary = "Find all products with pagination", description = "Find all products with pagination")
    @GetMapping("/page/{page}")
    public ResponseEntity<Iterable<Product>> findAll(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        // Default pagination size is 10
        Iterable<Product> entities = productService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all products with pagination and size 5", description = "Find all products with pagination and size 5")
    @GetMapping("/page/{page}/size=5")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeFive(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 5, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all products with pagination and size 10", description = "Find all products with pagination and size 10")
    @GetMapping("/page/{page}/size=10")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTen(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Find all products with pagination and size 20", description = "Find all products with pagination and size 20")
    @GetMapping("/page/{page}/size=20")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTwenty(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 20, sort);
        return ResponseEntity.ok(entities);
    }
}

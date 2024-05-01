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

@RestController
@RequestMapping("/paginable/products")
public class ProductControllerPaginable {

    private ProductServicePaginable productService;

    @Autowired
    public ProductControllerPaginable(@Qualifier("paginable") ProductServicePaginable productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "name,asc") String[] sort) {
        // Default pagination size is 10
        Iterable<Product> entities = productService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size=5")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeFive(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 5, sort);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size=10")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTen(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 10, sort);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size=20")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTwenty(
            @PathVariable int page,
            @RequestParam(value = "sort", defaultValue = "productName,asc") String[] sort) {
        Iterable<Product> entities = productService.getWithPagination(page, 20, sort);
        return ResponseEntity.ok(entities);
    }
}

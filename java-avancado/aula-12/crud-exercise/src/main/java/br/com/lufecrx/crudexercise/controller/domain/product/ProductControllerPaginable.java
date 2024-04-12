package br.com.lufecrx.crudexercise.controller.domain.product;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.services.domain.product.ProductServicePaginable;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paginable/products")
public class ProductControllerPaginable extends ProductController {
    
    @Qualifier("paginable")
    private ProductServicePaginable productService;

    @GetMapping("/page/{page}/size/5")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeFive(@PathVariable int page) {
        Iterable<Product> entities = productService.getWithPagination(page, 5); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/10")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTen(@PathVariable int page) {
        Iterable<Product> entities = productService.getWithPagination(page, 10); 
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/page/{page}/size/20")
    public ResponseEntity<Iterable<Product>> findAllWithPaginationAndSizeTwenty(@PathVariable int page) {
        Iterable<Product> entities = productService.getWithPagination(page, 20); 
        return ResponseEntity.ok(entities);
    }
}

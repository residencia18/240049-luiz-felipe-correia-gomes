package br.com.lufecrx.crudexercise.services.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.product.ProductsEmptyException;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Qualifier("paginable")
@Slf4j
public class ProductServicePaginable extends ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public Iterable<Product> getWithPagination(int page, int size) {
        log.info("Getting all products with pagination, page {} and size {}", page, size);

        Pageable pageRequest = PageRequest.of(page, size);

        if (!productRepository.findAll(pageRequest).iterator().hasNext()) {
            throw new ProductsEmptyException();
        }

        return productRepository.findAll(pageRequest);
    }
}
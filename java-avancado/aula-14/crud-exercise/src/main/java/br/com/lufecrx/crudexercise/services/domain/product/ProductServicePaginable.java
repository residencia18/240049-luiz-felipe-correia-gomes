package br.com.lufecrx.crudexercise.services.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Cacheable(value = "products", key = "#page + #size + #sort")
    public Iterable<Product> getWithPagination(int page, int size, String[] sort) {
        log.info("Getting all products with pagination, page {} and size {}", page, size);

        String property = sort[0];
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(direction, property));

        Page<Product> productPage = productRepository.findAll(pageRequest);

        if (!productPage.iterator().hasNext()) {
            throw new ProductsEmptyException();
        }

        return productPage;
    }
}
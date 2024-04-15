package br.com.lufecrx.crudexercise.services.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.category.CategoriesEmptyException;
import br.com.lufecrx.crudexercise.model.Category;
import br.com.lufecrx.crudexercise.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Qualifier("paginable")
public class CategoryServicePaginable extends CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    public Iterable<Category> getWithPagination(int page, int size, String[] sort) {
        log.info("Getting all categories with pagination, page {}, size {} and sort {}", page, size, sort);

        String property = sort[0];
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(direction, property));

        if (!categoryRepository.findAll(pageRequest).iterator().hasNext()) {
            throw new CategoriesEmptyException();
        }

        return categoryRepository.findAll(pageRequest);
    }

}

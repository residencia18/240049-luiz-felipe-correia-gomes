package br.com.lufecrx.crudexercise.api.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.lufecrx.crudexercise.api.model.Category;

public record CategoryDTO(
    String name
) {

    public static CategoryDTO from(Category category)
    {
        return new CategoryDTO(category.getName());
    }

    public static Set<CategoryDTO> from(Set<Category> categories)
    {
        return categories.stream().map(CategoryDTO::from).collect(Collectors.toSet());
    }    
}

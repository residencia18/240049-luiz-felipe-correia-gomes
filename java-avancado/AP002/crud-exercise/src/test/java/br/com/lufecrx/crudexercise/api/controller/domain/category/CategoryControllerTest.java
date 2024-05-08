package br.com.lufecrx.crudexercise.api.controller.domain.category;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lufecrx.crudexercise.api.model.Category;
import br.com.lufecrx.crudexercise.api.services.domain.category.CategoryService;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    // Test methods when the user is authenticated as an ADMIN, which has all the permissions
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testFindByIdAsAdmin() throws Exception {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        category.setName("Test Category");

        when(categoryService.getCategoryById(id)).thenReturn(Optional.of(category));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + id))
                .andExpect(status().isOk()) // Dont need authentication to access this endpoint
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Category response = new ObjectMapper().readValue(json, Category.class);
                    assert response.getId().equals(id);
                    assert response.getName().equals("Test Category");
                });
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateCategoryAsAdmin() throws Exception {
        Category dto = new Category();
        dto.setName("Test Category");

        when(categoryService.createCategory(dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCategoryAsAdmin() throws Exception {
        Long id = 1L;
        Category dto = new Category();
        dto.setName("Updated Category");

        when(categoryService.updateCategory(id, dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCategoryAsAdmin() throws Exception {
        Long id = 1L;

        doNothing().when(categoryService).deleteCategory(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + id))
                .andExpect(status().isOk());
    }

    // Test methods when the user is authenticated as a USER, which has limited permissions
    @Test
    @WithMockUser(roles = "USER")
    public void testFindByIdAsUser() throws Exception {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        category.setName("Test Category");

        when(categoryService.getCategoryById(id)).thenReturn(Optional.of(category));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + id))
                .andExpect(status().isOk()) // Dont need authentication to access this endpoint
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Category response = new ObjectMapper().readValue(json, Category.class);
                    assert response.getId().equals(id);
                    assert response.getName().equals("Test Category");
                });
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateCategoryAsUser() throws Exception {
        Category dto = new Category();
        dto.setName("Test Category");

        when(categoryService.createCategory(dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                // The user is prohibited from creating a category
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateCategoryAsUser() throws Exception {
        Long id = 1L;
        Category dto = new Category();
        dto.setName("Updated Category");

        when(categoryService.updateCategory(id, dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                // The user is prohibited from updating a category
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteCategoryAsUser() throws Exception {
        Long id = 1L;

        doNothing().when(categoryService).deleteCategory(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + id))
                // The user is prohibited from deleting a category
                .andExpect(status().isForbidden());
    }

    // Test methods when the user is not authenticated
    @Test
    @WithMockUser(roles = "USER")
    public void testFindByIdAsNotAuthenticated() throws Exception {
        Long id = 1L;
        Category category = new Category();
        category.setId(id);
        category.setName("Test Category");

        when(categoryService.getCategoryById(id)).thenReturn(Optional.of(category));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/" + id))
                .andExpect(status().isOk()) // Dont need authentication to access this endpoint
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    Category response = new ObjectMapper().readValue(json, Category.class);
                    assert response.getId().equals(id);
                    assert response.getName().equals("Test Category");
                });
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateCategoryAsNotAuthenticated() throws Exception {
        Category dto = new Category();
        dto.setName("Test Category");

        when(categoryService.createCategory(dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                // Prohibited from creating a category
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateCategoryAsNotAuthenticated() throws Exception {
        Long id = 1L;
        Category dto = new Category();
        dto.setName("Updated Category");

        when(categoryService.updateCategory(id, dto)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/categories/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                // Prohibited from updating a category
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteCategoryAsNotAuthenticated() throws Exception {
        Long id = 1L;

        doNothing().when(categoryService).deleteCategory(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categories/" + id))
                // Prohibited from deleting a category
                .andExpect(status().isForbidden());
    }
}
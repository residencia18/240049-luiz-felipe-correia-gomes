package br.com.lufecrx.crudexercise.services.domain.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistsEmptyException;
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.repository.WishlistRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Qualifier("paginable")
@Slf4j
public class WishlistServicePaginable {
    
    @Autowired
    private WishlistRepository wishlistRepository;

    @Cacheable(value = "wishlists", key = "#page.toString() + #size.toString() + T(java.util.Arrays).toString(#sort)")
    public Iterable<Wishlist> getWithPagination(int page, int size, String[] sort) {
        log.info("Getting all wishlists with pagination, page {} and size {}", page, size);

        String property = sort[0];
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(direction, property));
        
        Page<Wishlist> wishlistPage = wishlistRepository.findAll(pageRequest);

        if (!wishlistPage.iterator().hasNext()) {
            throw new WishlistsEmptyException();
        }
    
        return wishlistPage;
    }
}

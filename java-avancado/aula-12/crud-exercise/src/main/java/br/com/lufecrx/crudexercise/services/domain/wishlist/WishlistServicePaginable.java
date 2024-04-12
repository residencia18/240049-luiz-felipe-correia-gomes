package br.com.lufecrx.crudexercise.services.domain.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Iterable<Wishlist> getWithPagination(int page, int size) {
        log.info("Getting all wishlists with pagination, page {} and size {}", page, size);

        Pageable pageRequest = PageRequest.of(page, size);

        if (!wishlistRepository.findAll(pageRequest).iterator().hasNext()) {
            throw new WishlistsEmptyException();
        }

        return wishlistRepository.findAll(pageRequest);
    }
}

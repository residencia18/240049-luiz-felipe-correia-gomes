package br.com.lufecrx.crudexercise.services.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.user.UsersEmptyException;
import br.com.lufecrx.crudexercise.model.User;
import br.com.lufecrx.crudexercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Qualifier("paginable")
@Slf4j
public class UserServicePaginable {

    @Autowired
    private UserRepository userRepository;
    
    public Iterable<User> getWithPagination (int page, int size, String[] sort) {
        log.info("Getting all users with pagination, page {} and size {}", page, size);

        String property = sort[0];
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);

        Pageable pageRequest = PageRequest.of(page, size, Sort.by(direction, property));

        if (!userRepository.findAll(pageRequest).iterator().hasNext()) {
            throw new UsersEmptyException();
        }

        return userRepository.findAll(pageRequest);
    }
}

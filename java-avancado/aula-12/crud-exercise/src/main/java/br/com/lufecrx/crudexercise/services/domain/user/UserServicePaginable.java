package br.com.lufecrx.crudexercise.services.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    public Iterable<User> getWithPagination (int page, int size) {
        log.info("Getting all users with pagination, page {} and size {}", page, size);

        Pageable pageRequest = PageRequest.of(page, size);

        if (!userRepository.findAll(pageRequest).iterator().hasNext()) {
            throw new UsersEmptyException();
        }

        return userRepository.findAll(pageRequest);
    }
}

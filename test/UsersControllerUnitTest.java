package com.example.demo.presentation;

import java.util.Optional;

import com.example.demo.UserDto;
import com.example.demo.persistence.User;
import com.example.demo.persistence.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UsersControllerUnitTest {

    private UsersController usersController;

    @Test
    void testGetUserByIdWhenUserIsNotFound() {
        usersController = new UsersController(new UsersRepository() {
            @Override
            public <S extends User> S save(final S entity) {
                return null;
            }

            @Override
            public <S extends User> Iterable<S> saveAll(final Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<User> findById(final Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(final Long aLong) {
                return false;
            }

            @Override
            public Iterable<User> findAll() {
                return null;
            }

            @Override
            public Iterable<User> findAllById(final Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(final Long aLong) {

            }

            @Override
            public void delete(final User entity) {

            }

            @Override
            public void deleteAllById(final Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(final Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });

        final ResponseEntity<UserDto> result = usersController.getUserById(1L);

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(result.getBody().getId()).isNull();
        Assertions.assertThat(result.getBody().getUserName()).isNull();
        Assertions.assertThat(result.getBody().getEmail()).isNull();
        Assertions.assertThat(result.getBody().getName()).isNull();

        Assertions.assertThat(result.getBody().getError())
            .isEqualTo("User with the given id (1) does not exist");
    }

}
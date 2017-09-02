package io.codearte.jFairyOnline.repositories;

import java.util.Optional;

import io.codearte.jFairyOnline.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findFirstByEmail(String email);
}

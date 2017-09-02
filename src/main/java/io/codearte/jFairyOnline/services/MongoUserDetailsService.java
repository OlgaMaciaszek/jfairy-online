package io.codearte.jFairyOnline.services;

import java.util.Optional;

import io.codearte.jFairyOnline.model.User;
import io.codearte.jFairyOnline.model.security.MongoUserDetails;
import io.codearte.jFairyOnline.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findFirstByEmail(email);
		return userOpt.map(user -> new MongoUserDetails(email, user.getPassword(),
				getGrantedAuthorities(user), user.isActive()))
				.orElse(null);
	}

	private String[] getGrantedAuthorities(User user) {
		return user.getAuthorities().toArray(new String[user.getAuthorities().size()]);
	}
}

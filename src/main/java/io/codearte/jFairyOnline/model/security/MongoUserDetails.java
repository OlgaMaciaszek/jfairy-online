package io.codearte.jFairyOnline.model.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Olga Maciaszek-Sharma
 * @since 9/2/17
 */
public class MongoUserDetails implements UserDetails {

	private String username;
	private String password;
	private List<GrantedAuthority> grantedAuthorities;
	private boolean enabled;

	public MongoUserDetails(String username, String password, String[] grantedAuthorities,
	                        boolean enabled) {
		this.username = username;
		this.password = password;
		this.grantedAuthorities = AuthorityUtils.createAuthorityList(grantedAuthorities);
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}

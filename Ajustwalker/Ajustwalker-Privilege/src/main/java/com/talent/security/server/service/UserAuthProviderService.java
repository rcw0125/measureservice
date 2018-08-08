package com.talent.security.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.talent.core.privilege.model.User;

@Component
public class UserAuthProviderService implements AuthenticationProvider {

	@Autowired
	private UserAuthConfigService userAuthConfigService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Authentication signInUser(User user, List<GrantedAuthority> roles) {
		UserDetails springSecurityUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user,springSecurityUser,roles);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}
	
	@Override
	public Authentication authenticate(Authentication a) throws AuthenticationException {
		String username = a.getName();
		String password = a.getCredentials().toString();
		String[] usernameParams = username.split("@##@");
		if(usernameParams.length == 1){
			username = usernameParams[0];
		}else{
			username = usernameParams[1];
		}
		User user = userAuthConfigService.getUser(username);
		if (null != user) {
			/*if (!Objects.equals(user.getServicing(), "1")) {
				throw new AccountExpiredException("用户已禁用，不允许登录！");
	        }*/
			user.setReserve1(usernameParams[0]);
			if (passwordEncoder.matches(password, user.getPassword())) {
				return signInUser(user, userAuthConfigService.getRights(user));
			}
			throw new AuthenticationException("用户：'" + username + "'密码不正确！") {
				private static final long serialVersionUID = -2691730922171905272L;
			};
		}

		throw new AuthenticationException("不存在名为：'" + username + "'的用户！") {
			private static final long serialVersionUID = -6740275784112091932L;
		};
	}

	@Override
	public boolean supports(Class<?> type) {
		return type.equals(UsernamePasswordAuthenticationToken.class);
	}
}

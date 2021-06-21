package com.again.cloud.auth.config;

import com.again.cloud.auth.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.secret-key}")
	private String secretKey;

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return PasswordUtil.ENCODER;
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				System.out.println(username);
				System.out.println(secretKey);
				return null;
			}
		};
	}

}

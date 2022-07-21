package com.example.pet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.pet.AuthService;
import com.example.pet.service.AccountService;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;

	@Autowired
	AuthService authService;

	// mã hoá mật khẩu
	@Bean
	public BCryptPasswordEncoder getPassWordEnCoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}



	//lấy dữ liệu
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// vô hiệu hoá chia sẻ tài nguyên và truy cập từ bên ngoài
		http.csrf().disable().cors().disable();

		//phân quyền
		http.authorizeRequests().antMatchers("/admin/**").hasRole("1").antMatchers("/order/**")
				.authenticated().anyRequest().permitAll();

		// điều khiển lỗi khi không đúng vai trò
		http.exceptionHandling().accessDeniedPage("/");

		http.formLogin().loginPage("/login-form").loginProcessingUrl("/auth/login").defaultSuccessUrl("/", false)
				.failureUrl("/login-form").usernameParameter("username").passwordParameter("password");

		http.rememberMe().tokenValiditySeconds(86400);

		
		// đăng nhập từ mạng xã hội
		http.oauth2Login().loginPage("/login-form").defaultSuccessUrl("/oauth2/login/success", false).failureUrl("/auth/login/erro")
				.authorizationEndpoint().baseUri("/oauth2/authorization");// base uri

		//đăng nhập rồi mà truy cập vào trang ko có quyền
		http.exceptionHandling().accessDeniedPage("/");

		// đăng xuất
		http.logout().logoutUrl("/auth/logout") // truyền url gọi đến logout (không cần lập trình) truyền url vào thẻ a
												// rồi gọi
				.logoutSuccessUrl("/login-form"); // khi đăng xuất thành công trả về trang đăng nhập
	}

}

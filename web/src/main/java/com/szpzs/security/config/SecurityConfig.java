package com.szpzs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan
@ImportResource("classpath*:/web-servlet.xml")
public class SecurityConfig {
	
	@Autowired
    public AuthenticationManagerBuilder auth;
	
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return auth.getOrBuild();
   }
	
	/*@Bean  
	public Md5PasswordEncoder encoder() throws Exception {  
	  return new Md5PasswordEncoder();  
	}
	*/
	@Autowired
	@Qualifier("UserServiceImpl")
    private com.szpzs.service.UserServiceImpl UserServiceImpl;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth
			.userDetailsService(UserServiceImpl);
			//.passwordEncoder(encoder());
    }
	
	@Order(1)
	@Configuration
	 public static class PagesWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
	    http
			.csrf().disable()
			//.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
			//.and()
	        .authorizeRequests()
	        	.antMatchers("GET", "/pages/beCareful.html").hasRole("ADMIN")
	        	.antMatchers("GET", "/pages/admin.html").hasRole("ADMIN")
	        	.antMatchers("GET", "/js/adminCtrl.html").hasRole("ADMIN")
	        	.antMatchers("GET", "/pages/private_profile.html").hasAnyRole("USER", "ADMIN")
	        	.antMatchers("GET", "/pages/messages.html").hasAnyRole("USER", "ADMIN")
	        	.antMatchers("GET", "/pages/my_products.html").hasAnyRole("USER", "ADMIN")
	        	.antMatchers("GET", "/pages/product_upload.html").hasAnyRole("USER", "ADMIN")
	        	.antMatchers("GET", "/pages/reservation.html").hasAnyRole("USER", "ADMIN")
	        	.antMatchers("GET", "/pages/user_menu.html").hasAnyRole("USER", "ADMIN");
	    	//.requiresChannel().anyRequest().requiresSecure();
        }
	}

	@Order(2)
	@Configuration                                                   
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            	.csrf().disable()
            	.antMatcher("/api/*")
                .authorizeRequests()
                	.anyRequest().permitAll();
        }
    }
	
    @Configuration                                                   
    public static class IndexWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                	.antMatchers("/**").permitAll();
        }
    }

}

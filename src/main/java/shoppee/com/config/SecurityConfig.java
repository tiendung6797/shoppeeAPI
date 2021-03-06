package shoppee.com.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import shoppee.com.security.CustomUserDetailsService;
import shoppee.com.security.JwtAuthenticationEntryPoint;
import shoppee.com.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUserDetailsService ;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	
	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .cors()
	                    .and()
	                .csrf()
	                    .disable()
	                .exceptionHandling()
	                    .authenticationEntryPoint(unauthorizedHandler)
	                    .and()
	                .sessionManagement()
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                    .and()
	                .authorizeRequests()
	                	.antMatchers(AUTH_WHITELIST).permitAll()
	                    .antMatchers("/",
	                        "/favicon.ico",
	                        "/**/*.png",
	                        "/**/*.gif",
	                        "/**/*.svg",
	                        "/**/*.jpg",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js")
	                        .permitAll()
	                    .antMatchers("/admin/login","/category/parent","/category/byparent/{parent_id}",
	                    		"/category/{id}","/downloadFile/{fileId}","/paypal/**", "/productbill/**",
	                    		"/product/**", "/uploadMultipleFiles/{proId}","/uploadFile", "/review/**", 
	                    		"/store/**", "/user/**")
	                        .permitAll()
	                    .anyRequest()
	                        .authenticated();

	        // Add our custom JWT security filter
	        http.addFilterBefore((javax.servlet.Filter)jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	       

	    }
//	 @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//	        return source;
//	    }
	 @Bean
		CorsConfigurationSource corsConfigurationSource() 
		{
			CorsConfiguration configuration = new CorsConfiguration();
			
			configuration.setAllowCredentials(true);
			configuration.addAllowedOrigin("http://localhost:3000");
			configuration.addAllowedHeader("*");
			configuration.addAllowedMethod("*");
			
			
//			configuration.setAllowedOrigins(Collections.singletonList("*"));
//			configuration.setAllowedHeaders(Collections.singletonList("*"));
//			configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}
}








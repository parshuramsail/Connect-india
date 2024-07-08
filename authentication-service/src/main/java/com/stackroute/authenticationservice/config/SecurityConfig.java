package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.filter.JwtFilter;
import com.stackroute.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    public static final String[] PUBLIC_URLS = {"/api/v1/auth/**", "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**",
            "/swagger-ui/**", "/webjars/**", "/api/v1/users",
            "/api/v1/auth", "/api/v1/addUser","/api/v1/addUser/**","/api/v1/findUser/**"

    };

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig) throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        // Setting the password encoder to the daoAuthenticationProvider.
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.authorizeRequests().antMatchers("/api/v1/**").permitAll().antMatchers("/api/v1/login").authenticated().antMatchers(PUBLIC_URLS).permitAll().
////                anyRequest().authenticated().and().oauth2Login();
//
//        http.antMatcher("/api/v1/auth").authorizeRequests().
//                antMatchers("/api/v1/login").permitAll().anyRequest().authenticated().and().oauth2Login();
//
////        http.authorizeRequests().
////                antMatchers(PUBLIC_URLS).permitAll().
////                antMatchers("/api/v1/auth").permitAll();
//////                authenticated();
//
////        http.authorizeRequests()
////                .antMatchers("/api/v1/login", "/api/v1/auth").authenticated()
////                .and().oauth2Login();
//
////        http.csrf().disable().
////                authorizeRequests().antMatchers("/api/v1/login").authenticated().antMatchers(PUBLIC_URLS).permitAll().antMatchers(HttpMethod.GET).
////                permitAll().
////                anyRequest().fullyAuthenticated();
//
//    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().
                authorizeRequests().antMatchers(PUBLIC_URLS).permitAll().
                antMatchers(HttpMethod.GET).permitAll().
                anyRequest().authenticated().
                and().exceptionHandling().
                authenticationEntryPoint(this.jwtAuthenticationEntryPoint).
                and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
        DefaultSecurityFilterChain filterChain = http.build();
        return filterChain;
    }
}

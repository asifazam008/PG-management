package com.pg_management.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/api/users/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow public access to register and Swagger
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()  // Allow public access to login and register pages
                .requestMatchers("/admin/**").hasRole("ADMIN") // Admin access required for /admin paths
                .requestMatchers("/tenant/**").hasRole("TENANT") // Tenant access required for /tenant paths
                .anyRequest().authenticated() // All other requests need authentication
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .permitAll()
            .and()
            .logout()
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());  // Set the DataSource bean
        return jdbcDao; // Return JdbcDaoImpl as UserDetailsService
    }

    // DataSource bean for connecting to PostgreSQL
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pg_management"); // Ensure correct DB URL
        dataSource.setUsername("postgres");  // Your database username
        dataSource.setPassword("postgres");  // Your database password
        return dataSource;
    }
}

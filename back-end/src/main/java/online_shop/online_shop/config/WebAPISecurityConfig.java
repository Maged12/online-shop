package online_shop.online_shop.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import online_shop.online_shop.domain.Role;
import online_shop.online_shop.filter.JWTAuthFilter;
import online_shop.online_shop.util.ShopUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebAPISecurityConfig {

    private ShopUserDetailsService shopUserDetailsService;

    private JWTAuthFilter jwtAuthFilter;

    public WebAPISecurityConfig(ShopUserDetailsService cityLibraryUserDetailsService,
            JWTAuthFilter jwtAuthFilter) {
        this.shopUserDetailsService = cityLibraryUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.toString())
                                    .requestMatchers("/api/auth/admin/register").hasRole(Role.ADMIN.toString())
                                    .requestMatchers(HttpMethod.POST, "/api/products").hasRole(Role.ADMIN.toString())
                                    .requestMatchers(HttpMethod.POST, "/api/products/add")
                                    .hasRole(Role.ADMIN.toString())
                                    .requestMatchers(HttpMethod.PUT, "/api/products/update/**")
                                    .hasRole(Role.ADMIN.toString())
                                    .requestMatchers(HttpMethod.POST, "/api/categories").hasRole(Role.ADMIN.toString())
                                    .requestMatchers(HttpMethod.PUT, "/api/categories/{id}")
                                    .hasRole(Role.ADMIN.toString())
                                    .requestMatchers("/api/orders/me").hasRole(Role.CUSTOMER.toString())
                                    .requestMatchers(HttpMethod.POST, "/api/orders").hasRole(Role.CUSTOMER.toString())
                                    .requestMatchers(HttpMethod.GET, "/api/orders").hasRole(Role.ADMIN.toString())
                                    .requestMatchers("/api/carts/**").authenticated()
                                    .requestMatchers("/api/cartItems/**").authenticated()
                                    .requestMatchers("/api/orders/**").authenticated()
                                    .requestMatchers("/api/orderItems/**").authenticated()
                                    .requestMatchers("/citylibrary/api/v1/publisher/get/**").authenticated()
                                    .requestMatchers("/api/auth/**").permitAll()
                                    .requestMatchers("/api/products/**").permitAll()
                                    .requestMatchers("/api/categories/**").permitAll();
                        })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(shopUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(shopUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000" , "http://localhost:3001"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Allow credentials like cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

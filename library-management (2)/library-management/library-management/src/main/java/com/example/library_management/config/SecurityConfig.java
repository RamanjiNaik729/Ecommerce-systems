@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/**").authenticated()
            .and().httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails librarian = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("LIBRARIAN")
            .build();
        return new InMemoryUserDetailsManager(librarian);
    }
}

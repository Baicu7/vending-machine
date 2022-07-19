package vendingmachine.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vendingmachine.filter.AuthentificationFilter;
import vendingmachine.filter.AuthorizationFilter;
import vendingmachine.util.Role;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final NoOpPasswordEncoder noOpPasswordEncoder;

    private static final String PRODUCT = "/product";
    private static final String BUY = "/buy";
    private static final String DEPOSIT = "/deposit";
    private static final String RESET = "/reset";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter(authenticationManagerBean());
        authentificationFilter.setFilterProcessesUrl("/user/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers(POST, "/user/login").permitAll();

        http.authorizeHttpRequests().antMatchers(GET, PRODUCT).authenticated();
        http.authorizeHttpRequests().antMatchers(POST, PRODUCT).hasAnyAuthority(Role.SELLER.getValue());
        //Verificare introducere parametru SELLER > apartinere produs
        http.authorizeHttpRequests().antMatchers(PUT, PRODUCT).hasAnyAuthority(Role.SELLER.getValue());
        http.authorizeHttpRequests().antMatchers(DELETE, PRODUCT).hasAnyAuthority(Role.SELLER.getValue());

        http.authorizeHttpRequests().antMatchers(POST, BUY).hasAnyAuthority(Role.BUYER.getValue());
        http.authorizeHttpRequests().antMatchers(POST, DEPOSIT).hasAnyAuthority(Role.BUYER.getValue());
        http.authorizeHttpRequests().antMatchers(POST, RESET).hasAnyAuthority(Role.BUYER.getValue());

        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(authentificationFilter);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

package md.curs.config;

import md.curs.dao.UserJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MG
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth,
//                                UserDetailsService userDetailsService) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/secure/**").authenticated()
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }

    @Bean
    public UserDetailsService userDetailsService(UserJpaDao userRepo) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    md.curs.model.User user = userRepo.findByUsername(username);
                    List<SimpleGrantedAuthority> authorities = user.getPermissions().stream()
                            .map(a -> new SimpleGrantedAuthority(a.getName()))
                            .collect(Collectors.toList());
                    return new User(username, user.getPassword(), authorities);
                } catch (Exception e) {
                    throw new UsernameNotFoundException("User or password not found");
                }
            }
        };
    }

// Uncomment this encoder to activate the BCrypt password encoder
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

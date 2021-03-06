package app.agixis.com.protoTypeForClient.config;

import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.ws.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceimpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceimpl).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                // don't create session
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // handle an authorized attempts
//                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers( "/login/**","/signin/**", "/register/**","/sendemail/**","/h2/**/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/sendemail/**","/updatetask/**","/savetasks/**").hasAnyAuthority(String.valueOf(RoleEnum.ADMINISTRATOR), String.valueOf(RoleEnum.USER ))
                .antMatchers(HttpMethod.GET, "/registrationConfirm/**").hasAnyAuthority(String.valueOf(RoleEnum.ADMINISTRATOR), String.valueOf(RoleEnum.USER ))
                .antMatchers(HttpMethod.DELETE, "/deletetask/**").hasAnyAuthority(String.valueOf(RoleEnum.ADMINISTRATOR), String.valueOf(RoleEnum.USER ))
                .antMatchers(HttpMethod.PUT, "/updatetask/**").hasAnyAuthority(String.valueOf(RoleEnum.ADMINISTRATOR), String.valueOf(RoleEnum.USER ))
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterBefore(new JWTAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class);

    }
}
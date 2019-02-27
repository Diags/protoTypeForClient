package app.agixis.com.protoTypeForClient.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            try {
                // If there is no token provided and hence the user won't be authenticated.
                // It's Ok. Maybe the user accessing a public path or asking for a token.
                // All secured paths that needs a token are already defined and secured in config class.
                // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

                //  Get the token
                String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
                // validate the header and check the prefix
                if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                    filterChain.doFilter(request, response); // If not valid, go to the next filter.
                    return;
                }

                Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody();
                String username = claims.getSubject();
                ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>)
                        claims.get("roles");
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                roles.forEach(r -> {
                    authorities.add(new SimpleGrantedAuthority(r.get("authority")));
                });
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
                SecurityContextHolder.clearContext();
            }
            // go to the next filter in the filter chain
            filterChain.doFilter(request, response);
        }
    }
}

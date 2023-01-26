package com.project.banking.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.banking.model.Role;
import com.project.banking.model.User;
import com.project.banking.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Objects;

import static com.project.banking.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final String prefixRole = "ROLE_";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String userName = getUserNameFromToken(token);
            if (!Objects.isNull(userName)) {
                com.project.banking.model.User user = getUserFromDatabase(userName);

                if (user != null) {
                    List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
                    getRolesFromUser(user, roleList);
                    return new UsernamePasswordAuthenticationToken(userName, null, roleList);
                }
            }
        }
        return null;
    }

    private List<GrantedAuthority> getRolesFromUser(User user, List<GrantedAuthority> roleList) {
        //in repo get the roles for the user.
        List<Role> roles = userDetailsService.getRolesFromUser(user.getUserAccountId());
        roles.forEach(
                rol -> roleList.add(new SimpleGrantedAuthority(prefixRole + rol.getRoleName().toString().toUpperCase())));
        return roleList;
    }

    private com.project.banking.model.User getUserFromDatabase(String userName) {
        return userDetailsService.findByUserName(userName);
    }

    private String getUserNameFromToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build().verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}

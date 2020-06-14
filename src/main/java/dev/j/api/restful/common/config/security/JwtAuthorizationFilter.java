package dev.j.api.restful.common.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  @Qualifier("authenticationBlog")
  private JwtAuthenticationProvider authenticationBlog;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {


    String uri = request.getRequestURI();
    if(uri.startsWith("/auth")){
      filterChain.doFilter(request, response);
      return;
    }
    
    Authentication auth = authenticationBlog.getAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(auth);
    
    filterChain.doFilter(request, response);
  }
    
}
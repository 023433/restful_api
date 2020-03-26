package dev.j.api.restful.common.config.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public interface JwtAuthenticationProvider extends AuthenticationProvider{
    public Authentication getAuthentication(HttpServletRequest request);
}
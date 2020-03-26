package dev.j.api.restful.common.config.security;

import dev.j.api.restful.blog.service.ServiceUser;
import dev.j.api.restful.common.component.ComponentJwtToken;
import dev.j.api.restful.common.enums.EnumRole;
import dev.j.api.restful.common.property.PropertyJwtToken;
import dev.j.api.restful.common.property.PropertyLog;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "authenticationBlog")
public class AuthenticationBlog implements JwtAuthenticationProvider {

    @Autowired
    private ComponentJwtToken componentJwtToken;

    @Autowired
    private ServiceUser serviceUser;

    private Marker marker = MarkerFactory.getMarker(PropertyLog.MARKER_BLOG);

	public Authentication getAuthentication(HttpServletRequest request) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        String jwtToken = request.getHeader(PropertyJwtToken.STR_TOKEN);

        if(componentJwtToken.isExpiredToken(jwtToken)){
            String guest = EnumRole.GUEST.label();
            grantedAuthorityList.add(new SimpleGrantedAuthority(guest));
            return new UsernamePasswordAuthenticationToken(guest, null, grantedAuthorityList);
        }

        String userId = componentJwtToken.getUserId(jwtToken);
        List<String> roles = serviceUser.getUserRoles(userId);

        try {
            grantedAuthorityList = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(marker, "getAuthentication.Exception : " + userId + " / " + e.getMessage());
        }

		return new UsernamePasswordAuthenticationToken(userId, null, grantedAuthorityList);
	}

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    
}
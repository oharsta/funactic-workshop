package csof.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class UserDetailAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //we can do whatever we want here - we can use constructor dependency injection for repositories etc etc
        return "admin".equals(authentication.getPrincipal()) && "secret".equals(authentication.getCredentials()) ?
                new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null,
                        Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))) : null;
    }

}

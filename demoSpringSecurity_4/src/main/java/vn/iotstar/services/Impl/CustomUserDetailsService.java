package vn.iotstar.services.Impl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.Users;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.services.MyUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository by either username or email
        Users user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + username));

        // Return a new instance of your custom UserDetails implementation
        return new MyUserService(user);
    }

    // This method maps Roles to GrantedAuthority objects (needed by Spring Security)
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))  // Prefix 'ROLE_' is required
                    .collect(Collectors.toList());
    }
}

package vn.iotstar.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.Users;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.services.MyUserService;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Use the correct username parameter to find the user
        Users user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
        // Assuming MyUserService implements UserDetails
        return new MyUserService(user); // Ensure MyUserService exists and implements UserDetails
    }
}

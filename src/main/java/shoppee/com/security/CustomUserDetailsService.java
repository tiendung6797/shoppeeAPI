package shoppee.com.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shoppee.com.entities.Admin;
import shoppee.com.entities.Store;
import shoppee.com.repository.AdminRepository;
import shoppee.com.repository.StoreRepository;
import shoppee.com.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService  {
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Admin user = adminRepository.findByUsername(username);
                /*.orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username: " + username));*/
        if (user == null) {
        	Store user2 = storeRepository.findByUserName(username);
        	return UserPrincipal.create(user2);
        }
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) {
        Admin user = adminRepository.findById(id)/*.orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        )*/;
        
        if (user == null) {
        	Store user2 = storeRepository.findById(id);
        	return UserPrincipal.create(user2);
        }

        return UserPrincipal.create(user);
    }
}


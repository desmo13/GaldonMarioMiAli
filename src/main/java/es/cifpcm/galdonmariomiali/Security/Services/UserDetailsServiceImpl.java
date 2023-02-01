package es.cifpcm.galdonmariomiali.Security.Services;

import es.cifpcm.galdonmariomiali.dao.UserRepository;
import es.cifpcm.galdonmariomiali.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado master");
        }

        return new MyUserDetails(user);
    }

}

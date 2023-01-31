package es.cifpcm.galdonmariomiali.Security.Services;

import es.cifpcm.galdonmariomiali.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findUserByUserName(username);
    }
}




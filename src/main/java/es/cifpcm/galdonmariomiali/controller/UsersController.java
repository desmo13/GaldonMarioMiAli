package es.cifpcm.galdonmariomiali.controller;

import com.google.common.hash.Hashing;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.charset.StandardCharsets;

@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/Users")
    public String getUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return"Usuarios/users";
    }
    private String getSHA256(String data) {
        return Hashing.sha256().hashString(data, StandardCharsets.UTF_8).toString();
    }
}

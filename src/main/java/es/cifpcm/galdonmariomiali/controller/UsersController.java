package es.cifpcm.galdonmariomiali.controller;

import com.google.common.hash.Hashing;
import es.cifpcm.galdonmariomiali.dao.UserRepository;
import es.cifpcm.galdonmariomiali.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @RequestMapping("/UserEdit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);

        if(user!=null) {
            model.addAttribute("user", user);

            return "Usuarios/edit";
        }
        return "redirect:/error";
    }
    @RequestMapping("/UserUpdate")
    public String update(@RequestParam Long id, @RequestParam String userName, @RequestParam String userPass) throws IOException {
        User user = userRepository.findById(id).orElse(null);

        //Optional<es.cifpcm.galdonmariomiali.model.Municipio> municipio =municipioOfferRepository.findById(Municipio);
        if(user==null ||userName.trim().isEmpty()||userPass.isEmpty()){
            return "redirect:/error";
        }
        user.setUserId((short) Math.toIntExact(id));
        user.setUserName(userName);
        user.setPassword(getSHA256(userPass));


        return "redirect:/UserShow/" + user.getUserId();
    }
    @RequestMapping("/UserDelete")
    public String delete(@RequestParam Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null) {
            userRepository.delete(user);
           // userRepository.delete(user);




            return "redirect:/Users";

        }
        return "redirect:/error";

    }
    @GetMapping("/Usercreate")
    public String create() {

        return "Usuarios/userCreate";
    }
    @PostMapping("/UserSave")
    public String save(@RequestParam String userName, @RequestParam String userPass) {
        User user = new User();

        if(user==null ||userName.trim().isEmpty()||userPass.isEmpty()){
            return "redirect:/error";
        }

        user.setUserId((short) (userRepository.count()+1));
        user.setUserName(userName);
        user.setPassword(getSHA256(userPass));

        userRepository.save(user);

        return "redirect:/show/" + user.getUserId();
    }
    @RequestMapping("/UserShow/{id}")
    public String show(@PathVariable Long id, Model model) {
        User user= userRepository.findById(id).orElse(null);
        if (user !=null) {
            model.addAttribute("user", user);
            return "Usuarios/userShow";
        }
        return "redirect:/error";
    }
    @RequestMapping("/GetLogin")
    public String getLogin(){
        return "Usuarios/Login";
    }

    @PostMapping("/UserLogin")
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password){
        User usuario = userRepository.findUserByUserName(username);
        User contra = userRepository.findFirstByPasswordOrPasswordFalse(getSHA256(password));
        if(usuario==null|| contra==null){
            return "Usuarios/Login";
        }
        session.setAttribute("usuario",usuario.getUserId());
        return "redirect:/";
    }
    private String getSHA256(String data) {
        return Hashing.sha256().hashString(data, StandardCharsets.UTF_8).toString();
    }
}

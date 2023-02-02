package es.cifpcm.galdonmariomiali.controller;

import com.google.common.hash.Hashing;
import es.cifpcm.galdonmariomiali.dao.CustommerRepository;
import es.cifpcm.galdonmariomiali.dao.GroupRepository;
import es.cifpcm.galdonmariomiali.dao.UserRepository;
import es.cifpcm.galdonmariomiali.model.Customer;
import es.cifpcm.galdonmariomiali.model.Group;
import es.cifpcm.galdonmariomiali.model.User;
import es.cifpcm.galdonmariomiali.model.UsersGroup;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;

@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustommerRepository custommerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GroupRepository groupRepository;

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
        user.setPassword(passwordEncoder.encode(userPass));


        return "redirect:/UserShow/" + user.getUserId();
    }
    @RequestMapping("/UserDelete/{id}")
    public String delete(@RequestParam Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null) {
            try{
                userRepository.delete(user);
            }catch(Exception ex) {
                return "redirect:/error";
            }

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
        user.setPassword(passwordEncoder.encode(userPass));

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
    @RequestMapping("/GetRegister")
    public String getRegistre(){
        return "Usuarios/Register";
    }

    @PostMapping("/PostRegister")
    public String registre(Model model,@RequestParam String contra, @RequestParam String nombre, @RequestParam String apellido,@RequestParam String telefono,@RequestParam String email,@RequestParam LocalDate nacimiento){
        Customer cliente = custommerRepository.findCustomerByFirstNameAndLastName(nombre,apellido);
        if(cliente!=null){
            model.addAttribute("error","El usuario ya existe");
            return "Usuarios/Register";
        }
        if(nombre.isEmpty()||apellido.isEmpty()||telefono.length()!=9||telefono.isEmpty()||email.isEmpty()||nacimiento.toString().isEmpty()){
            model.addAttribute("error","Reviso los campos todos tienen que estar rellenados y el telefono tiene que ser de 9 digitos");
            return "Usuarios/Register";
        }
        Customer nuevoCliente = new Customer();
        nuevoCliente.setFirstName(nombre);
        nuevoCliente.setCustomerId(custommerRepository.findAll().size()+1);
        nuevoCliente.setLastName(apellido);
        nuevoCliente.setFechaDeNacimiento(nacimiento);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setEmail(email);

        User usuario = new User();
        usuario.setUserId((short) (userRepository.findAll().size()+1));
        usuario.setUserName(nombre+'.'+apellido);
        //usuario.setRoles(new Role("cliente",3));
        usuario.setPassword(passwordEncoder.encode(contra));
        userRepository.save(usuario);

        UsersGroup grupo = new UsersGroup();
        grupo.setUserName(nombre+'.'+apellido);
        grupo.setGroupId(3);
        groupRepository.save(grupo);
        return "redirect:/";
    }
    @RequestMapping("/Login")
    public String login(){
        return "redirect:/login";
    }
}

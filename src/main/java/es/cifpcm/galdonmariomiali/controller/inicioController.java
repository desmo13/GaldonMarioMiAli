package es.cifpcm.galdonmariomiali.controller;


import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class inicioController {
    @Autowired
    ProductOfferRepository productOfferRepository;

    @GetMapping("/")
    public String getRequest() {
        return "Inicio";
}

    @GetMapping("/Producto")
    public String getProducts(Model model){
        model.addAttribute("Products",productOfferRepository.findAll());
        return "Producto";
    }
}

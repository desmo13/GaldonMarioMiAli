package es.cifpcm.galdonmariomiali.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.WString;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CarritoController {
    @Autowired
    ProductOfferRepository productOfferRepository;
    private static ArrayList<Productoffer> carrito = new ArrayList<Productoffer>();

    @RequestMapping("/addCarrito/{id}")
    public String addCarrito(@PathVariable Long id, HttpSession sessionl){




            carrito.add(productOfferRepository.findByProductId(Math.toIntExact(id)));
            sessionl.setAttribute("carrito",carrito);



        return "redirect:/Producto";
    }

}

package es.cifpcm.galdonmariomiali.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.WString;
import es.cifpcm.galdonmariomiali.dao.PedidoRepository;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.model.Pedido;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import es.cifpcm.galdonmariomiali.model.Productos;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;



@Controller
public class CarritoController {
    @Autowired
    ProductOfferRepository productOfferRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    //private static ArrayList<Productoffer> carrito = new ArrayList<Productoffer>();

    @PostMapping("/addCarrito/{id}")
    public String addCarrito(@PathVariable Long id, HttpSession sessionl){
            ArrayList<Productoffer> carrito = new ArrayList<Productoffer>();
            ArrayList<Integer> cantidad= new ArrayList<>();
            if(sessionl.getAttribute("carrito")!=null){
                carrito= (ArrayList<Productoffer>) sessionl.getAttribute("carrito");

            }
        if (sessionl.getAttribute("cantidad") != null) {
            cantidad= (ArrayList<Integer>) sessionl.getAttribute("cantidad");
        }

            Productoffer producto=productOfferRepository.findByProductId(Math.toIntExact(id));
            carrito.add(producto);
           //nuevoPedido.setIdUsuario();
            if(carrito.size()>0){
                int rep=0;
                for(int i=0;i<carrito.size();i++){
                    if(carrito.get(i).getProductId().equals(id)){
                        rep++;
                    }
                    if(rep>1){
                        cantidad.add(i,rep-1);
                        carrito.remove(i);
                    }
                }
            }


            sessionl.setAttribute("carrito",carrito);
            sessionl.setAttribute("cantida",cantidad);


        return "redirect:/Producto";
    }
    @RequestMapping("/deleteCarrito/{id}")
    public String deleteCarrito(@PathVariable Long id, HttpSession sessionl){
        ArrayList<Productoffer> carrito = new ArrayList<Productoffer>();
        ArrayList<Integer> cantidad= new ArrayList<>();
        if(sessionl.getAttribute("carrito")!=null){
            carrito= (ArrayList<Productoffer>) sessionl.getAttribute("carrito");

        }
        if (sessionl.getAttribute("cantidad") != null) {
            cantidad= (ArrayList<Integer>) sessionl.getAttribute("cantidad");
        }
        Productoffer producto = productOfferRepository.findByProductId(Math.toIntExact((id)));
        for (int i=0;i<carrito.size();i++) {

            if(carrito.get(i).getProductId()==producto.getProductId()){
                if(cantidad.get(i)>1){
                    int can= cantidad.get(i);
                    cantidad.add(i,can-1);
                }else{
                    carrito.remove(i);
                }

                break;
            }
        }

        return "redirect:/Producto";
    }
}

package es.cifpcm.galdonmariomiali.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jna.WString;
import es.cifpcm.galdonmariomiali.dao.PedidoRepository;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.model.Pedido;
import es.cifpcm.galdonmariomiali.model.Producto;
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

    @RequestMapping("/addCarrito/{id}")
    public String addCarrito(@PathVariable Integer id, HttpSession sessionl){
            ArrayList<Producto> carrito = new ArrayList<Producto>();

            if(sessionl.getAttribute("carrito")!=null){
                carrito= (ArrayList<Producto>) sessionl.getAttribute("carrito");

            }


            Productoffer producto=productOfferRepository.findByProductId(Math.toIntExact(id));
            float total =0;
            if(sessionl.getAttribute("total")!=null){
                total= (float) sessionl.getAttribute("total");
            }

           //nuevoPedido.setIdUsuario();
            if(carrito.size()>0){
                Boolean encontrado=false;
                for (Producto p:carrito
                     ) {
                    if(p.getProductId().equals(id)){
                        p.setCantida(p.getCantida()+1);
                        encontrado=true;
                        total+=p.getProductPrice();
                    }
                }
                if(!encontrado){
                    Producto newProduct = new Producto(producto.getProductId(), producto.getProductName(), producto.getProductPrice(), producto.getProductPicture(), producto.getIdMunicipio(), producto.getProductStock(), 1);
                    carrito.add(newProduct);
                    total+=newProduct.getProductPrice();
                }
            }else{
                Producto newProduct = new Producto(producto.getProductId(), producto.getProductName(), producto.getProductPrice(), producto.getProductPicture(), producto.getIdMunicipio(), producto.getProductStock(), 1);
                 carrito.add(newProduct);
                total+=newProduct.getProductPrice();
            }


            sessionl.setAttribute("carrito",carrito);
            sessionl.setAttribute("total",total);



        return "redirect:/Producto";
    }
    @RequestMapping("/deleteCarrito/{id}")
    public String deleteCarrito(@PathVariable Integer id, HttpSession sessionl){
        ArrayList<Producto> carrito = new ArrayList<Producto>();
        if(sessionl.getAttribute("carrito")!=null){
            carrito= (ArrayList<Producto>) sessionl.getAttribute("carrito");

        }
        float total =0;
        if(sessionl.getAttribute("total")!=null){
            total= (float) sessionl.getAttribute("total");
        }
        Productoffer producto = productOfferRepository.findByProductId(Math.toIntExact((id)));
        if(carrito.size()>0){

            for (int i=0;i<carrito.size();i++) {
                if(carrito.get(i).getProductId().equals(id)){

                    carrito.get(i).setCantida(carrito.get(i).getCantida()-1);
                    total-=carrito.get(i).getProductPrice();
                    if(carrito.get(i).getCantida()<1){
                        //total-=carrito.get(i).getProductPrice();
                        carrito.remove(i);

                    }
                }
            }

        }else{
            total-=carrito.get(0).getProductPrice();
            carrito.remove(0);
        }
        sessionl.setAttribute("total",total);
        return "redirect:/Producto";
    }
}

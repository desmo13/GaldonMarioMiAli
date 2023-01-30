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
    private static ArrayList<Productos> carrito = new ArrayList<Productos>();

    @RequestMapping("/addCarrito/{id}")
    public String addCarrito(@PathVariable Long id, HttpSession sessionl){


            Productoffer producto=productOfferRepository.findByProductId(Math.toIntExact(id));
            Productos prodCarrito =new Productos();
            prodCarrito.setProductId(producto.getProductId());
            prodCarrito.setProductName(producto.getProductName());
            prodCarrito.setIdUsuario((Integer) sessionl.getAttribute("usuario"));
            prodCarrito.setIdMunicipio(prodCarrito.getIdMunicipio());

           // Pedido nuevoPedido = new Pedido();
           // nuevoPedido.setIdProductos(producto.getProductId().toString());
          //  nuevoPedido.setIdPedido(pedidoRepository.findAll().size());
          // nuevoPedido.setCantidadDeProductos("1");
           // nuevoPedido.setPagado(false);
           //nuevoPedido.setIdUsuario();


            carrito.add(producto);
            sessionl.setAttribute("carrito",carrito);



        return "redirect:/Producto";
    }
    @RequestMapping("/deleteCarrito/{id}")
    public String deleteCarrito(@PathVariable Long id, HttpSession sessionl){
        Productoffer producto = productOfferRepository.findByProductId(Math.toIntExact((id)));
        for (int i=0;i<carrito.size();i++) {

            if(carrito.get(i).getProductId()==producto.getProductId()){
                carrito.remove(i);
                break;
            }
        }

        return "redirect:/Producto";
    }
}

package es.cifpcm.galdonmariomiali.controller;


import es.cifpcm.galdonmariomiali.dao.PedidoRepository;
import es.cifpcm.galdonmariomiali.model.Pedido;
import es.cifpcm.galdonmariomiali.model.Producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class PerfilController {
    @Autowired
    PedidoRepository pedidoRepository;
    @RequestMapping("/Perfil")
    public String getPerfil(Model model){
        model.addAttribute("pedidos",pedidoRepository.findByIdUsuario(NombreUsusario()));
        return "Perfil/perfil";
    }
@RequestMapping("/Comprar")
    public String Comprar(HttpSession session){
        session.getAttribute("carrito");
        if(session.getAttribute("carrito")==null){
            return "redirect:/errorPage";
        }
        Pedido nuevoPedido = new Pedido();
        ArrayList<Producto> listaProductos= (ArrayList<Producto>) session.getAttribute("carrito");
        if(listaProductos.isEmpty()){
            return "redirect:/errorPage";
        }
        String nombresProductos="";
        String cantidadProductos="";
        for (Producto producto:listaProductos
             ) {

            nombresProductos+=producto.getProductName()+",";
            cantidadProductos+=producto.getCantida().toString()+",";
        }
        nuevoPedido.setIdPedido(pedidoRepository.findAll().size()+1);
        nuevoPedido.setFecha(LocalDate.now());

        nuevoPedido.setIdUsuario(NombreUsusario());
        nuevoPedido.setPagado(true);
        nuevoPedido.setIdProductos(nombresProductos);
        nuevoPedido.setCantidadDeProductos(cantidadProductos);
        ArrayList<Pedido> listaPedidos= (ArrayList<Pedido>) session.getAttribute("pedido");
        if (listaPedidos==null){
            listaPedidos=new ArrayList<>();
        }

        listaPedidos.add(nuevoPedido);
        session.setAttribute("pedidos",listaPedidos);
        pedidoRepository.save(nuevoPedido);
        session.setAttribute("carrito",null);
         return "redirect:/Producto";
    }
    private String NombreUsusario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        if(principal instanceof UserDetails){
            userDetails= (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        return userName;
    }
}

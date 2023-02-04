package es.cifpcm.galdonmariomiali.controller;

import es.cifpcm.galdonmariomiali.dao.PedidoRepository;
import es.cifpcm.galdonmariomiali.model.Pedido;
import es.cifpcm.galdonmariomiali.model.Producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class PerfilController {
    @Autowired
    PedidoRepository pedidoRepository;
    @RequestMapping("/Perfil")
    public String getPerfil(){
        return "Perfil/perfil";
    }

    public String Comprar(HttpSession session){
        session.getAttribute("carrito");
        if(session.getAttribute("carrito")==null){
            return "redirect:/error";
        }
        Pedido nuevoPedido = new Pedido();
        ArrayList<Producto> listaProductos= (ArrayList<Producto>) session.getAttribute("carrito");
        if(listaProductos.isEmpty()){
            return "redirect:/error";
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
        nuevoPedido.setPagado(true);
        nuevoPedido.setIdProductos(nombresProductos);
        nuevoPedido.setCantidadDeProductos(cantidadProductos);
        if (session.getAttribute("pedido")==null){
            ArrayList<Pedido> listaPedidos=new ArrayList<>();
        }
        ArrayList<Pedido> listaPedidos= (ArrayList<Pedido>) session.getAttribute("pedido");
        listaPedidos.add(nuevoPedido);
        session.setAttribute("pedidos",listaPedidos);
         return "redirect:/Producto";
    }

}

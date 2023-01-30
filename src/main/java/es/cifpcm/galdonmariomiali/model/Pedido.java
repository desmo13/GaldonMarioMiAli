package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @Column(name = "idPedido", nullable = false)
    private Integer idPedido;

    @Lob
    @Column(name = "idProductos")
    private String idProductos;

    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Lob
    @Column(name = "cantidadDeProductos")
    private String cantidadDeProductos;

    @Column(name = "pagado")
    private Boolean pagado;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(String idProductos) {
        this.idProductos = idProductos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCantidadDeProductos() {
        return cantidadDeProductos;
    }

    public void setCantidadDeProductos(String cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

}
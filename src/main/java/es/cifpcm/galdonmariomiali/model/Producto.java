package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.Column;

public class Producto {
    private Integer productId;
    private String productName;
    private Float productPrice;
    private String productPicture;

    public void setCantida(Integer cantida) {
        this.cantida = cantida;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public Integer getCantida() {
        return cantida;
    }

    private Integer idMunicipio;

    private Integer productStock;

    public Producto(Integer productId, String productName, Float productPrice, String productPicture, Integer idMunicipio, Integer productStock, Integer cantida) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productPicture = productPicture;
        this.idMunicipio = idMunicipio;
        this.productStock = productStock;
        this.cantida = cantida;
    }

    private Integer cantida;
}

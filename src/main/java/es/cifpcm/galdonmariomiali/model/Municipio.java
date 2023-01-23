package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipio {
    @Id
    @Column(name = "id_municipio", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer idMunicipio;

    @Column(name = "id_provincia", nullable = false)
    private Short idProvincia;

    @Column(name = "cod_municipio", nullable = false)
    private Integer codMunicipio;

    @Column(name = "DC", nullable = false)
    private Integer dc;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Short getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Short idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(Integer codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public Integer getDc() {
        return dc;
    }

    public void setDc(Integer dc) {
        this.dc = dc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
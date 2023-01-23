package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "provincias")
public class Provincia {
    @Id
    @Column(name = "id_provincia", nullable = false)
    private Short idProvincia;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    public Short getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Short idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
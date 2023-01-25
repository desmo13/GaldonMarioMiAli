package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.ArrayList;

public interface ProductOfferRepository extends JpaRepository<Productoffer, Long> {

    //Integer findMunibyID(final int Id);
    ArrayList findAllByIdMunicipio(int idMunicipio);
}

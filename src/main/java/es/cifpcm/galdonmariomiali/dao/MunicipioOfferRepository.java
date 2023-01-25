package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface MunicipioOfferRepository extends JpaRepository<Municipio, Long> {
    //Municipio findById(int municipio);
    List findAllByIdProvincia(int municipio);

}

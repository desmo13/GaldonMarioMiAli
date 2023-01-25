package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Municipio;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipioOfferRepository extends JpaRepository<Municipio, Long> {
    //Municipio findById(int municipio);
    Municipio findMunicipioByIdProvincia(Integer idMunicipio);
}

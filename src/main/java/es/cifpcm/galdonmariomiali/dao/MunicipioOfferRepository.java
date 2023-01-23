package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Municipio;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioOfferRepository extends JpaRepository<Municipio, Long> {
}

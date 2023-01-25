package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Productoffer;
import es.cifpcm.galdonmariomiali.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
}

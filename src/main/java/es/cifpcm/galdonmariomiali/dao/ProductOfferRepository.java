package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOfferRepository extends JpaRepository<Productoffer, Long> {

    //Integer findMunibyID(final int Id);
    Productoffer findProductoffersByIdMunicipio(Integer idMunicipio);
}

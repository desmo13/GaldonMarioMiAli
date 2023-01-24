package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Productoffer;
import es.cifpcm.galdonmariomiali.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

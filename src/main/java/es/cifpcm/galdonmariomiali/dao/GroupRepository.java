package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Customer;
import es.cifpcm.galdonmariomiali.model.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository  extends JpaRepository<UsersGroup, Long> {
}

package es.cifpcm.galdonmariomiali.dao;

import es.cifpcm.galdonmariomiali.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustommerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByFirstNameAndLastName(String Nombre,String Apellido);
}

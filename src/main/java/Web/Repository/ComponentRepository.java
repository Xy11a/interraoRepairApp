package Web.Repository;

import Web.Entity.ElectroComponents.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, Integer> {

}

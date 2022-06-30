package ru.interrao.itrepair.Web.Repository;

import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;
import org.springframework.data.repository.CrudRepository;

public interface ComponentRepository extends CrudRepository<Component, Integer> {

}

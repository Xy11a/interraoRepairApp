package ru.interrao.itrepair.Web.Repository;


import org.springframework.data.repository.CrudRepository;
import ru.interrao.itrepair.Web.Entity.CompositeEntity.UsedComponents;
import ru.interrao.itrepair.Web.Entity.PK.UsedComponentsPK;


public interface UsedComponentRepository extends CrudRepository<UsedComponents, UsedComponentsPK>
{

}

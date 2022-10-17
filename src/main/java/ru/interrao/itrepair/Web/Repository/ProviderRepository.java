package ru.interrao.itrepair.Web.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.interrao.itrepair.Web.Entity.Provider.Provider;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {
}

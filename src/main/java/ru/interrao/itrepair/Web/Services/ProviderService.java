package ru.interrao.itrepair.Web.Services;

import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;
import ru.interrao.itrepair.Web.Entity.Provider.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService
{
    Optional<Provider> save(Provider provider);
    List<Provider> saveAll(List<Provider> providers);

    Optional<Provider> get(int id);
    List<Provider> getAll();

    Optional<Provider> update(Provider provider);

    boolean deleteById(int id);
    boolean deleteAll();
}

package ru.interrao.itrepair.Web.Services;

import ru.interrao.itrepair.Web.Entity.ElectroComponents.Component;

import java.util.List;
import java.util.Optional;

public interface ComponentService {
    Optional<Component> save(Component component);
    List<Component> saveAll(List<Component> components);

    Optional<Component> get(int id);
    List<Component> getAll();

    Optional<Component> update(Component component);

    boolean deleteById(int id);
    boolean deleteAll();

}

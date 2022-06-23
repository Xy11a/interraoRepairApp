package Web.Services.Impl;

import Web.Entity.ElectroComponents.Component;
import Web.Repository.ComponentRepository;
import Web.Services.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComponentServiceImpl implements ComponentService {
    @Autowired
    private ComponentRepository repository;

    @Override
    public Optional<Component> save(Component component) {
        return Optional.of(repository.save(component));
    }

    @Override
    public List<Component> saveAll(List<Component> components) {
        repository.saveAll(components);
        return components;
    }

    @Override
    public Optional<Component> get(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Component> getAll() {
        List<Component> list = new ArrayList<>();
        for (Component component : repository.findAll()) list.add(component);
        return list;
    }

    @Override
    public Optional<Component> update(Component component) {
        return Optional.of(repository.save(component));
    }

    @Override
    public boolean deleteById(int id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
        }
        return exist;
    }

    @Override
    public boolean deleteAll() {
        repository.deleteAll();
        return repository.count() == 0;
    }
}

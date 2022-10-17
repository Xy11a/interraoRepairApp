package ru.interrao.itrepair.Web.Services.ServiceImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.interrao.itrepair.Web.Entity.Provider.Provider;
import ru.interrao.itrepair.Web.Repository.ProviderRepository;
import ru.interrao.itrepair.Web.Services.ProviderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository repository;

    @Override
    public Optional<Provider> save(Provider provider) {
        return Optional.of(repository.save(provider));
    }

    @Override
    public List<Provider> saveAll(List<Provider> providers) {
        repository.saveAll(providers);
        return providers;
    }

    @Override
    public Optional<Provider> get(int id) {
        return Optional.of(repository.findById(id)).get();
    }

    @Override
    public List<Provider> getAll() {
        List<Provider> list = new ArrayList<>();
        for(Provider p : repository.findAll()) list.add(p);
        return list;
    }

    @Override
    public Optional<Provider> update(Provider provider) {
        return Optional.of(repository.save(provider));
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

package ru.interrao.itrepair.Web.Services.ServiceImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.interrao.itrepair.Web.Entity.CompositeEntity.UsedComponents;
import ru.interrao.itrepair.Web.Entity.PK.UsedComponentsPK;
import ru.interrao.itrepair.Web.Repository.UsedComponentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsedComponentServiceImpl
{
    @Autowired
    UsedComponentRepository repository;


    public Optional<UsedComponents> save(UsedComponents obj) {
        return Optional.of(repository.save(obj));
    }


    public List<UsedComponents> saveAll(List<UsedComponents> listOfObj) {
        repository.saveAll(listOfObj);
        return listOfObj;
    }


    public Optional<UsedComponents> get(int reportId, int componentId) {
        UsedComponentsPK pk = new UsedComponentsPK();
        pk.setReportId(reportId);
        pk.setComponentId(componentId);
        return repository.findById(pk);
    }


    public List<UsedComponents> getAll() {
        List<UsedComponents> list = new ArrayList<>();
        for(UsedComponents components : repository.findAll()) list.add(components);
        return list;
    }


    public Optional<UsedComponents> update(UsedComponents obj) {
        return Optional.of(repository.save(obj));
    }


    public boolean deleteById(int reportId,int componentId) {
        UsedComponentsPK pk = new UsedComponentsPK();
        pk.setReportId(reportId);
        pk.setComponentId(componentId);
        boolean exist = repository.existsById(pk);
        if (exist) {
            repository.deleteById(pk);
        }
        return exist;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return repository.count() == 0;
    }

    public List<UsedComponents> getAllByReportId(Integer reportId)
    {
        List<UsedComponents> list = new ArrayList<>((Collection) repository.findAll());
        return  list.stream().filter(usedComponents -> usedComponents.getKey().getReportId() == reportId).collect(Collectors.toList());
    }
}

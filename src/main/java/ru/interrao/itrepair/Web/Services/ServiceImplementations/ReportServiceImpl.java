package ru.interrao.itrepair.Web.Services.ServiceImplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.interrao.itrepair.Web.Entity.Reports.Report;
import ru.interrao.itrepair.Web.Repository.ReportRepository;
import ru.interrao.itrepair.Web.Services.ReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository repository;

    @Override
    public Optional<Report> save(Report report) {
        return Optional.of(repository.save(report));
    }

    @Override
    public List<Report> saveAll(List<Report> reports) {
        repository.saveAll(reports);
        return reports;
    }

    @Override
    public Optional<Report> get(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Report> getAll() {
        List<Report> reports = new ArrayList<>();
        for(Report report : repository.findAll()) reports.add(report);
        return reports;
    }

    @Override
    public Optional<Report> update(Report report) {
        return Optional.of(repository.save(report));
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

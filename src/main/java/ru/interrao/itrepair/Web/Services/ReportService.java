package ru.interrao.itrepair.Web.Services;

import ru.interrao.itrepair.Web.Entity.Reports.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Optional<Report> save(Report report);

    List<Report> saveAll(List<Report> report);

    Optional<Report> get(int id);

    List<Report> getAll();

    Optional<Report> update(Report report);

    boolean deleteById(int id);

    boolean deleteAll();
}

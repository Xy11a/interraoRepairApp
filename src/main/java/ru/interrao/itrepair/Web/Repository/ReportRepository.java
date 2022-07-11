package ru.interrao.itrepair.Web.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.interrao.itrepair.Web.Entity.Reports.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {
}

package ru.interrao.itrepair.Web.Services;

import ru.interrao.itrepair.Web.Entity.Reports.Report;

import java.util.List;
import java.util.Optional;

public interface ServiceBD<T> {
    Optional<T> save(T obj);

    List<T> saveAll(List<T> listOfObj);

    Optional<T> get(int id);

    List<T> getAll();

    Optional<T> update(T obj);

    boolean deleteById(int id);

    boolean deleteAll();
}

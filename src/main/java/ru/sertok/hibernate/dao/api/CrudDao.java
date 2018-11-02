package ru.sertok.hibernate.dao.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    void save(T model);
    Optional<T> find(Integer id);
    void update(T model);
    void delete(Integer id);
    List<T> findAll();
}

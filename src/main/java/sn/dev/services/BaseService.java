package sn.dev.services;

import java.util.List;
import java.util.Optional;

public interface BaseService <T> {
    Optional<T> findById(Integer id);
    List<T> findAll();
    T save(T entity);
    void delete(Integer id);
}

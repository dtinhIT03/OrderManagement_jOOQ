package org.example.ordermanagement_jooq.repository;

public interface JOOQRepository<T> {
    T save(T pojo);
}

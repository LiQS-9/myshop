package service;

import entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    int addType(Type type);

    Type findById(int ntid);

    int update(Type type);

    boolean delete(int ntid);

    List<Type> findByName(String tname);
}

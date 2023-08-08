package com.medicalcenter.roleservice.mapper;

import java.lang.reflect.InvocationTargetException;

public interface AbstractMapper<E,D> {
    D entityToDto(E entity);
    E dtoToEntity(D dto);
}

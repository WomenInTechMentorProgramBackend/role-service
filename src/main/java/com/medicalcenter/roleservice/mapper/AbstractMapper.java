package com.medicalcenter.roleservice.mapper;


public interface AbstractMapper<E,D> {
    D entityToDto(E entity);
    E dtoToEntity(D dto);
}

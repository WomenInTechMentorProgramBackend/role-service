package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.Permission;
import io.tej.SwaggerCodgen.model.PermissionDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class PermissionMapper implements AbstractMapper<Permission, PermissionDto> {

    @Override
    public PermissionDto entityToDto(Permission entity) {
        return new PermissionDto()
                .id(entity.getId().toString())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(OffsetDateTime.from(entity.getCreatedAt()))
                .createdBy(entity.getCreatedBy())
                .updatedAt(OffsetDateTime.from(entity.getUpdatedAt()))
                .updatedBy(entity.getUpdatedBy())
                .isActive(entity.isActive());
    }

    @Override
    public Permission dtoToEntity(PermissionDto dto) {
        return Permission.builder()
                .id(UUID.fromString(dto.getId()))
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt().toLocalDateTime())
                .createdBy(dto.getCreatedBy())
                .updatedAt(dto.getUpdatedAt().toLocalDateTime())
                .updatedBy(dto.getUpdatedBy())
                .isActive(dto.getIsActive())
                .build();
    }
}

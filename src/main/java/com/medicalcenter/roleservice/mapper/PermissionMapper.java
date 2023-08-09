package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.Permission;
import io.tej.SwaggerCodgen.model.PermissionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class PermissionMapper implements AbstractMapper<Permission, PermissionDto> {
    @Value("${offset_id}")
    private String offsetID;

    @Override
    public PermissionDto entityToDto(Permission entity) {
        return new PermissionDto()
                .id(entity.getId().toString())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(!isNull(entity.getCreatedAt()) ? entity.getCreatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .createdBy(entity.getCreatedBy())
                .updatedAt(!isNull(entity.getUpdatedAt()) ? entity.getUpdatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .updatedBy(entity.getUpdatedBy())
                .isActive(entity.isActive());
    }

    @Override
    public Permission dtoToEntity(PermissionDto dto) {
        return Permission.builder()
                .id(!isNull(dto.getId()) ? UUID.fromString(dto.getId()) : null)
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(!isNull(dto.getCreatedAt()) ? dto.getCreatedAt().toLocalDateTime() : null)
                .createdBy(dto.getCreatedBy())
                .updatedAt(!isNull(dto.getUpdatedAt()) ? dto.getUpdatedAt().toLocalDateTime() : null)
                .updatedBy(dto.getUpdatedBy())
                .isActive(dto.getIsActive())
                .build();
    }
}

package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.Role;
import io.tej.SwaggerCodgen.model.RoleDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:properties.properties")
public class RoleMapper implements AbstractMapper<Role, RoleDto>{

    private final PermissionMapper mapper;

    @Value("${offset_id}")
    private String offsetID;
    @Override
    public RoleDto entityToDto(Role entity){
        return new RoleDto()
                .id(entity.getId().toString())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(!isNull(entity.getCreatedAt()) ? entity.getCreatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .createdBy(entity.getCreatedBy())
                .updatedAt(!isNull(entity.getUpdatedAt()) ? entity.getUpdatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .updatedBy(entity.getUpdatedBy())
                .isActive(entity.getIsActive())
                .permissions(Stream.ofNullable(entity.getPermissions())
                        .flatMap(Collection::stream)
                        .map(mapper::entityToDto)
                        .toList());
    }

    @Override
    public Role dtoToEntity(RoleDto dto) {
        return Role.builder()
                .id(!isNull(dto.getId()) ? UUID.fromString(dto.getId()) : null)
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(!isNull(dto.getCreatedAt()) ? dto.getCreatedAt().toLocalDateTime() : null)
                .createdBy(dto.getCreatedBy())
                .updatedAt(!isNull(dto.getUpdatedAt()) ? dto.getUpdatedAt().toLocalDateTime() : null)
                .updatedBy(dto.getUpdatedBy())
                .isActive(dto.getIsActive())
                .permissions(Stream.ofNullable(dto.getPermissions())
                        .flatMap(Collection::stream)
                        .map(mapper::dtoToEntity)
                        .toList())
                .build();
    }
}

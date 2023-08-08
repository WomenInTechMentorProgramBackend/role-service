package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.Permission;
import com.medicalcenter.roleservice.entity.Role;
import io.tej.SwaggerCodgen.model.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleMapper implements AbstractMapper<Role, RoleDto>{

    private final PermissionMapper mapper;
    @Override
    public RoleDto entityToDto(Role entity){
        return new RoleDto()
                .id(entity.getId().toString())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(OffsetDateTime.from(entity.getCreatedAt()))
                .createdBy(entity.getCreatedBy())
                .updatedAt(OffsetDateTime.from(entity.getUpdatedAt()))
                .updatedBy(entity.getUpdatedBy())
                .isActive(entity.getIsActive())
                .permissions(entity.getPermissions().stream()
                        .map(mapper::entityToDto)
                        .toList());
    }

    @Override
    public Role dtoToEntity(RoleDto dto) {
        return Role.builder()
                .id(UUID.fromString(dto.getId()))
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt().toLocalDateTime())
                .createdBy(dto.getCreatedBy())
                .updatedAt(dto.getUpdatedAt().toLocalDateTime())
                .updatedBy(dto.getUpdatedBy())
                .isActive(dto.getIsActive())
                .permissions(dto.getPermissions().stream()
                        .map(mapper::dtoToEntity)
                        .toList())
                .build();
    }
}

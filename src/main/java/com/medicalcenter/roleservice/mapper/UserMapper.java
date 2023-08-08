package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.User;
import io.tej.SwaggerCodgen.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper implements AbstractMapper<User, UserDto>{
    private final RoleMapper mapper;
    @Override
    public UserDto entityToDto(User entity) {
        return new UserDto()
                .id(entity.getId().toString())
                .externalId(entity.getExternalId().toString())
                .createdAt(OffsetDateTime.from(entity.getCreatedAt()))
                .createdBy(entity.getCreatedBy())
                .updatedAt(OffsetDateTime.from(entity.getUpdatedAt()))
                .updatedBy(entity.getUpdatedBy())
                .roles(entity.getRoles().stream()
                        .map(mapper::entityToDto)
                        .toList());
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        return User.builder()
                .id(UUID.fromString(dto.getId()))
                .externalId(UUID.fromString(dto.getExternalId()))
                .createdAt(dto.getCreatedAt().toLocalDateTime())
                .createdBy(dto.getCreatedBy())
                .updatedAt(dto.getUpdatedAt().toLocalDateTime())
                .updatedBy(dto.getUpdatedBy())
                .roles(dto.getRoles().stream()
                        .map(mapper::dtoToEntity)
                        .toList())
                .build();
    }
}

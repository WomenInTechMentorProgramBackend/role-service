package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.User;
import io.tej.SwaggerCodgen.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserMapper implements AbstractMapper<User, UserDto>{
    @Value("${offset_id}")
    private String offsetID;

    private final RoleMapper mapper;
    @Override
    public UserDto entityToDto(User entity) {
        return new UserDto()
                .id(entity.getId().toString())
                .externalId(entity.getExternalId().toString())
                .createdAt(!isNull(entity.getCreatedAt()) ? entity.getCreatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .createdBy(entity.getCreatedBy())
                .updatedAt(!isNull(entity.getUpdatedAt()) ? entity.getUpdatedAt().atOffset(ZoneOffset.of(offsetID)) : null)
                .updatedBy(entity.getUpdatedBy())
                .roles(Stream.ofNullable(entity.getRoles())
                        .flatMap(Collection::stream)
                        .map(mapper::entityToDto)
                        .toList());
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        return User.builder()
                .id(!isNull(dto.getId()) ? UUID.fromString(dto.getId()) : null)
                .externalId(UUID.fromString(dto.getExternalId()))
                .createdAt(!isNull(dto.getCreatedAt()) ? dto.getCreatedAt().toLocalDateTime() : null)
                .createdBy(dto.getCreatedBy())
                .createdAt(!isNull(dto.getUpdatedAt()) ? dto.getUpdatedAt().toLocalDateTime() : null)
                .updatedBy(dto.getUpdatedBy())
                .roles(Stream.ofNullable(dto.getRoles())
                        .flatMap(Collection::stream)
                        .map(mapper::dtoToEntity)
                        .toList())
                .build();
    }
}

package com.medicalcenter.roleservice.mapper;

import com.medicalcenter.roleservice.entity.Permission;
import io.tej.SwaggerCodgen.model.PermissionDto;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static java.util.Objects.nonNull;

@Mapper(componentModel = "spring")
public interface PermissionDtoPermissionMapper {
    String OFFSET_ID = "+00:00";
    Permission permissionDtoToPermission(PermissionDto permissionDto);
    PermissionDto permissionToPermissionDto(Permission permission);
    default LocalDateTime map(OffsetDateTime value){
        return nonNull(value) ? value.toLocalDateTime() : null;
    }
    default OffsetDateTime map(LocalDateTime value){
        return nonNull(value) ? value.atOffset(ZoneOffset.of(OFFSET_ID)) : null;
    }
}

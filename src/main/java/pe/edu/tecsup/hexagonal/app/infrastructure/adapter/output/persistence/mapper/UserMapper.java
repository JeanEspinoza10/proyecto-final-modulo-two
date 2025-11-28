package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pe.edu.tecsup.hexagonal.app.domain.model.User;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.UserRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.UserResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(UserEntity entity);

    // Request to Domain (for new users)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    User toDomain(UserRequest request);

    // Domain to Entity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(User domain);

    // Domain to Response
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserResponse toResponse(User domain);



    // List mappings
    List<User> toDomain(List<UserEntity> entities);

    List<UserResponse> toResponse(List<User> users);

}

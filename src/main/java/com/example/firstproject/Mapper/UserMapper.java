package com.example.firstproject.Mapper;

import com.example.firstproject.entities.UserEntity;
import com.example.firstproject.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //MA ELN 3EZI SINCE SAME NAME bl entity wl dto
//    @Mapping(target = "id", source = "userEntity.id")
//    @Mapping(target = "name", source = "userEntity.name")
//    @Mapping(target = "age", source = "userEntity.age")
//    @Mapping(target = "departmentID", source = "userEntity.department")
    UserDTO userEntityToUserDTO(UserEntity userEntity);//
    @Mapping(target = "id", ignore = true)
    UserEntity userDTOToUserEntity(UserDTO userDTO);

}

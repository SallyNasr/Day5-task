package com.example.firstproject.Repositories;

import com.example.firstproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // custom query methods

    @Query(value = "select * from user where user_name=?1",nativeQuery = true)
    List<UserEntity> findByName(String name);
    @Query(value = "select * from user where user_age>?1",nativeQuery = true)

    List<UserEntity> findByAgeGreaterThan(int age);
    @Query(value = "SELECT * FROM user WHERE user_age BETWEEN ?1 AND ?2", nativeQuery = true)
    List<UserEntity> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT * FROM user WHERE user_name = ?1 AND department = ?2", nativeQuery = true)
    List<UserEntity> findByNameAndDepartment(String name, String department);

    @Query(value = "SELECT * FROM user WHERE user_age < ?1 AND department = ?2", nativeQuery = true)
    List<UserEntity> findByAgeLessThanAndDepartment(int age, String department);

}

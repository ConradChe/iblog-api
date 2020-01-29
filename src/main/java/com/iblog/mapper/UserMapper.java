package com.iblog.mapper;

import com.iblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    List<User> queryUser(@Param("phone") String phone);
}

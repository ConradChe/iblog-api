package com.iblog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.iblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    int addUser(User user);

    List<User> queryUser(User user);

    User queryUserNameById(Long userId);
}

package com.iblog.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.iblog.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    UserToken queryUserToken(long userId);

    boolean insertUserToken(UserToken userToken);

    boolean updateUserToken(UserToken userToken);
}

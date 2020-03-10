package com.iblog.service;

import com.baomidou.mybatisplus.service.IService;
import com.iblog.entity.UserToken;

public interface UserTokenService extends IService<UserToken> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    String createToken(long userId);
}

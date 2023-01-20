package com.issja.service.impl;

import com.issja.entity.LoginUser;
import com.issja.mapper.LoginUserMapper;
import com.issja.service.ILoginUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录网站的用户 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-20
 */
@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements ILoginUserService {

}

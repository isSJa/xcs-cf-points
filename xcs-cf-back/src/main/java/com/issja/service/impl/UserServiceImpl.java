package com.issja.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.User;
import com.issja.mapper.UserMapper;
import com.issja.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<Map<String,Object>> getUserPage(int currentPage,int pageSize,String searchValue) {
        Page<Map<String,Object>> page=new Page<>(currentPage,pageSize);
        return page.setRecords(userMapper.getUserPage(page,searchValue));
    }
}

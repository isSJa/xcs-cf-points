package com.issja.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface IUserService extends IService<User> {
    /**
     * 获取所有用户信息，包括分数
     * @return
     */
    public List<User> getAllUsers();

    /**
     * 模糊查询
     * @return
     */
    public List<User> getSearch(String searchValue);

    /**
     * 分页+模糊查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page<Map<String,Object>> getUserPage(int currentPage, int pageSize,String searchValue);
}

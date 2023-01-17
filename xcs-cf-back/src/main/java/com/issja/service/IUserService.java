package com.issja.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.UserContestDetail;
import com.issja.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.issja.entity.AllUserContestsDetail;

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
     * 分页+模糊查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Page<Map<String,Object>> getUserPage(int currentPage, int pageSize,String searchValue);

    /**
     * 获取某一个用户的所有比萨数据
     * @param id
     * @return
     */
    public List<UserContestDetail> getUserScores(int id);

    /**
     * 获取所有用户的所有比赛信息
     * @return
     */
    public List<AllUserContestsDetail> getAllUserScores();
}

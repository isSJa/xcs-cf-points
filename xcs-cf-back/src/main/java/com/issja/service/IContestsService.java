package com.issja.service;

import com.issja.entity.Contests;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * cf赛 服务类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface IContestsService extends IService<Contests> {
    /**
     * 获取正式比赛情况
     * @param contestName
     * @return
     */
    public List<Map<String,Object>> getStudentsByContestDuring(String contestName);

    /**
     * 获取赛后补题情况
     * @param contestName
     * @return
     */
    public List<Map<String,Object>> getStudentsByContestAfter(String contestName);
}

package com.issja.service;

import com.issja.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 得分 服务类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface IScoreService extends IService<Score> {
    /**
     * 算出可以加多少分
     * @param score
     * @return
     */
    public Integer calculateScore(Map<String, Object> score);

    /**
     * 将分数写入数据库
     * @param score
     * @return
     */
    public boolean modifyScore(Map<String,Object> score);
}

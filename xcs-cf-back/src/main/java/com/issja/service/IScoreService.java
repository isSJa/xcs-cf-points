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
    public boolean modifyScore(Map<String,Object> score);
}

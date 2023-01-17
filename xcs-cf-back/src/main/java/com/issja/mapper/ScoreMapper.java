package com.issja.mapper;

import com.issja.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 得分 Mapper 接口
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    /**
     * 添加分数（之前尚未计入过）
     * @param score
     */
    public void addScore(Map<String,Object> score);

    /**
     * 是否计入过
     * @param account
     * @param name
     * @return
     */
    public int isExist(@Param("account") String account, @Param("name") String name);

    /**
     * 修改分数
     * @param score
     */
    public void updateScore(Map<String,Object> score);

    /**
     * 获取A题数
     * @param account
     * @param name
     */
    public int getScoreANum(@Param("account") String account, @Param("name") String name);
}

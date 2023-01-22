package com.issja.mapper;

import com.issja.entity.Contests;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * cf赛 Mapper 接口
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
public interface ContestsMapper extends BaseMapper<Contests> {
    /**
     * 正式赛时间内所有得分同学信息
     * @param name
     */
    @MapKey("id")
    public List<Map<String,Object>> getStudentsByContestDuring(String name);

    /**
     * 赛后补题所有得分同学信息
     * @param name
     */
    @MapKey("id")
    public List<Map<String,Object>> getStudentsByContestAfter(String name);

    /**
     * 获取某一个比赛的总赛题数
     * @param name
     * @return
     */
    @Select("select total,type from contests where name=#{name}")
    public Map<String,Object> getContestTotalAndType(@Param("name") String name);
}

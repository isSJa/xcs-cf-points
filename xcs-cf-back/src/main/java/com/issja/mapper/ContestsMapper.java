package com.issja.mapper;

import com.issja.entity.Contests;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface ContestsMapper extends BaseMapper<Contests> {
    /**
     * 正式赛时间内情况
     * @param contestName
     */
    @MapKey("id")
    public List<Map<String,Object>> getUsersByContestDuring(String contestName);

    /**
     * 赛后补题情况
     * @param contestName
     */
    @MapKey("id")
    public List<Map<String,Object>>  getUsersByContestAfter(String contestName);
}

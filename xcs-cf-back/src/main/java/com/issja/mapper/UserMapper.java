package com.issja.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 模糊查询+分页查询
     * @param page
     * @param searchValue
     * @return
     */
    @MapKey("id")
    public List<Map<String, Object>> getUserPage(Page<Map<String,Object>>page,String searchValue);

    /**
     * 用户账号是否存在
     * @param account
     * @return
     */
    @Select("select count(*) from xcs_cf_points.user where account=#{account}")
    public Integer isUserExist(@Param("account") String account);
}

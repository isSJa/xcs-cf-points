package com.issja.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
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
    public List<User> getAllUsers();

    public List<User> searchUser(String searchValue);

    @MapKey("id")
    public List<Map<String, Object>> getUserPage(Page<Map<String,Object>>page,String searchValue);
}

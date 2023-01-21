package com.issja.mapper;

import com.issja.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * 登录网站的用户 Mapper 接口
 * </p>
 *
 * @author issja
 * @since 2023-01-20
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 账密是否正确
     * @param account
     * @param pwd
     * @return
     */
    @Select("select * from xcs_cf_points.user where BINARY password=#{pwd} and(BINARY username=#{account} or BINARY email=#{account})")
    public User isLoginSuccess(@Param("account")String account,@Param("pwd")String pwd);

    /**
     * 用户名是否已注册
     * @param username
     * @return
     */
    @Select("select count(*) from xcs_cf_points.user where BINARY username=#{username}")
    public int isExistUsername(String username);

    /**
     * 邮箱是否已注册
     * @param email
     * @return
     */
    @Select("select count(*) from xcs_cf_points.user where BINARY username=#{email}")
    public int isExistEmail(String email);

    /**
     * 用户名或邮箱是否存在
     * @param account
     * @return
     */
    @Select("select count(*) from xcs_cf_points.user where BINARY username=#{account} or BINARY email=#{account}")
    public int isExistUsernameOrEmail(String account);

    /**
     * 更新密码
     * @param pwd
     * @return
     */
    @Select("update xcs_cf_points.user set password=#{nPwd} where id=#{id}")
    public Integer changePwd(Map<String,Object> pwd);
}

package com.issja.service;

import com.issja.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.issja.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 登录网站的用户 服务类
 * </p>
 *
 * @author issja
 * @since 2023-01-20
 */
public interface IUserService extends IService<User> {

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register(User user) throws IllegalAccessException;

    /**
     * 注册
     * @param user
     * @return
     */
    public Result login(Map<String,Object> form);

    /**
     * 更换用户头像
     * @param file
     */
    public void updateAvatar(MultipartFile file,Integer id);
}

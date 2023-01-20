package com.issja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.issja.entity.User;
import com.issja.mapper.UserMapper;
import com.issja.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.issja.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 登录网站的用户 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result register(User user) throws IllegalAccessException {
        // 用户名已存在
        if (userMapper.isExistUsername(user.getUsername()) != 0) {
            return Result.error("用户名已存在", null);
        }
        if (userMapper.isExistEmail(user.getEmail()) != 0) {
            return Result.error("邮箱已注册", null);
        }
        // 注册
        userMapper.insert(user);
        return Result.success("注册成功,请登录~", null);
    }

    @Override
    public Result login(Map<String, Object> form) {
        // 判断用户名或邮箱是否存在
        String account = (String) form.get("account");
        if (userMapper.isExistUsernameOrEmail(account) == 0)
            return Result.error("用户名或邮箱未注册", null);
        // 判断用户名密码是否正确
        String password = (String) form.get("password");
        User user = userMapper.isLoginSuccess(account, password);
        if (user == null) {
            return Result.error("密码错误", null);
        }
        return Result.success("登录成功！", user);
    }

    @Override
    public void updateAvatar(MultipartFile file, Integer id) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = id.toString() + "-" + UUID.randomUUID() + suffixName; // 新文件名
        String filePath = "F:/projs/files/avatar/"; // 上传后的路径
        // 删除之前的头像
        User user = userMapper.selectById(id);
        if (user.getImgurl() != null && user.getImgurl().length() > 0){
            String oldFileName = user.getImgurl().split("/file/")[1];
            new File(filePath + oldFileName).delete();
        }
        // 存新上传的图片
        File dest = new File(filePath, fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            // 修改数据库中数据
            user.setImgurl("/file/" + fileName);
            userMapper.updateById(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

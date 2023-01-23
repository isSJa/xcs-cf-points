package com.issja.controller;


import com.issja.entity.User;
import com.issja.service.IUserService;
import com.issja.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 登录网站的用户 前端控制器
 * </p>
 *
 * @author issja
 * @since 2023-01-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    // 添加用户
    @PostMapping("/register")
    public Result addUser(@RequestBody User user) throws IllegalAccessException {
        return userService.register(user);
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> form) {
        return userService.login(form);
    }

    // 验证token
    @PostMapping("/checkToken")
    public Result checkToken(@RequestParam String token){
        return userService.checkToken(token);
    }

    // 上传文件
    @PostMapping("/upload-avatar")
    public Result upload(@RequestParam(value = "file") MultipartFile file, Integer id) {
        userService.updateAvatar(file, id);
        return Result.success("更新头像成功！", null);
    }

    // 根据id获取用户信息
    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    // 更新用户信息
    @PostMapping("/changeInfo")
    public Result updateUser(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    // 更新密码
    @PostMapping("/changePwd")
    public Result updatePwd(@RequestBody Map<String,Object> pwd){
        return userService.updatePwd(pwd);
    }
}

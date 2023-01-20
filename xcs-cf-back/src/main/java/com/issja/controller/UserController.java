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

    @PostMapping("/register")
    public Result addUser(@RequestBody User user) throws IllegalAccessException {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> form) {
        return userService.login(form);
    }

    @PostMapping("/upload-avatar")
    public Result upload(@RequestParam(value = "file") MultipartFile file,Integer id){
        userService.updateAvatar(file,id);
        return Result.success("更新头像成功！",null);
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Integer id){
        return Result.success(userService.getById(id));
    }
}

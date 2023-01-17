package com.issja.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.User;
import com.issja.service.IUserService;
import com.issja.utils.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping()
    public Result getAll() {
        userService.getAllUsers().forEach(System.out::println);
        return Result.success(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @PostMapping()
    public Result addOne(@RequestBody User user) {
        boolean r = userService.save(user);
        if (r) {
            return Result.success("用户添加成功！", null);
        } else {
            return Result.error("用户添加失败！", null);
        }
    }

    @PutMapping()
    public Result modifyOne(@RequestBody User user) {
        boolean r = userService.updateById(user);
        if (r) {
            return Result.success("用户修改成功！", null);
        } else {
            return Result.error("用户修改失败！", null);
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Integer id) {
        boolean r = userService.removeById(id);
        if (r) {
            return Result.success("用户删除成功！", null);
        } else {
            return Result.error("用户删除失败！", null);
        }
    }

    @DeleteMapping()
    public Result deleteMany(@RequestBody List<Integer> ids) {
        boolean r = userService.removeByIds(ids);
        if (r) {
            return Result.success("批量删除成功！", null);
        } else {
            return Result.error("批量删除失败！", null);
        }
    }


    @PostMapping("/search")
    public Result getSearch(@RequestBody String searchValue) {
        if (searchValue == null || searchValue.length() == 0) return Result.success();
        return Result.success(userService.getSearch(searchValue));
    }

    // 分页+模糊查询
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam(value = "value", defaultValue = "") String searchValue) {
        Page<Map<String, Object>> page = userService.getUserPage(currentPage, pageSize, searchValue);
        if (currentPage > page.getCurrent()) {
            page = userService.getUserPage((int) page.getCurrent(), pageSize, searchValue);
        }
        return Result.success(page);
    }
}

package com.issja.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.Student;
import com.issja.service.IStudentService;
import com.issja.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 社员表 前端控制器
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    // 获取某个用户信息
    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id) {
        return Result.success(studentService.getById(id));
    }

    //增加用户
    @PostMapping()
    public Result addOne(@RequestBody Student student) {
        boolean r = studentService.save(student);
        if (r) {
            return Result.success("添加社员成功！", null);
        } else {
            return Result.error("添加社员失败！", null);
        }
    }

    // 修改用户信息
    @PutMapping()
    public Result modifyOne(@RequestBody Student student) {
        boolean r = studentService.updateById(student);
        if (r) {
            return Result.success("修改社员信息成功！", null);
        } else {
            return Result.error("修改社员信息失败！", null);
        }
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Integer id) {
        boolean r = studentService.removeById(id);
        if (r) {
            return Result.success("删除社员成功！", null);
        } else {
            return Result.error("删除社员失败！", null);
        }
    }

    // 分页+模糊查询
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam(value = "value", defaultValue = "") String searchValue) {
        Page<Map<String, Object>> page = studentService.getStudentPage(currentPage, pageSize, searchValue);
        if (currentPage > page.getCurrent()) {
            page = studentService.getStudentPage((int) page.getCurrent(), pageSize, searchValue);
        }
        return Result.success(page);
    }

    // 获取所有用户信息
    @GetMapping()
    public Result getAll() {
        return Result.success(studentService.getAllStudentIncludesScore());
    }

    //获取某一个用户的所有比赛数据
    @GetMapping("/contests/{id}")
    public Result getStudentScores(@PathVariable Integer id) {
        return Result.success(studentService.getStudentScores(id));
    }

    //获取所有用户的比赛信息（包括每场比赛情况）
    @GetMapping("/contests")
    public Result getAllStudentScores() {
        return Result.success(studentService.getAllStudentScores());
    }
}

package com.issja.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.Contests;
import com.issja.service.IContestsService;
import com.issja.utils.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * cf赛 前端控制器
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/contests")
public class ContestsController {
    @Autowired
    private IContestsService contestsService;

    @GetMapping()
    public Result getAll() {
        return Result.success(contestsService.list());
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id) {
        return Result.success(contestsService.getById(id));
    }

    @PostMapping()
    public Result addOne(@RequestBody Contests contests) {
        boolean r = contestsService.save(contests);
        if (r) {
            return Result.success("比赛添加成功！", null);
        } else {
            return Result.error("比赛添加失败！", null);
        }
    }

    @PutMapping()
    public Result modifyOne(@RequestBody Contests contests) {
        boolean r = contestsService.updateById(contests);
        if (r) {
            return Result.success("比赛修改成功！", null);
        } else {
            return Result.error("比赛修改失败！", null);
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Integer id) {
        boolean r = contestsService.removeById(id);
        if (r) {
            return Result.success("比赛删除成功！", null);
        } else {
            return Result.error("比赛删除失败！", null);
        }
    }

    @DeleteMapping()
    public Result deleteMany(@RequestBody List<Integer> ids) {
        boolean r = contestsService.removeByIds(ids);
        if (r) {
            return Result.success("批量删除成功！", null);
        } else {
            return Result.error("批量删除失败！", null);
        }
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Contests contests) {
        LambdaQueryWrapper<Contests> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(contests.getName()), Contests::getName, contests.getName());
        lambdaQueryWrapper.eq(contests.getType() != null, Contests::getType, contests.getType());
        IPage<Contests> page = new Page<Contests>(currentPage, pageSize);
        contestsService.page(page, lambdaQueryWrapper);
        return Result.success(page);
    }

    @GetMapping("/during")
    public Result getDuringDetail(@RequestParam String name){
        return Result.success(contestsService.getUsersByContestDuring(name));
    }

    @GetMapping("/after")
    public Result getAfterDetail(@RequestParam String name){
        return Result.success(contestsService.getUsersByContestAfter(name));
    }
}

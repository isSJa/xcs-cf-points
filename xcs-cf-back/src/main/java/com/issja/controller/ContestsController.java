package com.issja.controller;


import com.issja.entity.Contests;
import com.issja.service.IContestsService;
import com.issja.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 查找所有比赛信息
    @GetMapping()
    public Result getAll() {
        return Result.success(contestsService.list());
    }

    // 添加一个比赛
    @PostMapping()
    public Result addOne(@RequestBody Contests contests) {
        boolean r = contestsService.save(contests);
        if (r) {
            return Result.success("比赛添加成功！", null);
        } else {
            return Result.error("比赛添加失败！", null);
        }
    }

    // 获取某一比赛正式比赛时间内所用用户信息
    @GetMapping("/during")
    public Result getDuringDetail(@RequestParam String name){
        return Result.success(contestsService.getStudentsByContestDuring(name));
    }

    // 获取某一比赛赛后补题所有用户信息
    @GetMapping("/after")
    public Result getAfterDetail(@RequestParam String name){
        return Result.success(contestsService.getStudentsByContestAfter(name));
    }

    //删除比赛
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        boolean r = contestsService.removeById(id);
        if(r){
            return Result.success("比赛删除成功！",null);
        }else{
            return Result.error("比赛删除失败！",null);
        }
    }
}

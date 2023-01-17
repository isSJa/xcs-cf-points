package com.issja.controller;

import com.issja.entity.Score;
import com.issja.service.IScoreService;
import com.issja.utils.Result;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 得分 前端控制器
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @GetMapping()
    public Result getAll() {
        return Result.success(scoreService.list());
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Integer id) {
        return Result.success(scoreService.getById(id));
    }

    @PostMapping()
    public Result addOne(@RequestBody Map<String,Object> score) {
        boolean r = scoreService.modifyScore(score);
        if (r) {
            return Result.success("计分成功！添加"+score.get("score")+"分！", null);
        } else {
            return Result.error("计分失败！", null);
        }
    }

    @PutMapping()
    public Result modifyOne(@RequestBody Score score) {
        boolean r = scoreService.updateById(score);
        if (r) {
            return Result.success("计分修改成功！", null);
        } else {
            return Result.error("计分修改失败！", null);
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Integer id) {
        boolean r = scoreService.removeById(id);
        if (r) {
            return Result.success("计分删除成功！", null);
        } else {
            return Result.error("计分删除失败！", null);
        }
    }

    @DeleteMapping()
    public Result deleteMany(@RequestBody List<Integer> ids) {
        boolean r = scoreService.removeByIds(ids);
        if (r) {
            return Result.success("批量删除成功！", null);
        } else {
            return Result.error("批量删除失败！", null);
        }
    }
}

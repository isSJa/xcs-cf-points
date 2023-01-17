package com.issja.service.impl;

import com.issja.entity.Score;
import com.issja.mapper.ContestsMapper;
import com.issja.mapper.ScoreMapper;
import com.issja.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 得分 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public boolean modifyScore(Map<String, Object> score) {
        String account= (String) score.get("uAccount");
        String name= (String) score.get("cName");
        if(scoreMapper.isExist(account,name)==0){
            scoreMapper.addScore(score);
        }else{
            scoreMapper.updateScore(score);
        }
        return true;
    }
}

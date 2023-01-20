package com.issja.service.impl;

import com.issja.entity.Score;
import com.issja.mapper.ContestsMapper;
import com.issja.mapper.ScoreMapper;
import com.issja.mapper.StudentMapper;
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
    @Autowired
    private ContestsMapper contestsMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Integer calculateScore(Map<String, Object> score) {
        String name = (String) score.get("cName");
        Integer aNum = (Integer) score.get("aNum");
        Boolean isDuring = (Boolean) score.get("isDuring");
        Map<String, Object> totalAndType = contestsMapper.getContestTotalAndType(name);
        // 比赛未录入
        if(totalAndType==null)return -1;
        Integer total = (Integer) totalAndType.get("total");
        String type = (String) totalAndType.get("type");
        // 输入A题数有误
        if(aNum>total||aNum<0)return -1;
        // 算分
        Integer res = 0;
        if (isDuring) {
            // 正式比赛
            res = aNum * 50;
            if (aNum >= 1) res += 150;
            if (aNum >= Math.floor(total * 2 / 3)) res += 300;
            if (aNum.equals(total)) res += 750;
        } else {
            // 赛后补题
            if (aNum >= Math.floor(total * 2 / 3)) res += 100;
            if (aNum.equals(total)) res += 150;
        }
        if (type.equals("A")) res *= 2;
        return res;
    }

    @Override
    public boolean modifyScore(Map<String, Object> score) {
        Integer res = calculateScore(score);
        // 比赛信息不存在或输入A题数有误
        if(res==-1)return false;
        String account= (String) score.get("uAccount");
        // 用户信息不存在
        if(studentMapper.isStudentExist(account)==0)return false;
        String name= (String) score.get("cName");
        score.put("score",res);
        if(scoreMapper.isExist(account,name)==0){
            scoreMapper.addScore(score);
        }else{
            int aNum=scoreMapper.getScoreANum(account,name);
            if((Integer)score.get("aNum")<aNum)
                score.put("aNum",aNum);
            scoreMapper.updateScore(score);
        }
        return true;
    }
}

package com.issja.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.issja.entity.*;
import com.issja.mapper.ContestsMapper;
import com.issja.mapper.UserMapper;
import com.issja.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContestsMapper contestsMapper;

    @Override
    public Page<Map<String, Object>> getUserPage(int currentPage, int pageSize, String searchValue) {
        Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);
        return page.setRecords(userMapper.getUserPage(page, searchValue));
    }

    @Override
    public List<UserContestDetail> getUserScores(int id) {
        List<Score> userScores = userMapper.getUserScores(id);
        List<UserContestDetail> userUserContestDetails = new ArrayList<>();
        for (Score score : userScores) {
            if (score.getANum() != null) {
                UserContestDetail userContestDetail = new UserContestDetail();
                userContestDetail.setCid(score.getCid());
                userContestDetail.setANum(score.getANum());
                userContestDetail.setDuring(score.getDuringscore());
                userContestDetail.setAfter(score.getAfterscore());
                QueryWrapper<Contests> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("name");
                queryWrapper.eq("id", score.getCid());
                userContestDetail.setCname(
                        contestsMapper.selectOne(queryWrapper).getName());
                userUserContestDetails.add(userContestDetail);
            }
        }
        return userUserContestDetails;
    }

    @Override
    public List<AllUserContestsDetail> getAllUserScores() {
        Integer userCount = userMapper.selectCount(null);
        Integer contestCount = contestsMapper.selectCount(null);
        // TODO:只为测试，之后记得删掉
        contestCount=5;
        List<AllUserContestsDetail> allUserContestsDetailList = new ArrayList<>();
        for (int i = 1; i <= userCount; i++) {
            AllUserContestsDetail allUserContestsDetail = new AllUserContestsDetail();
            List<UserContestDetail> userContestDetailListBefore = getUserScores(i);
            List<UserContestDetail> userContestDetailList = new ArrayList<>(userContestDetailListBefore);
            allUserContestsDetail.setUid(i);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("name");
            queryWrapper.eq("id", i);
            allUserContestsDetail.setUname(userMapper.selectOne(queryWrapper).getName());
            // 求出doList和contestDetail，没有的值附0
            List<Integer> doList = new ArrayList<>();
            int j = 1;
            for (UserContestDetail d : userContestDetailListBefore) {
                while (j < d.getCid()) {
                    doList.add(0);
                    userContestDetailList.add(j-1,null);
                    j++;
                }
                doList.add(1);
                j++;
            }
            while (j <= contestCount) {
                doList.add(0);
                userContestDetailList.add(j-1,null);
                j++;
            }
            allUserContestsDetail.setContestDetail(userContestDetailList);
            allUserContestsDetail.setDoList(doList);
            allUserContestsDetailList.add(allUserContestsDetail);
        }
        return allUserContestsDetailList;
    }
}

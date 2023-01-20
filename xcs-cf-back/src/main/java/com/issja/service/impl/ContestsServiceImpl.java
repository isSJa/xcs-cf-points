package com.issja.service.impl;

import com.issja.entity.Contests;
import com.issja.mapper.ContestsMapper;
import com.issja.service.IContestsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * cf赛 服务实现类
 * </p>
 *
 * @author issja
 * @since 2023-01-06
 */
@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests> implements IContestsService {

    @Autowired
    private ContestsMapper contestsMapper;

    @Override
    public List<Map<String, Object>> getStudentsByContestDuring(String contestName) {
        return contestsMapper.getStudentsByContestDuring(contestName);
    }

    @Override
    public List<Map<String, Object>> getStudentsByContestAfter(String contestName) {
        return contestsMapper.getStudentsByContestAfter(contestName);
    }
}

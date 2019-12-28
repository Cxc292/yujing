package com.yujing.yujing.service.user.flow;

import com.yujing.yujing.dao.user.flow.UflowDao;
import com.yujing.yujing.dao.user.problem.UproblemDao;
import com.yujing.yujing.pojo.Flow;
import com.yujing.yujing.pojo.Problem;
import com.yujing.yujing.tool.Pagebean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UflowService {

    @Autowired
    UflowDao uflowDao;

    public List<Flow> uselectflow(){
        return uflowDao.uselectflow();
    }
}

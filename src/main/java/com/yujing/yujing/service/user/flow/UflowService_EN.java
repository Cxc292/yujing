package com.yujing.yujing.service.user.flow;

import com.yujing.yujing.dao.user.flow.UflowDao;
import com.yujing.yujing.dao.user.flow.UflowDao_EN;
import com.yujing.yujing.pojo.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UflowService_EN {

    @Autowired
    UflowDao_EN uflowDao;

    public List<Flow> uselectflow(){
        return uflowDao.uselectflow();
    }
}

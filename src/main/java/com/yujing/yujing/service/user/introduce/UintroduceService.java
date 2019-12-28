package com.yujing.yujing.service.user.introduce;

import com.yujing.yujing.dao.admin.IntroduceDao;
import com.yujing.yujing.dao.user.introduce.UintroduceDao;
import com.yujing.yujing.dao.user.support.UsupportDao;
import com.yujing.yujing.pojo.Introduce;
import com.yujing.yujing.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UintroduceService {

    @Autowired
    UintroduceDao uintroduceDao;

    public Introduce uselectintroduce(){
        return uintroduceDao.uselectintroduce();
    }

}

package com.yujing.yujing.service.user.introduce;

import com.yujing.yujing.dao.user.introduce.UintroduceDao;
import com.yujing.yujing.dao.user.introduce.UintroduceDao_EN;
import com.yujing.yujing.pojo.Introduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UintroduceService_EN {

    @Autowired
    UintroduceDao_EN uintroduceDao;

    public Introduce uselectintroduce(){
        return uintroduceDao.uselectintroduce();
    }

}

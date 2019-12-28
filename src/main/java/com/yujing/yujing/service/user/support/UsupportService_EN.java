package com.yujing.yujing.service.user.support;

import com.yujing.yujing.dao.user.support.UsupportDao;
import com.yujing.yujing.dao.user.support.UsupportDao_EN;
import com.yujing.yujing.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsupportService_EN {

    @Autowired
    UsupportDao_EN usupportDao;

    public Support uselectsupport(){
        return usupportDao.uselectsupport();
    }

}

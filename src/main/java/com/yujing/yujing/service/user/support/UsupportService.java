package com.yujing.yujing.service.user.support;

import com.yujing.yujing.dao.user.support.UsupportDao;
import com.yujing.yujing.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsupportService {

    @Autowired
    UsupportDao usupportDao;

    public Support uselectsupport(){

        return usupportDao.uselectsupport();
    }

}

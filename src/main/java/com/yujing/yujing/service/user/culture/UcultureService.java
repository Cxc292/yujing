package com.yujing.yujing.service.user.culture;

import com.yujing.yujing.dao.user.culture.UcultureDao;
import com.yujing.yujing.dao.user.support.UsupportDao;
import com.yujing.yujing.pojo.Culture;
import com.yujing.yujing.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UcultureService {

    @Autowired
    UcultureDao ucultureDao;

    public Culture uselectculture(){
        return ucultureDao.uselectculture();
    }

}

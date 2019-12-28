package com.yujing.yujing.service.user.culture;

import com.yujing.yujing.dao.user.culture.UcultureDao;
import com.yujing.yujing.dao.user.culture.UcultureDao_EN;
import com.yujing.yujing.pojo.Culture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UcultureService_EN {

    @Autowired
    UcultureDao_EN ucultureDao;

    public Culture uselectculture(){
        return ucultureDao.uselectculture();
    }

}

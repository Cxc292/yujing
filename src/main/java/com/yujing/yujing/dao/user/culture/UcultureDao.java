package com.yujing.yujing.dao.user.culture;

import com.yujing.yujing.pojo.Culture;
import com.yujing.yujing.pojo.Support;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UcultureDao {

    Culture uselectculture();

}

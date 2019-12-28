package com.yujing.yujing.dao.user.support;

import com.yujing.yujing.pojo.Flow;
import com.yujing.yujing.pojo.Support;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsupportDao {

    Support uselectsupport();

}

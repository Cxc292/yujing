package com.yujing.yujing.dao.user.flow;

import com.yujing.yujing.pojo.Flow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UflowDao_EN {

    List<Flow> uselectflow();

}

package com.yujing.yujing.dao.user.environmental;

import com.yujing.yujing.pojo.Environmental;
import com.yujing.yujing.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UenvironmentalDao {
    Integer uselectcountenvironmental();
    List<Environmental> uselectenvironmentallimit(int param1, int param2);
    //List<Problem> selectproblem();
}

package com.yujing.yujing.dao.user.problem;

import com.yujing.yujing.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UproblemDao {
    Integer uselectcountproblem();
    List<Problem> uselectproblemlimit(int param1, int param2);
    //List<Problem> selectproblem();
}

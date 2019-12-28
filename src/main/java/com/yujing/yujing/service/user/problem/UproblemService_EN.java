package com.yujing.yujing.service.user.problem;

import com.yujing.yujing.dao.user.problem.UproblemDao;
import com.yujing.yujing.dao.user.problem.UproblemDao_EN;
import com.yujing.yujing.pojo.Problem;
import com.yujing.yujing.tool.Pagebean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UproblemService_EN {

    @Autowired
    UproblemDao_EN uproblemDao;

    public Pagebean<Problem> uselectproblemlimit(){
        return tool(1);
    }

    public Pagebean<Problem> uselectproblemlimitid(Integer a){
        return tool(a);
    }
    private Pagebean<Problem> tool(Integer a){
        Pagebean<Problem> problemPagebean= new Pagebean<Problem>();
        //当前页数
        Integer page = a;
        //每页显示的个数
        Integer number = 12 ;
        //总个数
        Integer total = uproblemDao.uselectcountproblem();
        //总页数
        Integer pagecount;
        if((total%number) != 0){
            pagecount = (total / number) + 1;
        }else {
            pagecount = (total / number);
        }
        //从第几个开始查
        Integer param = (page - 1)* number;
        List<Problem> uproblem = uproblemDao.uselectproblemlimit(param, number);
        problemPagebean.setTotal(total);
        problemPagebean.setNumber(number);
        problemPagebean.setPagecount(pagecount);
        problemPagebean.setPage(page);
        problemPagebean.setList(uproblem);
        return problemPagebean;
    }
}

package com.yujing.yujing.service.user.environmental;

import com.yujing.yujing.dao.user.environmental.UenvironmentalDao;
import com.yujing.yujing.dao.user.environmental.UenvironmentalDao_EN;
import com.yujing.yujing.pojo.Environmental;
import com.yujing.yujing.tool.Pagebean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UenvironmentalService_EN {

    @Autowired
    UenvironmentalDao_EN uenvironmentalDao;

    public Pagebean<Environmental> uselectproblemlimit(){
        return tool(1);
    }

    public Pagebean<Environmental> uselectproblemlimitid(Integer a){
        return tool(a);
    }
    private Pagebean<Environmental> tool(Integer a){
        Pagebean<Environmental> problemPagebean= new Pagebean<Environmental>();
        //当前页数
        Integer page = a;
        //每页显示的个数
        Integer number = 3 ;
        //总个数
        Integer total = uenvironmentalDao.uselectcountenvironmental();
        //总页数
        Integer pagecount;
        if((total%number) != 0){
            pagecount = (total / number) + 1;
        }else {
            pagecount = (total / number);
        }
        //从第几个开始查
        Integer param = (page - 1)* number;
        List<Environmental> environmental = uenvironmentalDao.uselectenvironmentallimit(param, number);
        problemPagebean.setTotal(total);
        problemPagebean.setNumber(number);
        problemPagebean.setPagecount(pagecount);
        problemPagebean.setPage(page);
        problemPagebean.setList(environmental);
        return problemPagebean;
    }

}

package com.yujing.yujing.service.user.product;

import com.yujing.yujing.dao.user.product.UproductDao;
import com.yujing.yujing.dao.user.product.UproductDao_EN;
import com.yujing.yujing.pojo.*;
import com.yujing.yujing.tool.Pagebean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UproductService_EN {

    @Autowired
    UproductDao_EN uproductDao;
    //根据ID查 下一页
    public Product uselectproductbyidlower(Integer a){
        Product uselectproductbyid = uproductDao.uselectproductbyid(a);
        while (uselectproductbyid == null ){
            uselectproductbyid = uproductDao.uselectproductbyid(++a);
        }
        return uselectproductbyid;
    }
    //根据ID查 上一页
    public Product uselectproductbyidupper(Integer a){
        Product uselectproductbyid = uproductDao.uselectproductbyid(a);
        while (uselectproductbyid == null ){
            uselectproductbyid = uproductDao.uselectproductbyid(--a);
        }
        return uselectproductbyid;
    }
    //首次加载行
    public Pagebean<Product> uselectproductlimit(Integer type){
        return tool(1,type);
    }
    //非首次加载
    public Pagebean<Product> uselectproductlimitid(Integer a, Integer type) {
        return tool(a,type);
    }
    //加载类别
    public List<Product_type> uselectproducttype(){
        return uproductDao.uselectproducttype();
    }
    //加载所有
    public Pagebean<Product> uselectproductlimitAll(Integer page0, Integer type){
        Pagebean<Product> problemPagebean= new Pagebean<Product>();
        //当前页数
        Integer page = page0;
        //每页显示的个数
        Integer number = 9 ;
        //总个数
        Integer total = uproductDao.uselectcountproduct();
        //总页数
        Integer pagecount;
        if((total%number) != 0){
            pagecount = (total / number) + 1;
        }else {
            pagecount = (total / number);
        }
        //从第几个开始查
        Integer param = (page - 1)* number;
        List<Product> products = PackingProduct(param,number,type);
        problemPagebean.setTotal(total);
        problemPagebean.setNumber(number);
        problemPagebean.setPagecount(pagecount);
        problemPagebean.setPage(page);
        problemPagebean.setList(products);
        return problemPagebean;
    }

    //工具
    private Pagebean<Product> tool(Integer a,Integer type){
        Pagebean<Product> problemPagebean= new Pagebean<Product>();
        //当前页数
        Integer page = a;
        //每页显示的个数
        Integer number = 9 ;
        //总个数
        Integer total = uproductDao.uselectcountproduct();
        //总页数
        Integer pagecount;
        if((total%number) != 0){
            pagecount = (total / number) + 1;
        }else {
            pagecount = (total / number);
        }
        //从第几个开始查
        Integer param = (page - 1)* number;
        List<Product> product = uproductDao.uselectproductlimit(param, number, type);
        problemPagebean.setTotal(total);
        problemPagebean.setNumber(number);
        problemPagebean.setPagecount(pagecount);
        problemPagebean.setPage(page);
        problemPagebean.setList(product);
        return problemPagebean;
    }
    private List<Product> PackingProduct(Integer a,Integer b,Integer type){
        a = (a - 1) * b;
        b = a + b;
        List<Product> products = uproductDao.uselectproductlimit(a, b ,type);
        for (Product product:products
        ) {
            Integer id = product.getId();
            List<Product_flow> selectproductflows = uproductDao.uselectproductflowid(id);
            if(selectproductflows != null){
                product.setProduct_flows(selectproductflows);
            }
            List<Product_img> selectproductimg = uproductDao.uselectproductimgid(id);
            if(selectproductimg != null){
                product.setProduct_imgs(selectproductimg);
            }
            Product_details selectproductdetails = uproductDao.uselectproductdetailsid(id);
            if(selectproductdetails != null){
                product.setProduct_details(selectproductdetails);
            }
        }
        return products;
    }
}

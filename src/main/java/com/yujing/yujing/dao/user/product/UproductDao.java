package com.yujing.yujing.dao.user.product;

import com.yujing.yujing.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UproductDao {
    Integer uselectcountproduct();
    List<Product> uselectproductlimit(Integer a, Integer b, Integer type);
    List<Product_type> uselectproducttype();
    List<Product_flow> uselectproductflowid(Integer a);
    List<Product_img> uselectproductimgid(Integer a);
    Product_details uselectproductdetailsid(Integer a);
    Product uselectproductbyid(Integer param1);
    List<Product> uselectproductname(String s);
}

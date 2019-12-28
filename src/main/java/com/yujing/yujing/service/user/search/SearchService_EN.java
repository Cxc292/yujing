package com.yujing.yujing.service.user.search;

import com.yujing.yujing.dao.user.product.UproductDao;
import com.yujing.yujing.dao.user.product.UproductDao_EN;
import com.yujing.yujing.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService_EN {
    @Autowired
    UproductDao_EN uproductDao;
    public List<Product> uselectproductname(String name){
        List<Product> products = uproductDao.uselectproductname(name);
        return products;
    }

}

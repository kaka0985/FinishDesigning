package com.wen.service;

import com.wen.pojo.Product;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/20 15:07
 */
public interface ProductService {

    List<Product> getProductList(Product product);

    List<Product> selectByProductName(String product_name, String product_cannel, String product_status);

    Product getProductByIdAccurate(Integer product_id);

    Integer updateProduct(Product product);

    Integer addProduct(Product product);

    Integer deleteProduct(int product_id);

    List<Product> getProductListByStatus(String product_status);
}

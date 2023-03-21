package com.wen.service.servieimpl;

import com.wen.mapper.ProductMapper;
import com.wen.pojo.Product;
import com.wen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/20 15:07
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductList(Product product) {
        return productMapper.getProductList(product);
    }

    @Override
    public List<Product> selectByProductName(String product_name, String product_cannel, String product_status) {
        return productMapper.selectByProductName(product_name,product_cannel,product_status);
    }

    @Override
    public Product getProductByIdAccurate(Integer product_id) {
        return productMapper.getProductByIdAccurate(product_id);
    }

    @Override
    public Integer updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Integer deleteProduct(int product_id) {
        return productMapper.deleteProduct(product_id);
    }

    @Override
    public List<Product> getProductListByStatus(String product_status) {
        return productMapper.getProductListByStatus(product_status);
    }

    @Override
    public Integer updateProduct2(Product product) {
        return productMapper.updateProduct2(product);
    }
}

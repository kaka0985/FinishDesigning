package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.Product;
import com.wen.pojo.User;
import com.wen.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @作者：温
 * @时间：2023/2/20 15:06
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //跳转到产品页面
    @RequestMapping("/table")
    public String table() {
        return "ProductTable";
    }

    //查询产品列表
    @RequestMapping("/getProductList")
    @ResponseBody
    public Object getProductList(Integer page, Integer limit, Product Product, Model model) {
        PageHelper.startPage(page, limit);
        List<Product> sList = productService.getProductList(Product);
        PageInfo<Product> PageInfo = new PageInfo<>(sList);
        HashMap<String, Object> resultmap = new HashMap<>();
        model.addAttribute("list", sList);
        resultmap.put("code", 0);
        resultmap.put("msg", "出错啦");
        resultmap.put("count", PageInfo.getTotal());
        resultmap.put("data", PageInfo.getList());
        return resultmap;
    }

    //产品界面模糊查询
    @RequestMapping("/getProductListByProductName")
    @ResponseBody
    public Object getProductListByProductName(Integer page, Integer limit, Product product, Model model) {
        PageHelper.startPage(page, limit);
        //用户界面模糊查询
        List<Product> list = productService.selectByProductName(product.getProduct_name(), product.getProduct_cannel(), product.getProduct_status());
        PageInfo<Product> PageInfo = new PageInfo<>(list);
        HashMap<String, Object> resultmap = new HashMap<>();
        model.addAttribute("list", list);
        resultmap.put("code", 0);
        resultmap.put("msg", "出错啦");
        resultmap.put("count", PageInfo.getTotal());
        resultmap.put("data", PageInfo.getList());
        return resultmap;
    }

    //精确查找根据ID
    @RequestMapping("/getProductByIdAccurate")
    @ResponseBody
    public Product getProductByNameAccurate(Product product) {
        Product product2 = productService.getProductByIdAccurate(product.getProduct_id());
        return product2;
    }

    //产品界面修改产品
    @RequestMapping("/updateProduct")
    @ResponseBody
    public AjaxResult updateProduct(Product product) {
        AjaxResult result = new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=0;
        if (product.getProduct_img().equals("undefined")) {
             i = productService.updateProduct2(product);
        } else {
             i = productService.updateProduct(product);
        }
        if (i == 1) {
            result.setSuccess(true);
            result.setMesg("修改成功");
        } else {
            result.setSuccess(false);
            result.setMesg("修改失败");
        }
        return result;
    }

    //产品界面添加产品
    @RequestMapping("/addProduct")
    @ResponseBody
    public AjaxResult addproduct(Product product) {
        AjaxResult result = new AjaxResult();
        //登陆页面精准查询用户名
        Integer i = productService.addProduct(product);
        if (i == 1) {
            result.setSuccess(true);
            result.setMesg("添加成功");
        } else {
            result.setSuccess(false);
            result.setMesg("添加失败");
        }
        return result;
    }

    //产品界面删除产品
    @RequestMapping("/deleteProduct")
    @ResponseBody
    public AjaxResult deleteProduct(Product product) {
        AjaxResult result = new AjaxResult();
        //登陆页面精准查询用户名
        Integer i = productService.deleteProduct(product.getProduct_id());
        if (i == 1) {
            result.setSuccess(true);
            result.setMesg("删除成功");
        } else {
            result.setSuccess(false);
            result.setMesg("删除失败");
        }
        return result;
    }

    //查询状态为上线且收费节目
    @RequestMapping("/getProductListByStatus")
    @ResponseBody
    public Object getProductListByStatus(Integer page, Integer limit, Product product, Model model) {
        PageHelper.startPage(page, limit);
        //用户界面模糊查询
        product.setProduct_status("1");
        List<Product> list = productService.getProductListByStatus(product.getProduct_status());
        PageInfo<Product> PageInfo = new PageInfo<>(list);
        HashMap<String, Object> resultmap = new HashMap<>();
        model.addAttribute("list", list);
        resultmap.put("code", 0);
        resultmap.put("msg", "出错啦");
        resultmap.put("count", PageInfo.getTotal());
        resultmap.put("data", PageInfo.getList());
        return resultmap;
    }

    /**
     * 图片上传
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/file")
    public AjaxResult upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req) {
        AjaxResult result = new AjaxResult();
        result.setCode(0);
        System.out.println(multipartFile);
        // File folder = new File(realPath + format);
        String property = "D:\\idea\\毕设\\user";
        System.out.println(property);
        File folder = new File(property + "/src/assets/img");
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                return result;
            }
        }
        String oldName = multipartFile.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            multipartFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/products" + "/" + newName);
        String pictureUrl = "http://127.0.0.1:8081/src/assets/img/"+ newName;
        result.setCode(1);
        result.setMesg(pictureUrl);
        return result;
    }

}

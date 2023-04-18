package com.wen.controller;

import com.wen.pojo.*;
import com.wen.service.UserUseService;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @作者：温
 * @时间：2023/3/1 14:53
 */
@RestController
@Configuration
@RequestMapping("/api")
public class UserUseController {
    @Autowired
    private UserUseService userUseService;


    //登录@Configuration
    @RequestMapping("/login")
    public ResponseVO<User> login(User user) {

        User user2 = userUseService.selectByUserName(user.getUserid(), user.getPassword());
        if (user2 != null) {
            return new ResponseVO(user2, "success", 0, "密码正确");
        }
        return new ResponseVO(null, "error", 0, "用户名或者密码错误");
    }
    //注册用户
    @RequestMapping("/registerUser")
    public ResponseVO<User> registerUser(String userid,String password,String age,String nick_name,String address,String sex){
        User user=new User();
        user.setUserid(userid);
        user.setPassword(password);
        user.setAddress(address);
        user.setAge(age);
        user.setNick_name(nick_name);
        user.setSex(sex);
        user.setMember("0");
        User user2 = userUseService.selectByUserName(user.getUserid(), user.getPassword());
        if (user2!=null){
            return new ResponseVO(null, "error", 0, "账号已被注册");
        }else {
            Integer i= userUseService.registerUser(user);
            if (i==0){
                return new ResponseVO(null, "error", 0, "出错误啦");
            }
        }
        return new ResponseVO(null, "success", 0, "注册成功");
    }
    //查询套餐
    @RequestMapping("/getPackage")
    public ResponseVO<String> getPackage(){
        List<ProPackage> proPackage=userUseService.selectPackage();
        if(proPackage.size()==0){
            return new ResponseVO(null, "error", 0, "出错误啦");
        }
        return new ResponseVO(proPackage, "success", 0, "查询成功");
    }
    //根据产品名进行模糊查询
    @RequestMapping("/searchByname")
    public ResponseVO<String> searchByname(String text) {
        List<Product> list = userUseService.searchByName(text);
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询不到");
    }

    //获取订单信息
    @RequestMapping("/getOrderByUserId")
    public ResponseVO<String> getOrderByUserId(String userId) {
        List<Order> list = userUseService.getOrderByUserId(userId);
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "没有购买产品");
    }

    //获取产品信息
    @RequestMapping("/getProductByProductName")
    public ResponseVO<String> getProductByProductName(String Product_name, String User_id) {

        List<ProductAndUser> list = userUseService.getProductByProductName(Product_name);
        List<User> list2 = userUseService.getProductByUserName(User_id);
        list.get(0).setUserList(list2);
        return new ResponseVO(list, "success", 0, "查询成功");
    }

    //电影页面返回产品信息和订单信息
    @RequestMapping("/getUserOrderAndProduct")
    public ResponseVO<String> getUserOrderAndProduct(String Product_name, String User_id) {
        List<ProductAndUser> productList = userUseService.getProductByProductName(Product_name);
        List<User> userList = userUseService.getProductByUserName(User_id);
        List<Order> orderList = userUseService.getOrderByUserId(User_id);
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getOrder_productId().equals(String.valueOf(productList.get(0).getProduct_id()))) {
                    productList.get(0).setOrderStatus(1);
                }
            }
        }

        productList.get(0).setUserList(userList);
        return new ResponseVO(productList, "success", 0, "查询成功");
    }


    //获取用户信息 并且进行校验 会员日期过期的就会进行修改
    @RequestMapping("/getUser")
    public ResponseVO<String> getUser(String User_id) {
        List<User> list = userUseService.getProductByUserName(User_id);
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        //进行时间上的比较，如果会员已经过期就修改传入前端的值并且修改后台的会员等级
        if (list.get(0).getMember().equals("1")) {
            Integer i = list.get(0).getMember_end().compareTo(format);
            if (i != 1) {
                list.get(0).setMember("0");
                list.get(0).setMember_start(null);
                list.get(0).setMember_end(null);
                userUseService.updateUserMember(User_id);
            }
        }
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "没有该用户");
    }

    //续费会员
    @RequestMapping("/BuyMember")
    public ResponseVO<String> BuyMember(String month, String User_id) {
        List<User> list = userUseService.getProductByUserName(User_id);
        Integer i = null;
        if (list.get(0).getMember().equals("1")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = null;
            try {
                dt = sdf.parse(list.get(0).getMember_end());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, Integer.parseInt(month));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            i = userUseService.BuyMonth(reStr, User_id);
        } else {
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            //当前时间加一个月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = null;
            try {
                dt = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, Integer.parseInt(month));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            i = userUseService.BuyMonthInitial(format, reStr, User_id);
        }
        if (i == 1) {
            return new ResponseVO(null, "success", 0, "开通成功");

        }
        return new ResponseVO(null, "error", 0, "开通失败");
    }

    //购买电影 生成订单
    @RequestMapping("/BuyFilm")
    public ResponseVO<String> BuyFilm(String Price, String User_id, String Product_name,String UserVoucher,String voucher_id) {
        Product product = userUseService.getProductByProductNameAnother(Product_name);
        Order order = new Order();
        order.setOrder_userId(User_id);
        order.setOrder_productId(String.valueOf(product.getProduct_id()));
        order.setOrder_commodity(product.getProduct_name());
        order.setOrder_price(Price);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        order.setOrder_createTime(format);
        order.setOrder_dueTime("9999-99-99");

        order.setOrder_type("1");
        order.setOrder_cannel("3");
        order.setOrder_img(product.getProduct_img());
        Integer i = userUseService.addUserOrder(order);
        if(UserVoucher.equals("1")){
            VoucherUsage voucherUsage=new VoucherUsage();
            voucherUsage.setVoucherUsage_userID(User_id);
            voucherUsage.setVoucherUsage_voucherID(voucher_id);
            voucherUsage.setVoucherUsage_CreateTime(format);
           Integer j= userUseService.addUserVOucherUsage(voucherUsage);
           UserCollection collection=new UserCollection();
           collection.setCollection_voucherId(Integer.parseInt(voucher_id));
           collection.setCollection_userId(User_id);
           Integer m= userUseService.deletUserCollection(collection);
        }
        if (i == 1) {
            return new ResponseVO(null, "success", 0, "购买成功");
        }
        return new ResponseVO(null, "error", 0, "购买失败");
    }

    //获取各个频道的产品信息
    @RequestMapping("/getProductByCannel")
    public ResponseVO<String> getProductByCannel(String cannel) {
        List<Product> productList = userUseService.getProductByCannel(cannel);
        if (productList != null) {
            return new ResponseVO(productList, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询失败");
    }

    //获取各个频道的产品信息对电影频道的进行特别处理
    @RequestMapping("/getProductByCannelForFilm")
    public ResponseVO<String> getProductByCannelForFilm(String cannel) {
        List<Product> productList = userUseService.getProductByCannel(cannel);
        List<Product> tableData_comedy = new ArrayList<>();
        List<Product> tableData_commit = new ArrayList<>();
        List<Product> tableData_risk = new ArrayList<>();
        List<Product> tableData_science = new ArrayList<>();
        List<Product> tableData_Thriller = new ArrayList<>();
        List<Product> tableData_terrifying = new ArrayList<>();
        List<Product> tableData_love = new ArrayList<>();
        List<Product> tableData_cartoon = new ArrayList<>();
        List<Product> tableData_movement = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProduct_style().equals("喜剧")) {
                tableData_comedy.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("犯罪")) {
                tableData_commit.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("冒险")) {
                tableData_risk.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("科幻")) {
                tableData_science.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("惊悚")) {
                tableData_Thriller.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("恐怖")) {
                tableData_terrifying.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("爱情")) {
                tableData_love.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("动画")) {
                tableData_cartoon.add(productList.get(i));
            } else if (productList.get(i).getProduct_style().equals("动作")) {
                tableData_movement.add(productList.get(i));
            }
        }
        Map map = new HashMap();
        map.put("tableData_cartoon", tableData_cartoon);
        if (productList != null) {
            return new ResponseVO(map, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询失败");
    }


    //用户端领取卡券列表
    @RequestMapping("/getVoucher")
    public ResponseVO<String> getVoucher(String User_id) {
        List<User> user = userUseService.getProductByUserName(User_id);
        List<UserVoucherCollection> list = userUseService.getVoucher();
        List<UserCollection> userCollection = userUseService.getUserCollection(User_id);
        if (user.get(0).getMember().equals('1')) {
            if (userCollection != null) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < userCollection.size(); j++) {
                        if (list.get(i).getVoucher_id().equals(String.valueOf(userCollection.get(j).getCollection_voucherId()))) {
                            list.get(i).setUserCollections("1");
                            break;
                        } else {
                            list.get(i).setUserCollections("0");
                        }
                    }
                }
            }
        }else {
            if (userCollection != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (userCollection.size()!=0){
                    for (int j = 0; j < userCollection.size(); j++) {
                        if (list.get(i).getVoucher_scene().equals("1")&&list.get(i).getVoucher_id().equals(String.valueOf(userCollection.get(j).getCollection_voucherId()))) {
                            list.get(i).setUserCollections("1");
                            break;
                        }
                    }
                }else if(list.get(i).getVoucher_scene().equals("1")){
                        list.get(i).setUserCollections("0");
                    }else {
                        list.get(i).setUserCollections("1");
                    }
                }
            }
        }
        if (list != null) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询失败");
    }

    @RequestMapping("/getVoucherByUser")
    public ResponseVO<String> getVoucherByUser(String User_id){
        List<UserVoucherCollection> list = userUseService.getVoucher();
        List<UserCollection> userCollection = userUseService.getUserCollection(User_id);
        for(int i=0;i<list.size();i++){
            if(userCollection!=null){
                for(int j=0;j<userCollection.size();j++){
                    if(list.get(i).getVoucher_id().equals(String.valueOf(userCollection.get(j).getCollection_voucherId()))){
                        list.get(i).setUserCollections("1");
                    }
                }
            }
        }
        if (list != null) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询失败");
    }

    //领取卡券
    @RequestMapping("/CollectionVoucher")
    public ResponseVO<String> CollectionVoucher(String User_id, String Voucher_id) {
        UserCollection userCollection = new UserCollection();
        userCollection.setCollection_count("1");
        userCollection.setCollection_userId(User_id);
        userCollection.setCollection_voucherId(Integer.parseInt(Voucher_id));
        Integer i = userUseService.addUserVoucherCollection(userCollection);
        if (i == 0) {
            return new ResponseVO(null, "success", 0, "领取成功");
        }
        return new ResponseVO(null, "success", 0, "领取成功");
    }

    //购买套餐
    @RequestMapping("/BuyPackage")
    public ResponseVO<String> BuyPackage(String package_id,String User_id){
        ProPackage proPackage=userUseService.getPackageByID(package_id);
        String[] product_id=proPackage.getPackage_programId().split(",");
        String[] product_name=proPackage.getPackage_programName().split(",");
        String package_name= proPackage.getPackage_name();
        Order SelectOrder=userUseService.getOrderByUserIdAndPackage_Name(User_id,package_name);
        if(SelectOrder==null){
        for(int i=0;i<product_id.length;i++){
            String Product_name=product_name[i];
            Product product = userUseService.getProductByProductNameAnother(Product_name);
            Order order=new Order();
            order.setOrder_userId(User_id);
            order.setOrder_productId(product_id[i]);
            order.setOrder_commodity(product_name[i]);
            order.setOrder_price("2.5");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            order.setOrder_createTime(format);
            order.setOrder_dueTime("9999-99-99");
            order.setOrder_type("2");
            order.setOrder_cannel("3");
            order.setOrder_img(product.getProduct_img());
            Integer j = userUseService.addUserOrder(order);
        }
        Order order=new Order();
        order.setOrder_userId(User_id);
        order.setOrder_productId(package_id);
        order.setOrder_commodity(proPackage.getPackage_name());
        order.setOrder_price(proPackage.getPackage_price());
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        order.setOrder_createTime(format);
        order.setOrder_dueTime("9999-99-99");
        order.setOrder_type("2");
        order.setOrder_cannel("3");
        Integer i = userUseService.addUserOrder(order);
        if(i==0){
            return new ResponseVO(null, "error", 0, "购买失败");
        }
        return new ResponseVO(null, "success", 0, "购买成功");
    }
        return new ResponseVO(null, "error", 0, "重复购买");
    }
}

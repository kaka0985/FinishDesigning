package com.wen.mapper;

import com.wen.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/3/1 14:55
 */
@Mapper
public interface  UserUseMapper {
    User selectByUserName(String userid,String password);

    List<Product> searchByName(String text);

    List<Order> getOrderByUserId(String userId);

    List<ProductAndUser> getProductByProductName(String product_name);

    List<User> getProductByUserName(String user_id);

    Integer updateUserMember(String user_id);

    Integer BuyMonth(String reStr,String User_id);

    Integer BuyMonthInitial(String format, String reStr, String user_id);

    Product getProductByProductNameAnother(String product_name);

    Integer addUserOrder(Order order);

    List<Product> getProductByCannel(String cannel);

    List<UserVoucherCollection> getVoucher();

    List<UserCollection> getUserCollection(String user_id);

    Integer addUserVoucherCollection(UserCollection userCollection);

    Integer addUserVOucherUsage(VoucherUsage voucherUsage);

    Integer deletUserCollection(UserCollection collection);

    Integer registerUser(User user);


    List<ProPackage> selectPackage();

    ProPackage getPackageByID(String package_id);

    Order getOrderByUserIdAndPackage_Name(String user_id, String package_name);
}

package com.wen.mapper;

import com.wen.pojo.UserCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 14:47
 */
@Mapper
public interface UserCollectionMapper {
    List<UserCollection> getCollectionList(UserCollection collection);


    List<UserCollection> selectByCollectionUserID(String collection_userId, int collection_voucherId);
}

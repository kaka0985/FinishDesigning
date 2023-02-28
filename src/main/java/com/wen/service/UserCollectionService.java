package com.wen.service;

import com.wen.pojo.UserCollection;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 14:47
 */
public interface UserCollectionService {

    List<UserCollection> getCollectionList(UserCollection collection);

    List<UserCollection> selectByCollectionUserID(String collection_userId, int collection_voucherId);
}

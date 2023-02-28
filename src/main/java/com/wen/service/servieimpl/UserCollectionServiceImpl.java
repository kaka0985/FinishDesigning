package com.wen.service.servieimpl;

import com.wen.mapper.UserCollectionMapper;
import com.wen.pojo.UserCollection;
import com.wen.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 14:47
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired
    private UserCollectionMapper collectionMapper;

    @Override
    public List<UserCollection> getCollectionList(UserCollection collection) {
        return collectionMapper.getCollectionList(collection);
    }

    @Override
    public List<UserCollection> selectByCollectionUserID(String collection_userId, int collection_voucherId) {
        return collectionMapper.selectByCollectionUserID(collection_userId,collection_voucherId);
    }

}

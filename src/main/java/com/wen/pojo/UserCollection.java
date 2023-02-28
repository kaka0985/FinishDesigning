package com.wen.pojo;

import lombok.Data;

/**
 * @作者：温
 * @时间：2023/2/27 14:43
 */
@Data
public class UserCollection {
    private int collection_id;
    private String collection_userId;
    private int collection_voucherId;
    private String collection_count;

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id = collection_id;
    }

    public String getCollection_userId() {
        return collection_userId;
    }

    public void setCollection_userId(String collection_userId) {
        this.collection_userId = collection_userId;
    }

    public int getCollection_voucherId() {
        return collection_voucherId;
    }

    public void setCollection_voucherId(int collection_voucherId) {
        this.collection_voucherId = collection_voucherId;
    }

    public String getCollection_count() {
        return collection_count;
    }

    public void setCollection_count(String collection_count) {
        this.collection_count = collection_count;
    }
}

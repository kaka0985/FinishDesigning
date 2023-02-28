package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.UserCollection;
import com.wen.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 15:46
 */
@Controller
@RequestMapping("/collection")
public class UserCollectionController {

    @Autowired
    private UserCollectionService collectionService;

    @RequestMapping("/table")
    public String toTable(){
        return "CollectionTable";
    }
    //查询卡券领取列表
    @RequestMapping("/getCollectionList")
    @ResponseBody
    public  Object getCollectionList(Integer page, Integer limit, UserCollection collection, Model model){
        PageHelper.startPage(page,limit);
        List<UserCollection> sList = collectionService.getCollectionList(collection);
        PageInfo<UserCollection> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
    //卡券领取界面模糊查询
    @RequestMapping("/getCollectionListByCollectionUserId")
    @ResponseBody
    public  Object getCollectionListByCollectionName(Integer page, Integer limit, UserCollection collection, Model model){
        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<UserCollection> list=collectionService.selectByCollectionUserID(collection.getCollection_userId(),collection.getCollection_voucherId());
        PageInfo<UserCollection> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
}

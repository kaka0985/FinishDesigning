package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.Marketing;
import com.wen.pojo.Product;
import com.wen.service.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 10:38
 */
@Controller
@RequestMapping("/marketing")
public class MarketingController {

    @Autowired
    private MarketingService marketingService;


    @RequestMapping("/table")
    public String toTable(){
        return "MarketingTable";
    }
    
    //查询营销列表
    @RequestMapping("/getMarketingList")
    @ResponseBody
    public  Object getMarketingList(Integer page, Integer limit, Marketing marketing, Model model){
        PageHelper.startPage(page,limit);
        List<Marketing> sList = marketingService.getMarketingList(marketing);
        PageInfo<Marketing> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }

    //营销活动界面模糊查询
    @RequestMapping("/getMarketingListByMarketingName")
    @ResponseBody
    public  Object getMarketingListByMarketingName(Integer page, Integer limit, Marketing marketing, Model model){
        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<Marketing> list=marketingService.selectByMarketingName(marketing.getMarketing_name(),marketing.getMarketing_people());
        PageInfo<Marketing> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
    //营销活动界面添加
    @RequestMapping("/addMarketing")
    @ResponseBody
    public AjaxResult addMarketing(Marketing marketing){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=marketingService.addMarketing(marketing);
        if (i==1){
            result.setSuccess(true);
            result.setMesg("添加成功");
        }else {
            result.setSuccess(false);
            result.setMesg("添加失败");
        }
        return result;
    }
    //精确查找根据ID
    @RequestMapping("/getMarketingByIdAccurate")
    @ResponseBody
    public Marketing getMarketingByNameAccurate(Marketing marketing){
        Marketing Marketing2=marketingService.getMarketingByIdAccurate(marketing.getMarketing_id());
        return Marketing2;
    }
    //产品界面修改产品
    @RequestMapping("/updateMarketing")
    @ResponseBody
    public AjaxResult updateMarketing(Marketing marketing){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=marketingService.updateMarketing(marketing);
        if (i==1){
            result.setSuccess(true);
            result.setMesg("修改成功");
        }else {
            result.setSuccess(false);
            result.setMesg("修改失败");
        }
        return result;
    }
    //产品界面删除产品
    @RequestMapping("/deleteMarketing")
    @ResponseBody
    public AjaxResult deleteMarketing(Marketing marketing){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=marketingService.deleteMarketing(marketing.getMarketing_id());
        if (i==1){
            result.setSuccess(true);
            result.setMesg("删除成功");
        }else {
            result.setSuccess(false);
            result.setMesg("删除失败");
        }
        return result;
    }
}

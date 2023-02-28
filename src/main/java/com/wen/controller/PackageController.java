package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.ProPackage;
import com.wen.pojo.Product;
import com.wen.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/21 15:46
 */
@Controller
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    //跳转到套餐信息页面
    @RequestMapping("/table")
    public String table(){
        return "PackageTable";
    }
    
    //查询产品列表
    @RequestMapping("/getPackageList")
    @ResponseBody
    public  Object getPackageList(Integer page, Integer limit, ProPackage proPackage, Model model){
        PageHelper.startPage(page,limit);
        List<ProPackage> sList = packageService.getPackageList(proPackage);
        for (int i=0;i<sList.size();i++){
            String name = " ";
            String[] split = sList.get(i).getPackage_cannel().split(",");
            for (int j=0;j<split.length;j++){
                if (split[j].equals("1") ) {
                    name += "直播 ";
                    continue;
                } else if (split[j] .equals("2")) {
                    name += "电视剧 ";
                    continue;
                } else if (split[j] .equals("3")) {
                    name += "电影 ";
                    continue;
                } else if (split[j] .equals("4")) {
                    name += "少儿 ";
                    continue;
                } else if (split[j] .equals("5")) {
                    name += "综艺 ";
                    continue;
                } else if (split[j] .equals("6")) {
                    name += "新闻 ";
                    continue;
                } else if (split[j] .equals("7")) {
                    name += "体育 ";
                    continue;
                } else if (split[j] .equals("8")) {
                    name += "纪实 ";
                    continue;
                } else if (split[j] .equals("9")) {
                    name += "生活 ";
                    continue;
                }else {
                    continue;
                }
            }
            sList.get(i).setPackage_cannel(name);
        }
        PageInfo<ProPackage> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }

    //产品界面模糊查询
    @RequestMapping("/getPackageListByPackageName")
    @ResponseBody
    public  Object getProPackageListByProPackageName(Integer page, Integer limit, ProPackage proPackage, Model model){
        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<ProPackage> list=packageService.selectByProPackageName(proPackage.getPackage_name(),proPackage.getPackage_status());
        for (int i=0;i<list.size();i++){
            String name = " ";
            String[] split = list.get(i).getPackage_cannel().split(",");
            for (int j=0;j<split.length;j++){
                if (split[j].equals("1") ) {
                    name += "直播 ";
                    continue;
                } else if (split[j] .equals("2")) {
                    name += "电视剧 ";
                    continue;
                } else if (split[j] .equals("3")) {
                    name += "电影 ";
                    continue;
                } else if (split[j] .equals("4")) {
                    name += "少儿 ";
                    continue;
                } else if (split[j] .equals("5")) {
                    name += "综艺 ";
                    continue;
                } else if (split[j] .equals("6")) {
                    name += "新闻 ";
                    continue;
                } else if (split[j] .equals("7")) {
                    name += "体育 ";
                    continue;
                } else if (split[j] .equals("8")) {
                    name += "纪实 ";
                    continue;
                } else if (split[j] .equals("9")) {
                    name += "生活 ";
                    continue;
                }else {
                    continue;
                }
            }
            list.get(i).setPackage_cannel(name);
        }
        PageInfo<ProPackage> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
    //套餐界面添加套餐
    @RequestMapping("/addPackage")
    @ResponseBody
    public AjaxResult addPackage(ProPackage proPackage){
        AjaxResult result=new AjaxResult();
        String name = " ";
        String[] split = proPackage.getPackage_cannel().split(",");
        if(split[0]!=null){
            name=split[0]+",";
        }
        for(int i=0;i<split.length;i++){
            if(i+1<split.length&&(!split[i].equals(split[i+1]))){
                name+=split[i+1];
            }
        }
        proPackage.setPackage_cannel(name);
        //登陆页面精准查询用户名
        Integer i=packageService.addPackage(proPackage);
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
    @RequestMapping("/getPackageByIdAccurate")
    @ResponseBody
    public ProPackage getPackageByIdAccurate(ProPackage Package){
        ProPackage package2=packageService.getPackageByIdAccurate(Package.getPackage_id());
        return package2;
    }

    //产品界面修改产品
    @RequestMapping("/updatePackage")
    @ResponseBody
    public AjaxResult updatePackage(ProPackage proPackage){
        AjaxResult result=new AjaxResult();
        //去除多余项
        String name = " ";
        String[] split = proPackage.getPackage_cannel().split(",");
        if(split[0]!=null){
            name=split[0]+",";
        }
        for(int i=0;i<split.length;i++){
            if(i+1<split.length&&(!split[i].equals(split[i+1]))){
                name+=split[i+1];
            }
        }
        proPackage.setPackage_cannel(name);
        //登陆页面精准查询用户名
        Integer i=packageService.updatePackage(proPackage);
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
    @RequestMapping("/deletePackage")
    @ResponseBody
    public AjaxResult deletePackage(ProPackage proPackage){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=packageService.deletePackage(proPackage.getPackage_id());
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

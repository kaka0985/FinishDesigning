package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.Voucher;
import com.wen.pojo.Product;
import com.wen.pojo.Voucher;
import com.wen.service.VoucherService;
import lombok.Data;
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
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @RequestMapping("/table")
    public String toTable(){
        return "VoucherTable";
    }
    //查询卡券列表
    @RequestMapping("/getVoucherList")
    @ResponseBody
    public  Object getVoucherList(Integer page, Integer limit, Voucher voucher, Model model){
        PageHelper.startPage(page,limit);
        List<Voucher> sList = voucherService.getVoucherList(voucher);
        PageInfo<Voucher> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
    //卡券界面模糊查询
    @RequestMapping("/getVoucherListByVoucherName")
    @ResponseBody
    public  Object getVoucherListByVoucherName(Integer page, Integer limit, Voucher voucher, Model model){
        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<Voucher> list=voucherService.selectByVoucherName(voucher.getVoucher_name(),voucher.getVoucher_scene());
        PageInfo<Voucher> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }

    //营销活动界面添加
    @RequestMapping("/addVoucher")
    @ResponseBody
    public AjaxResult addVoucher(Voucher voucher){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=voucherService.addVoucher(voucher);
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
    @RequestMapping("/getVoucherByIdAccurate")
    @ResponseBody
    public Voucher getVoucherByNameAccurate(Voucher voucher){
        Voucher Voucher2=voucherService.getVoucherByIdAccurate(voucher.getVoucher_id());
        return Voucher2;
    }
    //产品界面修改产品
    @RequestMapping("/updateVoucher")
    @ResponseBody
    public AjaxResult updateVoucher(Voucher voucher){
        AjaxResult result=new AjaxResult();
        Integer i=voucherService.updateVoucher(voucher);
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
    @RequestMapping("/deleteVoucher")
    @ResponseBody
    public AjaxResult deleteVoucher(Voucher voucher){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        Integer i=voucherService.deleteVoucher(voucher.getVoucher_id());
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

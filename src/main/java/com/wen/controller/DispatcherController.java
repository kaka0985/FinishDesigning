package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.User;
import com.wen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {
    @Autowired
    private UserService userService;


    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }


    @RequestMapping("/towelcome")
    public  String towelcome(){
        return "welcome";
    }


    @RequestMapping("subject")
    public  String subject(){
        return "UserTable";
    }


    @RequestMapping("/tologin")
    public  String tologin(){
        return "login";
    }

    //登陆验证
    @RequestMapping("/toLogin")
    @ResponseBody
    public AjaxResult toLogin(User user){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
        User user2=userService.selectByUsername(user.getUserid());
        if (user.getUserid().equals("admin")){
            if (user2.getPassword().equals(user.getPassword())){
                result.setSuccess(true);
                result.setMesg("登录成功");
            }else {
                result.setSuccess(false);
                result.setMesg("密码错误");
            }
        }else {
            result.setSuccess(false);
            result.setMesg("用户名错误");
        }
        return result;
    }
    //用户界面查询
    @RequestMapping("/getUserList")
    @ResponseBody
    public  Object getUserList(Integer page, Integer limit, User user, Model model){
//        page=1;
//        limit=10;
        PageHelper.startPage(page,limit);
        List<User> sList = userService.getUserList(user);
        PageInfo<User> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }


    //用户界面模糊查询
    @RequestMapping("/getUserListByUserName")
    @ResponseBody
    public  Object getUserListByUserName(Integer page, Integer limit, User user, Model model){

        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<User> list=userService.selectByUsername2(user.getNick_name(),user.getMember());
        PageInfo<User> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }

    @RequestMapping("/getUserByName")
    @ResponseBody
    public User getUserByName(User user){
        User user2=userService.selectByUsername(user.getUserid());
        return user2;
    }

    //用户界面修改用户
    @RequestMapping("/updateUser")
    @ResponseBody
    public AjaxResult updateUser(User user){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
       Integer i=userService.updateUser(user);
       if (i==1){
           result.setSuccess(true);
           result.setMesg("修改成功");
       }else {
           result.setSuccess(false);
           result.setMesg("修改失败");
       }
        return result;
    }
    //用户界面添加用户
    @RequestMapping("/addUser")
    @ResponseBody
    public AjaxResult addUser(User user){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
       Integer i=userService.addUser(user);
       if (i==1){
           result.setSuccess(true);
           result.setMesg("添加成功");
       }else {
           result.setSuccess(false);
           result.setMesg("添加失败");
       }
        return result;
    }
    //用户界面删除用户
    @RequestMapping("/deleteUser")
    @ResponseBody
    public AjaxResult deleteUser(User user){
        AjaxResult result=new AjaxResult();
        //登陆页面精准查询用户名
       Integer i=userService.deleteUser(user.getId());
       if (i==1){
           result.setSuccess(true);
           result.setMesg("删除成功");
       }else {
           result.setSuccess(false);
           result.setMesg("删除失败");
       }
        return result;
    }






























    /**
     * 图片上传
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/file")
    public AjaxResult upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req) {
        AjaxResult result=new AjaxResult();
        result.setCode(0);
        System.out.println(multipartFile);
        // File folder = new File(realPath + format);
        String property = System.getProperty("user.dir");
        System.out.println(property);
        File folder = new File(property+"/src/main/resources/static/products/");
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                return result;
            }
        }
        String oldName = multipartFile.getOriginalFilename();
        assert oldName != null;
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            multipartFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
        System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/files"  + "/" + newName);
        String pictureUrl = "/products"  + "/" + newName;
        result.setCode(1);
        result.setMesg(pictureUrl);
        return result;
    }

}

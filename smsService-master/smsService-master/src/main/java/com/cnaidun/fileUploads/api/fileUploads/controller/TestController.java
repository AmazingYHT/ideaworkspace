package com.cnaidun.fileUploads.api.fileUploads.controller;

import com.cnaidun.fileUploads.api.fileUploads.utils.SMSService;
import com.cnaidun.fileUploads.api.fileUploads.utils.TypeConverterutil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试类
 * Created by sun on 2017-1-14.
 */
@RestController
@RequestMapping("/fileUploads")
public class TestController {

    private static final Logger log = LogManager.getLogger(TestController.class);


    @RequestMapping("/")
    @ResponseBody
    String test(HttpServletRequest req){
        return "Hello World!";
    }

    @RequestMapping("/typeConvertest")
    public void typeConvertest() {
        // 测试从Base64编码转换为图片文件
        String strImg = "iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAFMQAABTEBt+0oUgAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAVUSURBVEiJpZRZbFzVHcZ/5965c2ffV9sz8RjHdpyxweNEoSRN2YJLwxJBqYTahz6URSC1UqtWbSOlIPHQVuoDFeKpaaWqUh+iShRUElUCTBZRm8iBAnHiNfEyZuzxzHjuzHj224fUI4whQeJI5+Ecne/7/b9zjv7ous5XmQ6L/B3Aous6gBHwfBWdxC2G32F8Sgihhm3q8MEu19mQy9zlMCuJvSHbq7fSArcGRJymBx4fDI6ntNrGQ/3+4XjA8reoS33owT3e+zrctju+NqBYbXz0cNw/6DDJz9pVA30h62A8ZPt+PGz3+S3Sk18LYBciYDJIxwC+e3uoz6bK7A3arCN7fJ2VehNZkr4d8qjxm3mI/z8aAG4hXJ0d9uPVejNVrbNyYJfj1/ujzn6X2bBDuFlrIBB8mNQWzs1mXpFkyeU2GzqvrVX+cC1XnNgB2OO3DveErH9+NB4YLFQaldVCpdETsFliu3sJtkcQAj5dWqRer+P1B5ENMqsrSVLJJa6uZOtuk6K7LIpyaSmffHc6c2JiOX8SoFVau8d8/Kn7BweHDhzk3Oio5DRn1L1D+4h2dbeqdnl821IEwu3s7h9AeuffBiEp+uEjI0Q/nGibSZ//AXBy2xu4zIYeh9PF8nKyXtEyisPlJtrVzaamsTB6lvT0TMu4mM2yeP4CxUwG1WQiGuumpOXE5OXLNbvDScCm9gkhrNsSrBWqq1NXr/YaFYMBwOF0AbD+3jjdb15gsTdCPbYLg0Eh+845bjv7AbPfXMP6+DFkWQZgYeoTpdwQxWvZ0qyu68VtCS7NZp94/ePUit6oA2Cx2QGQvG5WQy6KATcGgwJA0+Ug7Xeiu5wA5DLrAOjNJuNz6cql5cJzW76tBBooMa/F9vnf0jacYLOvh5jF2tqL3ns3xf0JonYHpUKBXGadaFc32fQafcGq2zqVbgf+uw0QdZmO7PaZ3VvrxWuzRGJdqCYzZquN02OXSfREqNbqLKU3+EZ/JwCKauTuBx9uJai++U8RshkPAae3ATw2ZcCu3lj6giHaIrsoaHlUkxmASr3BP85+QLFcZX9vtJVGUYykshpehxWDLOH1B/Fa59t2XlG5MVuqNtgVjbD/0Lc+f1McOzhAuVpDMcjIkkQqq3F+Jk3d7OXS5Ay/fSIBQKPRIFuqr+4AzKZLZ+bWN7WhhM8OMH51mcFYAJNRaUHypTLnZ9apmb2UJAs/+c0rqKrKn35/onVmOZUmla+O7QDoun79e0OhRW1jox/AYDTy/MlRjt5zF0I2MDW/yOH7R/jhiWNIksSpU6dQVfWGuJwHTORzWSbmU+nruc13t3y3NbvkRmUyuXidbHqNRMzPzx/dh9UX5ulfvMihkUc4fN8IqqoihGCrxUxdmSSoNgCYn7rC7NrmFV3X178QMLdS/tm5udz8RxPjNJtN+trc7NGX+N2vfoyW30DTtBsiSaLZbJLL5Xj9L3/kzp4w66sp3r74SWY6XXjhs57builAvN3xy6cPtL3Uc1tMjif2tX7Ra2PTlCwBfB1d+IJhzvzrDbqdEo8NRygXNS6Mvs3Lb02ffH8h96ObAoQQ4s5Ox2tPJsJHvXaL3Be/nUhXN0IIACq1OlmtRMjjQNd1UsklLo79h7+OLbw3Op05stUivhSwBRkI247f2+N5Zqjd0WF3OHG6PZitNoQQ1KoVKpUK6dSnTC5nMqcn03+/uJj/qa7r1R1eXwTYGh1u2x0DYfMLQZuxN+Yxd0bcJpMkBMl8pTa3VlpIl2ozHy8XXp7JFE9/mcdNAZ9JJFkVZSDiUY7KQjItbJTPaJv193Vdr91K+z+BklGf3fugXAAAAABJRU5ErkJggg==";
        String str = "";
        // GenerateImage(strImg, "D:\\aaaa.jpg");
        // TypeConverterutil.GenerateImage(strImg);
        // 测试从图片文件转换为Base64编码
        // System.out.println(GetImageStr("D:\\MyFile\\mine\\img\\koala.jpg"));
        String base64str = TypeConverterutil.GetImageStr("D:\\MyFile\\mine\\img\\koala.jpg");
        String newImgpath  = "D:\\MyFile\\mine\\img\\kaola2.jpg";
       // System.out.println(base64str);
        TypeConverterutil.GenerateImage(base64str,newImgpath);
    }

    @RequestMapping("/sendSms")
    public String sendSms() {
        /*smsUtils sms = new smsUtils();
        sms.sendSMS("15251789640");*/
      /*  SMS sms = new SMS(1400114381,"47198744f79472a26834469fc7d5c070");
        sms.sendMsg("86","15251789640","短信测试");*/
      //群发  领取日期 到期日期
        String[] strs = {"15207484416"};//"15874152065","15251789640" "13055167103","15332391940""18651241363","13952979293","17351789293"
        String[] dates = {"2018-1-2","2018-9-12"};
        SMSService.sendMesModel(strs,dates);
        //单发  验证码 时间
       /* String[] code = {"243521","1"};
        SMSService.sendMeModel("13055167103",code);
        String[] code2 = {"243521","1"};
        SMSService.sendMeModel("15332391940",code2);*/
        return "请求成功";
    }
}


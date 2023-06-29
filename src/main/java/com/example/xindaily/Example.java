package com.example.xindaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
        //http://localhost:8080/?age=10&wangxinxin 参数方式
    String home(Integer age,String name) {
        if (name.equals("wangxinxin") && age <= 12) {
            return "儿童节快乐～";
        } else if (name.equals("ht")) {
            return "好好做饭老弟";
        } else {
            return "输入名称试试，输入节日试试呢";
        }
    }

    private String staticPath="/home/img"; //定义上传文件的根目录
    // 这里 @PostMapping("/loadimages")需要和小程序中url的参数一致
    // 这里 @RequestParam("files")需要和小程序中的name保持一致， 这里的file就是参数名称
    @ResponseBody
    @RequestMapping(value ="/loadimages", method = RequestMethod.POST)
    public String uploadImageByVisits(@RequestParam("files") List<MultipartFile> files) {
        if(files==null){}
//            throw new Exception(55500,"file 空");\
        else {
            File targetFile = new File(staticPath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < files.size(); i++) {
                //文件名：随机数+当前时间+格式
                Random r = new Random();
                int randomI = r.nextInt(1000);
                Date date = new Date();
                String fileName = "/" + date.getTime() + randomI + ".jpg";
                String url_path = "/images" + fileName;
                //图片保存路径
                String savePath = staticPath + url_path;
                // 访问路径=静态资源路径+文件目录路径
                String visitPath = "http://host:端口号" + url_path;

                File saveFile = new File(savePath);
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }
                try {
                    files.get(i).transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
                    //压缩
//                    Thumbnails.of(savePath)
//                            .scale(1f)
//                            .outputQuality(0.5f)
//                            .toFile(savePath);
                    stringBuffer.append(visitPath + ",");
                } catch (IOException e) {
//                    throw new RrException(555551, e.getMessage());
                }
            }
            if (stringBuffer.length() <= 1) {
            }
//                throw new RrException(555550,"图片上传异常");
            String url = stringBuffer.substring(0, stringBuffer.length() - 1);
            //url 插入数据库中，读取时则得到url，由逗号隔开来查看图片
        }
        return "success";
    }

    public static void main(String []args){
        SpringApplication.run(Example.class,args);
    }
}
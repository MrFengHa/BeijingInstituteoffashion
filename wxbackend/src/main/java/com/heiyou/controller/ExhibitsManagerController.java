package com.heiyou.controller;

import com.alibaba.druid.util.StringUtils;
import com.heiyou.entity.Exhibits;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/19 22:29
 */
@RestController
@CrossOrigin
@RequestMapping("manager")
public class ExhibitsManagerController {
    @PostMapping("addExhibits")
    public Map<String,Object> addExhibits(Exhibits exhibits, MultipartFile image,
    MultipartFile cnAudio,MultipartFile enAudio) throws IOException {

        System.out.println("exhibits = " + exhibits.toString());

        HashMap<String,Object> map = new HashMap();
        try {
            System.out.println(image.getOriginalFilename());
            System.out.println(cnAudio.getOriginalFilename());
            System.out.println(enAudio.getOriginalFilename());
        }catch (Exception e){

        }


        map.put("result","报名成功");
        return map;

    }

    @PostMapping("imageUpload")
    public Map<String, Object> uploadAttachFile(MultipartFile file,String uploadAttachName) {
        HashMap<String, Object> map = new HashMap<>();
        String dirName = "";
        String filePath = "d:/";
        List<String> accTypes = Arrays.asList(".png", ".jpg",".mp3");
        try {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            /** 校验文件合法性 */
            if (accTypes.contains(suffixName)) {
                System.out.println(suffixName + "后缀合法");
            } else {
                System.out.println(suffixName + "后缀不合法");
                map.put("success", false);
                map.put("message", "上传文件类型有误");
                return map;
            }

            // TODO: 2020/6/20 这里分割符不同系统可能不一样
            suffixName = ".zip";
            String attachName = uploadAttachName;
            if(StringUtils.isEmpty(uploadAttachName)){//用户上传了附件名称，使用用户名称命名
                attachName = fileName.substring(fileName.lastIndexOf("\\")+1);
                attachName = attachName.split("\\.")[0] + suffixName;//使用统一后缀名
            }
            System.out.println("attachName = " + attachName + " || suffixName = " + suffixName);


            // TODO: 2020/6/20 需要使用唯一的标记符来命名附件名称 日期 or teamName ?

            System.out.println("附件路径 = " + filePath);
            File folder = new File(filePath);
            if(!folder.exists()){
                folder.mkdirs();
            }

            File f = new File(filePath + attachName);
            File fileParent = f.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            file.transferTo(f);
            map.put("success", true);
            map.put("message", "success to upload");
            System.out.println("附件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getCause().toString();
            map.put("success", false);
            map.put("message", msg);
        }
        return map;
    }
}

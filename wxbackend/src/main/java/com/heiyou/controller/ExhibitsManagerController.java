package com.heiyou.controller;

import com.heiyou.entity.Exhibits;
import com.heiyou.service.ExhibitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Autowired
    ExhibitsService exhibitsService;

    @PostMapping("deleteExhibits")
    public Map<String, Object> deleteExhibits(@RequestBody Exhibits exhibits) {

        Map<String, Object> map = new HashMap<>();
        try {
            exhibitsService.delete(exhibits.getNumber());
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            return map;
        }
        return map;
    }


    /**
     * 添加展品信息
     *
     * @param exhibits
     * @param image
     * @param cnAudio
     * @param enAudio
     * @return
     * @throws IOException
     */
    @PostMapping("addExhibits")
    public Map<String, Object> addExhibits(
            Exhibits exhibits,
            @RequestParam("image") MultipartFile image,
            @RequestParam MultipartFile cnAudio,
            @RequestParam MultipartFile enAudio) throws IOException {
        HashMap<String, Object> map = new HashMap();

        /**
         * 查看重复元素
         */
        Integer exhibitsNumberCount = exhibitsService.selectOrderByNumberCount(exhibits.getNumber());
        Integer exhibitsNameCount = exhibitsService.selectOrderByNameCount(exhibits.getNumber());
        if (exhibitsNumberCount > 0) {
            map.put("success", "false");
            map.put("msg", "编号不能重复");
            return map;
        }
        if (exhibitsNameCount > 0) {
            map.put("success", "false");
            map.put("msg", "名称不能重复");
            return map;
        }
        //获取绝对路径
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(path.getAbsolutePath());

        //自定义路径
        File tempFile = new File(path.getAbsolutePath(), "static\\" + exhibits.getNumber() + "\\");
        if (!tempFile.isDirectory()) {
            tempFile.mkdirs();
        }
        File imageFile = new File(tempFile + "/image.jpg");
        File cnAudioFile = new File(tempFile + "/cn.mp3");
        File enAudioFile = new File(tempFile + "/en.mp3");

        try {
            image.transferTo(imageFile);
            cnAudio.transferTo(cnAudioFile);
            enAudio.transferTo(enAudioFile);
            System.out.println(new File(tempFile + "/image.jpg").getAbsoluteFile());

            exhibitsService.addExhibits(exhibits);
        } catch (Exception e) {
            e.printStackTrace();
            imageFile.delete();
            cnAudioFile.delete();
            enAudioFile.delete();
            map.put("success", "false");
            return map;
        }
        map.put("success", "true");
        return map;

    }


    @GetMapping("findAll")
    public List<Exhibits> findAll() {

        return exhibitsService.findAll();
    }
}

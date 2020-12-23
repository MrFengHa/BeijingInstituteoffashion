package com.heiyou.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.heiyou.entity.ExhibitionHall;
import com.heiyou.entity.Exhibits;
import com.heiyou.service.ExhibitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 * 展品管理
 * @author 冯根源
 * @date 2020/12/19 22:29
 */
@RestController
@CrossOrigin
@RequestMapping("exhibits")
public class ExhibitsController {
    @Autowired
    ExhibitsService exhibitsService;
    @Value("${myserviceres.path}")
    String serviceResPath;
    @Value("${myservice.path}")
    String resPath;

    @PostMapping("updateExhibits")
    public Map<String, Object> updateExhibits(
            Exhibits exhibits,
            @RequestParam("image") MultipartFile image,
            @RequestParam MultipartFile cnAudio,
            @RequestParam MultipartFile enAudio) {
        Integer exhibitsNameCount = exhibitsService.selectOrderByNameCount(exhibits.getCnName());
        HashMap<String, Object> map = new HashMap();
        if (exhibitsNameCount > 1) {
            map.put("success", false);
            map.put("msg", "名称不能重复");
            return map;
        }
        File tempFile = new File(serviceResPath + exhibits.getNumber());
        File imageFile = new File(tempFile + "/image.jpg");
        File cnAudioFile = new File(tempFile + "/cn.mp3");
        File enAudioFile = new File(tempFile + "/en.mp3");

        try {

            exhibitsService.updateExhibits(exhibits);
            image.transferTo(imageFile);
            cnAudio.transferTo(cnAudioFile);
            enAudio.transferTo(enAudioFile);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", "请联系管理员");
            return map;
        }


        map.put("success", true);
        return map;

    }


    /**
     * 根据id查询
     *
     * @param number
     * @return
     */
    @GetMapping("findById")
    public Exhibits findById(String number) {

        Exhibits exhibits = exhibitsService.findById(number);
        exhibits.setImagePath(resPath + exhibits.getNumber() + "/image.jpg");
        exhibits.setCnAudioPath(resPath + exhibits.getNumber() + "/cn.mp3");
        exhibits.setEnAudioPath(resPath + exhibits.getNumber() + "/en.mp3");

        return exhibits;
    }



    /**
     * 删除展品信息
     *
     * @param exhibits
     * @return
     */
    @PostMapping("deleteExhibits")
    public Map<String, Object> deleteExhibits(@RequestBody Exhibits exhibits) {

        Map<String, Object> map = new HashMap<>();

        try {
            File tempFile = new File(serviceResPath + exhibits.getNumber());
            deleteFile(tempFile);
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

        Integer exhibitsNameCount = exhibitsService.selectOrderByNameCount(exhibits.getCnName());
        if (exhibitsNumberCount > 0) {
            map.put("success", false);
            map.put("msg", "编号不能重复");
            return map;
        }
        if (exhibitsNameCount > 0) {
            map.put("success", false);
            map.put("msg", "名称不能重复");
            return map;
        }

        File tempFile = new File(serviceResPath + exhibits.getNumber());
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

            exhibitsService.addExhibits(exhibits);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            deleteFile(tempFile);
            map.put("success", false);
            map.put("msg", "请联系管理员");
            return map;
        }

        return map;

    }


    @GetMapping("findAll")
    public List<Exhibits> findAll() {


        return exhibitsService.findAll();
    }

    /**
     * 删除文件
     * @param tempFile
     */
    private void deleteFile(File tempFile){
        for (File file : tempFile.listFiles()) {
            file.delete();
        }
        tempFile.delete();
    }
}

package com.heiyou.controller;

import com.heiyou.entity.ExhibitionHall;
import com.heiyou.entity.Exhibits;
import com.heiyou.service.ExhibitionHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 * 展厅管理
 * @Author 冯根源
 * @create 2020/12/23 17:21
 */
@RestController
@CrossOrigin
@RequestMapping("exhibitionHall")
public class ExhibitionHallController {
    @Autowired
    ExhibitionHallService exhibitionHallService;
    @GetMapping("findAll")
    public List<ExhibitionHall> findAll() {

        return exhibitionHallService.findAll();
    }

    /**
     * 添加展厅信息
     * @param exhibitionHall
     * @return
     */
    @PostMapping("addExhibitionHall")
    public Map<String,Object> addExhibitionHall(@RequestBody ExhibitionHall exhibitionHall){
        Map<String,Object> map = new HashMap<>();
        try {
            exhibitionHallService.addExhibitionHall(exhibitionHall);
            map.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("msg","请联系管理员15044561730");
        }

        return  map;
    }

    /**
     * 根据主键查找展厅信息
     * @return
     */
    @GetMapping("findById")
    public ExhibitionHall findById(String id){
        
        return exhibitionHallService.findById(id);
    }

    /**
     * 修改
     * @param exhibitionHall
     * @return
     */
    @PostMapping("updateExhibitionHall")
    public Map<String,Object> updateExhibitionHall(@RequestBody ExhibitionHall exhibitionHall){
        Map<String,Object> map =new HashMap<>();
        try {
            exhibitionHallService.updateExhibitionHall(exhibitionHall);
            map.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("msg","请联系管理员15044561730");
        }

        return map;
    }

    @PostMapping("deleteExhibitionHall")
    public Map<String,Object> deleteExhibitionHall(@RequestBody ExhibitionHall hall){
        Map<String,Object> map = new HashMap<>();
        try {
            exhibitionHallService.deleteExhibitionHall(hall.getId());
            map.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("msg","请联系管理员15044561730");
        }
        return  map;
    }
}

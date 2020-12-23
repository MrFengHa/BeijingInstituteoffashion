package com.heiyou.service;

import com.heiyou.entity.ExhibitionHall;

import java.util.List;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/23 17:32
 */
public interface ExhibitionHallService {
    /**
     * 查找所有展厅
     * @return
     */
    List<ExhibitionHall> findAll();

    /**
     * 添加展厅信息
     * @param exhibitionHall
     */
    void addExhibitionHall(ExhibitionHall exhibitionHall);

    /**
     * 根据id查找
     * @return
     */
    ExhibitionHall findById(String id);

    /**
     * 更新展厅
     * @param exhibitionHall
     */
    void updateExhibitionHall(ExhibitionHall exhibitionHall);

    /**
     * 根据id删除展厅
     * @param id
     */
    void deleteExhibitionHall(String id);
}

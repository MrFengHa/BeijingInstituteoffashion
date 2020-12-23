package com.heiyou.mapper;

import com.heiyou.entity.ExhibitionHall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/23 17:33
 */
@Mapper
public interface ExhibitionHallMapper {
    /**
     * 查找所有展厅
     *
     * @return
     */
    List<ExhibitionHall> findAll();

    /**
     * 添加展厅信息
     *
     * @param exhibitionHall
     */
    void addExhibitionHall(ExhibitionHall exhibitionHall);

    /**
     * 根据主键查找
     * @return
     */
    ExhibitionHall findById(String id);
    /**
     * 更新展厅
     *
     * @param exhibitionHall
     */
    void updateExhibitionHall(ExhibitionHall exhibitionHall);
    /**
     * 根据id删除展厅
     *
     * @param id
     */
    void deleteExhibitionHall(String id);
}

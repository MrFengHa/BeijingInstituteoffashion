package com.heiyou.service.impl;

import com.heiyou.entity.ExhibitionHall;
import com.heiyou.mapper.ExhibitionHallMapper;
import com.heiyou.service.ExhibitionHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/23 17:32
 */
@Service
public class ExhibitionHallServiceImpl implements ExhibitionHallService {
    @Autowired
    ExhibitionHallMapper exhibitionHallMapper;
    /**
     * 查找所有展厅
     *
     * @return
     */
    @Override
    public List<ExhibitionHall> findAll() {
        return exhibitionHallMapper.findAll();
    }

    /**
     * 添加展厅信息
     *
     * @param exhibitionHall
     */
    @Override
    public void addExhibitionHall(ExhibitionHall exhibitionHall) {
        exhibitionHallMapper.addExhibitionHall(exhibitionHall);
    }

    /**
     * 根据id查找
     *
     * @return
     */
    @Override
    public ExhibitionHall findById(String id) {
        return exhibitionHallMapper.findById(id);
    }

    /**
     * 更新展厅
     *
     * @param exhibitionHall
     */
    @Override
    public void updateExhibitionHall(ExhibitionHall exhibitionHall) {
        exhibitionHallMapper.updateExhibitionHall(exhibitionHall);
    }

    /**
     * 根据id删除展厅
     *
     * @param id
     */
    @Override
    public void deleteExhibitionHall(String id) {
        exhibitionHallMapper.deleteExhibitionHall(id);
    }
}

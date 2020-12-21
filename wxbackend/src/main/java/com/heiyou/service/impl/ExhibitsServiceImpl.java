package com.heiyou.service.impl;

import com.heiyou.entity.Exhibits;
import com.heiyou.mapper.ExhibitsMapper;
import com.heiyou.service.ExhibitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/20 14:18
 */
@Service
public class ExhibitsServiceImpl implements ExhibitsService {
    @Autowired
    private ExhibitsMapper exhibitsMapper;
    /**
     * 添加展品
     *
     * @param exhibits
     */
    @Override
    public void addExhibits(Exhibits exhibits) {
        exhibitsMapper.addExhibits(exhibits);
    }

    /**
     * 查找所有
     *
     * @return
     */
    @Override
    public List<Exhibits> findAll() {
        return exhibitsMapper.findAll();
    }

    /**
     * 查找number相同的个数
     *
     * @param number
     * @return
     */
    @Override
    public Integer selectOrderByNumberCount(String number) {
        return exhibitsMapper.selectOrderByNumberCount(number);
    }

    /**
     * 查找name相同的个数
     *
     * @param name
     * @return
     */
    @Override
    public Integer selectOrderByNameCount(String name) {
        return exhibitsMapper.selectOrderByNameCount(name);
    }

    /**
     * 根据编号删除
     *
     * @param number
     * @return
     */
    @Override
    public void delete(String number) {
         exhibitsMapper.delete(number);
    }

    /**
     * 根据编号查询展品信息
     *
     * @param number
     * @return
     */
    @Override
    public Exhibits findById(String number) {
        return exhibitsMapper.findById(number);
    }
}

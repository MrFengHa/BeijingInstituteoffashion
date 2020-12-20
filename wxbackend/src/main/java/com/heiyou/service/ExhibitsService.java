package com.heiyou.service;

import com.heiyou.entity.Exhibits;

import java.util.List;

/**
 * 文件描述
 * 展品服务层
 * @Author 冯根源
 * @create 2020/12/20 14:17
 */
public interface ExhibitsService {
    /**
     * 添加展品
     * @param exhibits
     */
    void addExhibits(Exhibits exhibits);

    /**
     * 查找所有
     * @return
     */
    List<Exhibits> findAll();

    /**
     * 查找number相同的个数
     * @param number
     * @return
     */
    Integer selectOrderByNumberCount(String number);

    /**
     * 查找name相同的个数
     * @param name
     * @return
     */
    Integer selectOrderByNameCount(String name);

    /**
     * 根据编号删除
     * @param number
     * @return
     */
    void delete(String number);
}

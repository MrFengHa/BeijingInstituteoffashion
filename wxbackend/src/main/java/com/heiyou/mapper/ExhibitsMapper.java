package com.heiyou.mapper;

import com.heiyou.entity.Exhibits;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/20 14:00
 */
@Mapper
public interface ExhibitsMapper {
    /**
     * 添加展品信息
     *
     * @param exhibits
     */
    void addExhibits(Exhibits exhibits);

    /**
     * 查找所有展品信息
     *
     * @return
     */
    List<Exhibits> findAll();

    /**
     * 查找number相同的个数
     *
     * @param number
     * @return
     */
    Integer selectOrderByNumberCount(String number);

    /**
     * 查找name相同的个数
     *
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

    /**
     * 根据编号查询展品信息
     * @param number
     * @return
     */
    Exhibits findById(String number);

    /**
     * 更新展品信息
     * @param exhibits
     */
    void updateExhibits(Exhibits exhibits);
}

package com.heiyou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/23 17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ExhibitionHall {
    private String id;
    private String cnName;
    private String enName;

}

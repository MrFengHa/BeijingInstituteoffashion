package com.heiyou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/19 22:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Exhibits {
    private String number;
    private String cnName;
    private String enName;
    private String exhibitionHallId;
    private String cnDesc;
    private String enDesc;
    private String imagePath;
    private String cnAudioPath;
    private String enAudioPath;
    private ExhibitionHall exhibitionHall;
}

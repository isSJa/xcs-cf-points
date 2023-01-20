package com.issja.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * 上传文件到服务器本地静态资源文件位置
 * @Auth ： isSJa
 * @Time ： 2023/1/21 0:45
 **/
public class Upload {
    /**
     *
     * @param file 上传保存的文件
     * @param localFilePath 本地文件位置
     * @return
     */
    public static String uploadFile(MultipartFile file, String localFilePath) {
        Date date=new Date();
        File f = new File(localFilePath, date.toInstant().toString().substring(0,date.toInstant().toString().length()-5)
                + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().length()-4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        try {
            file.transferTo(f); //  保存文件
            return f.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    }
}

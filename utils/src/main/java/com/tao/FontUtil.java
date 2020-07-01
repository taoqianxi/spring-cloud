package com.tao;


import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.File;

public class FontUtil {

    public static Font loadFont(ClassPathResource resource, int style, float size) throws Exception{
        try {
            Font dynamicFont = Font.createFont(Font.PLAIN, resource.getInputStream());
            Font dynamicFontPt = dynamicFont.deriveFont(style,size);
            return dynamicFontPt;
        } catch (Exception e)//异常处理
        {
            e.printStackTrace();
            return new Font("思源黑体", Font.PLAIN, 28);
        }

    }

    public static Font loadFont(File file, int style, float size) throws Exception{
        try {
            Font dynamicFont = Font.createFont(Font.PLAIN, file);
            Font dynamicFontPt = dynamicFont.deriveFont(style,size);
            return dynamicFontPt;
        } catch (Exception e)//异常处理
        {
            e.printStackTrace();
            return new Font("思源黑体", Font.PLAIN, 28);
        }

    }
}

package com.tao;

import lombok.Data;

import java.io.InputStream;

@Data
public class JarBean {
    private InputStream inputStream;
    private String fileName;
}

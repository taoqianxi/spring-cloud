package com.tao;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

public class DozerFactory {
    private String dozerFilePattern = "classpath*:**/dozer-*-mapping.xml";
    private Mapper mapper;

    private static final DozerFactory instance = new DozerFactory();

    public static DozerFactory get() {
        return instance;
    }

    private DozerFactory() {
        try {
            mapper = this.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Mapper getMapper() {
        if (mapper == null) {
            throw new RuntimeException("Pls init dozer first!");
        }
        return mapper;
    }

    private Mapper init() throws IOException {
        DozerBeanMapper mapper = new DozerBeanMapper();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] list = resolver.getResources(dozerFilePattern);
        for (Resource res : list) {
            InputStream is = res.getInputStream();
            mapper.addMapping(is);
            is.close();
        }
        return mapper;
    }


    public static void main(String[] args) {
       DozerFactory dozerFactory =DozerFactory.get();
    }
}

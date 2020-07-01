package com.tao;


import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class RapidService {
    protected Mapper mapper = DozerFactory.get().getMapper();

    public Object copy(Object source, Object destination) {
        if (source == null) {
            return null;
        }
        mapper.map(source, destination);
        return destination;
    }

    public <T> T copy(Object source, Class<T> destClass) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destClass);
    }

    public <T> List<T> copyList(List<?> source, Class<T> destClass) {
        List<T> dest = new ArrayList<T>(source.size());
        for (Object obj : source) {
            dest.add(copy(obj, destClass));
        }
        return dest;
    }

    public boolean isBlank(String src) {
        return src == null || src.trim().length() == 0;
    }
}

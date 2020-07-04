package com.tao;

import java.util.*;

/**
 * tao
 */
public class ListPageUtil<T> {

    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public List<T> startPage(List<T> list, Integer pageNum,
                             Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List<T> pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ListPageUtil<Integer> listPageUtil = new ListPageUtil<>();
        List<Integer> integerList1 = listPageUtil.startPage(integerList, 4, 3);
        integerList1.forEach((v)->System.out.println(v));
    }
}

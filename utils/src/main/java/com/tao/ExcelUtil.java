package com.tao;


import com.dwart.report.finance.common.annotation.ExcelExportAnnotation;
import com.dwart.report.finance.util.ljw.responsibilities.DefaultValueHandler;
import com.dwart.report.finance.util.ljw.responsibilities.FormatHandler;
import com.dwart.report.finance.util.ljw.responsibilities.MergeHandler;
import com.dwart.report.finance.util.ljw.responsibilities.NameKeyHandler;
import com.dwart.report.finance.util.ljw.responsibilities.NullValueHandler;
import com.dwart.report.finance.util.ljw.responsibilities.OrdinaryHandler;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xiaoming
 */
public class ExcelUtil {

    private static HSSFWorkbook wb;

    public static void excelOS(String headStr, List<String> titleVec, int[] titleWidthAry, String[][] bodyAry, java.io.OutputStream os, String sheetName) throws Exception {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("sheet");
            wb.setSheetName(0, sheetName);
            sheet.getPrintSetup().setLandscape(true);// true：横向 false：纵向

            HSSFFont font = wb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 10);

            HSSFFont titleFont = wb.createFont();
            titleFont.setFontHeightInPoints((short) 10);
            titleFont.setFontName("楷体_GB2312");
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // titleFont.setColor(HSSFColor.BLUE.index);
            HSSFCellStyle titleStyle = wb.createCellStyle();
            titleStyle.setFont(titleFont);
            titleStyle.setBorderLeft((short) 1);
            titleStyle.setBorderRight((short) 1);
            titleStyle.setBorderTop((short) 1);
            titleStyle.setBorderBottom((short) 1);
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            titleStyle.setWrapText(true);
            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

            HSSFCellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setBorderLeft((short) 1);
            style.setBorderRight((short) 1);
            style.setBorderTop((short) 1);
            style.setBorderBottom((short) 1);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setWrapText(true);

            HSSFFont headFont = wb.createFont();
            headFont.setFontHeightInPoints((short) 18);
            headFont.setFontName("楷体_GB2312");
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

            HSSFCellStyle headStyle = wb.createCellStyle();
            headStyle.setFont(headFont);
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);

            setColumnWidth(sheet, titleWidthAry);

            // 第一行合并
//            sheet.addMergedRegion(new Region(0, (short) 0, 0,
//                    (short) (titleWidthAry.length - 1)));// 合并单元格
//            HSSFRow headRowOne = sheet.createRow((short) 0);
//            HSSFCell headCellOne = headRowOne.createCell((short) 0);
            // headCellOne.setEncoding(HSSFCell.ENCODING_UTF_16);
//            headCellOne.setCellStyle(headStyle);
//            headCellOne.setCellValue(headStr);

            // 数据项描述
            HSSFRow rowTitle = sheet.createRow(0);
            for (int i = 0; i < titleVec.size(); i++) {
                HSSFCell titleCell = rowTitle.createCell((short) i);
                // titleCell.setEncoding(HSSFCell.ENCODING_UTF_16);
                titleCell.setCellValue((String) titleVec.get(i));
                titleCell.setCellStyle(titleStyle);
            }

            int listFlag = 1;
            if (bodyAry != null) {
                for (int i = 0; i < bodyAry.length; i++) {
                    HSSFRow row = sheet.createRow(listFlag);
                    int dataFlag = 0;
                    HSSFCell Contentcell = null;
                    for (int j = 0; j < bodyAry[i].length; j++) {
                        Contentcell = row.createCell(dataFlag);
                        Contentcell.setCellStyle(style);
                        // Contentcell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        Contentcell.setCellValue(bodyAry[i][j]);
                        dataFlag++;
                    }
                    listFlag++;
                }
            }
            wb.write(os);
            os.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String buildMapKey(int i, int j) {
        return i + "_" + j;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell;
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("")) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    private static void setColumnWidth(HSSFSheet sheet, int[] width) {
        for (int i = 0; i < width.length; i++) {
            sheet.setColumnWidth((short) i, (short) (width[i] * 256));
        }
    }

    /**
     * 创造工作簿
     *
     * @param list 你要穿的数据
     * @param <T>  你的具体泛型
     * @return 结果
     */
    @SuppressWarnings("all")
    public static <T> XSSFWorkbook createExcel(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        T t = list.get(0);
        Class<?> aClass = t.getClass();
        Class<?> superclass = aClass.getSuperclass();
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.stream(aClass.getDeclaredFields()).filter(item -> item.isAnnotationPresent(ExcelExportAnnotation.class)).collect(Collectors.toList()));
        fields.addAll(Arrays.stream(superclass.getDeclaredFields()).filter(item -> item.isAnnotationPresent(ExcelExportAnnotation.class)).collect(Collectors.toList()));
        List<Field> collect = fields.stream().sorted(Comparator.comparing(item -> {
            ExcelExportAnnotation annotation = item.getAnnotation(ExcelExportAnnotation.class);

            return annotation.index();
        })).collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();
        // 公共样式
        XSSFCellStyle cellStylePublic = workbook.createCellStyle();

        cellStylePublic.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        cellStylePublic.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStylePublic.setWrapText(true);
        // 标题
        XSSFRow rowTitle = sheet.createRow(0);

        // 创建标题值
        ExcelUtil.createHeaderValue(collect, cellStylePublic, rowTitle);
        int index = 1;
        boolean las = true;
        int startIndex = 1;
        for (T target : list) {
            XSSFRow row = sheet.createRow(index);
            //--- 计算合并的
            boolean merge = false;
            Class<?> eleClass = target.getClass();
            int rowEndIndex = 0;

            Field[] eleDeclaredFields = eleClass.getDeclaredFields();

            // 这个元素的所有属性带注解的
            List<Field> collectTargetAll = Arrays.stream(eleDeclaredFields).filter(item -> {
                item.setAccessible(true);
                return item.isAnnotationPresent(ExcelExportAnnotation.class);
            }).collect(Collectors.toList());
            // 找到要合并的属性
            List<Field> collectTargetField = collectTargetAll.stream().filter(btem -> {
                ExcelExportAnnotation annotation = btem.getAnnotation(ExcelExportAnnotation.class);
                return annotation.merge();
            }).filter(ctem -> {
                try {
                    ctem.setAccessible(true);
                    Object o = ctem.get(target);
                    return o instanceof List;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

            if (!collectTargetField.isEmpty()) {
                // 给合并属性排个序
                List<List> mList = collectTargetField.stream().map(field -> {
                    List o1 = null;
                    try {
                        field.setAccessible(true);
                        o1 = (List) field.get(target);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return o1;
                }).sorted(Comparator.comparing(flist -> flist != null ? flist.size() : 0)).collect(Collectors.toList());

                // 得到需要合并的属性
                collectTargetAll.removeAll(collectTargetField);

                // 得到当前这个元素对象要合并的长度
                rowEndIndex = mList.get(mList.size() - 1).size();

                merge = true;
            }


            for (int i = 0; i < collect.size(); i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(cellStylePublic);

                Field field = collect.get(i);
                field.setAccessible(true);
                ExcelExportAnnotation annotation = field.getAnnotation(ExcelExportAnnotation.class);
                try {
                    Object value = field.get(target);
                    NullValueHandler nullValueNode = new NullValueHandler(annotation, value);
                    // 合并单元格必须传输元素
                    MergeHandler mergeNode = new MergeHandler(annotation, value);
                    FormatHandler formatNode = new FormatHandler(annotation, value);
                    NameKeyHandler nameKeyNode = new NameKeyHandler(annotation, value);
                    DefaultValueHandler defaultValue = new DefaultValueHandler(annotation, value);
                    OrdinaryHandler ordinaryNode = new OrdinaryHandler(annotation, value);
                    nullValueNode.setNextHandler(formatNode);
                    formatNode.setNextHandler(nameKeyNode);
                    nameKeyNode.setNextHandler(defaultValue);
                    defaultValue.setNextHandler(ordinaryNode);
                    ordinaryNode.setNextHandler(mergeNode);
                    mergeNode.setRowEndIndex(rowEndIndex);
                    mergeNode.setCellStylePublic(cellStylePublic);
                    // 开始处理
                    nullValueNode.handlerRequest(cell);

                    if (mergeNode.isJump()) {
                        las = false;
                        index = mergeNode.getRowIndex();
                        mergeNode.setJump(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            // 合并操作
            if (merge) {
                for (Field field : collectTargetAll) {
                    field.setAccessible(true);
                    ExcelExportAnnotation annotation = field.getAnnotation(ExcelExportAnnotation.class);
                    int indexM = annotation.index() - 1;
                    CellRangeAddress region = new CellRangeAddress(startIndex, index - 1, indexM, indexM);
                    sheet.addMergedRegion(region);
                }
                startIndex = index;
                merge = false;
            }
            if (las) {
                index++;
            }
        }
        // 设置自动列宽
        for (int i = 0; i < collect.size(); i++) {
            // 解决中文自动设置列宽出现的问题
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
//
//        // 冻结第一行
//        sheet.createFreezePane( 0, 1, 0, 1 );
        return workbook;

    }

    /**
     * 创建标题值
     *
     * @param collect         属性列表
     * @param cellStylePublic 公共样式
     * @param rowTitle        标题行
     */
    private static void createHeaderValue(List<Field> collect, XSSFCellStyle cellStylePublic, XSSFRow rowTitle) {
        for (int i = 0; i < collect.size(); i++) {
            Field field = collect.get(i);
            field.setAccessible(true);
            ExcelExportAnnotation annotation = field.getAnnotation(ExcelExportAnnotation.class);
            XSSFCell cell = rowTitle.createCell(i);
            cell.setCellStyle(cellStylePublic);
            cell.setCellValue(annotation.title());
        }
    }


}

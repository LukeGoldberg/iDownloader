package org.lashly.service.helper;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ExportExcelUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);

    public void exportExcel(String sheetName, String[] headers, Collection<T> data, OutputStream out) {
        Workbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet;
        if (sheetName == null) {
            sheet = workbook.createSheet("sheet1");
        } else {
            sheet = workbook.createSheet(sheetName);
        }
        CellStyle style = createTitleStyle(workbook);
        DataFormat format = workbook.createDataFormat();
        CellStyle csNum = workbook.createCellStyle();
        csNum.setDataFormat(format.getFormat("0"));
        CellStyle csDecimal = workbook.createCellStyle();
        csDecimal.setDataFormat(format.getFormat("0.00###########"));
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
            sheet.setColumnWidth(i,4000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Iterator<T> it = data.iterator();
        int index = 0;
        T t;
        Method[] fieldGetMethods = new Method[]{};
        Object[] objects = new Object[]{};
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            t = it.next();
            if(index == 1){
                fieldGetMethods = getObjectFieldGetMethod(t);
            }
            Object value;
            Cell cell;
            for (int i = 0; i < fieldGetMethods.length; i++) {
                cell = row.createCell(i);
                try {
                    value = fieldGetMethods[i].invoke(t, objects);
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof Date) {
                        cell.setCellValue(simpleDateFormat.format(value));
                    } else if (value instanceof Long) {
                        cell.setCellStyle(csNum);
                        cell.setCellValue((Long) value);
                    } else if(value instanceof Double) {
                        cell.setCellStyle(csDecimal);
                        cell.setCellValue((Double) value);
                    } else if(value instanceof BigDecimal) {
                        cell.setCellStyle(csDecimal);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellValue(value.toString());
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                workbook.close();
            } catch (IOException ignore) {
            }
        }
    }

    private static CellStyle createTitleStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight((short)700);
        font.setFontHeightInPoints((short)11);
        font.setFontName("Fira Code");
        headerStyle.setFont(font);
        headerStyle.setAlignment((short)2);
        headerStyle.setFillForegroundColor((short)42);
        headerStyle.setFillPattern((short)1);
        headerStyle.setLeftBorderColor((short)8);
        headerStyle.setBorderLeft((short)1);
        headerStyle.setRightBorderColor((short)8);
        headerStyle.setBorderRight((short)1);
        headerStyle.setTopBorderColor((short)8);
        headerStyle.setBorderTop((short)1);
        headerStyle.setBottomBorderColor((short)8);
        headerStyle.setBorderBottom((short)1);
        return headerStyle;
    }

    @SuppressWarnings("unchecked")
    private Method[] getObjectFieldGetMethod(T t){
        Class cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        Method[] fieldGetMethods = new Method[fields.length];
        Class[] classes = new Class[]{};
        Field field;
        String fieldName;
        String getMethodName;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            fieldName = field.getName();
            getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                fieldGetMethods[i] = cls.getMethod(getMethodName, classes);
            } catch (NoSuchMethodException e) {
                logger.error(e.getMessage());
            }
        }
        return fieldGetMethods;
    }
}

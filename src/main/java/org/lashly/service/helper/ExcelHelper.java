package org.lashly.service.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * excel helper
 */
@Component
@Slf4j
public class ExcelHelper {

	/**
	 * generate excel.
	 * caller should deal with the exception.
	 * 
	 * @param dataList data list
	 * @param outputStream stream which excel write to
	 * @param sheetName sheet's name
	 * @param headers excel header'name, should be ordered by field order in the data
	 * @param datePattern date's format pattern
	 * @throws Exception
	 */
    public void generateExcel(List<? extends Serializable> dataList, final OutputStream outputStream, 
    		String sheetName, final String[] headers, final String datePattern) throws Exception {
        final SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        final SXSSFSheet sheet = initialSheet(sheetName, workbook);
        CellStyle style = createTitleStyle(workbook);
        initialHeader(headers, sheet, style);
        if (CollectionUtils.isEmpty(dataList)) {
        	workbook.close();
        	return;
        }
        DataFormat dateFormat = workbook.createDataFormat();
        final CellStyle csNum = workbook.createCellStyle();
        csNum.setDataFormat(dateFormat.getFormat("0"));
        final CellStyle csDecimal = workbook.createCellStyle();
        csDecimal.setDataFormat(dateFormat.getFormat("0.00###########"));
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        dataList.forEach(data -> {
        	int index = atomicInteger.incrementAndGet();
        	Row row = sheet.createRow(index);
            Field[] fields = data.getClass().getDeclaredFields();
            Class[] classes = new Class[]{};
            Object[] objects = new Object[]{};
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Class cls = data.getClass();
                    Method getMethod = cls.getMethod(getMethodName, classes);
                    Object value = getMethod.invoke(data, objects);
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof Date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
                        cell.setCellValue(simpleDateFormat.format(value));
                    } else if (value instanceof Long) {
                        cell.setCellStyle(csNum);
                        cell.setCellValue((Long) value);
                    } else if (value instanceof Double) {
                        cell.setCellStyle(csDecimal);
                        cell.setCellValue((Double) value);
                    } else if (value instanceof BigDecimal) {
                        cell.setCellStyle(csDecimal);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellValue(value.toString());
                    }
                    sheet.flushRows(headers.length);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
                    log.error(e.getMessage(), e);
                }
            }       
        });
        workbook.write(outputStream);
        workbook.close();
    }

    /**
     * initial headers
     * 
     * @param headers header names
     * @param sheet sheet
     * @param style style
     */
	private void initialHeader(final String[] headers, final SXSSFSheet sheet, CellStyle style) {
		Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
            sheet.setColumnWidth(i, 4000);
        }
	}

    /**
     * initial sheet with a name
     * 
     * @param sheetName sheet's name
     * @param workbook workbook
     * @return SXSSFSheet
     */
	private SXSSFSheet initialSheet(String sheetName, final SXSSFWorkbook workbook) {
		final SXSSFSheet sheet;
		if (sheetName == null) {
            sheet = workbook.createSheet("sheet1");
        } else {
            sheet = workbook.createSheet(sheetName);
        }
		return sheet;
	}

    /**
     * configure style
     * 
     * @param workbook excel
     * @return style
     */
	private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight((short) 700);
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Fira Code");
        headerStyle.setFont(font);
        headerStyle.setAlignment((short) 2);
        headerStyle.setFillForegroundColor((short) 42);
        headerStyle.setFillPattern((short) 1);
        headerStyle.setLeftBorderColor((short) 8);
        headerStyle.setBorderLeft((short) 1);
        headerStyle.setRightBorderColor((short) 8);
        headerStyle.setBorderRight((short) 1);
        headerStyle.setTopBorderColor((short) 8);
        headerStyle.setBorderTop((short) 1);
        headerStyle.setBottomBorderColor((short) 8);
        headerStyle.setBorderBottom((short) 1);
        return headerStyle;
    }
	
}

package cn.lz.hospital.utils.file;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExcelImportUtil {


    private static String getValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 解析excel
     *
     * @param begin 表头行号(除标题)
     * @return
     */
    public static ArrayList<Map<String, String>> readExcel(Integer begin,
                                                           InputStream input) {
        ArrayList<Map<String, String>> Row = new ArrayList<Map<String, String>>();

        try {
            Workbook workBook = null;
            try {
                workBook = new XSSFWorkbook(input);
            } catch (Exception ex) {
                workBook = new HSSFWorkbook(input);
            }

            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workBook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }

                Row row1 = sheet.getRow(begin - 1);
                if (row1 == null) {
                    continue;
                }
                // 循环列Cell
                ArrayList<String> title = new ArrayList<String>();
                for (int cellNum = 0; cellNum <= row1.getLastCellNum(); cellNum++) {
                    Cell cell = row1.getCell(cellNum);
                    if (cell == null || "".equals(cell.toString())) {
                        continue;
                    }
                    title.add(getValue(cell));
                }
                // 循环行Row
                for (int rowNum = begin; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }

                    // 循环列Cell
                    Map<String, String> set = new HashMap<String, String>();
                    DecimalFormat df = new DecimalFormat("#");
                    for (int cellNum = 0; cellNum < title.size(); cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        if (cell != null && !"".equals(cell.toString())) {
                            String mobile = cell.toString();
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                                    mobile = df.format(cell.getNumericCellValue());
                                    break;
                                default:
                                    mobile = cell.toString();
                                    break;
                            }
                            cell.setCellValue(mobile);
                        }

                        if (cell == null) {
                            continue;
                        }
                        set.put(title.get(cellNum), getValue(cell));
                    }
                    if (set != null && set.size() > 0) {
                        Row.add(set);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Row = null;
        }

        return Row;
    }
}

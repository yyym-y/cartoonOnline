package org.yyym.back.serve;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.multi.PlayInfoMapper;
import org.yyym.back.util.entity.PlayInfo;

import java.util.List;

@Service
public class DownloadService {

    @Autowired
    private DataService dataService;

    public Workbook getPlayTimeWorkbook() {
        Workbook workbook = initWorkbook(List.of("影视名称", "年", "月", "播放量"));
        List<PlayInfo> data = (List<PlayInfo>) dataService.getPlayLineData().getData();
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 0 ; i < data.size() ; i ++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data.get(i).getCartoon().getCartoonName());
            row.createCell(1).setCellValue(data.get(i).getYear());
            row.createCell(2).setCellValue(data.get(i).getMonth());
            row.createCell(3).setCellValue(data.get(i).getPlayTime());
        }
        return workbook;
    }

    public Workbook getAllRankWorkbook() {
        Workbook workbook = initWorkbook(List.of("影视名称", "总播放量"));
        List<PlayInfo> data = (List<PlayInfo>) dataService.getAllPlayData().getData();
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 0 ; i < data.size() ; i ++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data.get(i).getCartoon().getCartoonName());
            row.createCell(1).setCellValue(data.get(i).getPlayTime());
        }
        return workbook;
    }

    public Workbook getMonthRankWorkbook() {
        Workbook workbook = initWorkbook(List.of("影视名称", "总播放量"));
        List<PlayInfo> data = (List<PlayInfo>) dataService.getMonthPlayData().getData();
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 0 ; i < data.size() ; i ++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data.get(i).getCartoon().getCartoonName());
            row.createCell(1).setCellValue(data.get(i).getPlayTime());
        }
        return workbook;
    }

    public Workbook getYearWorkbook(String year) {
        Workbook workbook = initWorkbook(List.of("影视名称", year + " 年播放量"));
        List<PlayInfo> data = (List<PlayInfo>) dataService.getYearPlayData(year).getData();
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 0 ; i < data.size() ; i ++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data.get(i).getCartoon().getCartoonName());
            row.createCell(1).setCellValue(data.get(i).getPlayTime());
        }
        return workbook;
    }



    private Workbook initWorkbook(List<String> headName) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        cellStyle.setWrapText(true);//自动换行

        Row head = sheet.createRow(0);
        head.setHeight((short) (8 * 20 * 5));
        for(int i = 0 ; i < headName.size() ; i ++) {
            head.createCell(i).setCellValue(headName.get(i));
        }
        return workbook;
    }


    private CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

}

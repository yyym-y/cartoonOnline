package org.yyym.back.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.serve.DownloadService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    private void triggerDownload(HttpServletResponse response, String fileName, Workbook workbook) {
        OutputStream outputStream = null;
        response.reset();
        try {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"),"ISO-8859-1"));
            outputStream =  new BufferedOutputStream(response.getOutputStream());
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {  e.printStackTrace(); }
        }
    }

    @RequestMapping("/getPlayTimeWorkbook")
    public void getPlayTimeWorkbook(HttpServletResponse response) {
        String fileName = "全部影视播放量(年-月) " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".xlsx";
        triggerDownload(response, fileName, downloadService.getPlayTimeWorkbook());
    }

    @RequestMapping("/getAllRankWorkbook")
    public void getAllRankWorkbook(HttpServletResponse response) {
        String fileName = "全部影视播放量(总计) " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".xlsx";
        triggerDownload(response, fileName, downloadService.getAllRankWorkbook());
    }

    @RequestMapping("/getMonthRankWorkbook")
    public void getMonthRankWorkbook(HttpServletResponse response) {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        String fileName = "全部影视播放量(" + year + "-" + month + ") " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".xlsx";
        triggerDownload(response, fileName, downloadService.getMonthRankWorkbook());
    }

    @RequestMapping("/getYearWorkbook")
    public void getYearWorkbook(String year, HttpServletResponse response) {
        String fileName = "全部影视播放量(" + year + ") " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + ".xlsx";
        triggerDownload(response, fileName, downloadService.getYearWorkbook(year));
    }
}

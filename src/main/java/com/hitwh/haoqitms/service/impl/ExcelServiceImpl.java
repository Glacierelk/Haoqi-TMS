package com.hitwh.haoqitms.service.impl;

import com.hitwh.haoqitms.entity.Student;
import com.hitwh.haoqitms.entity.StudentCourse;
import com.hitwh.haoqitms.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public InputStream generateStudentEmailExcel(List<?> students) {
        // 创建一个工作簿
        try (Workbook workbook = new HSSFWorkbook()) {
            // 创建一个工作表
            Sheet sheet = workbook.createSheet("学员邮箱");

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("姓名");
            headerRow.createCell(1).setCellValue("邮箱");

            // 将学员信息添加到行中
            int rowNumber = 1;
            for (Object student : students) {
                Row row = sheet.createRow(rowNumber++);
                if (student instanceof StudentCourse studentCourse) {
                    row.createCell(0).setCellValue(studentCourse.getName());
                    row.createCell(1).setCellValue(studentCourse.getEmail());
                } else if (student instanceof Student student1) {
                    row.createCell(0).setCellValue(student1.getName());
                    row.createCell(1).setCellValue(student1.getEmail());
                } else {
                    return null;
                }
            }

            // 调整列宽以适合内容
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}

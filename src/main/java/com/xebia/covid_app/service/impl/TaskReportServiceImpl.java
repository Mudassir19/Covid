package com.xebia.covid_app.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.covid_app.entities.Task;
import com.xebia.covid_app.repository.TaskManagementRepository;
import com.xebia.covid_app.service.TaskReportService;

@Service
public class TaskReportServiceImpl implements TaskReportService {

	private static final String CLASS_NAME = TaskReportServiceImpl.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	TaskManagementRepository taskRepository;

	@Override
	public void createExcel(List<Task> list, String filePath) throws IOException {

		LOGGER.info("Inside WriteExcel method of:" + CLASS_NAME);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Task_Details");

		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yy h:mm"));

		for (int i = 0; i < list.size(); i++) {

			if (i == 0) {

				HSSFRow rowhead = sheet.createRow((short) 0);
				rowhead.createCell(0).setCellValue("Sr. No.");
				rowhead.createCell(1).setCellValue("Task_ID");
				rowhead.createCell(2).setCellValue("AssignTo");
				rowhead.createCell(3).setCellValue("Frequency");
				rowhead.createCell(4).setCellValue("Area");
				rowhead.createCell(5).setCellValue("Category");
				rowhead.createCell(6).setCellValue("Location");
				rowhead.createCell(7).setCellValue("Status");
				rowhead.createCell(8).setCellValue("TaskCreatedBy");
				rowhead.createCell(9).setCellValue("TaskDescription");
				rowhead.createCell(10).setCellValue("TaskCreationDate");
				rowhead.createCell(11).setCellValue("TaskUpdationDate");
				rowhead.createCell(12).setCellValue("TaskUpdationBy");
				rowhead.createCell(13).setCellValue("Imagepath");
				rowhead.createCell(14).setCellValue("ManPower");
				rowhead.createCell(15).setCellValue("Comments");

			}

			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellValue(i + 1); // Sr. No.
			row.createCell(1).setCellValue(list.get(i).getId());

			row.createCell(2).setCellValue(list.get(i).getAssignTo().getFirstName());

			row.createCell(3).setCellValue(list.get(i).getFrequency().getFrequency());
			row.createCell(4).setCellValue(list.get(i).getArea().getArea());
			row.createCell(5).setCellValue(list.get(i).getCategory().getCategory());
			row.createCell(6).setCellValue(list.get(i).getArea().getLocation().getLocation());
			row.createCell(7).setCellValue(list.get(i).getStatus().getStatus());
			row.createCell(8).setCellValue(list.get(i).getTaskCreatedBy().getFirstName());
			row.createCell(9).setCellValue(list.get(i).getTaskDescription());

			row.createCell(10).setCellStyle(cellStyle);
			row.createCell(10).setCellValue(list.get(i).getTaskCreationDate());
			
			row.createCell(11).setCellStyle(cellStyle);
			row.createCell(11).setCellValue(list.get(i).getTaskUpdationDate());
			

			row.createCell(12).setCellValue(list.get(i).getTaskUpdatedBy().getFirstName());

			row.createCell(13).setCellValue(list.get(i).getImagepath());
			row.createCell(14).setCellValue(list.get(i).getManpower());
			row.createCell(15).setCellValue(list.get(i).getComments());

		}
		FileOutputStream fileOut = new FileOutputStream(filePath);
		workbook.write(fileOut);
		LOGGER.info("File has been written:" + filePath);

		fileOut.close();

	}

	public List<Task> getRecordsBetweenDates(Date startDate, Date endDate) {

		LOGGER.info("getRecordsBetweenDates Service Impl");

		List<Task> listTask = taskRepository.getRecordsBetweenDates(startDate, endDate);

		LOGGER.info("Query Result:" + listTask);

		return listTask;

	}

}

package com.xebia.covid_app.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
				rowhead.createCell(11).setCellValue("TaskDate");
				rowhead.createCell(12).setCellValue("TaskUpdationDate");
				rowhead.createCell(13).setCellValue("TaskUpdationBy");
				rowhead.createCell(14).setCellValue("Imagepath");
				rowhead.createCell(15).setCellValue("ManPower");
				rowhead.createCell(16).setCellValue("Comments");

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

			row.createCell(10).setCellValue(Date2String(list.get(i).getTaskCreationDate()));

			row.createCell(11).setCellValue(Date2String(list.get(i).getTaskDate()));

			row.createCell(12).setCellValue(Date2String(list.get(i).getTaskUpdationDate()));

			row.createCell(13).setCellValue(list.get(i).getTaskUpdatedBy().getFirstName());

			row.createCell(14).setCellValue(list.get(i).getImagepath());
			row.createCell(15).setCellValue(list.get(i).getManpower());
			row.createCell(16).setCellValue(list.get(i).getComments());

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

	private static String Date2String(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");

		String date1 = dateFormat.format(date);
		// LOGGER.info("Converted date to string:" + date1);
		return date1;

	}

	@Override
	public List<Task> getRecords() {

		return taskRepository.findAll();
	}

}

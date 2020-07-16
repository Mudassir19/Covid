package com.xebia.covid_app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.covid_app.entities.Task;
import com.xebia.covid_app.service.TaskReportService;

@CrossOrigin("*")
@RestController
public class TaskReporController {

	private static final String CLASS_NAME = TaskReporController.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	private TaskReportService service;

	@PostMapping(value = "/GenerateReport")
	public ResponseEntity<Object> downlaodExcel(@RequestHeader String date1, @RequestHeader String date2)
			throws IOException {

		LOGGER.info("Inside WriteExcel method of:" + CLASS_NAME);

		LOGGER.info("date1:" + date1);
		LOGGER.info("date2:" + date2);

		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			LOGGER.info("Converted Date1:" + startDate);
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
			LOGGER.info("Converted Date2:" + endDate);
		} catch (ParseException e1) {
			LOGGER.error("Exception occured while converting the date:");
			e1.printStackTrace();
		}

		List<Task> list = service.getRecordsBetweenDates(startDate, endDate);
		LOGGER.info("List of Task is: " + list.toString());
		int totalTask = list.size();
		LOGGER.info("Total Task is:" + totalTask);

		//String filePath = "D:/covidApp/excel/taskRecord.xls";
	
		 File file = File.createTempFile("temp", null);
		 String filePath=file.getAbsolutePath();
		 System.out.println("AbsolutePath"+file.getAbsolutePath());
		
	
		

		service.createExcel(list, filePath);

		//String filename = filePath;
		//File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		HttpHeaders headers = new HttpHeaders();
		// headers.add("Content-Disposition", String.format("filename=\"%s\"",
		// filename));
		headers.add("Content-disposition", "attachment;filename=sample.xls");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

		 file.deleteOnExit();
		return responseEntity;

	}

}

package com.xebia.covid_app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.xebia.covid_app.entities.Task;

public interface TaskReportService {
	
	
	public List<Task> getRecordsBetweenDates(Date startDate,Date endDate);
	
	public  void createExcel(List<Task> list, String filePath) throws IOException;

}

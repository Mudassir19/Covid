package com.xebia.covid_app.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.covid_app.entities.Task;
import com.xebia.covid_app.models.PieChart;
import com.xebia.covid_app.models.Weekday;
import com.xebia.covid_app.repository.TaskManagementRepository;
import com.xebia.covid_app.service.PieChartService;

@Service
public class PieChartServiceImpl implements PieChartService {

	@Autowired
	TaskManagementRepository taskRepository;

	@Override
	public List<PieChart> createStatusList() {
		List<PieChart> statusList = new ArrayList<>();
		List<Task> taskList = taskRepository.findAll();

		int completedCount = 0; // S001
		int upcomingCount = 0; // S002
		int pendingCount = 0; // S003

		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getStatus().getId().equals("S101")) {
				completedCount++;
			} else if (taskList.get(i).getStatus().getId().equals("S102")) {
				upcomingCount++;
			} else if (taskList.get(i).getStatus().getId().equals("S103")
					|| taskList.get(i).getStatus().getId().equals("S104")) {
				pendingCount++;
			}
		}

		PieChart completed = new PieChart("Completed", completedCount);
		PieChart upcoming = new PieChart("Upcoming", upcomingCount);
		PieChart pending = new PieChart("Pending", pendingCount);

		statusList.add(completed);
		statusList.add(upcoming);
		statusList.add(pending);
		return statusList;
	}

	@Override
	public List<Weekday> createGraphStatusList() {
		List<Weekday> weekList = new ArrayList<>();
		List<PieChart> monday = new ArrayList<>();
		List<PieChart> tuesday = new ArrayList<>();
		List<PieChart> wednesday = new ArrayList<>();
		List<PieChart> thursday = new ArrayList<>();
		List<PieChart> friday = new ArrayList<>();
		List<Task> taskList = taskRepository.findAll();
		Calendar current = Calendar.getInstance();
		int comMon = 0, comTue = 0, comWed = 0, comThu = 0, comFri = 0; // S001
		int upcMon = 0, upcTue = 0, upcWed = 0, upcThu = 0, upcFri = 0; // S002
		int penMon = 0, penTue = 0, penWed = 0, penThu = 0, penFri = 0; // S003
		for (int i = 0; i < taskList.size(); i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(taskList.get(i).getTaskDate());
			if (calendar.get(Calendar.WEEK_OF_YEAR) != current.get(Calendar.WEEK_OF_YEAR))
				taskList.remove(taskList.get(i));
		}
		for (int i = 0; i < taskList.size(); i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(taskList.get(i).getTaskDate());
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				if (taskList.get(i).getStatus().getId().equals("S101")) {
					comMon++;
				} else if (taskList.get(i).getStatus().getId().equals("S102")) {
					upcMon++;
				} else if (taskList.get(i).getStatus().getId().equals("S103")
						|| taskList.get(i).getStatus().getId().equals("S104")) {
					penMon++;
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
				if (taskList.get(i).getStatus().getId().equals("S101")) {
					comTue++;
				} else if (taskList.get(i).getStatus().getId().equals("S102")) {
					upcTue++;
				} else if (taskList.get(i).getStatus().getId().equals("S103")
						|| taskList.get(i).getStatus().getId().equals("S104")) {
					penTue++;
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
				if (taskList.get(i).getStatus().getId().equals("S101")) {
					comWed++;
				} else if (taskList.get(i).getStatus().getId().equals("S102")) {
					upcWed++;
				} else if (taskList.get(i).getStatus().getId().equals("S103")
						|| taskList.get(i).getStatus().getId().equals("S104")) {
					penWed++;
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
				if (taskList.get(i).getStatus().getId().equals("S101")) {
					comThu++;
				} else if (taskList.get(i).getStatus().getId().equals("S102")) {
					upcThu++;
				} else if (taskList.get(i).getStatus().getId().equals("S103")
						|| taskList.get(i).getStatus().getId().equals("S104")) {
					penThu++;
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				if (taskList.get(i).getStatus().getId().equals("S101")) {
					comFri++;
				} else if (taskList.get(i).getStatus().getId().equals("S102")) {
					upcFri++;
				} else if (taskList.get(i).getStatus().getId().equals("S103")
						|| taskList.get(i).getStatus().getId().equals("S104")) {
					penFri++;
				}
			}
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		PieChart cMon = new PieChart("Completed", comMon);
		PieChart uMon = new PieChart("Upcoming", upcMon);
		PieChart pMon = new PieChart("Pending", penMon);
		monday.add(cMon);
		monday.add(uMon);
		monday.add(pMon);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		PieChart cTue = new PieChart("Completed", comTue);
		PieChart uTue = new PieChart("Upcoming", upcTue);
		PieChart pTue = new PieChart("Pending", penTue);
		tuesday.add(cTue);
		tuesday.add(uTue);
		tuesday.add(pTue);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		PieChart cWed = new PieChart("Completed", comWed);
		PieChart uWed = new PieChart("Upcoming", upcWed);
		PieChart pWed = new PieChart("Pending", penWed);
		wednesday.add(cWed);
		wednesday.add(uWed);
		wednesday.add(pWed);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		PieChart cThu = new PieChart("Completed", comThu);
		PieChart uThu = new PieChart("Upcoming", upcThu);
		PieChart pThu = new PieChart("Pending", penThu);
		thursday.add(cThu);
		thursday.add(uThu);
		thursday.add(pThu);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		PieChart cFri = new PieChart("Completed", comFri);
		PieChart uFri = new PieChart("Upcoming", upcFri);
		PieChart pFri = new PieChart("Pending", penFri);
		friday.add(cFri);
		friday.add(uFri);
		friday.add(pFri);

		Weekday weekday = new Weekday(monday, tuesday, wednesday, thursday, friday);
		weekList.add(weekday);
		return weekList;
	}

}

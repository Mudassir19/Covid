package com.xebia.covid_app.service;

import java.util.List;

import com.xebia.covid_app.models.PieChart;
import com.xebia.covid_app.models.Weekday;

public interface PieChartService {
    List<PieChart> createStatusList();
    
    List<Weekday> createGraphStatusList();
}

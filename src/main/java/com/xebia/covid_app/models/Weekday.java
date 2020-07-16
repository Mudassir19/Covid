package com.xebia.covid_app.models;

import java.util.List;

public class Weekday {
    private List<PieChart> Mon;
    private List<PieChart> Tue;
    private List<PieChart> Wed;
    private List<PieChart> Thu;
    private List<PieChart> Fri;
    public Weekday(List<PieChart> mon, List<PieChart> tue, List<PieChart> wed, List<PieChart> thu, List<PieChart> fri) {
        Mon = mon;
        Tue = tue;
        Wed = wed;
        Thu = thu;
        Fri = fri;
    }
    public List<PieChart> getMon() {
        return Mon;
    }
    public void setMon(List<PieChart> mon) {
        Mon = mon;
    }
    public List<PieChart> getTue() {
        return Tue;
    }
    public void setTue(List<PieChart> tue) {
        Tue = tue;
    }
    public List<PieChart> getWed() {
        return Wed;
    }
    public void setWed(List<PieChart> wed) {
        Wed = wed;
    }
    public List<PieChart> getThu() {
        return Thu;
    }
    public void setThu(List<PieChart> thu) {
        Thu = thu;
    }
    public List<PieChart> getFri() {
        return Fri;
    }
    public void setFri(List<PieChart> fri) {
        Fri = fri;
    }
}
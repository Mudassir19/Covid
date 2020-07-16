package com.xebia.covid_app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xebia.covid_app.entities.Task;

@Repository
public interface TaskManagementRepository extends JpaRepository<Task, Integer> {
	
	@Query("from Task t where t.taskDate BETWEEN :startDate AND :endDate")
	public List<Task> getRecordsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}

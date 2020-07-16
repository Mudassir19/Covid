package com.xebia.covid_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.covid_app.models.PieChart;
import com.xebia.covid_app.models.UserResponse;
import com.xebia.covid_app.models.Weekday;
import com.xebia.covid_app.service.PieChartService;

@CrossOrigin("*")
@RestController
public class GraphController {

    @Autowired
    PieChartService pieChartService;

    @GetMapping("/getPieChartsValues")
    public ResponseEntity<UserResponse> getPieChartValues(){
        UserResponse userResponse = new UserResponse();
        UserResponse.Payload payload = userResponse.new Payload();
        try{
            List<PieChart> statusList= pieChartService.createStatusList();
            System.out.println(statusList);
            userResponse.setStatus("success");
            userResponse.setMessage("values");
            payload.setObjectList(statusList);
            userResponse.setPayload(payload);
            return ResponseEntity.ok(userResponse);
        }
        catch (Exception e){
            userResponse.setStatus("failure");
            userResponse.setMessage("something went wrong");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
    }
    
    @GetMapping("/getBarGraphValues")
    public ResponseEntity<UserResponse> getBarGraphValues(){
        UserResponse userResponse = new UserResponse();
        UserResponse.Payload payload = userResponse.new Payload();
        try{
            List<Weekday> weekList= pieChartService.createGraphStatusList();
            System.out.println(weekList);
            userResponse.setStatus("success");
            userResponse.setMessage("values");
            payload.setObjectList(weekList);
            userResponse.setPayload(payload);
            return ResponseEntity.ok(userResponse);
        }
        catch (Exception e){
            userResponse.setStatus("failure");
            userResponse.setMessage("something went wrong");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }
    }
    
    
}

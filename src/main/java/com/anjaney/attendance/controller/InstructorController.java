package com.anjaney.attendance.controller;

import com.anjaney.attendance.requestDto.CheckInRequest;
import com.anjaney.attendance.requestDto.CheckOutRequest;
import com.anjaney.attendance.responseDto.MonthlyReport;
import com.anjaney.attendance.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController {
    @Autowired
    private  InstructorService instructorService;



    @PostMapping("/checkIn")
    public ResponseEntity<?> checkIn(@RequestBody CheckInRequest request) {
        try {
            instructorService.checkIn(request);
            return ResponseEntity.ok("Check-Fin successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while checking in: " + e.getMessage());
        }
    }

    @PostMapping("/checkOut")
    public ResponseEntity<String> checkOut(@RequestBody CheckOutRequest checkOutRequest) {
        try {
            instructorService.checkOut(checkOutRequest);
            return ResponseEntity.ok("Check-out successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while checking out: " + e.getMessage());
        }
    }

    @GetMapping("/monthly-report")
    public ResponseEntity<?> getMonthlyReport(@RequestParam("month") int month, @RequestParam("year") int year) {
        try {
            List<MonthlyReport> monthlyReport = instructorService.getMonthlyReport(month, year);
            return ResponseEntity.ok(monthlyReport);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while generating monthly report: " + e.getMessage());
        }
    }
}
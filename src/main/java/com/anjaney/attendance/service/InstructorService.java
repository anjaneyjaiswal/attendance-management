package com.anjaney.attendance.service;

import com.anjaney.attendance.entity.CheckIn;
import com.anjaney.attendance.entity.CheckOut;
import com.anjaney.attendance.entity.Instructor;
import com.anjaney.attendance.repository.CheckInRepository;
import com.anjaney.attendance.repository.CheckOutRepository;
import com.anjaney.attendance.repository.InstructorRepository;
import com.anjaney.attendance.requestDto.CheckInRequest;
import com.anjaney.attendance.requestDto.CheckOutRequest;
import com.anjaney.attendance.responseDto.MonthlyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InstructorService {
    @Autowired
    private  InstructorRepository instructorRepository;
    @Autowired
    private  CheckInRepository checkInRepository;
    @Autowired
    private  CheckOutRepository checkOutRepository;



    public void checkIn(CheckInRequest checkInRequest) {
        Instructor instructor = instructorRepository.findById(checkInRequest.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInTime(checkInRequest.getCheckInTime());
        checkIn.setInstructor(instructor);
        checkInRepository.save(checkIn);
    }

    public void checkOut(CheckOutRequest checkOutRequest) {
        Instructor instructor = instructorRepository.findById(checkOutRequest.getInstructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        CheckOut checkOut = new CheckOut();
        checkOut.setCheckOutTime(checkOutRequest.getCheckOutTime());
        checkOut.setInstructor(instructor);
        checkOutRepository.save(checkOut);
    }

    public List<MonthlyReport> getMonthlyReport(int month, int year) {
        Map<Long, Long> monthlyReport = new HashMap<>();
        List<CheckIn> checkIns = checkInRepository.findByMonthAndYear(month, year);
        List<CheckOut> checkOuts = checkOutRepository.findByMonthAndYear(month, year);

        for (CheckIn checkIn : checkIns) {
            Long instructorId = checkIn.getInstructor().getId();
            LocalDateTime checkInTime = checkIn.getCheckInTime();
            Optional<CheckOut> checkOutTime = getCheckOutTime(checkOuts, instructorId, checkInTime);

            if(checkOutTime.isPresent()){
            checkOuts.remove(checkOutTime.get());
            long hoursWorked = Duration.between(checkInTime, checkOutTime.get().getCheckOutTime()).toHours();
            monthlyReport.put(instructorId, monthlyReport.getOrDefault(instructorId, 0L) + hoursWorked);}
        }

        List<MonthlyReport> responseList = new ArrayList<>();
        for (Map.Entry<Long, Long> entry : monthlyReport.entrySet()) {
            MonthlyReport response = new MonthlyReport();
            response.setInstructorId(entry.getKey());
            response.setWorkedHours(entry.getValue());
            responseList.add(response);
        }
        return responseList;
    }

    private Optional<CheckOut> getCheckOutTime(List<CheckOut> checkOuts, Long instructorId, LocalDateTime checkInTime) {
        for (CheckOut checkOut : checkOuts) {
            if (checkOut.getInstructor().getId().equals(instructorId)) {
                return Optional.of(checkOut);
            }
        }
        return Optional.empty();
    }

}
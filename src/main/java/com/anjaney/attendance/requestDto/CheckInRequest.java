package com.anjaney.attendance.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInRequest {
    private Long instructorId;
    private LocalDateTime checkInTime;
}
package com.anjaney.attendance.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyReport {
    private Long instructorId;
    private Long workedHours;
}

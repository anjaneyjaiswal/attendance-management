package com.anjaney.attendance.repository;

import com.anjaney.attendance.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    @Query("SELECT c FROM CheckIn c WHERE MONTH(c.checkInTime) = :month AND YEAR(c.checkInTime) = :year ORDER BY c.checkInTime")
    List<CheckIn> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
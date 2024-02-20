package com.anjaney.attendance.repository;

import com.anjaney.attendance.entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {
    @Query("SELECT c FROM CheckOut c WHERE MONTH(c.checkOutTime) = :month AND YEAR(c.checkOutTime) = :year ORDER BY c.checkOutTime")
    List<CheckOut> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
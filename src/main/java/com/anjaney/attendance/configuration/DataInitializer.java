package com.anjaney.attendance.configuration;

import com.anjaney.attendance.entity.Instructor;
import com.anjaney.attendance.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InstructorRepository instructorRepository;

    @Autowired
    public DataInitializer(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        instructorRepository.save(Instructor.builder().name("Anjaney").build());
        instructorRepository.save(Instructor.builder().name("Amit").build());
        instructorRepository.save(Instructor.builder().name("Rashmi").build());
        instructorRepository.save(Instructor.builder().name("Rohit").build());
    }
}

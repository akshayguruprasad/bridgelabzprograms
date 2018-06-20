package com.clinicapplication.service;

import java.util.List;

import com.clinicapplication.entity.Doctor;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public interface DoctorService {


    void showDoctors(List<Doctor> allDoctors);

    List<Doctor> searchDoctorsByName(String name,List<Doctor> allDoctors);


    Doctor searchDoctorById(int id,List<Doctor> allDoctors);

    List<Doctor> searchDoctorsBySpeciality(String speciality,List<Doctor> allDoctors);

    List<Doctor> searchDoctorsByAvailability(String availiability,List<Doctor> allDoctors);

}

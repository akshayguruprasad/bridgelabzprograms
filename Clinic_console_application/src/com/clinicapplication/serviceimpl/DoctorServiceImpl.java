package com.clinicapplication.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.clinicapplication.entity.Doctor;
import com.clinicapplication.service.DoctorService;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class DoctorServiceImpl implements DoctorService {

    public DoctorServiceImpl() {// DEFAULT CONSTRUCTOR
	// IF THE FILE IS NOT EMPTY THEN ASSIGN FILE VALUE ELSE ASSIGN THE NEW VALUE

    }

    /*
     * THIS METHOD WILL PRINT ALL THE DOCTORS IN A FORMATTED WAY
     * 
     * @return void
     * 
     * @see com.clinicapplication.service.DoctorService#showDoctors()
     */
    @Override
    public void showDoctors(List<Doctor> allDoctors) {
	System.out.println("-------All Doctors details---------");
	allDoctors.forEach(System.out::println);
	System.out.println("-----------------------------------");

    }

    /*
     * SEARCHES THE DOCTORS BY THEIR NAME
     * 
     * @return List<Doctor>
     * 
     * @see
     * com.clinicapplication.service.DoctorService#searchDoctorsByName(java.lang.
     * String)
     */
    @Override
    public List<Doctor> searchDoctorsByName(String name, List<Doctor> allDoctors) {
	List<Doctor> searchedDoctors = allDoctors.stream().filter(p -> p.getName().equals(name))
		.collect(Collectors.toList());
	return searchedDoctors;
    }

    /*
     * SEARCHES THE DOCTOR BY THEIR ID
     * 
     * @return Doctor
     * 
     * @see com.clinicapplication.service.DoctorService#searchDoctorById(int)
     */
    @Override
    public Doctor searchDoctorById(int id, List<Doctor> allDoctors) {
	Doctor searchedDoctor = allDoctors.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	return searchedDoctor;
    }

    /*
     * SEARCHES THE DOCTOR BY THE SECIALIZATION
     * 
     * @return List<Doctor>
     * 
     * @see
     * com.clinicapplication.service.DoctorService#searchDoctorsBySpeciality(java.
     * lang.String)
     */
    @Override
    public List<Doctor> searchDoctorsBySpeciality(String speciality, List<Doctor> allDoctors) {
	List<Doctor> searchedDoctors = allDoctors.stream().filter(p -> p.getSpecialization().equals(speciality))
		.collect(Collectors.toList());
	return searchedDoctors;
    }

    /*
     * SEARCHES THE DOCTOR BY THE AVAILABILITY
     * 
     * @return List<Doctor>
     * 
     * @see
     * com.clinicapplication.service.DoctorService#searchDoctorsByAvailability(java.
     * lang.String)
     */
    @Override
    public List<Doctor> searchDoctorsByAvailability(String availiability, List<Doctor> allDoctors) {
	List<Doctor> searchedDoctors = allDoctors.stream().filter(p -> p.getAvailability().equals(availiability))
		.collect(Collectors.toList());
	return searchedDoctors;
    }
}

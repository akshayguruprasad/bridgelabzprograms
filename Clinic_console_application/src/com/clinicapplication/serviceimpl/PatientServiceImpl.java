package com.clinicapplication.serviceimpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;
import com.clinicapplication.service.PatientService;
import com.clinicapplication.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class PatientServiceImpl implements PatientService {

    public PatientServiceImpl() {}

    /*
     * (non-Javadoc)
     * 
     * @see com.clinicapplication.service.PatientService#takeAppointment()
     */
    @Override
    public Appointment takeAppointment(Patient patient, Doctor doctor, List<Appointment> appointmentList)
	    throws ParseException {
	Appointment appointment = null;
	/*
	 * System.out.println("Enter the date for which the appointment is requested");
	 * String dateInString = Utility.getUserStringValue();
	 * 
	 * System.out.println(dateInString); Date date =
	 * Utility.convertStringToDate(dateInString); System.out.println(date);
	 */
	Date convertedDate = new Date();

	try {
	    convertedDate = Utility.convertDateFormat(convertedDate, 1);
	} catch (Exception e) {

	    System.out.println(e.getMessage());

	}
	System.out.println(convertedDate);
	while (appointment == null) {
	    System.out.println("Looping in while loop ");
	    appointment = Utility.findAppointment(doctor, convertedDate, appointmentList, patient);

	    convertedDate = Utility.convertDateFormat(convertedDate, 1);

	    System.out.println("Loop value is " + convertedDate);

	}
	System.out.println("Appointment available on " + Utility.formatDates(appointment.getTimeStamp())
		+ " do you want to confirm the appointment [true|false]?");
	boolean answer = Utility.getUserBoolean();
	if (!answer) {
	    System.out.println("Try next time");
	    return null;
	}
	System.out.println("APPOINTMENT");
	System.out.println(appointment);

	doctor.setCountOfPatients(doctor.getCountOfPatients() + 1);
	return appointment;
    }

    /*
     * PRINTS ALL THE PATIENTS DETAILS IN FORMATTED WAY
     * 
     * @see com.clinicapplication.service.PatientService#showPatients()
     */
    @Override
    public void showPatients(List<Patient> allPatients) {
	System.out.println("-------All Patient details---------");
	allPatients.stream().forEach(System.out::println);
	System.out.println("-----------------------------------");
    }

    /*
     * SEARCH THE PATIENT BY THEIR NAME
     * 
     * @see
     * com.clinicapplication.service.PatientService#searchPatientsByName(java.lang.
     * String)
     */
    @Override
    public List<Patient> searchPatientsByName(String name, List<Patient> allPatients) {

	List<Patient> searchedPatients = allPatients.stream().filter(p -> p.getName().equalsIgnoreCase(name))
		.collect(Collectors.toList());
	return searchedPatients;
    }

    /*
     * SEARCH THE PATIENTS BY THE MOBILE NUMBER
     * 
     * @see
     * com.clinicapplication.service.PatientService#searchPatientByMobileNumber(java
     * .lang.String)
     */
    @Override
    public Patient searchPatientByMobileNumber(String mobileNumber, List<Patient> allPatients) {

	Patient searchedPatients = allPatients.stream().filter(p -> p.getMobile().equals(mobileNumber)).findFirst()
		.orElse(null);

	System.out.println(searchedPatients);

	return searchedPatients;

    }

    /*
     * SEARCH THE PATIENTS BY THE ID
     * 
     * @see com.clinicapplication.service.PatientService#searchPatientById(int)
     */
    @Override
    public Patient searchPatientById(int id, List<Patient> allPatients) {
	Patient patient = null;

	patient = allPatients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

	return patient;
    }

    /*
     * SHOW POPULAR DOCTORS ****NEED TESTING**
     * 
     * @see com.clinicapplication.service.PatientService#showPopularDoctor()
     */
    @Override
    public List<Doctor> showPopularDoctor(List<Appointment> allAppointments) {

	List<Doctor> doctorList = null;
	Map<String, List<Appointment>> groupedData = allAppointments.stream()
		.collect(Collectors.groupingBy(p -> p.getDoctorName()));
	List<List<Appointment>> va = groupedData.keySet().stream().map(p -> groupedData.get(p))
		.collect(Collectors.toList());
	int max = 0;

	for (List<Appointment> list : va) {
	    max = max > list.size() ? max : list.size();
	}
	doctorList = new ArrayList<Doctor>();
	for (List<Appointment> list : va) {
	    if (list.size() == max) {

		Doctor doctor = new Doctor();
		doctor.setId(list.get(0).getDoctorId());
		doctor.setName(list.get(0).getDoctorName());
		doctorList.add(doctor);
	    }

	}

	return doctorList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.clinicapplication.service.PatientService#showPopularSpecilization()
     */
    @Override
    public List<Doctor> showPopularSpecilization(List<Appointment> allAppointments) {
	
	List<Doctor> doctorList = null;
	Map<Integer, List<Appointment>> groupedData = allAppointments.stream()
		.collect(Collectors.groupingBy(p -> p.getDoctorId()));
	List<List<Appointment>> va = groupedData.keySet().stream().map(p -> groupedData.get(p))
		.collect(Collectors.toList());
	int max = 0;

	for (List<Appointment> list : va) {
	    max = max > list.size() ? max : list.size();
	}
	doctorList = new ArrayList<Doctor>();
	for (List<Appointment> list : va) {
	    if (list.size() == max) {

		Doctor doctor = new Doctor();
		doctor.setId(list.get(0).getDoctorId());
		doctor.setName(list.get(0).getDoctorName());
		doctorList.add(doctor);
	    }

	}

	
	
	
	return doctorList;
    }
}

package com.clinicapplication.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;
import com.clinicapplication.service.ManagerService;
import com.clinicapplication.util.Utility;
import com.clinicapplication.util.UtilityInterface;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class ManagerServiceImpl implements ManagerService {
    private List<Doctor> allDoctors;
    private List<Patient> allPatients;
    private List<Appointment> allAppointments;

    private final AtomicInteger dId = new AtomicInteger(100);
    private final AtomicInteger pId = new AtomicInteger(1000);

    public List<Doctor> getAllDoctors() {
	return allDoctors;
    }

    public List<Patient> getAllPatients() {
	return allPatients;
    }

    public ManagerServiceImpl() {
	// HER WILL BE THE INIT OF THE IDS
	try {

	    Utility.createFileIfAbsent(UtilityInterface.doctorFile);
	    Utility.createFileIfAbsent(UtilityInterface.appointmentFile);
	    Utility.createFileIfAbsent(UtilityInterface.patientsFile);
	    this.allDoctors = Utility.readFromJSONFile(UtilityInterface.doctorFile, Doctor.class);
	    this.allPatients = Utility.readFromJSONFile(UtilityInterface.patientsFile, Patient.class);
	    this.allAppointments = Utility.readFromJSONFile(UtilityInterface.appointmentFile, Appointment.class);
	    if (this.allDoctors == null) {

		this.allDoctors = new ArrayList<Doctor>();

	    }
	    if (this.allPatients == null) {

		this.allPatients = new ArrayList<Patient>();

	    }
	    if (this.allAppointments == null) {

		this.allAppointments = new ArrayList<Appointment>();

	    }

	    for (Doctor doctor : allDoctors) {

		dId.incrementAndGet();

	    }

	    for (Patient patient : allPatients) {

		pId.incrementAndGet();

	    }

	}

	catch (Exception e) {

	    System.out.println(e.getMessage());

	}

    }

    /*
     * TAKES IN THE DOCTOR OBJECT AND ADDS TO THE ALLDOCTORS LIST
     * 
     * @return boolean
     * 
     * @see
     * com.clinicapplication.service.ManagerService#addDoctor(com.clinicapplication.
     * entity.Doctor)
     */
    @Override
    public boolean addDoctor(Doctor doctor) {
	doctor.setId(dId.incrementAndGet());

	System.out.println("Doctor added is ");

	System.out.println(doctor);
	return this.allDoctors.add(doctor);
    }

    /*
     * UPDATES THE DOCTOR VALUES IN THE ALLDOCTORS LIST
     * 
     * @see com.clinicapplication.service.ManagerService#updateDoctor(com.
     * clinicapplication.entity.Doctor)
     */
    @Override
    public boolean updateDoctor(Doctor doctor) {
	if (doctor == null) {
	    return false;
	}
	this.allDoctors = this.allDoctors.stream().filter(p -> p.getId() == doctor.getId()).map(p -> doctor)
		.collect(Collectors.toList());
	return true;

    }

    /*
     * DELETES THE DOCTOR FROM THE DOCTORS LIST
     * 
     * @see com.clinicapplication.service.ManagerService#deleteDoctor(int)
     */
    @Override
    public Doctor deleteDoctor(int id) {
	Doctor deleteDoctor = null;
	for (int i = 0; i < allDoctors.size(); i++) {

	    if (allDoctors.get(i).getId() == id) {
		deleteDoctor = allDoctors.get(i);

		this.allDoctors.remove(i);
	    }

	}
	return deleteDoctor;
    }

    /*
     * ALL THE PATIENTS TO THE ALLPATIENTS LIST
     * 
     * @see
     * com.clinicapplication.service.ManagerService#addPatient(com.clinicapplication
     * .entity.Patient)
     */
    @Override
    public boolean addPatient(Patient patient) {
	patient.setId(pId.incrementAndGet());
	return this.allPatients.add(patient);

    }

    /*
     * UPDATES THE EXISTING PATIENT VALUE IN THE LIST
     * 
     * @see com.clinicapplication.service.ManagerService#updatePatient(com.
     * clinicapplication.entity.Patient)
     */
    @Override
    public boolean updatePatient(Patient patient) {
	if (patient == null) {
	    return false;
	}
	this.allPatients = this.allPatients.stream().filter(p -> p.getId() == patient.getId()).map(p -> patient)
		.collect(Collectors.toList());
	return true;
    }

    /*
     * DELETE THE PATIENT FROM THE ALLPATIENTS LIST
     * 
     * @see com.clinicapplication.service.ManagerService#deletePatient(int)
     */
    @Override
    public Patient deletePatient(int id) {
	Patient patient = null;
	for (int i = 0; i < allPatients.size(); i++) {

	    if (allPatients.get(i).getId() == id) {
		patient = allPatients.get(i);

		this.allPatients.remove(i);
	    }

	}
	return patient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.clinicapplication.service.ManagerService#getPatients()
     */
    @Override
    public List<Patient> getPatients() {
	return this.allPatients;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.clinicapplication.service.ManagerService#getDoctors()
     */
    @Override
    public List<Doctor> getDoctors() {
	return this.allDoctors;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.clinicapplication.service.ManagerService#getAllAppointments()
     */
    @Override
    public List<Appointment> getAllAppointments() {
	return this.allAppointments;
    }
}

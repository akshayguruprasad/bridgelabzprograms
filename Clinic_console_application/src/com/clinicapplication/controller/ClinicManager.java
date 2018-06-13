package com.clinicapplication.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;
import com.clinicapplication.service.Common;
import com.clinicapplication.service.DoctorService;
import com.clinicapplication.service.ManagerService;
import com.clinicapplication.service.PatientService;
import com.clinicapplication.serviceimpl.DoctorServiceImpl;
import com.clinicapplication.serviceimpl.ManagerServiceImpl;
import com.clinicapplication.serviceimpl.PatientServiceImpl;
import com.clinicapplication.util.Utility;
import com.clinicapplication.util.UtilityInterface;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class ClinicManager implements Common {
    private PatientService patient = new PatientServiceImpl();
    private DoctorService doctor = new DoctorServiceImpl();
    private ManagerService managerService = new ManagerServiceImpl();

    public static void main(String... args)
	    throws JsonGenerationException, JsonMappingException, IOException, ParseException {
	ClinicManager manager = new ClinicManager();
	int choice = 0;
	while (true) {

	    System.out.println("1.Crud Doctor");
	    System.out.println("2.Crud patient");
	    System.out.println("3.desk");
	    choice = Utility.getUserInteger();
	    switch (choice) {
	    case 1:

		manager.crudDoctor(manager.managerService);
		break;
	    case 2:

		manager.crudPatient(manager.managerService);

		break;
	    case 3:
		manager.clinicManagerDesk();
		break;

	    default:

		System.out.println("Invalid choice ");
		break;
	    }
	    System.out.flush();

	}

    }

    /**
     * @param managerService2
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws ParseException
     */
    private void crudPatient(ManagerService manager)
	    throws JsonGenerationException, JsonMappingException, IOException, ParseException {
	Patient patient = null;
	while (true) {
	    System.out.println("1.Add patient");
	    System.out.println("2.Update patient");
	    System.out.println("3.Delete patient");
	    System.out.println("4.List all patient");
	    System.out.println("5.Main menu");

	    switch (Utility.getUserInteger()) {
	    case 1:
		patient = Utility.getpatient();
		if (manager.addPatient(patient)) {
		    System.out.println("patient Created ");
		    System.out.println(patient);
		}

		break;
	    case 2:
		System.out.println("Enter the patient id to update patient");
		patient = this.patient.searchPatientById(Utility.getUserInteger(), manager.getPatients());
		patient = Utility.specificFieldPatientUpdate(patient);
		manager.updatePatient(patient);
		break;
	    case 3:
		System.out.println("Enter the patient id to delete the patient");
		int patientId = Utility.getUserInteger();

		patient = manager.deletePatient(patientId);

		if (patient == null) {

		    System.out.println("No records found for the patient ");
		    break;

		}

		Predicate<Appointment> filter = (data) -> {

		    if (data.getPatientId() == patientId) {
			return true;
		    }
		    return false;

		};
		Utility.removeAppointments(this.managerService.getAllAppointments(), filter);
		System.out.println("Removed all appointments");
		System.out.println("Deleted patient success");
		System.out.println(patient);

		System.out.println("---------------------------------");

		break;
	    case 4:
		this.patient.showPatients(manager.getPatients());
		break;

	    default:
		System.out.println("Going back to main menu");
		main();
		break;
	    }

	    System.out.println("Saving the data");
	    Utility.writeToJSONFile(manager.getAllAppointments(), UtilityInterface.appointmentFile);
	    Utility.writeToJSONFile(manager.getPatients(), UtilityInterface.patientsFile);
	    System.out.flush();
	}
    }

    /**
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws ParseException
     * 
     */
    private void crudDoctor(ManagerService manager)
	    throws JsonGenerationException, JsonMappingException, IOException, ParseException {
	Doctor doctor = null;
	while (true) {

	    System.out.println("1.Add Doctor");
	    System.out.println("2.Update Doctor");
	    System.out.println("3.Delete Doctor");
	    System.out.println("4.List all doctors");
	    System.out.println("5.Main menu");

	    switch (Utility.getUserInteger()) {
	    case 1:
		doctor = Utility.getDoctor();
		if (manager.addDoctor(doctor)) {
		    System.out.println("Doctor Created ");
		    System.out.println(doctor);
		}

		break;
	    case 2:
		System.out.println("Enter the doctor id to update doctor");

		int id = Utility.getUserInteger();
		doctor = this.doctor.searchDoctorById(id, manager.getDoctors());
		if (doctor == null) {
		    System.out.println("No doctor found with id " + id + " to update ");
		    break;
		}
		doctor = Utility.specificFieldDoctorUpdate(doctor);
		manager.updateDoctor(doctor);
		break;
	    case 3:
		System.out.println("Enter the doctor id to delete the doctor");
		int doctorId = Utility.getUserInteger();

		doctor = manager.deleteDoctor(doctorId);

		if (doctor == null) {

		    System.out.println("No records found for the doctor ");
		    break;

		}

		Predicate<Appointment> filter = (data) -> {

		    if (data.getDoctorId() == doctorId) {
			return true;
		    }
		    return false;

		};
		Utility.removeAppointments(this.managerService.getAllAppointments(), filter);
		System.out.println("Removed all appointments");
		System.out.println("Deleted doctor value");
		System.out.println(doctor);
		break;
	    case 4:

		System.out.println("--------------------All Doctors--------------------");
		manager.getDoctors().forEach(System.out::println);
		System.out.println("---------------------------------------------------");
		break;

	    default:
		System.out.println("Going back to main menu");
		main();
		break;
	    }

	    System.out.println("Saving the data");
	    Utility.writeToJSONFile(manager.getAllAppointments(), UtilityInterface.appointmentFile);
	    Utility.writeToJSONFile(manager.getDoctors(), UtilityInterface.doctorFile);
	    System.out.flush();
	}
    }

    /**
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     * @throws ParseException
     * 
     */
    private void clinicManagerDesk() throws JsonGenerationException, JsonMappingException, IOException, ParseException {

	Patient patientDetails = null;
	while (true) {

	    System.out.println("1.take appointment \n" + "2.Show all patients \n" + "3 Show patients by name \n"
		    + "4. Show patients by mobile number \n" + "5. Show patients by ID \n" + "6 showPopularDoctor \n"
		    + "7.showPopularSpecilization\n");

	    int choice = Utility.getUserInteger();

	    switch (choice) {
	    case 1:
		System.out.println("Taking appointment");
		System.out.println("Enter the patient id");
		Patient searchedPatient = patient.searchPatientById(Utility.getUserInteger(),
			managerService.getPatients());
		if (searchedPatient == null) {
		    System.out.println("invalid patient id");
		    break;
		}
		System.out.println("Enter the doctor id");
		Doctor searchedDoctor = doctor.searchDoctorById(Utility.getUserInteger(), managerService.getDoctors());
		if (searchedDoctor == null) {
		    System.out.println("invalid doctor id");
		    break;
		}
		Appointment app = patient.takeAppointment(searchedPatient, searchedDoctor,
			this.managerService.getAllAppointments());
		if (app != null) {
		    this.managerService.getAllAppointments().add(app);
		    System.out.println("Created success appointment");
		}

		break;
	    case 2:
		patient.showPatients(managerService.getPatients());
		break;
	    case 3:
		System.out.println("Enter the patient name");
		String name = Utility.getUserStringValue();
		List<Patient> allpatients = patient.searchPatientsByName(name, managerService.getPatients());
		if (allpatients == null) {
		    System.out.println("No records found ");
		    break;
		}
		allpatients.stream().forEach(System.out::println);
		break;
	    case 4:
		System.out.println("Enter the patient Mobile number");
		String mobileNumber = Utility.getUserStringValue();
		patientDetails = patient.searchPatientByMobileNumber(mobileNumber, managerService.getPatients());
		if (patientDetails == null) {
		    System.out.println("No records found ");
		    break;
		}
		System.out.println(patientDetails);
		break;
	    case 5:
		System.out.println("Enter the patient id");
		int id = Utility.getUserInteger();
		patientDetails = patient.searchPatientById(id, managerService.getPatients());
		if (patientDetails == null) {
		    System.out.println("No records found ");
		    break;
		}
		System.out.println(patientDetails);
		break;
	    case 6:

		List<Doctor> doctor = patient.showPopularDoctor(this.managerService.getAllAppointments());
		if (doctor == null) {
		    System.out.println("No doctors has patients");
		    break;
		}
		doctor.forEach(p -> {
		    System.out.println(p.getId());
		    Doctor doctorItem = this.doctor.searchDoctorById(p.getId(), managerService.getDoctors());
		    System.out.println(doctorItem);
		});

		break;
	    case 7:

		List<Doctor> specialization = patient
			.showPopularSpecilization(this.managerService.getAllAppointments());
		if (specialization == null) {
		    System.out.println("No doctors has patients");
		    break;
		}

		Iterator<Doctor> iterator = specialization.iterator();

		System.out.println("Most reputed Department in the hospital  : ");
		while (iterator.hasNext()) {
		    Doctor doctorItem = iterator.next();
		    doctorItem = this.doctor.searchDoctorById(doctorItem.getId(), this.managerService.getDoctors());
		    System.out.println(doctorItem);
		}

		break;

	    default:

		System.out.println("Invalid choice chosen");
		main();
	    }
	    System.out.println("Saving the data");
	    Utility.writeToJSONFile(this.managerService.getAllAppointments(), UtilityInterface.appointmentFile);
	    System.out.flush();
	}
    }
}

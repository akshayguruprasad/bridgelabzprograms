package com.clinicapplication.util;

import java.io.File;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 11-Jun-2018
 */

public interface UtilityInterface {
    String PATH = "/home/bridgeit/AkshayWork/Clinic_console_application/src/com/clinicapplication/resources";
    String DOCTOR_FILE = "/Doctors.json";
    String PATIENTS_FILE = "/Patients.json";
    File doctorFile = new File(PATH + DOCTOR_FILE);
    File patientsFile = new File(PATH + PATIENTS_FILE);
    String DATEPATTERN = "dd/MM/yyyy";
    String APPOINTMENT_FILE = "/Appointments.json";
    File appointmentFile = new File(PATH + APPOINTMENT_FILE);
    long LIMIT = 5;
}

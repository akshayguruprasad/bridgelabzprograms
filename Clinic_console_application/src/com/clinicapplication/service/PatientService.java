package com.clinicapplication.service;

import java.text.ParseException;
import java.util.List;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public interface PatientService {

    Appointment takeAppointment(Patient patient,Doctor doctor,List<Appointment> appointmentList) throws ParseException;

    void showPatients(List<Patient> allPatients);

    List<Patient> searchPatientsByName(String name,List<Patient> allPatients);

    Patient searchPatientByMobileNumber(String mobileNumber,List<Patient> allPatients);

    Patient searchPatientById(int id,List<Patient> allPatients);

    List<Doctor> showPopularDoctor(List<Appointment> allAppointments);

    List<Doctor> showPopularSpecilization(List<Appointment> allAppointments);

}

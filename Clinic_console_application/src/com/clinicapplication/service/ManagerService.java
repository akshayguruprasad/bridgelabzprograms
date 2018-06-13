package com.clinicapplication.service;

import java.util.List;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public interface ManagerService {
    boolean addDoctor(Doctor doctor);

    boolean updateDoctor(Doctor doctor);

    Doctor deleteDoctor(int id);

    boolean addPatient(Patient patient);

    boolean updatePatient(Patient patient);

    Patient deletePatient(int id);

    List<Patient> getPatients();

    List<Doctor> getDoctors();
    List<Appointment> getAllAppointments();

}

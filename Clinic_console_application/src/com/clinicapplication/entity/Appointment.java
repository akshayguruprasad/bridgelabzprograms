package com.clinicapplication.entity;

import java.io.Serializable;
import java.util.Date;

import com.clinicapplication.util.Utility;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class Appointment implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String patientName;
    private String doctorName;
    private int doctorId;
    private int patientId;
    private Date timeStamp;
    private String available;
    
    
    public Appointment() {}
    public Appointment(Doctor doctor,Patient patient,Date date) {
	
	
	this.available=doctor.getAvailability();
	this.doctorId=doctor.getId();
	this.doctorName=doctor.getName();
	this.patientId=patient.getId();
	this.patientName=patient.getName();
	this.timeStamp=date;
	
	
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public String getAvailable() {
        return available;
    }
    public void setAvailable(String available) {
        this.available = available;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    @Override
    public String toString() {
	return String.format("	 Apointment Set  \nDoctor name \n%s\nTime        : %-10s\nDate        : %10s\nPatient\n%10s", doctorName, available,
		Utility.dateFormatter.format(timeStamp).toString(),patientName);

    }


}

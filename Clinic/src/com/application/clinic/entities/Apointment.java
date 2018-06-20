package com.application.clinic.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.application.clinic.intefaces.Common;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 05-Jun-2018
 */

public class Apointment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private SimpleDateFormat format = new SimpleDateFormat(Common.DDMMYYYY);
    private Doctor doctor;
    private String time;
    private Date apointmentDate;
    private Patient patient;
    private String pateientName;

    public String getPateientName() {
	return pateientName;
    }

    public void setPateientName(String pateientName) {
	this.pateientName = pateientName;
    }

    /**
     * @param id
     * @param apointmentDate
     * @param clinic
     */
    public Apointment() {
    }

    public Apointment(int id, Date apointmentDate, Clinic clinic, Patient patient) {
	this.doctor = clinic.getDoctor(id);
	this.apointmentDate = apointmentDate;
	this.time = clinic.getDoctor(id).getAvaliable();
	this.patient = patient;

    }

    public Date getApointmentDate() {
	return apointmentDate;
    }

    public void setApointmentDate(Date apointmentDate) {
	this.apointmentDate = apointmentDate;
    }

    public Doctor getDoctor() {
	return doctor;
    }

    public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    @Override
    public String toString() {
	return String.format("	 Apointment Set  \nDoctor\n%s\nTime        : %-10s\nDate        : %10s\nPatient\n%10s",
		doctor, time, format.format(apointmentDate).toString(), patient.toString());

    }

}

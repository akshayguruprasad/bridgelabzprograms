package com.clinicapplication.util;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.clinicapplication.entity.Appointment;
import com.clinicapplication.entity.Doctor;
import com.clinicapplication.entity.Patient;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 09-Jun-2018
 */

public class Utility {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ObjectMapper mapper = new ObjectMapper();
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(UtilityInterface.DATEPATTERN);
    private static Calendar calendar = Calendar.getInstance();

    /**
     * @return
     * 
     */
    public static int getUserInteger() {
	int data = scanner.nextInt();

	scanner.nextLine();
	return data;

    }

    /**
     * @return
     * 
     */
    public static String getUserStringValue() {
	return scanner.nextLine();
    }

    public static Patient getpatient() {

	Patient patient = new Patient();

	System.out.println("Enter patient name");
	patient.setName(Utility.getUserStringValue());
	System.out.println("Enter patient mobile number");
	patient.setMobile(Utility.getUserStringValue());
	System.out.println("Enter patient age");
	patient.setAge(Utility.getUserByte());
	return patient;

    }

    /**
     * @return
     */
    public static byte getUserByte() {
	byte data = scanner.nextByte();

	scanner.nextLine();
	return data;

    }

    /**
     * @return
     */
    public static Doctor getDoctor() {
	Doctor doctor = new Doctor();
	System.out.println("Enter Doctor name");
	doctor.setName(Utility.getUserStringValue());
	System.out.println("Enter Doctor Specialization");
	doctor.setSpecialization(Utility.getUserStringValue());
	System.out.println("Doctor Availability ");
	doctor.setAvailability(Utility.getUserStringValue());

	return doctor;
    }

    public static <T> void writeToJSONFile(List<T> list, File file)
	    throws JsonGenerationException, JsonMappingException, IOException {
	mapper.writeValue(file, list);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readFromJSONFile(File file, Class<T> className)
	    throws JsonParseException, JsonMappingException, IOException {
	try {
	    return (List<T>) mapper.readValue(file,
		    mapper.getTypeFactory().constructCollectionType(List.class, className));
	} catch (EOFException e) {

	    return null;

	} catch (Exception e) {
	    throw e;
	}
    }

    /**
     * @param doctor
     * @return
     */
    public static Doctor specificFieldDoctorUpdate(Doctor doctor) {

	System.out.println("Doctor details ");
	System.out.println(doctor);
	System.out.println("Enter the field to update ");
	String field = Utility.getUserStringValue().toLowerCase();
	System.out.println("Enter the value for " + field);
	String updateData = Utility.getUserStringValue();
	switch (field) {
	case "name":
	    doctor.setName(updateData);

	    break;
	case "availability":
	    doctor.setAvailability(updateData);

	    break;
	case "specilization":
	    doctor.setSpecialization(updateData);

	    break;

	default:
	    System.out.println("No field found " + field);
	    break;
	}

	return doctor;
    }

    /**
     * @param patient
     * @return
     */
    public static Patient specificFieldPatientUpdate(Patient patient) {

	System.out.println("patient details ");
	System.out.println(patient);
	System.out.println("Enter the field to update ");
	String field = Utility.getUserStringValue().toLowerCase();
	System.out.println("Enter the value for " + field);
	String updateData = Utility.getUserStringValue();
	switch (field) {
	case "name":
	    patient.setName(updateData);

	    break;
	case "mobile":
	    patient.setMobile(updateData);

	    break;
	case "age":
	    patient.setAge(Byte.parseByte(updateData));

	    break;

	default:
	    System.out.println("No field found " + field);
	    break;
	}

	return patient;

    }

    public static String dateFormatted() throws ParseException {
	System.out.println("Enter the date in dd/mm/yyyy");
	String date = Utility.getUserStringValue();
	return date = dateFormatter.format(dateFormatter.parse(date));

    }

    public static void createFileIfAbsent(File file) throws IOException {
	if (!file.exists()) {
	    file.createNewFile();

	}

    }

    public static Date convertDateFormat(Date date, int increment) throws ParseException {
	calendar.setTime(date);
	calendar.add(calendar.get(Calendar.DATE), increment);
	String dateFormatted = dateFormatter.format(calendar.getTime());
	return dateFormatter.parse(dateFormatted);
    }

    public static Date convertStringToDate(String dateString) throws ParseException {

	return dateFormatter.parse(dateString);

    }

    /**
     * @param id
     * @param convertedDate
     * @param appointmentList
     * @return
     * 
     */
    public static Appointment findAppointment(Doctor doctor, Date convertedDate, List<Appointment> appointmentList,Patient patient ){

	Appointment appointment = null;

	long totalAppointments = appointmentList.stream().filter(p -> {
	    {

		if (p.getDoctorId() == doctor.getId()) {
		    if (p.getTimeStamp().equals(convertedDate)) {
			return true;
		    }

		}

		return false;

	    }
	}).count();

	if (totalAppointments < UtilityInterface.LIMIT) {
	    appointment = new Appointment(doctor,patient,convertedDate);

	    appointment.setTimeStamp(convertedDate);
	}

	return appointment;

    }

    public static String formatDates(Date date) {

	return dateFormatter.format(date);

    }

    /**
     * @return
     * 
     */
    public static boolean getUserBoolean() {
	boolean data = scanner.nextBoolean();
	scanner.nextLine();
	return data;
    }

    /**
     * @param allAppointments
     * 
     */
    public static void removeAppointments(List<Appointment> allAppointments, Predicate<Appointment> function) {
	if (allAppointments.size() > 0) {
	    List<Appointment> removableAppointments = allAppointments.stream().filter(function)
		    .collect(Collectors.toList());
	    allAppointments.removeAll(removableAppointments);
	}
    }
    
    
    
}

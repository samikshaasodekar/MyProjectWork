package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doctorId")
	private int doctorId;
	
	@Column(name="name")
	@NotBlank(message="Name cannot be blank")
	@Size(min=2,max=30,message="Name must be between 2 and 30 characters")
	@Pattern(regexp="^[a-zA-Z\\s]+$", message="Name must contain only letters and spaces")
	private String name;
	
    @Column(name = "specialization")
    @NotBlank(message = "Specialization cannot be blank")
    @Size(min = 2, max = 50, message = "Specialization must be between 2 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Specialization must contain only letters and spaces")
	private String specialization;
	
    @Column(name = "contact_number")
    @NotBlank(message = "Contact number cannot be blank")
    @Pattern(regexp = "\\d+", message = "Contact number must be digits only")
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits")
	private String contactNumber;
	
    @Column(name = "availability_schedule")
    @NotBlank(message = "Availability schedule cannot be blank")
	@Lob
	private String availabilitySchedule;
	
	//Bidirectional:One doctor → many appointments
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAvailabilitySchedule() {
		return availabilitySchedule;
	}
	public void setAvailabilitySchedule(String availabilitySchedule) {
		this.availabilitySchedule = availabilitySchedule;
	}
	
	public Doctor(String name, String specialization, String contactNumber, String availabilitySchedule) {
		super();
		//this.doctorId = doctorId;
		this.name = name;
		this.specialization = specialization;
		this.contactNumber = contactNumber;
		this.availabilitySchedule = availabilitySchedule;
	}
	
	public Doctor() {

	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", specialization=" + specialization
				+ ", contactNumber=" + contactNumber + ", availabilitySchedule=" + availabilitySchedule + "]";
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}






package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
	
    private String appointmentDate;
    private String timeSlot;
    private String status;
    
//  Bidirectional: Many appointments → one doctor
    @ManyToOne
/* No, you should not put cascade on the @ManyToOne side in this case.
 * The owning side is Appointment, but the parent is Doctor. You 
 * typically apply cascade on the parent side (Doctor) to control what
 * happens to its children (Appointment). */
    @JoinColumn(name="doctorId")
    private Doctor doctor;

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
















package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}






package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Doctor;

//One Repository for Unidirectional

//Two Repository for Bidirectional
/*Doctor knows about its appointments (List<Appointment> appointments)
Appointment knows which doctor it belongs to (Doctor doctor)*/

//Pagination,sorting : JpaRepository
//curdRepository:basic 

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    
}





package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Doctor;
import com.example.demo.repo.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor add(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> getAll(){
		return doctorRepository.findAll();
	}
	
	public Doctor getById(int id) {
		return doctorRepository.findById(id).orElse(null);
	}
	
	public Doctor update(int id, Doctor doctorDetails) {
		Doctor doctor = doctorRepository.findById(id).orElse(null);
		if(doctor!=null) {
			doctor.setName(doctorDetails.getName());
			doctor.setSpecialization(doctorDetails.getSpecialization());
			doctor.setContactNumber(doctorDetails.getContactNumber());
			doctor.setAvailabilitySchedule(doctorDetails.getAvailabilitySchedule());
			return doctorRepository.save(doctor);
		}
		return null;	
	}
}










package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/add")
	public String showAddDoctorForm(Model model) {
		model.addAttribute("doctor", new Doctor());
		return "add-doctor";		
	}

	@PostMapping("/add")
	public String addDoctor(@Valid @ModelAttribute Doctor doctor,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-doctor";
		}
		Doctor saved = doctorService.add(doctor);
		model.addAttribute("doctor",saved);
		model.addAttribute("message", "Doctor added successfully!!!");
		return "success";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateDoctorForm(@PathVariable("id") int doctorId, Model model) {
		Doctor doctor = doctorService.getById(doctorId);
		if(doctor != null) {
			model.addAttribute("doctor",doctor);
			return "update-doctor";
		}else {
			model.addAttribute("error", "Doctor not found");
			return "error";
		}
	}
	
	@PostMapping("/update/{id}")
	public String updateDoctor(@PathVariable int id,
	                           @Valid @ModelAttribute Doctor doctor,   // <-- ADD @Valid
	                           BindingResult result,                   // <-- ADD BindingResult
	                           Model model) {
	    if (result.hasErrors()) {   
	        return "update-doctor"; 
	    }
	    Doctor updated = doctorService.update(id, doctor);
	    if (updated == null) {
	        model.addAttribute("error", "Doctor not found for update");
	        return "error";
	    }
	    model.addAttribute("doctor", updated);
	    model.addAttribute("message", "Doctor updated successfully!");
	    return "success";
	}

	
	@GetMapping("/{id}")
	public String getDoctorDetails(@PathVariable("id") int doctorId, Model model) {
		Doctor doctor = doctorService.getById(doctorId);
		if(doctor != null) {
			model.addAttribute("doctor" ,doctor);
			return "doctor-details";			
		}else {
			model.addAttribute("error","Doctor not found");
			return "error";
		}
	}
}












package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.repo.AppointmentRepository;
import com.example.demo.service.DoctorService;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner{
//public class SpringDataJpaApplication{	
	@Autowired
	DoctorService doctorService;	
	@Autowired
	AppointmentRepository appointmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);		
	}

//Table created!!!
//	@Override
//	public void run(String... args) throws Exception {
//		Doctor doctor = new Doctor();
//		doctor.setName("S");
//		doctor.setSpecialization("H");
//		doctor.setContactNumber("9509567849");
//		doctor.setAvailabilitySchedule("Mon");
//		doctorService.add(doctor);
//	}
	
//	@Override
//	public void run(String... args) throws Exception {
//		Doctor doctor = new Doctor("Dr. New", "Dermatology", "9509567849", "Mon-Fri");
//		doctorService.add(doctor);
//		
//		Appointment appointment = new Appointment();
//		appointment.setAppointmentDate("2025-10-08");
//        appointment.setTimeSlot("10:00 AM");
//        appointment.setStatus("CONFIRMED");
//        appointment.setDoctor(doctor); //foreign key linked
//        appointmentRepository.save(appointment);
//        System.out.println("Appointment saved with foreign key to Doctor ID: " + doctor.getDoctorId());
//	}
}









<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Add Doctor</title>
</head>
<body>
<h2>Add Doctor</h2>

<form th:action="@{/doctors/add}" th:object="${doctor}" method="post">

<label>Name:</label>
<input type="text" th:field="*{name}" />   
<span th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></span><br/>  

<label>Specialization:</label>
<input type="text" th:field="*{specialization}" />   
<span th:if="${#fields.hasErrors('specialization')}" th:errors="*{specialization}"></span><br/> 

<label>Contact Number:</label>
<input type="text" th:field="*{contactNumber}" />   
<span th:if="${#fields.hasErrors('contactNumber')}" th:errors="*{contactNumber}"></span><br/>  

<label>Availability Schedule:</label>
<input type="text" th:field="*{availabilitySchedule}" />  
<span th:if="${#fields.hasErrors('availabilitySchedule')}" th:errors="*{availabilitySchedule}"></span><br/>   

<button type="submit">Add Doctor</button>

</form>
</body>
</html>






<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Doctor Details</title>
</head>
<body>
<h2>Doctor Details</h2>
<p><strong>ID:</strong> <span th:text="${doctor.doctorId}"></span></p>
<p><strong>Name:</strong> <span th:text="${doctor.name}"></span></p>
<p><strong>Specialization:</strong> <span th:text="${doctor.specialization}"></span></p>
<p><strong>Contact Number:</strong> <span th:text="${doctor.contactNumber}"></span></p>
<p><strong>Availability:</strong> <span th:text="${doctor.availabilitySchedule}"></span></p>
</body>
</html>









<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
<h2>Error</h2>
<p th:text="${error}">Something went wrong</p>
<a th:href="@{/doctors/add}">Back to Add Doctor</a>
</body>
</html>





<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
<h2>Operation Successful!!</h2>
<p th:text="${message}">Action completed successfully.</p>
<p th:if="${doctor != null}">
	Doctor <span th:text="${doctor.name}"></span> saved/updated successfully.</p>
<a th:href="@{/doctors/add}">Add another doctor</a>
</body>
</html>










<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Update Doctor</title>
</head>
<body>
<h2>Update Doctor</h2>

<form th:action="@{'/doctors/update/' + ${doctor.doctorId}}" th:object="${doctor}" method="post">

<label>Name:</label>
<input type="text" th:field="*{name}" /><br/>
<span th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></span><br/>

<label>Specialization:</label>
<input type="text" th:field="*{specialization}" /><br/>
<span th:if="${#fields.hasErrors('specialization')}" th:errors="*{specialization}"></span><br/>

<label>Contact Number:</label>
<input type="text" th:field="*{contactNumber}" /><br/>
<span th:if="${#fields.hasErrors('contactNumber')}" th:errors="*{contactNumber}"></span><br/>

<label>Availability Schedule:</label>
<input type="text" th:field="*{availabilitySchedule}" /><br/>
<span th:if="${#fields.hasErrors('availabilitySchedule')}" th:errors="*{availabilitySchedule}"></span><br/>

<button type="submit">Update Doctor</button>

</form>
</body>
</html>













spring.application.name=SpringDataJPA
spring.datasource.url=jdbc:mysql://localhost:3306/doctor?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8082














<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <!-- ⚠️ Use the latest stable version available in Maven Central -->
        <version>3.3.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.example</groupId>
    <artifactId>SpringDataJPA</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>SpringDataJPA</name>
    <description>Demo project for Spring Data JPA</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <!-- Spring Web (REST APIs, MVC) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Thymeleaf template engine -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- MySQL Driver -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- DevTools (development only) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- Bean Validation (Jakarta Validation API + Hibernate Validator) -->
        <dependency>
   			 <groupId>org.springframework.boot</groupId>
  			 <artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>












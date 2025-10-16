package com.hms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String timeSlot;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment() {}

    public Appointment(LocalDate date, String timeSlot, Doctor doctor, Patient patient, AppointmentStatus status) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
    
}




package com.hms.model;

public enum AppointmentStatus {
    BOOKED,
    CANCELLED
}




package com.hms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDate billDate;

    @OneToOne
    private Appointment appointment;

    public Bill() {}

    public Bill(Double amount, LocalDate billDate, Appointment appointment) {
        this.amount = amount;
        this.billDate = billDate;
        this.appointment = appointment;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}





package com.hms.model;

import jakarta.persistence.*;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @OneToOne
    private User user;

    public Doctor() {}

    public Doctor(String name, String specialization, User user) {
        this.name = name;
        this.specialization = specialization;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}






package com.hms.model;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;

    @OneToOne
    private User user;

    public Patient() {}

    public Patient(String name, String contact, User user) {
        this.name = name;
        this.contact = contact;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}








package com.hms.model;

public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT
}







package com.hms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}


==================================================================================


package com.hms.dto;

import java.time.LocalDate;

public class AppointmentRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDate date;
    private String timeSlot;

    public AppointmentRequest() {}

    public AppointmentRequest(Long doctorId, Long patientId, LocalDate date, String timeSlot) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

}




package com.hms.dto;

public class BillRequest {
    private Long appointmentId;
    private Double amount;

    public BillRequest() {}

    public BillRequest(Long appointmentId, Double amount) {
        this.appointmentId = appointmentId;
        this.amount = amount;
    }

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}




package com.hms.dto;

public class RegisterDoctorRequest {
    private String username;
    private String password;
    private String name;
    private String specialization;

    public RegisterDoctorRequest() {}

    public RegisterDoctorRequest(String username, String password, String name, String specialization) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.specialization = specialization;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}









package com.hms.dto;

public class RegisterPatientRequest {
    private String username;
    private String password;
    private String name;
    private String contact;

    public RegisterPatientRequest() {}

    public RegisterPatientRequest(String username, String password, String name, String contact) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}





==================================================



package com.hms.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI hmsOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Hospital Management System API")
                .description("Simple, role-based REST APIs for Admin, Doctor, Patient, Appointments and Billing")
                .version("1.0.0")
                .contact(new Contact()
                    .name("HMS Support")
                    .email("support@example.com"))
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/licenses/MIT"))
            );
    }
}








package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    // Will be provided in Section 7 as UserDetailsServiceImpl
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt for hashing passwords
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Expose AuthenticationManager for AuthController (authenticationManager.authenticate(...))
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // REST APIs: disable CSRF and use stateless sessions
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/auth/**",                  // login
                    "/api/user/register-patient",    // patient self-registration
                    "/v3/api-docs/**",              // OpenAPI JSON
                    "/swagger-ui/**",               // Swagger UI resources
                    "/swagger-ui.html"              // Swagger UI entry
                ).permitAll()

                // Admin-only endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // Everything else needs authentication
                .anyRequest().authenticated()
            )

            // Simple for Postman/testing
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * CORS for local dev (VS Code Live Server and typical local ports)
     * Adjust or tighten for prod as needed.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of(
            "http://127.0.0.1:5500",
            "http://localhost:5500",
            "http://localhost:3000",
            "http://localhost"
        ));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}





===========================================================


package com.hms.exception;

import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ApiError(int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

}





package com.hms.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}





package com.hms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}






package com.hms.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}




=========================================================



package com.hms.controller;

import com.hms.dto.RegisterDoctorRequest;
import com.hms.model.Doctor;
import com.hms.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register-doctor")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody RegisterDoctorRequest request) {
        Doctor doctor = doctorService.registerDoctor(request);
        return ResponseEntity.ok(doctor);
    }
}







package com.hms.controller;

import com.hms.dto.AppointmentRequest;
import com.hms.model.Appointment;
import com.hms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request);
        return ResponseEntity.ok(appointment);
    }
}





package com.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        if (auth.isAuthenticated()) {
            return ResponseEntity.ok("Login successful for user: " + username);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}






package com.hms.controller;

import com.hms.dto.BillRequest;
import com.hms.model.Bill;
import com.hms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/generate")
    public ResponseEntity<Bill> generateBill(@RequestBody BillRequest request) {
        Bill bill = billService.generateBill(request);
        return ResponseEntity.ok(bill);
    }
}





package com.hms.controller;

import com.hms.model.Appointment;
import com.hms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments/{doctorId}")
    public List<Appointment> getAppointments(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }
}





package com.hms.controller;

import com.hms.dto.RegisterPatientRequest;
import com.hms.model.Patient;
import com.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register-patient")
    public ResponseEntity<Patient> registerPatient(@RequestBody RegisterPatientRequest request) {
        Patient patient = patientService.registerPatient(request);
        return ResponseEntity.ok(patient);
    }
}



==========================================================



package com.hms.security;

import com.hms.model.User;
import com.hms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}




=======================================================








package com.hms.repository;

import com.hms.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
}





package com.hms.repository;

import com.hms.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}



package com.hms.repository;

import com.hms.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}






package com.hms.repository;

import com.hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}



package com.hms.repository;

import com.hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


============================================================




package com.hms.service;

import com.hms.dto.AppointmentRequest;
import com.hms.model.*;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Appointment bookAppointment(AppointmentRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Check if slot already booked
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorId(doctor.getId());
        boolean slotTaken = existingAppointments.stream()
                .anyMatch(a -> a.getDate().equals(request.getDate()) && a.getTimeSlot().equals(request.getTimeSlot()));
        if (slotTaken) {
            throw new RuntimeException("Slot already booked");
        }

        Appointment appointment = new Appointment(request.getDate(), request.getTimeSlot(), doctor, patient, AppointmentStatus.BOOKED);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}




package com.hms.service;

import com.hms.dto.BillRequest;
import com.hms.model.Appointment;
import com.hms.model.Bill;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Bill generateBill(BillRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Bill bill = new Bill(request.getAmount(), LocalDate.now(), appointment);
        return billRepository.save(bill);
    }
}








package com.hms.service;

import com.hms.model.Role;
import com.hms.model.User;
import com.hms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), Role.ADMIN);
            userRepository.save(admin);
            System.out.println("Default ADMIN created: username=admin, password=admin123");
        }
    }
}





package com.hms.service;

import com.hms.dto.RegisterDoctorRequest;
import com.hms.model.Doctor;
import com.hms.model.Role;
import com.hms.model.User;
import com.hms.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;

    public Doctor registerDoctor(RegisterDoctorRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword(), Role.DOCTOR);
        Doctor doctor = new Doctor(request.getName(), request.getSpecialization(), user);
        return doctorRepository.save(doctor);
    }
}






package com.hms.service;

import com.hms.dto.RegisterPatientRequest;
import com.hms.model.Patient;
import com.hms.model.Role;
import com.hms.model.User;
import com.hms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    public Patient registerPatient(RegisterPatientRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword(), Role.PATIENT);
        Patient patient = new Patient(request.getName(), request.getContact(), user);
        return patientRepository.save(patient);
    }
}






package com.hms.service;

import com.hms.model.Role;
import com.hms.model.User;
import com.hms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(username, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}




====================================================


package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsProjectApplication.class, args);
	}

}
========================================================






spring.application.name=HMSProject
spring.datasource.url=jdbc:mysql://localhost:3306/hms?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=9090

=======================================================


<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.5.6</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.hms</groupId>
	<artifactId>HMSProject</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>HMSProject</name>
	<description>Hospital Management System Project</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
      		<groupId>org.springframework.boot</groupId>
     		<artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
		<dependency>
    			<groupId>org.springdoc</groupId>
   			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
   			<version>2.8.13</version>
		</dependency>
		<!-- Swagger / OpenAPI for Spring Boot 3+ -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>
					</annotationProcessorPaths>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>



============================================================================














<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Hospital Management System</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>
<style>
body { font-family: Arial, sans-serif; }
.hero { background: #0d6efd; color: white; padding: 50px; text-align: center; }
.card img { max-height: 150px; object-fit: cover; }
</style>
</head>
<body class='bg-light'>
<nav class='navbar navbar-expand-lg navbar-dark bg-primary'>
<div class='container'>
<a class='navbar-brand fw-bold' href='#'>HMS</a>
<ul class='navbar-nav ms-auto'>
<li class='nav-item'><a class='nav-link' href='index.html'>Home</a></li>
<li class='nav-item'><a class='nav-link' href='patient-dashboard.html'>Patient</a></li>
<li class='nav-item'><a class='nav-link' href='doctor-dashboard.html'>Doctor</a></li>
<li class='nav-item'><a class='nav-link' href='billing.html'>Billing</a></li>
</ul>
</div>
</nav>
<div class='hero'>
<h1>Hospital Management System</h1>
<p>Manage patients, doctors, appointments, and billing easily</p>
<!-- Insert hero image here -->
<img src='images/hospital-banner.jpg' class='img-fluid rounded shadow mt-3' alt='Hospital Banner'>
</div>
<div class='container mt-5'>
<div class='row'>
<div class='col-md-6 mb-4'>
<div class='card p-4 shadow'>
<!-- Insert login image here -->
<img src='images/login.jpg' class='img-fluid rounded mb-3' alt='Login'>
<h4 class='mb-3'>Login</h4>
<form id='loginForm'>
<input type='text' id='loginUsername' class='form-control mb-2' placeholder='Username'>
<input type='password' id='loginPassword' class='form-control mb-2' placeholder='Password'>
<button class='btn btn-primary w-100'>Login</button>
</form>
</div>
</div>
<div class='col-md-6 mb-4'>
<div class='card p-4 shadow'>
<!-- Insert register image here -->
<img src='images/register.jpg' class='img-fluid rounded mb-3' alt='Register'>
<h4 class='mb-3'>Register Patient</h4>
<form id='registerPatientForm'>
<input type='text' id='regUsername' class='form-control mb-2' placeholder='Username'>
<input type='password' id='regPassword' class='form-control mb-2' placeholder='Password'>
<input type='text' id='regName' class='form-control mb-2' placeholder='Full Name'>
<input type='text' id='regContact' class='form-control mb-2' placeholder='Contact'>
<button class='btn btn-success w-100'>Register</button>
</form>
</div>
</div>
</div>
</div>
<footer class='bg-primary text-white text-center p-3 mt-5'>
<p>&copy; 2025 Hospital Management System</p>
</footer>
<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>
</body>
</html>














<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Book Appointment</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>
<style>.hero { background:#198754;color:white;padding:40px;text-align:center; }</style>
</head>
<body>
<nav class='navbar navbar-expand-lg navbar-dark bg-primary'>
<div class='container'><a class='navbar-brand fw-bold' href='#'>HMS</a></div>
</nav>
<div class='hero'>
<h2>Book Appointment</h2>
<p>Select doctor and time slot</p>
<!-- Insert appointment image here -->
<img src='images/appointment.jpg' class='img-fluid rounded shadow mt-3' alt='Appointment'>
</div>
<div class='container mt-4'>
<div class='card p-4 shadow'>
<form id='appointmentForm'>
<input type='number' id='doctorId' class='form-control mb-2' placeholder='Doctor ID'>
<input type='number' id='patientId' class='form-control mb-2' placeholder='Patient ID'>
<input type='date' id='date' class='form-control mb-2'>
<input type='text' id='timeSlot' class='form-control mb-2' placeholder='Time Slot (e.g., 10:00 AM)'>
<button class='btn btn-success w-100'>Book</button>
</form>
</div>
</div>
<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>
</body>
</html>












<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Doctor Dashboard</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>
<style>.hero { background:#6f42c1;color:white;padding:40px;text-align:center; }</style>
</head>
<body>
<nav class='navbar navbar-expand-lg navbar-dark bg-primary'>
<div class='container'><a class='navbar-brand fw-bold' href='#'>HMS</a></div>
</nav>
<div class='hero'>
<h2>Doctor Appointments</h2>
<p>View your scheduled appointments</p>
<!-- Insert doctor image here -->
<img src='images/doctor.jpg' class='img-fluid rounded shadow mt-3' alt='Doctor'>
</div>
<div class='container mt-4'>
<div class='card p-4 shadow'>
<input type='number' id='doctorId' class='form-control mb-2' placeholder='Enter Doctor ID'>
<button id='loadAppointments' class='btn btn-info mb-3'>Load Appointments</button>
<table class='table table-bordered'>
<thead><tr><th>ID</th><th>Date</th><th>Time Slot</th><th>Patient</th></tr></thead>
<tbody id='appointmentsTable'></tbody>
</table>
</div>
</div>
<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>
</body>
</html>










<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Generate Bill</title>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>
<style>.hero { background:#dc3545;color:white;padding:40px;text-align:center; }</style>
</head>
<body>
<nav class='navbar navbar-expand-lg navbar-dark bg-primary'>
<div class='container'><a class='navbar-brand fw-bold' href='#'>HMS</a></div>
</nav>
<div class='hero'>
<h2>Generate Bill</h2>
<p>Enter details to create a bill</p>
<!-- Insert billing image here -->
<img src='images/billing.jpg' class='img-fluid rounded shadow mt-3' alt='Billing'>
</div>
<div class='container mt-4'>
<div class='card p-4 shadow'>
<form id='billForm'>
<input type='number' id='appointmentId' class='form-control mb-2' placeholder='Appointment ID'>
<input type='number' id='amount' class='form-control mb-2' placeholder='Amount'>
<button class='btn btn-danger w-100'>Generate</button>
</form>
</div>
</div>
<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>
</body>
</html>





===================================================================





<!DOCTYPE html>

<html lang='en'>

<head>

    <meta charset='UTF-8'>

    <meta name='viewport' content='width=device-width, initial-scale=1.0'>

    <title>Hospital Management System</title>

    <!-- Tailwind CSS CDN -->

    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body { font-family: 'Inter', sans-serif; background-color: #f7fafc; }

        .card-shadow { box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); }

    </style>

</head>

<body class="min-h-screen flex flex-col">

    <!-- Navbar -->

    <nav class="bg-blue-600 p-4 shadow-lg">

        <div class="container mx-auto flex justify-between items-center">

            <a class="text-white text-2xl font-bold tracking-wider" href="index.html">HMS</a>

            <ul class="flex space-x-4">

                <li class="hidden sm:block"><a class="text-white hover:text-blue-200 transition duration-150" href="index.html">Home</a></li>

                <li class="hidden sm:block"><a class="text-white hover:text-blue-200 transition duration-150" href="book-appointment.html">Book</a></li>

            </ul>

        </div>

    </nav>



    <!-- Hero Section -->

    <div class="bg-blue-500 py-16 text-white text-center shadow-inner">

        <h1 class="text-5xl font-extrabold mb-3">Health at Your Fingertips</h1>

        <p class="text-xl opacity-90">Manage patients, doctors, and appointments seamlessly.</p>

        <img src="https://placehold.co/600x200/4c4cff/ffffff?text=Hospital+Banner" alt="Hospital Banner" class="mt-8 mx-auto rounded-lg shadow-xl w-4/5 max-w-lg">

    </div>



    <!-- Main Content: Login & Register -->

    <div class="container mx-auto p-4 sm:p-8 flex-grow">

        <div class="grid grid-cols-1 md:grid-cols-2 gap-8 max-w-5xl mx-auto">

            

            <!-- Login Card -->

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">User Login</h2>

                <form id='loginForm' class="space-y-4">

                    <div>

                        <label for="loginUsername" class="block text-sm font-medium text-gray-700">Username</label>

                        <input type='text' id='loginUsername' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' placeholder='Enter your username' required>

                    </div>

                    <div>

                        <label for="loginPassword" class="block text-sm font-medium text-gray-700">Password</label>

                        <input type='password' id='loginPassword' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' placeholder='Enter your password' required>

                    </div>

                    <button type="submit" class='w-full py-3 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-150 transform hover:scale-[1.01]'>

                        Secure Login

                    </button>

                </form>

            </div>



            <!-- Patient Register Card -->

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">New Patient Register</h2>

                <form id='registerPatientForm' class="space-y-4">

                    <div>

                        <label for="regUsername" class="block text-sm font-medium text-gray-700">Choose Username</label>

                        <input type='text' id='regUsername' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500 mt-1' placeholder='Unique Username' required>

                    </div>

                    <div>

                        <label for="regPassword" class="block text-sm font-medium text-gray-700">Choose Password</label>

                        <input type='password' id='regPassword' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500 mt-1' placeholder='Strong Password' required>

                    </div>

                    <input type='text' id='regName' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500' placeholder='Full Name' required>

                    <input type='text' id='regContact' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500' placeholder='Contact Number' required>

                    <button type="submit" class='w-full py-3 bg-green-600 text-white font-semibold rounded-lg shadow-md hover:bg-green-700 transition duration-150 transform hover:scale-[1.01]'>

                        Register & Get Started

                    </button>

                </form>

            </div>

        </div>

    </div>



    <!-- Footer -->

    <footer class="bg-gray-800 text-white text-center p-4 mt-auto shadow-inner">

        <p class="text-sm">&copy; 2025 Hospital Management System. All rights reserved.</p>

    </footer>



    <!-- Scripts -->

    <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

    <script src='hms.js'></script>

    <script>

        $('#loginForm').on('submit', function(event) {

            event.preventDefault();

            const username = $('#loginUsername').val();

            const password = $('#loginPassword').val();

            handleLogin(username, password);

        });



        $('#registerPatientForm').on('submit', function(event) {

            event.preventDefault();

            const userData = {

                username: $('#regUsername').val(),

                password: $('#regPassword').val(),

                name: $('#regName').val(),

                contact: $('#regContact').val()

            };

            handleRegisterPatient(userData);

        });

    </script>

</body>

</html>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Patient Dashboard | Appointment Booking</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>

        body { background-color: #e9ecef; }

        .dashboard-header { background-color: #007bff; color: white; padding: 15px 0; margin-bottom: 30px; }

        .card { border: none; box-shadow: 0 0.5rem 1rem rgba(0,0,0,.15); }

    </style>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <script src="hms.js"></script>

    <script>

        // Check if logged in. If not, silently redirect to login page.

        if (!localStorage.getItem('hmsAuth')) {

            window.location.href = 'index.html';

        }

    </script>

</head>

<body>

    <div class="dashboard-header">

        <div class="container d-flex justify-content-between align-items-center">

            <h3 class="mb-0">Patient Dashboard</h3>

            <button class="btn btn-light" id="logoutBtn">Logout</button>

        </div>

    </div>



    <div class="container">

        <h1 class="mb-4">Welcome Back, Patient!</h1>

        <div class="row">

            <div class="col-lg-6 mb-4">

                <div class="card p-4">

                    <h4 class="card-title text-primary mb-4">Book New Appointment</h4>

                    <form id="appointmentForm">

                        

                        <div class="mb-3">

                            <label for="book-doctorIdSelect" class="form-label">Select Doctor</label>

                            <select class="form-select" id="book-doctorIdSelect" required>

                                <option value="" disabled selected>Loading Doctors...</option>

                            </select>

                        </div>

                        

                        <div class="mb-3">

                            <label for="book-patientId" class="form-label">Your Patient ID</label>

                            <input type="number" class="form-control" id="book-patientId" placeholder="Enter your Patient ID" required>

                            <small class="text-muted">Enter your Patient ID (e.g., 1) to book the appointment.</small>

                        </div>

                        

                        <div class="row">

                            <div class="col-md-6 mb-3">

                                <label for="book-date" class="form-label">Date</label>

                                <input type="date" class="form-control" id="book-date" required>

                            </div>

                            <div class="col-md-6 mb-3">

                                <label for="book-timeSlot" class="form-label">Time Slot</label>

                                <select class="form-select" id="book-timeSlot" required>

                                    <option value="" disabled selected>Select Time</option>

                                    <option value="09:00-10:00">09:00 AM - 10:00 AM</option>

                                    <option value="10:00-11:00">10:00 AM - 11:00 AM</option>

                                    <option value="11:00-12:00">11:00 AM - 12:00 PM</option>

                                    <option value="14:00-15:00">02:00 PM - 03:00 PM</option>

                                </select>

                            </div>

                        </div>



                        <button type="submit" class="btn btn-primary w-100 mt-2">Book Appointment</button>

                        <p id="appointment-message" class="mt-3 text-center"></p>

                    </form>

                </div>

            </div>



            <div class="col-lg-6 mb-4">

                <div class="card p-4">

                    <h4 class="card-title text-success mb-4">Your Upcoming Appointments</h4>

                    <ul class="list-group list-group-flush">

                        <li class="list-group-item text-muted">**Feature:** Appointments dynamically loaded here.</li>

                        <li class="list-group-item text-muted">Date: YYYY-MM-DD, Doctor: Name, Status: BOOKED</li>

                    </ul>

                </div>

            </div>

        </div>

    </div>

</body>

</html><!DOCTYPE html>

<html lang='en'>

<head>

    <meta charset='UTF-8'>

    <meta name='viewport' content='width=device-width, initial-scale=1.0'>

    <title>Doctor Dashboard</title>

    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body { font-family: 'Inter', sans-serif; background-color: #f7fafc; }

        .card-shadow { box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); }

        th { text-align: left; }

    </style>

</head>

<body class="min-h-screen flex flex-col">

    <!-- Navbar -->

    <nav class="bg-green-700 p-4 shadow-lg">

        <div class="container mx-auto flex justify-between items-center">

            <a class="text-white text-2xl font-bold tracking-wider" href="index.html">Doctor Panel</a>

            <ul class="flex space-x-4 items-center">

                 <li><a class="text-white hover:text-green-200 transition duration-150 cursor-pointer" onclick="handleLogout()">Logout</a></li>

            </ul>

        </div>

    </nav>



    <!-- Hero Section -->

    <div class="bg-green-600 py-12 text-white text-center shadow-inner">

        <h1 class="text-4xl font-extrabold mb-2">My Appointment Schedule</h1>

        <p class="text-lg opacity-90">View and manage your upcoming patient consultations.</p>

    </div>



    <!-- Main Content: Appointments List -->

    <div class="container mx-auto p-4 sm:p-8 flex-grow">

        <div class="max-w-4xl mx-auto">

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-2xl font-bold text-gray-800 mb-6 border-b pb-2">Upcoming Appointments</h2>



                <div class="flex flex-col sm:flex-row gap-4 mb-6 items-center">

                    <input type='number' id='doctorId' class='flex-grow w-full sm:w-auto p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500' placeholder='Enter Your Doctor ID (e.g., 1)' required>

                    <button id='loadAppointments' class='w-full sm:w-1/3 py-3 bg-green-600 text-white font-semibold rounded-lg shadow-md hover:bg-green-700 transition duration-150 transform hover:scale-[1.01]'>

                        Load Appointments

                    </button>

                </div>



                <div class="overflow-x-auto rounded-lg shadow-md">

                    <table class='min-w-full bg-white'>

                        <thead class="bg-gray-100 border-b border-gray-300 text-gray-600 uppercase text-sm leading-normal">

                            <tr>

                                <th class='py-3 px-4 font-bold'>ID</th>

                                <th class='py-3 px-4 font-bold'>Date</th>

                                <th class='py-3 px-4 font-bold'>Time Slot</th>

                                <th class='py-3 px-4 font-bold'>Patient & Status</th>

                            </tr>

                        </thead>

                        <tbody id='appointmentsTable' class="text-gray-700 text-sm font-light">

                            <tr class="border-b border-gray-200"><td colspan="4" class="p-4 text-center text-gray-500">Enter Doctor ID and click Load to see schedule.</td></tr>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>



    <!-- Footer -->

    <footer class="bg-gray-800 text-white text-center p-4 mt-auto shadow-inner">

        <p class="text-sm">&copy; 2025 Hospital Management System | Doctor Panel</p>

    </footer>



    <!-- Scripts -->

    <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

    <script src='hms.js'></script>

    <script>

        // Check if Doctor/Admin is logged in on page load

        $(document).ready(function() {

            if (sessionUser.role !== 'DOCTOR' && sessionUser.role !== 'ADMIN') {

                alert("Unauthorized access. Redirecting to login.");

                window.location.href = 'index.html';

            }

        });



        $('#loadAppointments').on('click', function() {

            const doctorId = $('#doctorId').val();

            const tableBody = $('#appointmentsTable');

            if (doctorId) {

                // handleLoadDoctorAppointments is defined in hms.js

                handleLoadDoctorAppointments(doctorId, tableBody);

            } else {

                alert("Please enter a valid Doctor ID.");

            }

        });

    </script>

</body>

</html><!DOCTYPE html>

<html lang='en'>

<head>

    <meta charset='UTF-8'>

    <meta name='viewport' content='width=device-width, initial-scale=1.0'>

    <title>Admin Dashboard</title>

    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body { font-family: 'Inter', sans-serif; background-color: #f7fafc; }

        .card-shadow { box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); }

    </style>

</head>

<body class="min-h-screen flex flex-col">

    <!-- Navbar -->

    <nav class="bg-purple-700 p-4 shadow-lg">

        <div class="container mx-auto flex justify-between items-center">

            <a class="text-white text-2xl font-bold tracking-wider" href="index.html">HMS Admin</a>

            <ul class="flex space-x-4 items-center">

                <li><a class="text-white hover:text-purple-200 transition duration-150" href="billing.html">Billing</a></li>

                <li><a class="text-white hover:text-purple-200 transition duration-150 cursor-pointer" onclick="handleLogout()">Logout</a></li>

            </ul>

        </div>

    </nav>



    <!-- Hero Section -->

    <div class="bg-purple-600 py-12 text-white text-center shadow-inner">

        <h1 class="text-4xl font-extrabold mb-2">Welcome, System Admin</h1>

        <p class="text-lg opacity-90">Manage doctors and system settings securely.</p>

    </div>



    <!-- Main Content: Register Doctor -->

    <div class="container mx-auto p-4 sm:p-8 flex-grow">

        <div class="max-w-xl mx-auto">

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2 text-center">Register New Doctor</h2>

                <p class="text-sm text-red-500 mb-4 text-center">Requires Admin privileges to succeed.</p>

                <form id='registerDoctorForm' class="space-y-4">

                    <div>

                        <label for="docUsername" class="block text-sm font-medium text-gray-700">Username</label>

                        <input type='text' id='docUsername' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-purple-500 focus:border-purple-500 mt-1' placeholder='Unique Doctor Username' required>

                    </div>

                    <div>

                        <label for="docPassword" class="block text-sm font-medium text-gray-700">Password</label>

                        <input type='password' id='docPassword' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-purple-500 focus:border-purple-500 mt-1' placeholder='Initial Password' required>

                    </div>

                    <input type='text' id='docName' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-purple-500 focus:border-purple-500' placeholder='Full Name (e.g., Dr. Jane Doe)' required>

                    <input type='text' id='docSpecialization' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-purple-500 focus:border-purple-500' placeholder='Specialization (e.g., Oncology, Pediatrics)' required>

                    <button type="submit" class='w-full py-3 bg-purple-600 text-white font-semibold rounded-lg shadow-md hover:bg-purple-700 transition duration-150 transform hover:scale-[1.01]'>

                        Create Doctor Account

                    </button>

                </form>

            </div>

        </div>

    </div>



    <!-- Footer -->

    <footer class="bg-gray-800 text-white text-center p-4 mt-auto shadow-inner">

        <p class="text-sm">&copy; 2025 Hospital Management System | Admin Panel</p>

    </footer>



    <!-- Scripts -->

    <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

    <script src='hms.js'></script>

    <script>

        // Check if Admin is logged in on page load

        $(document).ready(function() {

            if (sessionUser.role !== 'ADMIN' || !sessionUser.isLoggedIn) {

                alert("Unauthorized access. Redirecting to login.");

                window.location.href = 'index.html';

            }

        });



        $('#registerDoctorForm').on('submit', function(event) {

            event.preventDefault();

            const userData = {

                username: $('#docUsername').val(),

                password: $('#docPassword').val(),

                name: $('#docName').val(),

                specialization: $('#docSpecialization').val()

            };

            handleRegisterDoctor(userData);

        });

    </script>

</body>

</html>

<!DOCTYPE html>

<html lang='en'>

<head>

    <meta charset='UTF-8'>

    <meta name='viewport' content='width=device-width, initial-scale=1.0'>

    <title>Book Appointment</title>

    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body { font-family: 'Inter', sans-serif; background-color: #f7fafc; }

        .card-shadow { box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); }

    </style>

</head>

<body class="min-h-screen flex flex-col">

    <!-- Navbar -->

    <nav class="bg-blue-700 p-4 shadow-lg">

        <div class="container mx-auto flex justify-between items-center">

            <a class="text-white text-2xl font-bold tracking-wider" href="index.html">Patient Portal</a>

            <ul class="flex space-x-4 items-center">

                <li><a class="text-white hover:text-blue-200 transition duration-150" href="patient-dashboard.html">My Dashboard</a></li>

                <li><a class="text-white hover:text-blue-200 transition duration-150 cursor-pointer" onclick="handleLogout()">Logout</a></li>

            </ul>

        </div>

    </nav>



    <!-- Hero Section -->

    <div class="bg-blue-600 py-12 text-white text-center shadow-inner">

        <h1 class="text-4xl font-extrabold mb-2">Schedule a Consultation</h1>

        <p class="text-lg opacity-90">Find the right time to meet with your healthcare professional.</p>

    </div>



    <!-- Main Content: Booking Form -->

    <div class="container mx-auto p-4 sm:p-8 flex-grow">

        <div class="max-w-xl mx-auto">

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2 text-center">Book New Appointment</h2>



                <form id='appointmentForm' class="space-y-4">

                    <p class="text-sm text-gray-600 border-l-4 border-blue-400 pl-3 py-1 bg-blue-50 rounded-md">

                        *Note: You must enter your Patient ID and the Doctor's ID to proceed.

                    </p>

                    

                    <div>

                        <label for="patientId" class="block text-sm font-medium text-gray-700">Your Patient ID</label>

                        <input type='number' id='patientId' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' placeholder='e.g., 2' required>

                    </div>



                    <div>

                        <label for="doctorId" class="block text-sm font-medium text-gray-700">Doctor ID</label>

                        <input type='number' id='doctorId' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' placeholder='e.g., 1 (Dr. Jones)' required>

                    </div>



                    <div>

                        <label for="appointmentDate" class="block text-sm font-medium text-gray-700">Date</label>

                        <input type='date' id='appointmentDate' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' required>

                    </div>

                    

                    <div>

                        <label for="appointmentTime" class="block text-sm font-medium text-gray-700">Time Slot</label>

                        <input type='time' id='appointmentTime' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 mt-1' required>

                    </div>



                    <button type="submit" class='w-full py-3 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-150 transform hover:scale-[1.01]'>

                        Book Appointment

                    </button>

                </form>

            </div>

        </div>

    </div>



    <!-- Footer -->

    <footer class="bg-gray-800 text-white text-center p-4 mt-auto shadow-inner">

        <p class="text-sm">&copy; 2025 Hospital Management System | Patient Portal</p>

    </footer>



    <!-- Scripts -->

    <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

    <script src='hms.js'></script>

    <script>

        // Check if Patient/Admin is logged in on page load

        $(document).ready(function() {

            if (sessionUser.role !== 'PATIENT' && sessionUser.role !== 'ADMIN') {

                alert("Unauthorized access. Redirecting to login.");

                window.location.href = 'index.html';

            }

        });



        $('#appointmentForm').on('submit', function(event) {

            event.preventDefault();

            const appointmentData = {

                patientId: $('#patientId').val(),

                doctorId: $('#doctorId').val(),

                date: $('#appointmentDate').val(),

                time: $('#appointmentTime').val()

            };

            handleBookAppointment(appointmentData);

        });

    </script>

</body>

</html><!DOCTYPE html>

<html lang='en'>

<head>

    <meta charset='UTF-8'>

    <meta name='viewport' content='width=device-width, initial-scale=1.0'>

    <title>Generate Bill</title>

    <script src="https://cdn.tailwindcss.com"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body { font-family: 'Inter', sans-serif; background-color: #f7fafc; }

        .card-shadow { box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); }

    </style>

</head>

<body class="min-h-screen flex flex-col">

    <!-- Navbar -->

    <nav class="bg-red-700 p-4 shadow-lg">

        <div class="container mx-auto flex justify-between items-center">

            <a class="text-white text-2xl font-bold tracking-wider" href="index.html">Admin Panel</a>

            <ul class="flex space-x-4 items-center">

                <li><a class="text-white hover:text-red-200 transition duration-150" href="admin-dashboard.html">Register Doctor</a></li>

                <li><a class="text-white hover:text-red-200 transition duration-150 cursor-pointer" onclick="handleLogout()">Logout</a></li>

            </ul>

        </div>

    </nav>



    <!-- Hero Section -->

    <div class="bg-red-600 py-12 text-white text-center shadow-inner">

        <h1 class="text-4xl font-extrabold mb-2">Billing and Financial Management</h1>

        <p class="text-lg opacity-90">Generate and record patient bills.</p>

    </div>



    <!-- Main Content: Bill Form -->

    <div class="container mx-auto p-4 sm:p-8 flex-grow">

        <div class="max-w-xl mx-auto">

            <div class="bg-white p-6 rounded-xl card-shadow border border-gray-100">

                <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2 text-center">Generate New Bill</h2>



                <form id='billForm' class="space-y-4">

                    <p class="text-sm text-red-600 border-l-4 border-red-400 pl-3 py-1 bg-red-50 rounded-md">

                        *Note: Only the Admin role is authorized to perform this operation.

                    </p>

                    

                    <div>

                        <label for="appointmentId" class="block text-sm font-medium text-gray-700">Appointment ID</label>

                        <input type='number' id='appointmentId' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-red-500 focus:border-red-500 mt-1' placeholder='Enter Appointment ID (e.g., 1)' required>

                    </div>



                    <div>

                        <label for="amount" class="block text-sm font-medium text-gray-700">Bill Amount ($)</label>

                        <input type='number' step="0.01" id='amount' class='w-full p-3 border border-gray-300 rounded-lg focus:ring-red-500 focus:border-red-500 mt-1' placeholder='e.g., 150.00' required>

                    </div>



                    <button type="submit" class='w-full py-3 bg-red-600 text-white font-semibold rounded-lg shadow-md hover:bg-red-700 transition duration-150 transform hover:scale-[1.01]'>

                        Generate Bill

                    </button>

                </form>

            </div>

        </div>

    </div>



    <!-- Footer -->

    <footer class="bg-gray-800 text-white text-center p-4 mt-auto shadow-inner">

        <p class="text-sm">&copy; 2025 Hospital Management System | Admin Panel</p>

    </footer>



    <!-- Scripts -->

    <script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>

    <script src='hms.js'></script>

    <script>

        // Check if Admin is logged in on page load

        $(document).ready(function() {

            if (sessionUser.role !== 'ADMIN') {

                alert("Access Denied. Only Admin can generate bills. Redirecting to login.");

                window.location.href = 'index.html';

            }

        });



        $('#billForm').on('submit', function(event) {

            event.preventDefault();

            const billData = {

                appointmentId: $('#appointmentId').val(),

                amount: $('#amount').val()

            };

            handleGenerateBill(billData);

        });

    </script>

</body>

</html>// --- Configuration ---

const API_BASE_URL = 'http://localhost:9090/api';



// --- Global Authentication State / Persistence ---



/**

 * Loads session user details from localStorage or returns a default logged-out state.

 */

function loadSessionUser() {

    try {

        const storedUser = localStorage.getItem('hmsSessionUser');

        if (storedUser) {

            return JSON.parse(storedUser);

        }

    } catch (e) {

        console.error("Error loading session user from localStorage:", e);

    }

    return {

        username: null,

        password: null,

        role: null, // ADMIN, DOCTOR, PATIENT

        isLoggedIn: false

    };

}



let sessionUser = loadSessionUser();



/**

 * Saves the current session user details to localStorage.

 */

function saveSessionUser(user) {

    sessionUser = user;

    localStorage.setItem('hmsSessionUser', JSON.stringify(user));

}



/**

 * Clears session and redirects to home.

 */

function handleLogout() {

    saveSessionUser({

        username: null,

        password: null,

        role: null,

        isLoggedIn: false

    });

    window.location.href = 'index.html';

}



// --- Utility Functions ---



/**

 * Parses the backend's ApiError response for a user-friendly message.

 */

function getErrorMessage(xhr) {

    try {

        const error = JSON.parse(xhr.responseText);

        return error.message || (xhr.statusText ? `Server Error (${xhr.statusText})` : 'An unknown error occurred.');

    } catch (e) {

        return xhr.responseText || 'Could not connect to the API server or server error.';

    }

}



/**

 * Gets the Basic Authorization header string for the logged-in user.

 */

function getAuthHeader() {

    if (!sessionUser.isLoggedIn) {

        // This usually means a protected page was accessed without login state

        console.error("Authentication Error: User not logged in for protected call.");

        alert("Session expired or user not logged in. Please log in again.");

        window.location.href = 'index.html';

        return null;

    }

    const credentials = sessionUser.username + ':' + sessionUser.password;

    return 'Basic ' + btoa(credentials);

}



/**

 * Redirects the user based on their role.

 */

function redirectToDashboard(role) {

    if (role === 'ADMIN') {

        window.location.href = 'admin-dashboard.html';

    } else if (role === 'DOCTOR') {

        window.location.href = 'doctor-dashboard.html';

    } else if (role === 'PATIENT') {

        window.location.href = 'patient-dashboard.html';

    } else {

        alert('Unknown user role. Redirecting to home.');

        window.location.href = 'index.html';

    }

}





// --- API Handlers (Called by HTML forms) ---



/**

 * Handles user login (POST /api/auth/login)

 */

function handleLogin(username, password) {

    // Attempt to log in to verify credentials

    $.ajax({

        url: `${API_BASE_URL}/auth/login?username=${username}&password=${password}`,

        type: 'POST',

        success: function(response, status, xhr) {

            // Success: Credentials are valid. Now we need the role.

            // In a real Basic Auth setup, we can't get the role without another authorized endpoint.

      

============================================











as per backend make like this frontend by html little jQuery little ajax , bootstrap, little js and link because I'm making application in eclipse and frontend in vscode . practically make as per requirement per make n







================


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HMS Doctor Dashboard & Management</title>
    <!-- 1. Bootstrap CSS CDN (For Styling and Responsiveness) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <style>
        /* Custom Styles to mimic the dark sidebar look and feel */
        body {
            background-color: #f4f7f6;
        }
        #sidebar-wrapper {
            min-height: 100vh;
            background-color: #343a40; /* Dark background */
            color: #ffffff;
            transition: margin 0.3s;
            width: 250px;
        }
        #sidebar-wrapper .list-group-item {
            background-color: #343a40;
            color: #ffffff;
            border: none;
            padding: 15px 20px;
            cursor: pointer;
        }
        #sidebar-wrapper .list-group-item:hover,
        #sidebar-wrapper .list-group-item.active {
            background-color: #495057; /* Slightly lighter on hover/active */
            border-left: 5px solid #007bff; /* Blue active line */
            color: #ffffff;
        }
        .sidebar-header {
            padding: 20px;
            border-bottom: 1px solid #495057;
            text-align: center;
        }
        .main-content {
            padding: 30px;
            flex-grow: 1;
        }
        .info-card {
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s;
        }
        .info-card:hover {
            transform: translateY(-2px);
        }
        .disease-tag {
            border-radius: 5px;
            padding: 4px 8px;
            font-size: 0.85rem;
            font-weight: 600;
            color: white;
            display: inline-block;
        }
        /* Responsive sidebar toggle for small screens */
        @media (max-width: 768px) {
            #wrapper {
                display: block;
            }
            #sidebar-wrapper {
                margin-left: -250px;
                position: fixed;
                z-index: 1000;
            }
            #sidebar-wrapper.toggled {
                margin-left: 0;
            }
            #menu-toggle {
                display: block !important;
            }
            .main-content {
                width: 100%;
            }
        }
        @media (min-width: 768px) {
            #menu-toggle {
                display: none !important;
            }
        }
    </style>
</head>
<body>

    <div class="d-flex" id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <div class="sidebar-header">
                <h5 class="text-white">FEDPOLEL CLINIC</h5>
                <div class="mt-3">
                    <!-- Avatar changes based on role (Mock) -->
                    <img src="https://placehold.co/50x50/dc3545/white?text=ADM" class="rounded-circle" id="user-avatar" onerror="this.onerror=null; this.src='https://placehold.co/50x50/dc3545/white?text=ADM';" alt="User Avatar">
                    <p class="mb-0 mt-2" id="user-name">Admin</p>
                    <small>System Access</small>
                </div>
                <div class="d-flex justify-content-around mt-3 small">
                    <span class="badge text-bg-primary">15 Pending</span>
                    <span class="badge text-bg-success">20 Visits</span>
                    <span class="badge text-bg-warning">04 Units</span>
                </div>
            </div>
            <div class="list-group list-group-flush" id="sidebar-menu">
                <!-- Menu Items - Visibility controlled by JS based on role -->
                <a class="list-group-item active" href="#" data-view-id="dashboard-view" data-role-access="doctor,admin"><i class="fas fa-home me-2"></i>Dashboard</a>
                <a class="list-group-item" href="#" data-view-id="admin-view" data-role-access="admin"><i class="fas fa-cog me-2"></i>Admin Settings</a>
                <a class="list-group-item" href="#" data-view-id="patient-register-view" data-role-access="doctor,admin"><i class="fas fa-user-injured me-2"></i>Patient Register</a>
                <a class="list-group-item" href="#" data-view-id="doctor-management-view" data-role-access="admin"><i class="fas fa-user-md me-2"></i>Doctor Management</a>
                <a class="list-group-item" href="#" data-view-id="appointment-view" data-role-access="doctor,admin"><i class="fas fa-calendar-check me-2"></i>Appointments</a>
                <a class="list-group-item" href="#" data-view-id="billing-view" data-role-access="doctor,admin"><i class="fas fa-file-invoice-dollar me-2"></i>Billing</a>
            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content Wrapper -->
        <div id="page-content-wrapper" class="main-content">
            
            <nav class="navbar navbar-expand-lg navbar-light bg-white border-bottom mb-4 rounded shadow-sm">
                <!-- Toggle button for mobile -->
                <button class="btn btn-primary d-block d-md-none me-3" id="menu-toggle">
                    <i class="fas fa-bars"></i>
                </button>
                <div class="ms-auto d-flex align-items-center">
                    <span class="me-3 d-none d-sm-inline" id="welcome-message">Welcome Admin</span>
                    <!-- User Icons from Screenshot -->
                    <i class="fas fa-bell mx-2 text-primary cursor-pointer"></i>
                    <i class="fas fa-cog mx-2 text-secondary cursor-pointer"></i>
                    <i class="fas fa-user-circle mx-2 text-dark cursor-pointer"></i>
                </div>
            </nav>
            
            <!-- ALERT MESSAGE BOX FOR SUCCESS/ERROR -->
            <div id="alert-container" class="mb-4"></div>

            <!-- --------------------------------------- -->
            <!-- 1. DASHBOARD VIEW (Default View) -->
            <!-- --------------------------------------- -->
            <div id="dashboard-view" class="d-none">
                <h2 class="mb-4">Dashboard Overview</h2>
                <!-- Summary Cards Row -->
                <div class="row g-4 mb-5" id="summary-cards-container">
                    <!-- Cards will be injected here by jQuery -->
                </div>
                <!-- New Patient List Section -->
                <div class="card info-card border-0">
                    <div class="card-header bg-primary text-white">
                        <h5 class="mb-0">NEW PATIENT LIST</h5>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-hover mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">First Name</th>
                                        <th scope="col">Last Name</th>
                                        <th scope="col">Diseases</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="patient-list-body">
                                    <!-- Patient data will be dynamically injected here by jQuery -->
                                </tbody>
                            </table>
                        </div>
                        <div id="loading-spinner" class="text-center p-5 d-none">
                            <div class="spinner-border text-primary" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                            <p class="mt-2 text-primary">Fetching patient data...</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- --------------------------------------- -->
            <!-- 2. DOCTOR MANAGEMENT VIEW -->
            <!-- --------------------------------------- -->
            <div id="doctor-management-view" class="d-none">
                <h2 class="mb-4">Doctor Management</h2>
                <div class="d-flex justify-content-end mb-4">
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addDoctorModal">
                        <i class="fas fa-plus me-2"></i>Add New Doctor
                    </button>
                </div>
                <!-- Doctor List Table -->
                <div class="card info-card border-0">
                    <div class="card-header bg-info text-white">
                        <h5 class="mb-0">Registered Doctors List</h5>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-hover mb-0">
                                <thead>
                                    <tr><th>ID</th><th>Name</th><th>Specialization</th><th>Email</th><th>Action</th></tr>
                                </thead>
                                <tbody id="doctor-list-body">
                                    <!-- Doctor data will be dynamically injected here -->
                                </tbody>
                            </table>
                        </div>
                        <div id="doctor-loading-spinner" class="text-center p-5 d-none">
                            <div class="spinner-border text-info" role="status">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                            <p class="mt-2 text-info">Fetching doctor data...</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- --------------------------------------- -->
            <!-- 3. ADMIN VIEW -->
            <!-- --------------------------------------- -->
            <div id="admin-view" class="d-none">
                <h2 class="mb-4">System Administration</h2>
                <div class="card p-4 shadow-sm border-0">
                    <h5 class="card-title text-danger"><i class="fas fa-exclamation-triangle me-2"></i>Admin Dashboard Placeholder</h5>
                    <p class="text-muted">This section is where you manage user roles, system settings, and other high-level administrative functions.</p>
                </div>
            </div>

            <!-- --------------------------------------- -->
            <!-- 4. PATIENT REGISTER VIEW -->
            <!-- --------------------------------------- -->
            <div id="patient-register-view" class="d-none">
                <h2 class="mb-4">Patient Register (File Lookup)</h2>
                
                <!-- Registration Form - Visible only to Admin -->
                <div id="patient-registration-form-container" class="card p-4 shadow-sm border-0 mb-4">
                    <h5 class="card-title text-success mb-4"><i class="fas fa-user-plus me-2"></i>New Patient Intake Form</h5>
                    <form id="add-patient-form">
                        <h6 class="text-uppercase text-primary mb-3 mt-3 border-bottom pb-2">Personal Details</h6>
                        <div class="row g-3">
                            <div class="col-md-4"><label for="patient-file-no" class="form-label">Patient ID / File No.</label><input type="text" class="form-control" id="patient-file-no" placeholder="Auto-generated or Manual ID" required></div>
                            <div class="col-md-4"><label for="patient-first-name" class="form-label">First Name</label><input type="text" class="form-control" id="patient-first-name" required></div>
                            <div class="col-md-4"><label for="patient-last-name" class="form-label">Last Name</label><input type="text" class="form-control" id="patient-last-name" required></div>
                            <div class="col-md-3"><label for="patient-dob" class="form-label">Date of Birth</label><input type="date" class="form-control" id="patient-dob" required></div>
                            <div class="col-md-3"><label for="patient-gender" class="form-label">Gender</label><select class="form-select" id="patient-gender" required><option value="">Choose...</option><option>Male</option><option>Female</option><option>Other</option></select></div>
                            <div class="col-md-3"><label for="patient-blood-group" class="form-label">Blood Group</label><select class="form-select" id="patient-blood-group"><option value="">N/A</option><option>A+</option><option>A-</option><option>B+</option><option>B-</option><option>AB+</option><option>AB-</option><option>O+</option><option>O-</option></select></div>
                        </div>
                        <h6 class="text-uppercase text-primary mb-3 mt-4 border-bottom pb-2">Contact Details</h6>
                        <div class="row g-3">
                            <div class="col-md-6"><label for="patient-phone" class="form-label">Phone Number</label><input type="tel" class="form-control" id="patient-phone" required></div>
                            <div class="col-md-6"><label for="patient-email" class="form-label">Email Address</label><input type="email" class="form-control" id="patient-email"></div>
                            <div class="col-12"><label for="patient-address" class="form-label">Residential Address</label><textarea class="form-control" id="patient-address" rows="2" required></textarea></div>
                        </div>
                        <h6 class="text-uppercase text-primary mb-3 mt-4 border-bottom pb-2">Medical History (Initial)</h6>
                        <div class="row g-3">
                            <div class="col-12"><label for="patient-allergies" class="form-label">Known Allergies / Existing Conditions</label><textarea class="form-control" id="patient-allergies" rows="3" placeholder="List any known allergies to medication, food, or any chronic conditions (e.g., Diabetes, Hypertension)."></textarea></div>
                        </div>
                    </form>
                    <div class="mt-4 pt-3 border-top d-flex justify-content-end">
                        <button type="button" class="btn btn-success" id="submit-patient-btn">Register Patient</button>
                    </div>
                </div>
                
                <!-- Patient Search/Lookup for Doctor/Admin -->
                <div class="card p-4 shadow-sm border-0">
                     <h5 class="card-title text-info mb-4"><i class="fas fa-search me-2"></i>Patient File Lookup</h5>
                     <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Enter Patient ID (e.g., P001) or Name" aria-label="Search Patient" id="patient-search-input">
                        <button class="btn btn-primary" type="button" id="patient-search-btn"><i class="fas fa-search me-1"></i> Search</button>
                    </div>
                    <div id="patient-search-results" class="mt-3">
                        <p class="text-muted">Search results will appear here. Doctors can view full patient records after searching.</p>
                        <!-- Example Patient Record (Visible after search) -->
                        <div class="card p-3 border-primary" style="display: none;" id="mock-patient-record">
                            <h6 class="text-primary">File: P001 - Virginia Rose</h6>
                            <p class="mb-1 small"><strong>DOB:</strong> 1985-04-21 | <strong>Gender:</strong> Female | <strong>Blood:</strong> A+</p>
                            <p class="mb-1 small"><strong>Phone:</strong> (555) 123-4567 | <strong>Allergies:</strong> Penicillin</p>
                            <span class="badge bg-danger mt-2">Condition: Mumps</span>
                        </div>
                    </div>
                </div>

            </div>

            <!-- --------------------------------------- -->
            <!-- 5. APPOINTMENT VIEW -->
            <!-- --------------------------------------- -->
            <div id="appointment-view" class="d-none">
                <h2 class="mb-4">Appointment Scheduling</h2>
                
                <div class="row g-4">
                    
                    <!-- 5a. DOCTOR SCHEDULE MANAGEMENT (Visible only to Doctor) -->
                    <div class="col-lg-4" id="doctor-schedule-ui">
                        <div class="card p-4 shadow-sm border-0 h-100 bg-light">
                            <h5 class="card-title text-success mb-4"><i class="fas fa-clock me-2"></i>My Weekly Availability</h5>
                            <form id="set-schedule-form">
                                <p class="small text-muted border-bottom pb-2">Set your daily standard working hours.</p>
                                <div class="mb-3">
                                    <label for="schedule-day" class="form-label">Day of Week</label>
                                    <select class="form-select" id="schedule-day" required>
                                        <option value="Monday">Monday</option>
                                        <option value="Tuesday">Tuesday</option>
                                        <option value="Wednesday">Wednesday</option>
                                        <option value="Thursday">Thursday</option>
                                        <option value="Friday">Friday</option>
                                    </select>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-6">
                                        <label for="schedule-start" class="form-label">Start Time</label>
                                        <input type="time" class="form-control" id="schedule-start" value="09:00" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="schedule-end" class="form-label">End Time</label>
                                        <input type="time" class="form-control" id="schedule-end" value="17:00" required>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-success w-100" id="submit-schedule-btn">Save Schedule</button>
                            </form>
                            <div class="mt-4 pt-3 border-top">
                                <h6 class="text-secondary">Current Schedule</h6>
                                <ul class="list-group list-group-flush small" id="current-schedule-list">
                                    <!-- Schedule items mocked in JS for current doctor -->
                                </ul>
                            </div>
                        </div>
                    </div>
                    
                    <!-- 5b. SCHEDULE APPOINTMENT FORM (Visible only to Admin/Receptionist) -->
                    <div class="col-lg-4" id="schedule-appointment-ui">
                        <div class="card p-4 shadow-sm border-0 h-100">
                            <h5 class="card-title text-primary mb-4"><i class="fas fa-calendar-plus me-2"></i>New Appointment</h5>
                            <form id="schedule-appointment-form">
                                <div class="mb-3">
                                    <label for="appt-patient-id" class="form-label">Patient File ID</label>
                                    <input type="text" class="form-control" id="appt-patient-id" placeholder="e.g., P001" required>
                                </div>
                                <div class="mb-3">
                                    <label for="appt-doctor" class="form-label">Select Doctor</label>
                                    <select class="form-select" id="appt-doctor" required>
                                        <option value="">Choose Doctor...</option>
                                        <!-- Doctor options injected by JS -->
                                    </select>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-6">
                                        <label for="appt-date" class="form-label">Date</label>
                                        <input type="date" class="form-control" id="appt-date" required>
                                    </div>
                                    <div class="col-6">
                                        <label for="appt-time" class="form-label">Available Time Slot</label>
                                        <select class="form-select" id="appt-time" disabled required>
                                            <option value="">Select Doctor & Date First</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <label for="appt-reason" class="form-label">Reason for Visit</label>
                                    <textarea class="form-control" id="appt-reason" rows="2" required></textarea>
                                </div>
                                <button type="button" class="btn btn-primary w-100" id="submit-appointment-btn">Schedule Appointment</button>
                            </form>
                        </div>
                    </div>

                    <!-- 5c. Upcoming Appointments List -->
                    <div class="col-lg-8" id="appointment-list-container">
                        <div class="card info-card border-0 h-100">
                            <div class="card-header bg-primary text-white">
                                <h5 class="mb-0">Upcoming Appointments</h5>
                            </div>
                            <div class="card-body p-0">
                                <div class="table-responsive">
                                    <table class="table table-hover mb-0">
                                        <thead>
                                            <tr><th>ID</th><th>Patient</th><th>Doctor</th><th>Date & Time</th><th>Status</th><th>Action</th></tr>
                                        </thead>
                                        <tbody id="appointment-list-body">
                                            <!-- Appointments will be dynamically injected here -->
                                        </tbody>
                                    </table>
                                </div>
                                <div id="appointment-loading-spinner" class="text-center p-5 d-none">
                                    <div class="spinner-border text-primary" role="status">
                                        <span class="visually-hidden">Loading...</span>
                                    </div>
                                    <p class="mt-2 text-primary">Fetching appointments...</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- --------------------------------------- -->
            <!-- 6. BILLING VIEW -->
            <!-- --------------------------------------- -->
            <div id="billing-view" class="d-none">
                <h2 class="mb-4">Billing and Invoicing</h2>
                
                <!-- Billing Summary Card -->
                <div class="row g-4 mb-5">
                    <div class="col-6 col-md-4 col-lg-3">
                        <div class="card info-card border-0 h-100 bg-white border-warning border-start border-5">
                            <div class="card-body">
                                <p class="text-uppercase text-muted mb-1 small">Total Outstanding</p>
                                <h3 class="card-title text-warning mb-0">$ <span id="total-outstanding-value">0.00</span></h3>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row g-4">
                    <!-- Invoice Creation Form - Visible only to Admin -->
                    <div class="col-lg-5" id="invoice-creation-form-container">
                        <div class="card p-4 shadow-sm border-0 h-100">
                            <h5 class="card-title text-primary mb-4"><i class="fas fa-file-invoice me-2"></i>Create New Invoice</h5>
                            <form id="create-invoice-form">
                                <div class="mb-3"><label for="invoice-patient-id" class="form-label">Patient File ID</label><input type="text" class="form-control" id="invoice-patient-id" placeholder="e.g., P001" required></div>
                                <div class="mb-3"><label for="invoice-description" class="form-label">Description</label><input type="text" class="form-control" id="invoice-description" required></div>
                                <div class="mb-3"><label for="invoice-amount" class="form-label">Amount ($)</label><input type="number" class="form-control" id="invoice-amount" step="0.01" min="0.01" required></div>
                                <div class="mb-4"><label for="invoice-status" class="form-label">Status</label><select class="form-select" id="invoice-status" required><option value="Pending">Pending</option><option value="Paid">Paid</option><option value="Cancelled">Cancelled</option></select></div>
                                <button type="button" class="btn btn-warning w-100" id="submit-invoice-btn">Generate & Save Invoice</button>
                            </form>
                        </div>
                    </div>

                    <!-- Recent Invoices List -->
                    <div class="col-lg-7" id="invoice-list-container">
                        <div class="card info-card border-0 h-100">
                            <div class="card-header bg-secondary text-white">
                                <h5 class="mb-0">Recent Billing Transactions</h5>
                            </div>
                            <div class="card-body p-0">
                                <div class="table-responsive">
                                    <table class="table table-hover mb-0">
                                        <thead>
                                            <tr><th>Invoice ID</th><th>Patient ID</th><th>Amount</th><th>Status</th><th>Date</th><th>Action</th></tr>
                                        </thead>
                                        <tbody id="invoice-list-body">
                                            <!-- Invoices will be dynamically injected here -->
                                        </tbody>
                                    </table>
                                </div>
                                <div id="billing-loading-spinner" class="text-center p-5 d-none">
                                    <div class="spinner-border text-secondary" role="status">
                                        <span class="visually-hidden">Loading...</span>
                                    </div>
                                    <p class="mt-2 text-secondary">Fetching billing data...</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Modal for Add New Doctor Form -->
    <div class="modal fade" id="addDoctorModal" tabindex="-1" aria-labelledby="addDoctorModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content info-card">
          <div class="modal-header bg-success text-white">
            <h5 class="modal-title" id="addDoctorModalLabel">Add New Doctor</h5>
            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <h6 class="text-uppercase text-primary mb-3">Basic Information</h6>
            <form id="add-doctor-form">
                <div class="row g-3">
                    <div class="col-md-6"><label for="doctor-file-no" class="form-label">File ID (Unique ID)</label><input type="text" class="form-control" id="doctor-file-no" required></div>
                    <div class="col-md-6"><label for="doctor-title" class="form-label">Title/Scheme</label><select class="form-select" id="doctor-title" required><option value="">Choose...</option><option>Dr.</option><option>Prof.</option><option>Ms.</option></select></div>
                    <div class="col-md-6"><label for="doctor-first-name" class="form-label">First Name</label><input type="text" class="form-control" id="doctor-first-name" required></div>
                    <div class="col-md-6"><label for="doctor-middle-name" class="form-label">Middle Name</label><input type="text" class="form-control" id="doctor-middle-name"></div>
                    <div class="col-md-6"><label for="doctor-dob" class="form-label">Date of Birth</label><input type="date" class="form-control" id="doctor-dob" required></div>
                    <div class="col-md-6"><label for="doctor-gender" class="form-label">Gender</label><select class="form-select" id="doctor-gender" required><option value="">Choose...</option><option>Male</option><option>Female</option><option>Other</option></select></div>
                    <div class="col-md-6"><label for="doctor-speciality" class="form-label">Speciality</label><select class="form-select" id="doctor-speciality" required><option value="">Choose...</option><option>Cardiology</option><option>Neurology</option><option>Pediatrics</option><option>General Practice</option></select></div>
                    <div class="col-md-6"><label for="doctor-phone" class="form-label">Phone Number</label><input type="tel" class="form-control" id="doctor-phone" required></div>
                    <div class="col-md-6"><label for="doctor-email" class="form-label">Email</label><input type="email" class="form-control" id="doctor-email" required></div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-success" id="submit-doctor-btn">Submit</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 3. jQuery and Bootstrap JS CDNs -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // --- ROLE SIMULATION ---
        // Change this variable to 'doctor' to see the Doctor's schedule management tools!
        const CURRENT_USER_ROLE = 'admin'; 
        const CURRENT_DOCTOR_ID = 'D1001'; // Mock ID for the logged-in doctor
        
        // Mock data to display the UI correctly before your backend is connected
        const MOCK_DASHBOARD_DATA = {
            summary: [
                { title: "New Patient Today", value: 3, icon: "fas fa-user-plus", color: "primary" },
                { title: "Old Patient", value: 12, icon: "fas fa-user-check", color: "success" },
                { title: "Total Patient", value: 15, icon: "fas fa-users", color: "warning" },
                { title: "Resolved", value: 10, icon: "fas fa-check-circle", color: "danger" }
            ],
            patients: [
                { id: 1, firstName: "Virginia", lastName: "Rose", disease: "Health", diseaseColor: "danger" },
                { id: 2, firstName: "Gaylord", lastName: "Gaysord", disease: "Common", diseaseColor: "warning" },
                { id: 3, firstName: "Jacqueline", lastName: "Woods", disease: "Mumps", diseaseColor: "info" },
                { id: 4, firstName: "Jonathan", lastName: "Lewis", disease: "Malaria", diseaseColor: "success" },
            ]
        };

        const MOCK_DOCTOR_DATA = [
            { id: 'D1001', name: 'Dr. Evelyn Reed', specialization: 'Cardiology', email: 'e.reed@clinic.com' },
            { id: 'D1002', name: 'Dr. Marcus Cole', specialization: 'Neurology', email: 'm.cole@clinic.com' },
            { id: 'D1003', name: 'Dr. Sarah Jonson', specialization: 'Pediatrics', email: 's.jonson@clinic.com' },
            { id: 'D1004', name: 'Dr. Ben Carter', specialization: 'General Practice', email: 'b.carter@clinic.com' },
        ];
        
        const MOCK_BILLING_DATA = {
            totalOutstanding: 1250.75,
            invoices: [
                { id: 'INV-0010', patientId: 'P001', amount: 350.50, status: 'Paid', date: '2025-10-15' },
                { id: 'INV-0009', patientId: 'P005', amount: 800.25, status: 'Pending', date: '2025-10-14' },
                { id: 'INV-0008', patientId: 'P002', amount: 100.00, status: 'Pending', date: '2025-10-14' },
            ]
        };

        // Mock Appointment Data
        let MOCK_APPOINTMENTS_DATA = [
            { id: 'A2001', patientId: 'P001', patientName: 'Virginia Rose', doctorId: 'D1001', doctorName: 'Dr. Evelyn Reed', date: '2025-10-20', time: '10:00', status: 'Scheduled' },
            { id: 'A2002', patientId: 'P005', patientName: 'Margareth Griffin', doctorId: 'D1002', doctorName: 'Dr. Marcus Cole', date: '2025-10-21', time: '11:30', status: 'Confirmed' },
            { id: 'A2003', patientId: 'P010', patientName: 'Alex Johnson', doctorId: 'D1001', doctorName: 'Dr. Evelyn Reed', date: '2025-10-20', time: '10:30', status: 'Scheduled' },
        ];
        
        // Mock Doctor Availability Data: Doctor ID -> { Day: [Start, End] }
        let MOCK_DOCTOR_AVAILABILITY = {
            'D1001': {
                'Monday': ['09:00', '17:00'],
                'Tuesday': ['09:00', '17:00'],
                'Wednesday': ['09:00', '13:00'],
                'Thursday': ['09:00', '17:00'],
                'Friday': ['09:00', '17:00'],
            },
            'D1002': {
                'Tuesday': ['10:00', '15:00'],
                'Thursday': ['10:00', '15:00'],
            },
            // Other doctors default to no set schedule
        };

        $(document).ready(function() {
            // 1. Apply role permissions and setup user UI
            applyRolePermissions();

            // 2. Set initial view and load dashboard data
            loadContent('dashboard-view'); 

            // 3. Populate the Doctor dropdown on load
            populateDoctorSelect(); 

            // Event handlers
            $('.list-group-item').on('click', function(e) {
                e.preventDefault();
                const viewId = $(this).data('view-id');
                if (viewId) {
                    loadContent(viewId);
                    $('.list-group-item').removeClass('active');
                    $(this).addClass('active');
                    if ($('#sidebar-wrapper').hasClass('toggled')) {
                        $("#wrapper").toggleClass("toggled");
                        $("#sidebar-wrapper").toggleClass("toggled");
                    }
                }
            });

            // Schedule-related events
            $('#appt-doctor, #appt-date').on('change', updateAvailableSlots);
            $('#submit-doctor-btn').on('click', submitDoctor);
            $('#submit-patient-btn').on('click', submitPatient);
            $('#submit-invoice-btn').on('click', submitInvoice);
            $('#submit-appointment-btn').on('click', submitAppointment); 
            $('#submit-schedule-btn').on('click', setDoctorSchedule); // New schedule handler
            $('#patient-search-btn').on('click', searchPatient); 
            $("#menu-toggle").click(function(e) { e.preventDefault(); $("#wrapper").toggleClass("toggled"); $("#sidebar-wrapper").toggleClass("toggled"); });
        });
        
        /**
         * Applies visibility rules and sets up the current user's UI details.
         */
        function applyRolePermissions() {
            const isDoctor = CURRENT_USER_ROLE === 'doctor';
            const doctorName = isDoctor ? MOCK_DOCTOR_DATA.find(d => d.id === CURRENT_DOCTOR_ID)?.name || 'Dr. User' : 'Admin';
            
            // --- UI Updates ---
            $('#user-name').text(doctorName);
            $('#welcome-message').text(`Welcome ${doctorName}`);
            $('#user-avatar').attr('src', isDoctor 
                ? `https://placehold.co/50x50/007bff/white?text=${doctorName.split(' ')[1][0]}` 
                : 'https://placehold.co/50x50/dc3545/white?text=ADM'
            );

            // --- Sidebar & Content Filtering ---
            $('#sidebar-menu a[data-role-access]').each(function() {
                const requiredRoles = $(this).data('role-access').split(',');
                if (!requiredRoles.includes(CURRENT_USER_ROLE)) {
                    $(this).hide();
                }
            });
            
            // Patient Register View
            $('#patient-registration-form-container').toggle(CURRENT_USER_ROLE === 'admin');

            // Billing View
            $('#invoice-creation-form-container').toggle(CURRENT_USER_ROLE === 'admin');
            $('#invoice-list-container')
                .removeClass('col-lg-7 col-lg-12')
                .addClass(CURRENT_USER_ROLE === 'admin' ? 'col-lg-7' : 'col-lg-12');
            
            // Appointment View Conditional Layout
            if (isDoctor) {
                $('#schedule-appointment-ui').hide();
                $('#doctor-schedule-ui').show();
                $('#appointment-list-container').removeClass('col-lg-8').addClass('col-lg-8');
            } else {
                $('#schedule-appointment-ui').show();
                $('#doctor-schedule-ui').hide();
                $('#appointment-list-container').removeClass('col-lg-8').addClass('col-lg-8');
            }
        }

        /**
         * Populates the Doctor selection dropdown using MOCK_DOCTOR_DATA.
         */
        function populateDoctorSelect() {
            const $select = $('#appt-doctor');
            $select.find('option:not(:first)').remove();

            MOCK_DOCTOR_DATA.forEach(doctor => {
                const optionHtml = `<option value="${doctor.id}">${doctor.name} (${doctor.specialization})</option>`;
                $select.append(optionHtml);
            });
        }
        
        /**
         * Calculates and updates the available time slots for the selected doctor and date.
         */
        function updateAvailableSlots() {
            const doctorId = $('#appt-doctor').val();
            const dateStr = $('#appt-date').val();
            const $slotSelect = $('#appt-time');
            
            $slotSelect.empty().prop('disabled', true).append('<option value="">Select Doctor & Date First</option>');

            if (!doctorId || !dateStr) {
                return;
            }

            const date = new Date(dateStr);
            const dayOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][date.getDay()];
            
            const schedule = MOCK_DOCTOR_AVAILABILITY[doctorId];
            if (!schedule || !schedule[dayOfWeek]) {
                 $slotSelect.append(`<option value="" disabled>Dr. has no schedule set for ${dayOfWeek}</option>`);
                 return;
            }
            
            $slotSelect.prop('disabled', false).empty().append('<option value="">Choose Time Slot...</option>');

            const [startTime, endTime] = schedule[dayOfWeek];
            
            // Generate 30-minute slots between start and end
            let current = new Date(`2000/01/01 ${startTime}`);
            const end = new Date(`2000/01/01 ${endTime}`);
            
            const bookedSlots = MOCK_APPOINTMENTS_DATA
                .filter(appt => appt.doctorId === doctorId && appt.date === dateStr)
                .map(appt => appt.time);

            while (current < end) {
                const slotTime = current.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false });
                
                // Blocked slot logic: hide if booked and not cancelled
                if (!bookedSlots.includes(slotTime)) {
                     $slotSelect.append(`<option value="${slotTime}">${slotTime}</option>`);
                }
                
                current.setMinutes(current.getMinutes() + 30); // Increment by 30 mins
            }
            
            if ($slotSelect.find('option').length <= 1) {
                $slotSelect.append(`<option value="" disabled>No available slots left!</option>`);
            }
        }
        
        /**
         * Doctor sets their weekly schedule. (Mock)
         */
        function setDoctorSchedule() {
            const $form = $('#set-schedule-form');
            if (!$form[0].checkValidity()) {
                $form[0].reportValidity();
                return;
            }

            const day = $('#schedule-day').val();
            const start = $('#schedule-start').val();
            const end = $('#schedule-end').val();
            
            if (!MOCK_DOCTOR_AVAILABILITY[CURRENT_DOCTOR_ID]) {
                MOCK_DOCTOR_AVAILABILITY[CURRENT_DOCTOR_ID] = {};
            }
            
            MOCK_DOCTOR_AVAILABILITY[CURRENT_DOCTOR_ID][day] = [start, end];
            
            updateDoctorScheduleList();
            showAlert(`Schedule for ${day} saved successfully for ${start} - ${end}!`, 'success');
        }
        
        /**
         * Updates the list showing the current doctor's schedule.
         */
        function updateDoctorScheduleList() {
            const $list = $('#current-schedule-list');
            $list.empty();
            const schedule = MOCK_DOCTOR_AVAILABILITY[CURRENT_DOCTOR_ID];
            
            if (!schedule || Object.keys(schedule).length === 0) {
                $list.html('<li class="list-group-item text-muted">No schedule set yet.</li>');
                return;
            }
            
            Object.entries(schedule).forEach(([day, times]) => {
                const itemHtml = `<li class="list-group-item d-flex justify-content-between"><strong>${day}:</strong> <span>${times[0]} - ${times[1]}</span></li>`;
                $list.append(itemHtml);
            });
        }

        /**
         * Switches the main content area to display the requested view.
         * @param {string} viewId - The ID of the div to show (e.g., 'dashboard-view').
         */
        function loadContent(viewId) {
            // Hide all content views
            $('#page-content-wrapper > div').addClass('d-none');
            // Show the requested view
            $(`#${viewId}`).removeClass('d-none');

            // Only fetch data if we are loading the relevant view
            if (viewId === 'dashboard-view') {
                fetchDashboardData();
            } else if (viewId === 'doctor-management-view') {
                fetchDoctors(); 
            } else if (viewId === 'billing-view') {
                fetchBillingData();
            } else if (viewId === 'appointment-view') { 
                fetchAppointments();
                if (CURRENT_USER_ROLE === 'doctor') {
                    updateDoctorScheduleList();
                } else {
                    updateAvailableSlots(); // Ensure slots are calculated if Admin lands here
                }
            }
        }

        /* --- API INTERACTION / MOCK FUNCTIONS --- */

        function submitDoctor() {
            const $form = $('#add-doctor-form');
            if (!$form[0].checkValidity()) { $form[0].reportValidity(); return; }
            const $btn = $('#submit-doctor-btn');
            $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm me-2"></span>Submitting...');

            setTimeout(() => {
                const newId = $('#doctor-file-no').val() || `D${Math.floor(Math.random() * 9000) + 1000}`;
                const newDoctor = {
                    id: newId, 
                    name: `Dr. ${$('#doctor-first-name').val()} ${$('#doctor-middle-name').val() || ''} ${$('#doctor-last-name').val() || ''}`.trim(), 
                    specialization: $('#doctor-speciality').val(), 
                    email: $('#doctor-email').val() 
                };
                MOCK_DOCTOR_DATA.push(newDoctor); // Add to mock list
                populateDoctorSelect(); // Update dropdown
                showAlert('Doctor added successfully!', 'success');
                $('#addDoctorModal').modal('hide');
                $form[0].reset(); 
                $btn.prop('disabled', false).html('Submit');
                fetchDoctors(); 
            }, 1000);
        }

        function submitPatient() {
            const $form = $('#add-patient-form');
            if (!$form[0].checkValidity()) { $form[0].reportValidity(); return; }
            const $btn = $('#submit-patient-btn');
            $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm me-2"></span>Registering...');

            setTimeout(() => {
                showAlert(`Patient registered successfully! ID: P${Math.floor(Math.random() * 100) + 10}`, 'success');
                $form[0].reset(); 
                $btn.prop('disabled', false).html('Register Patient');
            }, 1000);
        }

        function submitInvoice() {
            const $form = $('#create-invoice-form');
            if (!$form[0].checkValidity()) { $form[0].reportValidity(); return; }
            const $btn = $('#submit-invoice-btn');
            $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm me-2"></span>Generating...');

            setTimeout(() => {
                $btn.prop('disabled', false).html('Generate & Save Invoice');
                const newId = `INV-${Math.floor(Math.random() * 1000) + 100}`;
                showAlert(`Invoice ${newId} created successfully!`, 'success');
                $form[0].reset(); 
                fetchBillingData();
            }, 1000);
        }
        
        function submitAppointment() {
            const $form = $('#schedule-appointment-form');
            if (!$form[0].checkValidity()) { $form[0].reportValidity(); return; }

            const doctorId = $('#appt-doctor').val();
            const doctorName = $('#appt-doctor option:selected').text().split(' (')[0];
            const patientId = $('#appt-patient-id').val();
            const date = $('#appt-date').val();
            const time = $('#appt-time').val();

            if (time === '') {
                 showAlert('Please select an available time slot.', 'warning');
                 return;
            }

            const $btn = $('#submit-appointment-btn');
            $btn.prop('disabled', true).html('<span class="spinner-border spinner-border-sm me-2"></span>Scheduling...');

            setTimeout(() => {
                $btn.prop('disabled', false).html('Schedule Appointment');
                const newId = `A${Math.floor(Math.random() * 9000) + 1000}`;
                const patientName = 'New Patient'; // Mock patient name
                
                // CRITICAL: Book the slot by adding the appointment
                MOCK_APPOINTMENTS_DATA.push({
                    id: newId, patientId, patientName, doctorId, doctorName, date, time, status: 'Scheduled'
                });

                showAlert(`Appointment ${newId} scheduled for Patient ${patientId} with ${doctorName} on ${date} at ${time}. That time slot is now blocked.`, 'success');
                $form[0].reset(); 
                fetchAppointments(); // Refresh list
                updateAvailableSlots(); // Re-calculate slots (blocking the booked one)
            }, 1000);
        }

        /**
         * Mock function for patient search
         */
        function searchPatient() {
            const searchInput = $('#patient-search-input').val().trim();
            if (searchInput) {
                $('#mock-patient-record').slideDown(300);
                showAlert(`Successfully retrieved patient file for ${searchInput}.`, 'info');
            } else {
                showAlert('Please enter a Patient ID or Name to search.', 'warning');
                $('#mock-patient-record').slideUp(300);
            }
        }


        /* --- DATA FETCHING AND RENDERING FUNCTIONS --- */

        function fetchAppointments() {
            const $body = $('#appointment-list-body');
            const $spinner = $('#appointment-loading-spinner');
            $body.empty();
            $spinner.removeClass('d-none');

            setTimeout(() => {
                $spinner.addClass('d-none');
                
                // Doctors only see their own appointments
                const appointmentsToShow = CURRENT_USER_ROLE === 'doctor'
                    ? MOCK_APPOINTMENTS_DATA.filter(appt => appt.doctorId === CURRENT_DOCTOR_ID)
                    : MOCK_APPOINTMENTS_DATA;

                updateAppointmentView(appointmentsToShow);
            }, 700);
        }

        function updateAppointmentView(appointments) {
            const $body = $('#appointment-list-body');
            $body.empty();

            if (appointments.length === 0) {
                $body.html('<tr><td colspan="6" class="text-center p-4 text-muted">No upcoming appointments found.</td></tr>');
                return;
            }
            
            appointments.forEach(appt => {
                let statusClass = 'primary';
                if (appt.status === 'Confirmed') { statusClass = 'success'; } 
                else if (appt.status === 'Completed') { statusClass = 'secondary'; } 
                else if (appt.status === 'Cancelled') { statusClass = 'danger'; }

                const dateTimeStr = `${appt.date} ${appt.time}`;

                const rowHtml = `
                    <tr>
                        <th scope="row">${appt.id}</th>
                        <td><a href="#" onclick="viewPatient('${appt.patientId}')">${appt.patientId} - ${appt.patientName}</a></td>
                        <td>${appt.doctorName}</td>
                        <td>${dateTimeStr}</td>
                        <td><span class="badge text-bg-${statusClass}">${appt.status}</span></td>
                        <td>
                            <button class="btn btn-sm btn-outline-info me-1" onclick="viewAppointment('${appt.id}')" title="View Details"><i class="fas fa-search"></i></button>
                            <button class="btn btn-sm btn-outline-danger" onclick="cancelAppointment('${appt.id}')" title="Cancel Appointment"><i class="fas fa-times-circle"></i></button>
                        </td>
                    </tr>
                `;
                $body.append(rowHtml);
            });
        }
        
        function fetchDashboardData() { /* ... unchanged ... */ }
        function fetchDoctors() { /* ... unchanged ... */ }
        function fetchBillingData() { /* ... unchanged ... */ }
        function updateDoctorView(doctors) { /* ... unchanged ... */ }
        function updateBillingView(data) { /* ... unchanged ... */ }
        function updateDashboard(data) { /* ... unchanged ... */ }
        function showAlert(message, type) { /* ... unchanged ... */ }
        function viewPatient(patientId) { showAlert(`Viewing Patient ID: ${patientId}. This would load the full medical record.`, 'info'); }
        function viewAppointment(appointmentId) { showAlert(`Viewing Appointment ID: ${appointmentId}. (Detailed appointment info)`, 'info'); }
        function cancelAppointment(appointmentId) { showAlert(`Cancelling Appointment ID: ${appointmentId}. (AJAX update call)`, 'danger'); }

    </script>

</body>
</html>



























































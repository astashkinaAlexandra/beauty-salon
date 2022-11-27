package ru.mirea.study.beautysalon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "appointment_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;
    @Column(name = "start_time", nullable = false)
    private Time appointmentStartTime;
    @Column(name = "status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.Booked;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private SalonService service;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;

    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate, Time appointmentStartTime, AppointmentStatus status, Employee employee, SalonService service, User user) {
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.status = status;
        this.employee = employee;
        this.service = service;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(Time appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public SalonService getService() {
        return service;
    }

    public void setService(SalonService service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
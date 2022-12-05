package ru.mirea.study.beautysalon.service;

import ru.mirea.study.beautysalon.model.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Appointment saveAppointment(Appointment appointment);

    Appointment getAppointmentById(Long id);

    List<Appointment> getAppointmentByUserId(Long id);

    Appointment updateAppointment(Long id, Appointment appointment);

//    Appointment updateAppointmentStatus(Long id, Appointment appointment);

    void deleteAppointmentById(Long id);
}
package ru.mirea.study.beautysalon.service;

import org.springframework.stereotype.Service;
import ru.mirea.study.beautysalon.model.Appointment;
import ru.mirea.study.beautysalon.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);
        return appointments;
    }

//    @Override
//    public Appointment save(User user) {
//        Appointment appointment = appointmentRepository.findByUser(user);
//        appointment.setUser(user);
//        return appointmentRepository.save(appointment);
//    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

//    @Override
//    public Appointment getAppointmentById(Long id) {
//        return appointmentRepository.findById(id).get();
//    }
//
//    @Override
//    public Appointment updateAppointment(Long id, Appointment appointment) {
//        Appointment appointmentFromDb = appointmentRepository.findById(id).get();
//        System.out.println(appointmentFromDb.toString());
//        appointmentFromDb.setAppointmentDate(appointment.getAppointmentDate());
//        appointmentFromDb.setAppointmentStartTime(appointment.getAppointmentStartTime());
//        appointmentFromDb.setEmployeeName(appointment.getEmployeeName());
//        appointmentFromDb.setServiceName(appointment.getServiceName());
//        appointmentFromDb.setStatus(appointment.getStatus());
//        return appointmentRepository.save(appointment);
//    }
//
//    @Override
//    public Appointment updateAppointmentStatus(Long id, Appointment appointment) {
//        Optional<Appointment> appointmentList = appointmentRepository.findById(id);
//
//        if (appointmentList.isPresent()) {
//            if (appointment.getStatus() != null) {
//                appointmentList.get().setStatus(appointment.getStatus());
//            }
//            return appointmentRepository.save(appointmentList.get());
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteAppointmentById(Long id) {
//        appointmentRepository.deleteById(id);
//    }
}

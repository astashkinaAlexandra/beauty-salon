package ru.mirea.study.beautysalon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.study.beautysalon.exception.ResourceNotFoundException;
import ru.mirea.study.beautysalon.model.Appointment;
import ru.mirea.study.beautysalon.repository.AppointmentRepository;
import ru.mirea.study.beautysalon.repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/employees/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByEmployeeId(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
        }

        List<Appointment> appointments = appointmentRepository.findByEmployeeId(employeeId);
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentByEmployeeId(@PathVariable(value = "id") Long appointmentId) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));
        return ResponseEntity.ok().body(appointment);
    }

    @PostMapping("/employees/{id}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable(value = "id") Long employeeId,
                                                         @Valid @RequestBody Appointment appointmentRequest) throws ResourceNotFoundException {
        Appointment appointment = employeeRepository.findById(employeeId).map(employee -> {
            appointmentRequest.setEmployee(employee);
            return appointmentRepository.save(appointmentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(appointment);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long appointmentId,
                                                         @Valid @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));
        appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        appointment.setAppointmentStartTime(appointmentDetails.getAppointmentStartTime());
        appointment.setStatus(appointmentDetails.getStatus());
        final Appointment updateAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(updateAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    public Map<String, Boolean> deleteAppointment(@PathVariable(value = "id") Long appointmentId) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/employees/{id}/appointments")
    public Map<String, Boolean> deleteAllAppointmentsOfEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(("Employee not found for this id :: " + employeeId));
        }

        appointmentRepository.deleteByEmployeeId(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
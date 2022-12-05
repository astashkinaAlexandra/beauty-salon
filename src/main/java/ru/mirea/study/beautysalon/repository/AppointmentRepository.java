package ru.mirea.study.beautysalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.study.beautysalon.model.Appointment;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByEmployeeId(Long employeeId);

    List<Appointment> findByUserId(Long userId);

    @Transactional
    void deleteByEmployeeId(Long employeeId);

    @Transactional
    void deleteByUserId(Long userId);
}
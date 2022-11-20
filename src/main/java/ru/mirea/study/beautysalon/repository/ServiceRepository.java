package ru.mirea.study.beautysalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.study.beautysalon.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
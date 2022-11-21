package ru.mirea.study.beautysalon.service;

import ru.mirea.study.beautysalon.model.SalonService;

import java.util.List;

public interface ServiceService {
    List<SalonService> getAllServices();

    SalonService saveService(SalonService service);

    SalonService getServiceById(Long id);

    SalonService updateService(Long id, SalonService service);

    void deleteServiceById(Long id);
}
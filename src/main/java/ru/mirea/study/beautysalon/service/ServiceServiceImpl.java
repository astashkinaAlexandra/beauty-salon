package ru.mirea.study.beautysalon.service;


import org.springframework.stereotype.Service;
import ru.mirea.study.beautysalon.model.SalonService;
import ru.mirea.study.beautysalon.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {
    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<SalonService> getAllServices() {
        List<SalonService> services = new ArrayList<>();
        serviceRepository.findAll().forEach(services::add);
        return services;
    }

    @Override
    public SalonService saveService(SalonService service) {
        return serviceRepository.save(service);
    }

    @Override
    public SalonService getServiceById(Long id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public SalonService updateService(Long id, SalonService service) {
        SalonService serviceFromDb = serviceRepository.findById(id).get();
        System.out.println(serviceFromDb.toString());
        serviceFromDb.setName(service.getName());
        serviceFromDb.setPrice(service.getPrice());
        serviceFromDb.setDuration(service.getDuration());
        return serviceRepository.save(serviceFromDb);
    }

    @Override
    public void deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
    }
}
package ru.mirea.study.beautysalon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.study.beautysalon.exception.ResourceNotFoundException;
import ru.mirea.study.beautysalon.model.SalonService;
import ru.mirea.study.beautysalon.repository.ServiceRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/services")
    public List<SalonService> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<SalonService> getServiceById(@PathVariable(value = "id") Long serviceID) throws ResourceNotFoundException {
        SalonService service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));
        return ResponseEntity.ok().body(service);
    }

    @PostMapping("/services")
    public SalonService createService(@Valid @RequestBody SalonService service) {
        return serviceRepository.save(service);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<SalonService> updateService(@PathVariable(value = "id") Long serviceID,
                                                      @Valid @RequestBody SalonService serviceDetails) throws ResourceNotFoundException {
        SalonService service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));

        service.setName(serviceDetails.getName());
        service.setPrice(serviceDetails.getPrice());
        service.setDuration(serviceDetails.getDuration());
        SalonService updatedService = serviceRepository.save(service);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/services/{id}")
    public Map<String, Boolean> deleteService(@PathVariable(value = "id") Long serviceID) throws ResourceNotFoundException {
        SalonService service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));

        serviceRepository.delete(service);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

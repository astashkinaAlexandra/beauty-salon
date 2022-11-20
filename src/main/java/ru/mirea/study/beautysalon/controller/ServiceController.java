package ru.mirea.study.beautysalon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.study.beautysalon.exception.ResourceNotFoundException;
import ru.mirea.study.beautysalon.model.Service;
import ru.mirea.study.beautysalon.repository.ServiceRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/services")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable(value = "id") Long serviceID)
            throws ResourceNotFoundException {
        Service service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));
        return ResponseEntity.ok().body(service);
    }

    @PostMapping("/services")
    public Service createService(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Service> updateService(@PathVariable(value = "id") Long serviceID,
                                                   @Valid @RequestBody Service serviceDetails) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));

        service.setName(serviceDetails.getName());
        service.setPrice(serviceDetails.getPrice());
        service.setDuration(serviceDetails.getDuration());
        final Service updatedService = serviceRepository.save(service);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/services/{id}")
    public Map<String, Boolean> deleteService(@PathVariable(value = "id") Long serviceID)
            throws ResourceNotFoundException {
        Service service = serviceRepository.findById(serviceID)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceID));

        serviceRepository.delete(service);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

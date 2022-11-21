package ru.mirea.study.beautysalon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.study.beautysalon.model.Employee;
import ru.mirea.study.beautysalon.model.SalonService;
import ru.mirea.study.beautysalon.service.EmployeeService;
import ru.mirea.study.beautysalon.service.ServiceService;

@Controller
public class MainController {
    private final EmployeeService employeeService;
    private final ServiceService serviceService;

    public MainController(EmployeeService employeeService, ServiceService serviceService) {
        this.employeeService = employeeService;
        this.serviceService = serviceService;
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailId(employee.getEmailId());
        employeeService.updateEmployee(id, existingEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        return "services";
    }

    @GetMapping("/services/new")
    public String createServiceForm(Model model) {
        SalonService services = new SalonService();
        model.addAttribute("service", services);
        return "create_service";
    }

    @PostMapping("/services")
    public String saveService(@ModelAttribute("service") SalonService service) {
        serviceService.saveService(service);
        return "redirect:/services";
    }

    @GetMapping("/services/edit/{id}")
    public String editServiceForm(@PathVariable Long id, Model model) {
        model.addAttribute("service", serviceService.getServiceById(id));
        return "edit_service";
    }

    @PostMapping("/services/{id}")
    public String updateService(@PathVariable Long id, @ModelAttribute("service") SalonService service) {
        SalonService existingService = serviceService.getServiceById(id);
        existingService.setId(id);
        existingService.setName(service.getName());
        existingService.setPrice(service.getPrice());
        existingService.setDuration(service.getDuration());
        serviceService.updateService(id, existingService);
        return "redirect:/services";
    }

    @GetMapping("/services/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteServiceById(id);
        return "redirect:/services";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}

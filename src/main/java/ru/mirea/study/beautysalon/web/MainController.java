package ru.mirea.study.beautysalon.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mirea.study.beautysalon.model.Appointment;
import ru.mirea.study.beautysalon.model.CustomUserDetails;
import ru.mirea.study.beautysalon.model.Employee;
import ru.mirea.study.beautysalon.model.SalonService;
import ru.mirea.study.beautysalon.service.AppointmentService;
import ru.mirea.study.beautysalon.service.EmployeeService;
import ru.mirea.study.beautysalon.service.ServiceService;
import ru.mirea.study.beautysalon.service.UserService;

@Controller
public class MainController {
    private final EmployeeService employeeService;
    private final ServiceService serviceService;
    private final AppointmentService appointmentService;
    private final UserService userService;

    public MainController(EmployeeService employeeService, ServiceService serviceService, AppointmentService appointmentService, UserService userService) {
        this.employeeService = employeeService;
        this.serviceService = serviceService;
        this.appointmentService = appointmentService;
        this.userService = userService;
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
        SalonService service = new SalonService();
        model.addAttribute("service", service);
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

    @GetMapping("/catalog")
    public String catalog(Model model){
        model.addAttribute("services", serviceService.getAllServices());
        return "catalog";
    }

    @GetMapping("/appointments")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments";
    }

//    @PostMapping("/booking/new/{id}")
//    public String saveAppointmentToList(@PathVariable("id") Long appointmentId,
//                                        @AuthenticationPrincipal Authentication authentication) {
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "You must login to book the service";
//        }
//
//
//        appointmentService.saveAppointment()
//
//    }

//    @GetMapping("/booking")
//    public String listBooking(Model model, @AuthenticationPrincipal Authentication authentication){
//        String username = userService.getCurrentlyLoggedInUser(authentication);
//        List<Appointment> appointments = appointmentService.findByUserName(username);
//
//        model.addAttribute("appointments", appointments);
//        return "booking";
//    }

    @GetMapping("/appointments/new")
    public String createAppointmentForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("services", serviceService.getAllServices());
        model.addAttribute("user", customUserDetails);
        return "booking_form";
    }

    @PostMapping("/appointments")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }



//    @GetMapping("/appointments")
//    public String listAppointments(Model model) {
//        model.addAttribute("appointments", appointmentService.getAllAppointments());
//        return "appointments";
//    }
//
//    @GetMapping("/appointments/new")
//    public String createAppointForm(Model model) {
//        Appointment appointment = new Appointment();
//        model.addAttribute("appointment", appointment);
//        return "create_appointment";
//    }
//
//    @PostMapping("/appointments")
//    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
//        appointmentService.saveAppointment(appointment);
//        return "redirect:/appointments";
//    }
//
//    @GetMapping("/appointments/edit/{id}")
//    public String editAppointForm(@PathVariable Long id, Model model) {
//        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
//        return "edit_appointment";
//    }
//
//    @PostMapping("/appointments/{id}")
//    public String updateAppointment(@PathVariable Long id, @ModelAttribute("appointment") Appointment appointment) {
//        Appointment existingAppointment = appointmentService.getAppointmentById(id);
//        existingAppointment.setId(id);
//        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
//        existingAppointment.setAppointmentStartTime(appointment.getAppointmentStartTime());
//        existingAppointment.setEmployeeName(appointment.getEmployeeName());
//        existingAppointment.setStatus(appointment.getStatus());
//        existingAppointment.setServiceName(appointment.getServiceName());
//        appointmentService.updateAppointment(id, existingAppointment);
//        return "redirect:/appointments";
//    }
//
//    @GetMapping("/appointments/{id}")
//    public String deleteAppointment(@PathVariable Long id) {
//        appointmentService.deleteAppointmentById(id);
//        return "redirect:/appointments";
//    }
}

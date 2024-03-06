package club.warmingup.empapp.controller.employee;

import club.warmingup.empapp.dto.employee.EmployeeCreateRequest;
import club.warmingup.empapp.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.save(request);
    }
}

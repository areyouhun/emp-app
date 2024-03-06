package club.warmingup.empapp.service.employee;

import club.warmingup.empapp.domain.Employee;
import club.warmingup.empapp.domain.Role;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EmployeeResponse {
    private String name;
    private String teamName;
    private Role role;
    private LocalDate workStartDate;
    private LocalDate birthday;

    public EmployeeResponse(Employee employee) {
        this.name = employee.getName();
        this.teamName = employee.getTeam().getName();
        this.role = employee.getRole();
        this.workStartDate = employee.getWorkStartDate();
        this.birthday = employee.getBirthday();
    }
}

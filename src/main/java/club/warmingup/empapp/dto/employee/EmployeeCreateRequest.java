package club.warmingup.empapp.dto.employee;

import club.warmingup.empapp.domain.Role;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequest {
    private String name;
    private String teamName;
    private Role role;
    private LocalDate workStartDate;
    private LocalDate birthday;
}

package club.warmingup.empapp.service.employee;

import club.warmingup.empapp.domain.Employee;
import club.warmingup.empapp.domain.Team;
import club.warmingup.empapp.dto.employee.EmployeeCreateRequest;
import club.warmingup.empapp.repository.employee.EmployeeRepository;
import club.warmingup.empapp.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void save(EmployeeCreateRequest request) {
        Team team = teamRepository.findByName(request.getTeamName())
                                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));

        Employee employee = new Employee(request.getName(),
                                        team,
                                        request.getRole(),
                                        request.getWorkStartDate(),
                                        request.getBirthday());
        employeeRepository.save(employee);
    }
}

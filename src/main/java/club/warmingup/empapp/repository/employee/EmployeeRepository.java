package club.warmingup.empapp.repository.employee;

import club.warmingup.empapp.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

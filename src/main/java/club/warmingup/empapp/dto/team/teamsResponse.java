package club.warmingup.empapp.dto.team;

import club.warmingup.empapp.domain.Employee;
import club.warmingup.empapp.domain.Role;
import club.warmingup.empapp.domain.Team;
import lombok.Data;

@Data
public class teamsResponse {
    private String name;
    private String manager;
    private int memberCount;

    public teamsResponse(Team team) {
        this.name = team.getName();
        this.manager = getManager(team);
        this.memberCount = team.countEmployees();
    }

    private String getManager(Team team) {
        Employee manager = team.getEmployees().stream()
                                            .filter(employee -> employee.getRole() == Role.MANAGER)
                                            .findFirst()
                                            .orElse(null);

        return manager != null ? manager.getName() : null;
    }
}

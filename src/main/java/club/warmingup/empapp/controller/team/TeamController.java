package club.warmingup.empapp.controller.team;

import club.warmingup.empapp.dto.team.TeamResponse;
import club.warmingup.empapp.service.team.TeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping("/team")
    public void saveTeam(@RequestParam String name) {
        teamService.save(name);
    }

    @GetMapping("/teams")
    public List<TeamResponse> getTeams() {
        return teamService.findAll();
    }
}

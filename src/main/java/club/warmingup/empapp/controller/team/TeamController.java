package club.warmingup.empapp.controller.team;

import club.warmingup.empapp.domain.Team;
import club.warmingup.empapp.dto.team.teamsResponse;
import club.warmingup.empapp.service.team.TeamService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<teamsResponse> getTeams() {
        return teamService.findAll();
    }
}

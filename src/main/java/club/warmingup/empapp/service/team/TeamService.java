package club.warmingup.empapp.service.team;

import club.warmingup.empapp.domain.Team;
import club.warmingup.empapp.dto.team.teamsResponse;
import club.warmingup.empapp.repository.team.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public List<teamsResponse> findAll() {
        return teamRepository.findAll().stream()
                                        .map(teamsResponse::new)
                                        .collect(Collectors.toList());
    }
}

package club.warmingup.empapp.repository.team;

import club.warmingup.empapp.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);
}

package club.warmingup.empapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false, name = "team_id")
    private Team team;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDate workStartDate;

    @Column(nullable = false)
    private LocalDate birthday;

    public Employee(String name, Team team, Role role, LocalDate workStartDate, LocalDate birthday) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.workStartDate = workStartDate;
        this.birthday = birthday;
    }
}

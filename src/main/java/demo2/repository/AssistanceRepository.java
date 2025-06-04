package demo2.repository;

import demo2.model.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRepository extends JpaRepository<Assistance, Long> {
}

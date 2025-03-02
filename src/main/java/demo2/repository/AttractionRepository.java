package demo2.repository;

import java.util.List;
import demo2.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByLocality_Place(String place);
    void deleteAttractionById(Long id);
}

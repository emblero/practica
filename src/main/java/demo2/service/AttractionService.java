package demo2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo2.repository.AttractionRepository;
import demo2.repository.LocalityRepository;
import demo2.model.Attraction;
import demo2.model.Locality;

@Service
public class AttractionService {
    @Autowired
    private AttractionRepository repository;
    @Autowired
    private LocalityRepository localRepository;

    public void addAttraction(Attraction attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("Attraction cannot be null");
        }
        repository.save(attraction);
    }

    public List<Attraction> getAttractions() {
        return repository.findAll();
    }

    public List<Attraction> getAttractionByLocality_place(String place) {
        return repository.findByLocality_Place(place);
    }

    public void editAttraction(Long id, String description) {
        if (id == null) {
            throw new IllegalArgumentException("Attraction ID cannot be null");
        }
        Attraction attraction = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Attraction not found"));
        attraction.setDescription(description);
        repository.save(attraction);
    }

    public void deleteAttraction(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Attraction ID cannot be null");
        }
        repository.deleteById(id);
    }

    public void addLocality(Locality locality) {
        if (locality == null) {
            throw new IllegalArgumentException("Locality cannot be null");
        }
        localRepository.save(locality);
    }
}
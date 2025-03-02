package demo2.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import demo2.model.Attraction;
import demo2.repository.AttractionRepository;
import demo2.repository.LocalityRepository;
import demo2.service.AttractionService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @InjectMocks
    private AttractionService attractionService;

    @Mock
    AttractionRepository attractionRepository;

    @Mock
    LocalityRepository localityRepository;



    @Test
    void getAttractions(){
        Attraction attraction = new Attraction(
            1L,                            // id
            "Eiffel Tower Ride",           // name
            LocalDate.of(2023, 6, 15),     // dateCreation
            "A beautiful ride with a great view of Paris");

        List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attraction);

        when(attractionRepository.findAll()).thenReturn(attractionList);
        assertNotNull(attractionService);
        assertEquals(attractionList, attractionService.getAttractions());
        verify(attractionRepository, times(1)).findAll();
    }




    @Test
    void testAddAttraction() {
        Attraction attraction = new Attraction();
        attractionService.addAttraction(attraction);
        verify(attractionRepository, times(1)).save(attraction);
    }


    @Test
    void testAddAttractionNullException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> attractionService.addAttraction(null));
        assertEquals("Attraction cannot be null", exception.getMessage());
    }


    @Test
    void testAttractionByLocalityPlace(){
        List<Attraction> attractions = Arrays.asList(new Attraction());

        when(attractionRepository.findByLocality_Place("Paris")).thenReturn(attractions);
        List<Attraction> result = attractionService.getAttractionByLocality_place("Paris");
        assertEquals(1, result.size());
        verify(attractionRepository, times(1)).findByLocality_Place("Paris");
    }


    @Test
    void testEditAttraction(){
        Attraction attraction = new Attraction();
        attraction.setDescription("1");
        when(attractionRepository.findById(1L)).thenReturn(Optional.of(attraction));
        attractionService.editAttraction(1L, "2");
        assertEquals("2", attraction.getDescription());
    }

}

package demo2.Controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import demo2.controller.Controller;
import demo2.model.Attraction;
import demo2.service.AttractionService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;


@WebMvcTest(Controller.class)
public class ControllerTest {

    @MockitoBean
    public AttractionService attractionService;



    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAttractionsTest() throws Exception {
        Attraction attraction = new Attraction(
            1L,                            // id
            "Eiffel Tower Ride",           // name
            LocalDate.of(2023, 6, 15),     // dateCreation
            "A beautiful ride with a great view of Paris");

        List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attraction);



        when(attractionService.getAttractions()).thenReturn(attractionList);


        mockMvc.perform(get("/attractions/getAttractions"))// 👈 Выведет ответ в консоль
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Eiffel Tower Ride"))
            .andExpect(jsonPath("$[0].description").value("A beautiful ride with a great view of Paris"));
    }

    @Test
    public void getAnithing() throws Exception{
        mockMvc.perform(get("/attractions//"))
            .andExpect(status().isOk())
            .andExpect(content().string("Welcome to the Attractions API!"));
    }

    @Test
    public void getAttractionsByLocality() throws Exception{
        String place = new String("place");
        Attraction attraction = new Attraction(
            1L,                            // id
            "Eiffel Tower Ride",           // name
            LocalDate.of(2023, 6, 15),     // dateCreation
            "A beautiful ride with a great view of Paris");

        List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attraction);
        when(attractionService.getAttractionByLocality_place(place)).thenReturn(attractionList);

        mockMvc.perform(MockMvcRequestBuilders.get("/attractions/getAttractionByLocality")
                .param("place", place)) // 👈 Добавили параметр
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"name\":\"Eiffel Tower Ride\",\"dateCreation\":\"2023-06-15\",\"description\":\"A beautiful ride with a great view of Paris\"}]"));

    }

}
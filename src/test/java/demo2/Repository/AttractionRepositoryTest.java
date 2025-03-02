package demo2.Repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import demo2.model.Attraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AttractionRepositoryTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.3")
        .withDatabaseName("testdb")
        .withUsername("testuser")
        .withPassword("testpass"); // Убрали ручное пробрасывание порта

    @BeforeAll
    static void setUp() {
        postgres.start(); // Запуск контейнера

        System.setProperty("spring.datasource.url",
            "jdbc:postgresql://localhost:" + postgres.getMappedPort(5432) + "/testdb");
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }


    @Test
    void saveAttraction() throws Exception {
        Attraction attraction = new Attraction();
        attraction.setName("Башня");
        attraction.setDescription("селямю");
        mvc.perform(post("/attractions/addAttraction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attraction)))
            .andExpect(status().isOk());
    }


    @Test
    void getAttractionsTest() throws Exception{
        mvc.perform(get("/attractions/getAttractions"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").value("Башня"))
            .andExpect(jsonPath("$[0].description").value("селямю"));
    }


}

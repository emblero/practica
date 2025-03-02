package demo2.controller;

import demo2.model.Locality;
import java.util.List;
import demo2.model.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import demo2.service.AttractionService;

@RestController
@RequestMapping("/attractions")
public class Controller {
    @Autowired
    private  AttractionService attractionService;


    @PostMapping("/addAttraction")
    public void save(@RequestBody  Attraction attraction){
        System.out.println("Received attraction: " + attraction);
        attractionService.addAttraction(attraction);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Attractions API!";
    }

    @GetMapping("/getAttractions")
    public List<Attraction> allAttractions(){
        return attractionService.getAttractions();
    }

    @GetMapping("/getAttractionByLocality")
    public List<Attraction> getAttractionByLocality_place(@RequestParam String place){
        return attractionService.getAttractionByLocality_place(place);
    }

    @PostMapping("/addLocality")
    public void addLocality(@RequestBody Locality locality){
        attractionService.addLocality(locality);
    }

    @PostMapping("/deleteAttractionById")
    public void deleteAttraction(Long id){
        attractionService.deleteAttraction(id);
    }

    @PostMapping("/editAttractionById")
    public void editAttraction(@RequestParam Long id, @RequestParam String description){
        attractionService.editAttraction(id, description);
    }
}

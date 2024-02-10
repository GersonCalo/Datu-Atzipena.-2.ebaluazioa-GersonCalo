package dambi.springFutbolariak.controller;



import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dambi.springFutbolariak.model.Futbolariak;
import dambi.springFutbolariak.model.FutbolariakRepository;
import dambi.springFutbolariak.model.Player;


@RestController
@RequestMapping(path = "/futbolariak")
public class MainController {
    @Autowired
    private FutbolariakRepository futbolariakRepository;
    //GET
    @GetMapping(path = "/futbolariakAll")
    public @ResponseBody Iterable<Futbolariak> getAll(){
        return futbolariakRepository.findAll();
    }

    @GetMapping(path = "/futbolariakById")
	public @ResponseBody Futbolariak findById(@RequestParam String futbolariId) {
		return futbolariakRepository.findById(futbolariId);
	}

    @GetMapping(path = "/findNacionalityAndPosition")
    public @ResponseBody Iterable<Futbolariak> getByNacionalityAndPosition(@RequestParam String nationality,@RequestParam String position){
        return futbolariakRepository.findByNationalityAndPosition(nationality, position);
    }

    //DELETE


    @DeleteMapping(path = "/deleteById")
	public ResponseEntity<String> deleteFutbolaria(@RequestParam String futbolariId) {
		try {
			futbolariakRepository.deleteById(futbolariId);
            return ResponseEntity.ok().build();

		} catch (Exception ex) {
			System.out.println("Ez da futbolaririk aurkitu");
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(path = "/deleteByNameAndBirthDate")
	public ResponseEntity<String> deleteFutbolariName(@RequestParam String name,@RequestParam String birthdate) {
		try {
			futbolariakRepository.deleteByNameAndBirthDate(name, birthdate);
            return ResponseEntity.ok().build();

		} catch (Exception ex) {
			System.out.println("Ez da futbolaririk bilatu");
			return ResponseEntity.notFound().build();
		}
	}

    //PUT

    @PutMapping(value = "/updateValue/{_id}/{newValue_euro}")
    public ResponseEntity<Futbolariak> updateValue(@PathVariable String _id, @PathVariable String newValue_euro){
        try{
            Double euroValue = Double.parseDouble(newValue_euro);
            ObjectId id = new ObjectId(_id);
            futbolariakRepository.update(id , euroValue);
            return ResponseEntity.ok().build();
            
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //POST

    @PostMapping(path =  "/newFutbolaria")
    public @ResponseBody String addNewFutbolaria(@RequestParam String name,
                                                @RequestParam String full_name,
                                                @RequestParam String birth_date,
                                                @RequestParam int age,
                                                @RequestParam Double height_cm,
                                                @RequestParam Double weight_kgs,
                                                @RequestParam String positionSpilt_,
                                                @RequestParam String nationality){
    String[] position = positionSpilt_.split("_");
    List<String> positions = Arrays.asList(position);
    Futbolariak futbolaria = new Futbolariak();
    futbolaria.setPlayer(new Player(name, full_name, birth_date, age, height_cm, weight_kgs));
    futbolaria.setPositions(positions);
    futbolaria.setNationality(nationality);
    futbolariakRepository.save(futbolaria);
        return "futbolaria gordeta";
    }
    
}

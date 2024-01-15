package dambi.springFutbolariak.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;


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

    @GetMapping(path = "/futbolariakNationality")
    public @ResponseBody Iterable<Futbolariak> getFutboariakByNationality(@RequestParam String Nationality){
        return futbolariakRepository.findByNationality(Nationality);
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

    @DeleteMapping(path = "/deleteFutbolariName")
	public ResponseEntity<String> deleteFutbolariName(@RequestParam String name) {
		try {
			futbolariakRepository.deleteByName(name);
            return ResponseEntity.ok().build();

		} catch (Exception ex) {
			System.out.println("Ez da futbolaririk izen orrekin bilatu");
			return ResponseEntity.notFound().build();
		}
	}

    //PUT

    @PutMapping(value = "/updateValue")
    public Futbolariak updateValue(@RequestParam String futbolariId, @RequestParam Double newValue){
        Futbolariak futbolaria = futbolariakRepository.findById(futbolariId);
        futbolaria.setValue_euro(newValue);
        Futbolariak emaitza = futbolariakRepository.save(futbolaria);
        return emaitza;
    }

    //POST

    @PostMapping(path =  "/newFutbolaria")
    public @ResponseBody String addNewFutbolaria(@RequestParam String name,@RequestParam String full_name,@RequestParam int age,@RequestParam Double value_euro){
        Futbolariak futbolaria = new Futbolariak(name, full_name, age, value_euro);
        futbolariakRepository.save(futbolaria);
        return "futbolaria gordeta";
    }
    


   

    
}

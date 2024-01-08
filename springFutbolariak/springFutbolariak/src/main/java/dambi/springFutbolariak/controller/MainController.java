package dambi.springFutbolariak.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dambi.springFutbolariak.model.Futbolariak;
import dambi.springFutbolariak.model.FutbolariakRepository;

@RestController
@RequestMapping(path = "/futbolariakDataBase")
public class MainController {
    @Autowired
    private FutbolariakRepository futbolariakRepository;

    @GetMapping(path = "/futbolariPorLetra/{letar}")
    public @ResponseBody Iterable<Futbolariak> getAllusers(){
        return futbolariakRepository.findAll();
    }

    @PostMapping(path = "/futbolariberria")
    public @ResponseBody String addNewUser(@RequestParam String izena){
        Futbolariak u = new Futbolariak();
        u.setName(izena);
        futbolariakRepository.save(u);
        return "Saved";
    }

    @DeleteMapping(path = "/delete/{futbolariarenIzena}")
    public ResponseEntity<Void> deleteFutbolaria(@PathVariable String futbolariarenIzena){
        try {
            long zenbat = futbolariakRepository.delete(futbolariarenIzena);
            System.out.println("Ezabatutako futbolari kopurua🔆: " + zenbat);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            System.out.println("Errorea " + futbolariarenIzena + " futbolaria ezabatzerakoan. ");
            return ResponseEntity.notFound().build();
        }
    }
}

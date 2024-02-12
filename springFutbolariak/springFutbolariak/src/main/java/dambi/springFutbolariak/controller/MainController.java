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

    // GET
    /**
     * Controlador que maneja la obtención de todos los futbolistas mediante una
     * solicitud GET.
     * Llama al método findAll() del repositorio futbolariakRepository para
     * recuperar todos los futbolistas presentes en la base de datos.
     * Devuelve una Iterable de objetos Futbolariak que representa a todos los
     * futbolistas.
     */

    @GetMapping(path = "/futbolariakAll")
    public @ResponseBody Iterable<Futbolariak> getAll() {
        return futbolariakRepository.findAll();
    }

    /**
     * Controlador que maneja la búsqueda de un futbolista por su ObjectId mediante
     * una solicitud GET.
     * Utiliza un parámetro de solicitud, futbolariId, para identificar el
     * futbolista deseado.
     * Llama al método findById() del repositorio futbolariakRepository para
     * realizar la búsqueda.
     * Devuelve un objeto Futbolariak que coincide con el ObjectId proporcionado.
     */

    @GetMapping(path = "/futbolariakById")
    public @ResponseBody Futbolariak findById(@RequestParam String futbolariId) {
        return futbolariakRepository.findById(futbolariId);
    }

    /**
     * Controlador que maneja la búsqueda de futbolistas por nacionalidad y posición
     * mediante una solicitud GET.
     * Utiliza parámetros de solicitud como nationality y position para identificar
     * los futbolistas deseados.
     * Llama al método findByNationalityAndPosition() del repositorio
     * futbolariakRepository para realizar la búsqueda.
     * Devuelve una Iterable de objetos Futbolariak que coinciden con los criterios
     * de búsqueda.
     */

    @GetMapping(path = "/findNacionalityAndPosition")
    public @ResponseBody Iterable<Futbolariak> getByNacionalityAndPosition(@RequestParam String nationality,
            @RequestParam String position) {
        return futbolariakRepository.findByNationalityAndPosition(nationality, position);
    }

    // DELETE

    /**
     * Controlador que maneja la eliminación de un futbolista por su ObjectId
     * mediante una solicitud DELETE.
     * Utiliza un parámetro de solicitud, futbolariId, para identificar el
     * futbolista a eliminar.
     * Llama al método deleteById() del repositorio futbolariakRepository para
     * realizar la eliminación.
     * Devuelve una respuesta ResponseEntity indicando el éxito de la operación o la
     * falta de éxito en caso de excepción.
     * Imprime un mensaje en la consola si no se encuentra ningún futbolista para
     * eliminar.
     */

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

    /**
     * Controlador que maneja la eliminación de futbolistas por nombre y fecha de
     * nacimiento mediante una solicitud DELETE.
     * Utiliza parámetros de solicitud como name y birthdate para identificar los
     * futbolistas a eliminar.
     * Llama al método deleteByNameAndBirthDate() del repositorio
     * futbolariakRepository para realizar la eliminación.
     * Devuelve una respuesta ResponseEntity indicando el éxito de la operación o la
     * falta de éxito en caso de excepción.
     * Imprime un mensaje en la consola si no se encuentra ningún futbolista para
     * eliminar.
     */

    @DeleteMapping(path = "/deleteByNameAndBirthDate")
    public ResponseEntity<String> deleteFutbolariName(@RequestParam String name, @RequestParam String birthdate) {
        try {
            futbolariakRepository.deleteByNameAndBirthDate(name, birthdate);
            return ResponseEntity.ok().build();

        } catch (Exception ex) {
            System.out.println("Ez da futbolaririk bilatu");
            return ResponseEntity.notFound().build();
        }
    }

    // PUT

    /**
     * Controlador que maneja la actualización del valor en euros de un futbolista
     * mediante una solicitud PUT.
     * Utiliza los parámetros de la ruta (_id y newValue_euro) para obtener el
     * ObjectId y el nuevo valor en euros.
     * Llama al método update() del repositorio futbolariakRepository para
     * actualizar el valor en euros del futbolista identificado por el ObjectId.
     * Devuelve una respuesta ResponseEntity indicando el éxito de la operación o la
     * falta de éxito en caso de excepción.
     */

    @PutMapping(value = "/updateValue/{_id}/{newValue_euro}")
    public ResponseEntity<Futbolariak> updateValue(@PathVariable String _id, @PathVariable String newValue_euro) {
        try {
            Double euroValue = Double.parseDouble(newValue_euro);
            ObjectId id = new ObjectId(_id);
            futbolariakRepository.update(id, euroValue);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // POST
    /**
     * Controlador que maneja la creación de nuevos futbolistas (futbolaria)
     * mediante una solicitud POST.
     * Los parámetros de la solicitud, como nombre, nombre completo, fecha de
     * nacimiento, edad, altura, peso, posición, nacionalidad, etc., se utilizan
     * para crear un nuevo objeto Futbolariak.
     * Este objeto se guarda en la base de datos utilizando el método save() del
     * repositorio futbolariakRepository.
     * Devuelve un mensaje indicando el éxito de la operación.
     */

    @PostMapping(path = "/newFutbolaria")
    public @ResponseBody String addNewFutbolaria(@RequestParam String name,
            @RequestParam String full_name,
            @RequestParam String birth_date,
            @RequestParam int age,
            @RequestParam Double height_cm,
            @RequestParam Double weight_kgs,
            @RequestParam String positionSpilt_,
            @RequestParam String nationality) {
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

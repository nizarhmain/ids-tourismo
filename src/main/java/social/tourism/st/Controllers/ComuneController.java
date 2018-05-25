package social.tourism.st.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.Comune;
import social.tourism.st.Repositories.ComuneRepository;

import java.util.List;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

    private ComuneRepository repository;

    @Autowired
    public ComuneController(ComuneRepository repository){
        this.repository = repository;
    }

    // get all
    @RequestMapping(method = RequestMethod.GET)
    public List<Comune> findAll(){
        return repository.findAll();
    }

    // get all comuni in a provincia
    @RequestMapping(value ="/provincia/{provincia}", method = RequestMethod.GET)
    public List<Comune> findByProvinces(@PathVariable String provincia){
        return repository.findByProvincia(provincia);
    }

    // get all comuni in one region
    @RequestMapping(value ="/regione/{regione}", method = RequestMethod.GET)
    public List<Comune> findByRegions(@PathVariable String regione){
        return repository.findByRegione(regione);
    }

    // add one
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Validated @RequestBody Comune comune){

        if (repository.findByNome(comune.getNome()) != null ) {
            System.out.println("A Comune with name " + comune.getNome()+ " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Comune model = new Comune();
            model.setNome(comune.getNome());
            model.setProvincia(comune.getProvincia());
            model.setRegione(comune.getRegione());
            model.setSuperficie(comune.getSuperficie());
            repository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    //find one
    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    public Comune findOne(@PathVariable String nome){
        return repository.findByNome(nome);
    }

    // updating the comune
    @RequestMapping(value = "/{nome}", method = RequestMethod.PUT)
    public Comune update(@PathVariable String nome, @Validated @RequestBody Comune comune){
        Comune model = repository.findByNome(nome);
        if(model != null) {
            model.setSuperficie(comune.getSuperficie());
            model.setProvincia(comune.getProvincia());
            model.setRegione(comune.getRegione());
            model.setNome(comune.getNome());
            return repository.save(model);
        }
        return null;
    }
    // delete
    @RequestMapping(value = "/{nome}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String nome){
        repository.delete(repository.findByNome(nome));
    }

}


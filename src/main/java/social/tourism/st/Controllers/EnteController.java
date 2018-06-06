package social.tourism.st.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.Account;
import social.tourism.st.Models.Ente;
import social.tourism.st.Repositories.AccountRepository;
import social.tourism.st.Repositories.EnteRepository;

import java.util.List;

@RestController
@RequestMapping("/api/ente")
public class EnteController {

    private EnteRepository repository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public EnteController(EnteRepository repository){
        this.repository = repository;
    }

    // get all
    @RequestMapping(method = RequestMethod.GET)
    public List<Ente> findAll(){
        return repository.findAll();
    }

    // get all comuni in a provincia
    @RequestMapping(value ="/provincia/{provincia}", method = RequestMethod.GET)
    public List<Ente> findByProvinces(@PathVariable String provincia){
        return repository.findByProvincia(provincia);
    }

    // get all comuni in one region
    @RequestMapping(value ="/regione/{regione}", method = RequestMethod.GET)
    public List<Ente> findByRegions(@PathVariable String regione){
        return repository.findByRegione(regione);
    }

    // add one
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Validated @RequestBody Ente ente){

        if (repository.findByNome(ente.getNome()) != null ) {
            System.out.println("A Ente with name " + ente.getNome()+ " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Ente model = new Ente();
            model.setNome(ente.getNome());
            model.setProvincia(ente.getProvincia());
            model.setRegione(ente.getRegione());
            model.setSuperficie(ente.getSuperficie());
            model.setPassword(ente.getPassword());

            // creates an account for that ente
            Account account = new Account(ente.getNome(),
                    new BCryptPasswordEncoder().encode(ente.getPassword()));

            account.setRole("ROLE_ENTE");
            accountRepository.save(account);

            repository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    //find one
    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    public Ente findOne(@PathVariable String nome){
        return repository.findByNome(nome);
    }

    // updating the ente
    @RequestMapping(value = "/{nome}", method = RequestMethod.PUT)
    public Ente update(@PathVariable String nome, @Validated @RequestBody Ente ente){
        Ente model = repository.findByNome(nome);
        if(model != null) {
            model.setSuperficie(ente.getSuperficie());
            model.setProvincia(ente.getProvincia());
            model.setRegione(ente.getRegione());
            model.setNome(ente.getNome());
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


package social.tourism.st.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.GeoBoundaries;
import social.tourism.st.Models.HistoricalSpot;
import social.tourism.st.Repositories.HistoricalSpotRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/historical")
public class HistoricalSpotController {

    private HistoricalSpotRepository repository;

    @Autowired
    public HistoricalSpotController(HistoricalSpotRepository repository){
        this.repository = repository;
    }

    // get all
    @RequestMapping(method = RequestMethod.GET)
    public List<HistoricalSpot> findAll(){
        return repository.findAll();
    }

    // add one
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Validated @RequestBody HistoricalSpot historicalSpot){

        if (repository.findByName(historicalSpot.getName()) != null ) {
            System.out.println("A Monument with the name " + historicalSpot.getName()+ " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            HistoricalSpot model = new HistoricalSpot();
            model.setName(historicalSpot.getName());
            model.setType(historicalSpot.getType());
            model.setInfo(historicalSpot.getInfo());
            model.setCoordinates(historicalSpot.getCoordinates());
            model.setComune(historicalSpot.getComune());
            model.setImage(historicalSpot.getImage());
            repository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // find all monuments near the users position
    @RequestMapping(value="/nearby" , method = RequestMethod.POST)
    public void nearby(@Validated @RequestBody GeoBoundaries geoBoundaries) {
        List<HistoricalSpot> hss = repository.findAll();
        List<HistoricalSpot> hstarget;

        for ( HistoricalSpot h: hss) {
            if      (Double.valueOf(h.latitude()) < Double.valueOf(geoBoundaries.maxLat) &&
                    Double.valueOf(h.latitude()) > Double.valueOf(geoBoundaries.minLat) &&
                    Double.valueOf(h.longitude()) < Double.valueOf(geoBoundaries.maxLon) &&
                    Double.valueOf(h.longitude()) > Double.valueOf(geoBoundaries.minLon)) {
                System.out.println(h.getName() + h.latitude() + " -- " + h.longitude());
            }
        }

        System.out.println(geoBoundaries.toString());
    }

    // find all the monuments in one comune
    @RequestMapping(value="/{comune}" ,method = RequestMethod.GET)
    public List<HistoricalSpot> findByComune(@PathVariable String comune){
        return repository.findByComune(comune.toLowerCase());
    }

    // find monument by name
    @RequestMapping(value="/monument/", method = RequestMethod.GET)
    public ResponseEntity<Void> findAllMonuments(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // find monument by name
    @RequestMapping(value="/monument/{name}", method = RequestMethod.GET)
    public HistoricalSpot findByName(@PathVariable String name){
        return repository.findByName(name);
    }


    // update a monument
    @RequestMapping(value = "/monument/{name}", method = RequestMethod.PUT)
    public HistoricalSpot update(@PathVariable String name, @Validated @RequestBody HistoricalSpot updatedSpot){
        HistoricalSpot model = repository.findByName(name);
            if(model != null){
                model.setName(updatedSpot.getName());
                model.setInfo(updatedSpot.getInfo());
                model.setCoordinates(updatedSpot.getCoordinates());
                model.setType(updatedSpot.getType());
                model.setComune(updatedSpot.getComune());
                return repository.save(model);
            }

        return null;
    }

    //TODO throw illegalArgumentException here, the object to delete must not be null
    //delete a monument
    @RequestMapping(value="/monument/{name}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String name){
        repository.delete(repository.findByName(name));
    }
}

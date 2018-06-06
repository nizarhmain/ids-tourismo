package social.tourism.st.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.GeoBoundaries;
import social.tourism.st.Models.POI;
import social.tourism.st.Repositories.POI_Repository;

import java.util.List;

@RestController
@RequestMapping("/api/poi")
public class POI_Controller {

    private POI_Repository repository;

    @Autowired
    public POI_Controller(POI_Repository repository){
        this.repository = repository;
    }

    // get all
    @RequestMapping(method = RequestMethod.GET)
    public List<POI> findAll(){
        return repository.findAll();
    }

    // add one
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(@Validated @RequestBody POI POI){

        if (repository.findByName(POI.getName()) != null ) {
            System.out.println("A Monument with the name " + POI.getName()+ " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            POI model = new POI();
            model.setName(POI.getName());
            model.setType(POI.getType());
            model.setInfo(POI.getInfo());
            model.setCoordinates(POI.getCoordinates());
            model.setEnte(POI.getEnte());
            model.setImage(POI.getImage());
            repository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    // find all monuments near the users position
    @RequestMapping(value="/nearby" , method = RequestMethod.POST)
    public void nearby(@Validated @RequestBody GeoBoundaries geoBoundaries) {
        List<POI> hss = repository.findAll();
        List<POI> hstarget;

        for ( POI h: hss) {
            if      (Double.valueOf(h.latitude()) < Double.valueOf(geoBoundaries.maxLat) &&
                    Double.valueOf(h.latitude()) > Double.valueOf(geoBoundaries.minLat) &&
                    Double.valueOf(h.longitude()) < Double.valueOf(geoBoundaries.maxLon) &&
                    Double.valueOf(h.longitude()) > Double.valueOf(geoBoundaries.minLon)) {
                System.out.println(h.getName() + h.latitude() + " -- " + h.longitude());
            }
        }

        System.out.println(geoBoundaries.toString());
    }

    // find all the monuments in one ente
    @RequestMapping(value="/{ente}" ,method = RequestMethod.GET)
    public List<POI> findByEnte(@PathVariable String ente){
        return repository.findByEnte(ente);
    }

    // find monument by name
    @RequestMapping(value="/monument/", method = RequestMethod.GET)
    public ResponseEntity<Void> findAllMonuments(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // find monument by name
    @RequestMapping(value="/monument/{name}", method = RequestMethod.GET)
    public POI findByName(@PathVariable String name){
        return repository.findByName(name);
    }


    // update a monument
    @RequestMapping(value = "/monument/{name}", method = RequestMethod.PUT)
    public POI update(@PathVariable String name, @Validated @RequestBody POI updatedSpot){
        POI model = repository.findByName(name);
            if(model != null){
                model.setName(updatedSpot.getName());
                model.setInfo(updatedSpot.getInfo());
                model.setCoordinates(updatedSpot.getCoordinates());
                model.setType(updatedSpot.getType());
                model.setEnte(updatedSpot.getEnte());
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

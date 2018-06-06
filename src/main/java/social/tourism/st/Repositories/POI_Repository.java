package social.tourism.st.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.POI;

import java.util.List;

public interface POI_Repository extends MongoRepository<POI, String>{

    POI findByName(String name);
    List<POI> findByEnte(String ente);

}

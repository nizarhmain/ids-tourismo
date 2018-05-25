package social.tourism.st.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.HistoricalSpot;

import java.util.List;

public interface HistoricalSpotRepository extends MongoRepository<HistoricalSpot, String>{

    HistoricalSpot findByName(String name);
    List<HistoricalSpot> findByComune(String comune);

}

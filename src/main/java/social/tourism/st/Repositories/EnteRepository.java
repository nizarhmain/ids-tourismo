package social.tourism.st.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.Ente;

import java.util.List;


public interface EnteRepository extends MongoRepository<Ente, String> {
    Ente findByNome(String nome);
    List<Ente> findByProvincia(String provincia);
    List<Ente> findByRegione(String regione);
}

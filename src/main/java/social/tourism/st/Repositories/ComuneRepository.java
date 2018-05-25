package social.tourism.st.Repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.Comune;
import java.util.List;


public interface ComuneRepository extends MongoRepository<Comune, String> {
    Comune findByNome(String nome);
    List<Comune> findByProvincia(String provincia);
    List<Comune> findByRegione(String regione);
}

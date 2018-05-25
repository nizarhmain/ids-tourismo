package social.tourism.st.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import social.tourism.st.Models.Account;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

     Account findByUsername(String username);
     List<Account> findAll();
}
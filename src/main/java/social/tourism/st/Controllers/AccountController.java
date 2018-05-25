package social.tourism.st.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.tourism.st.Models.Account;
import social.tourism.st.Repositories.AccountRepository;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/whois/{username}", method = RequestMethod.GET)
    public ResponseEntity<Account> findByUsername(@PathVariable String username) {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/whois/all", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findAll() {
        List<Account> user = accountRepository.findAll();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<Account> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return new ResponseEntity<>(accountRepository.findByUsername(currentUserName), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccount(@Validated @RequestBody Account user) {

        if (accountRepository.findByUsername(user.getUsername()) != null) {
            System.out.println("A Account with name " + user.getUsername() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Account model = new Account(user.getUsername(),
                    new BCryptPasswordEncoder().encode(user.getPassword()));
            accountRepository.save(model);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
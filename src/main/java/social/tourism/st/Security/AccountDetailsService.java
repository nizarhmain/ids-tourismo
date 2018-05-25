package social.tourism.st.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import social.tourism.st.Models.Account;
import social.tourism.st.Repositories.AccountRepository;

@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username_passed_by_user) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username_passed_by_user);
        System.out.println(username_passed_by_user);
            if(account == null){
                System.out.println("no account found");
            } else {
                System.out.println(account.getUsername() + " is attempting to connect ");
                return new User(account.getUsername(), account.getPassword(), true
                ,true, true, true, AuthorityUtils.createAuthorityList(account.getRole()));
            }
            return null;
        }
}

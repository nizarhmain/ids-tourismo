package social.tourism.st.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService accountDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// basic auth headers have to be sent each time
		// to logout a user, we basically empty out their basic auth www-auth fi
		// everything is secured with basic auth, nothing to fancy for the sake of focusing on the design mainly

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().
				// Auth CRUD
				antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/auth/**").hasRole("USER")

				// restrict user access here, all users can see all others users

				//Commenti CRUD
				.antMatchers(HttpMethod.GET, "/api/comments/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/comments/**").hasRole("USER")
				// Ente CRUD
				.antMatchers(HttpMethod.GET, "/api/comuni").permitAll()
				.antMatchers(HttpMethod.POST, "/api/comuni").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/comuni").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/comuni").hasRole("ADMIN")

				// POI CRUD
				.antMatchers(HttpMethod.GET, "/api/poi/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/poi/nearby").permitAll()
				.antMatchers(HttpMethod.POST, "/api/poi/**").hasRole("USER")
				.antMatchers(HttpMethod.PUT, "/api/poi/**").hasRole("USER")
				.antMatchers(HttpMethod.DELETE, "/api/poi/**").hasRole("USER")
				.and()
				.httpBasic().and()
				.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}

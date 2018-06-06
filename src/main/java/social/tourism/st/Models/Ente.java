package social.tourism.st.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import social.tourism.st.Repositories.AccountRepository;

import javax.validation.constraints.Size;

public class Ente {

    @Id
    private String id;

    private String password;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String nome;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String regione;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String provincia;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String superficie;

    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() { return this.password ;}

    public void setPassword(String password) { this.password = password; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public Ente(String nome, String regione, String provincia, String superficie, String password) {
        this.nome = nome;
        this.regione = regione;
        this.provincia = provincia;
        this.superficie = superficie;
        this.password = password;
    }

    public Ente(){};

    @Override
    public String toString() {
        return "Ente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", superficie='" + superficie + '\'' +
                '}';
    }

}

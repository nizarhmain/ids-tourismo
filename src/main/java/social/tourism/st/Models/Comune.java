package social.tourism.st.Models;


import org.springframework.data.annotation.Id;
import javax.validation.constraints.Size;

public class Comune {
    @Id
    private String id;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String nome;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String regione;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String provincia;

    @Size(min = 1, max = 30, message = "error.title.size")
    public String superficie;

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

    public Comune(String nome, String regione, String provincia, String superficie) {
        this.nome = nome;
        this.regione = regione;
        this.provincia = provincia;
        this.superficie = superficie;
    }

    public Comune(){};

    @Override
    public String toString() {
        return "Comune{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", regione='" + regione + '\'' +
                ", provincia='" + provincia + '\'' +
                ", superficie='" + superficie + '\'' +
                '}';
    }

}

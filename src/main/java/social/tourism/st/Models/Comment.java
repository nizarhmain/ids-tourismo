package social.tourism.st.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class Comment {
    @Id
    private String id;

    @NotNull
    private String monumentAttachedTo;

    @NotNull
    private String content;

    private String author;

    private String date;

    public Comment(){};

    public String getMonumentAttachedTo() {
        return monumentAttachedTo;
    }

    public void setMonumentAttachedTo(String monumentAttachedTo) {
        this.monumentAttachedTo = monumentAttachedTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    @JsonIgnore
    @JsonProperty(value = "date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonIgnore
    @JsonProperty(value = "author")
    public void setAuthor(String author) {
        this.author = author;
    }



}

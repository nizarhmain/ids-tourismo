package social.tourism.st.Models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class POI {
    @Id
    private String id;

    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String info;
    @NotNull
    private String coordinates;
    @NotNull
    private String image;


    @NotNull
    private String comune;

    public POI(){}

    public POI(String name, String type, String info, String coordinates, String comune, String image) {
        this.name = name;
        this.type = type;
        this.info = info;
        this.coordinates = coordinates;
        this.comune = comune;
        this.image = image;
    }

    public String latitude(){
        String[] co = coordinates.split(",", 2);
        return co[0];
    }

    public String longitude(){
        String[] co = coordinates.split(",", 2);
        return co[1];
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }
}

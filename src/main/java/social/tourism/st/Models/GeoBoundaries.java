package social.tourism.st.Models;

public class GeoBoundaries {
    public String maxLat;
    public String minLat;
    public String maxLon;
    public String minLon;

    public GeoBoundaries(){}

    public GeoBoundaries(String maxLat, String minLat, String maxLon, String minLon) {
        this.maxLat = maxLat;
        this.minLat = minLat;
        this.maxLon = maxLon;
        this.minLon = minLon;
    }

    public String getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(String maxLat) {
        this.maxLat = maxLat;
    }

    public String getMinLat() {
        return minLat;
    }

    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    public String getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(String maxLon) {
        this.maxLon = maxLon;
    }

    public String getMinLon() {
        return minLon;
    }

    public void setMinLon(String minLon) {
        this.minLon = minLon;
    }

    @Override
    public String toString() {
        return "GeoBoundaries{" +
                "maxLat='" + maxLat + '\'' +
                ", minLat='" + minLat + '\'' +
                ", maxLon='" + maxLon + '\'' +
                ", minLon='" + minLon + '\'' +
                '}';
    }
}

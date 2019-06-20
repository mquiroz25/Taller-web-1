package ar.edu.unlam.tallerweb1.modelo;


import java.util.List;

public class Message {
    private String category;
    private Double latitude;
    private Double longitude;
    private Long distance;
    private Long idItem;

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        if (distance == null) {
            distance = new Long(0);
        }
        this.distance = distance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

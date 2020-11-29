package andreitam.entity;

import andreitam.enums.Season;

import java.util.UUID;

public class RoomFair {
    private UUID id;
    private double value;
    private Season season;

    public RoomFair(double value, Season season) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.season = season;
    }

    public RoomFair(UUID id, double value, Season season) {
        this.id = id;
        this.value = value;
        this.season = season;
    }

    public UUID getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public Season getSeason() {
        return season;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "RoomFair{" +
                "id=" + id +
                ", value=" + value +
                ", season=" + season +
                '}';
    }
}

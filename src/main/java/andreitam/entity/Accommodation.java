package andreitam.entity;


import andreitam.enums.BedType;
import andreitam.enums.RoomType;

import java.util.UUID;

public class Accommodation {
    private UUID id;
    private RoomType type;
    private BedType bedType;
    private int maxGuests;
    private String description;

    public Accommodation(RoomType type, BedType bedType, int maxGuests, String description) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.bedType = bedType;
        this.maxGuests = maxGuests;
        this.description = description;
    }

    public Accommodation(UUID id, RoomType type, BedType bedType, int maxGuests, String description) {
        this.id = id;
        this.type = type;
        this.bedType = bedType;
        this.maxGuests = maxGuests;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public BedType getBedType() {
        return bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", type=" + type +
                ", bedType=" + bedType +
                ", maxGuests=" + maxGuests +
                ", description='" + description + '\'' +
                '}';
    }
}

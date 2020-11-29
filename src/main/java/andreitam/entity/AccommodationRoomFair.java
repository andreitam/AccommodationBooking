package andreitam.entity;

import java.util.UUID;

public class AccommodationRoomFair {
    private UUID id;
    private UUID idAccommodation;
    private UUID idFair;

    public AccommodationRoomFair(UUID idAccommodation, UUID idFair) {
        this.id = UUID.randomUUID();
        this.idAccommodation = idAccommodation;
        this.idFair = idFair;
    }

    public AccommodationRoomFair(UUID id, UUID idAccommodation, UUID idFair) {
        this.id = id;
        this.idAccommodation = idAccommodation;
        this.idFair = idFair;
    }

    public UUID getId() {
        return id;
    }

    public UUID getIdAccommodation() {
        return idAccommodation;
    }

    public UUID getIdFair() {
        return idFair;
    }

    @Override
    public String toString() {
        return "AccommodationRoomFair{" +
                "id=" + id +
                ", idAccommodation=" + idAccommodation +
                ", idFair=" + idFair +
                '}';
    }
}

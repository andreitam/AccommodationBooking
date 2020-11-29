package andreitam.model;

import andreitam.entity.Accommodation;
import andreitam.enums.BedType;
import andreitam.enums.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccommodationData {
    private Connection connection = ConnectionData.getInstance().getConnection();

    public AccommodationData() throws SQLException {
    }

    public Accommodation getAccommodation(UUID id) {
        try {
            PreparedStatement selectAccommodationStatement = connection.prepareStatement(
                    "SELECT * from accommodation WHERE id = ?");
            selectAccommodationStatement.setObject(1, id);
            ResultSet accommodationResultSet = selectAccommodationStatement.executeQuery();

            if (accommodationResultSet.next()){
               Accommodation accommodation =
                       new Accommodation(UUID.fromString(accommodationResultSet.getObject(1).toString()),
                               RoomType.valueOf(accommodationResultSet.getString(2)),
                               BedType.valueOf(accommodationResultSet.getString(3)),
                               accommodationResultSet.getInt(4),
                               accommodationResultSet.getString(5));
                return accommodation;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addAccommodation(Accommodation accommodation) {
        try {
            PreparedStatement insertAccommodationStatement = connection.prepareStatement(
                    "INSERT INTO accommodation VALUES (?, ?, ?, ?, ?)");
            insertAccommodationStatement.setObject(1, accommodation.getId());
            insertAccommodationStatement.setString(2, accommodation.getType().toString());
            insertAccommodationStatement.setString(3, accommodation.getBedType().toString());
            insertAccommodationStatement.setInt(4, accommodation.getMaxGuests());
            insertAccommodationStatement.setString(5, accommodation.getDescription());
            insertAccommodationStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateAccommodation(UUID updateId, Accommodation updatedAccommodation) {
        try {
            PreparedStatement updateAccommodationStatement = connection.prepareStatement(
                    "UPDATE accommodation SET type = ?, bed_type = ?, max_guests = ?, description = ?  WHERE id = ?");
            updateAccommodationStatement.setString(1, updatedAccommodation.getType().toString());
            updateAccommodationStatement.setString(2, updatedAccommodation.getBedType().toString());
            updateAccommodationStatement.setInt(3, updatedAccommodation.getMaxGuests());
            updateAccommodationStatement.setString(4, updatedAccommodation.getDescription());
            updateAccommodationStatement.setObject(5, updateId);
            updateAccommodationStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAccomodation(UUID id) {
        try {
            PreparedStatement deleteAccommodationStatement = connection.prepareStatement(
                    "DELETE from accommodation WHERE id = ?");
            deleteAccommodationStatement.setObject(1, id);
            deleteAccommodationStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Accommodation> listAccommodation() {
        List<Accommodation> accommodations = new ArrayList<>();
        try {
            Statement selectAccommodationstatement = connection.createStatement();
            ResultSet accommodationResultSet = selectAccommodationstatement.executeQuery("SELECT * from accommodation");
            while(accommodationResultSet.next()){
                Accommodation accommodation =
                        new Accommodation(UUID.fromString(accommodationResultSet.getObject(1).toString()),
                                RoomType.valueOf(accommodationResultSet.getString(2)),
                                BedType.valueOf(accommodationResultSet.getString(3)),
                                accommodationResultSet.getInt(4),
                                accommodationResultSet.getString(5));
                accommodations.add(accommodation);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accommodations;
    }
}

package andreitam.model;

import andreitam.entity.AccommodationRoomFair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccommodationRoomFairData {
    private Connection connection = ConnectionData.getInstance().getConnection();

    public AccommodationRoomFairData() throws SQLException {
    }

    public AccommodationRoomFair getAccommodationRoomFair(UUID id) {
        try {
            PreparedStatement selectAccommodationRoomFairStatement = connection.prepareStatement(
                    "SELECT * from accommodation_fair_relation WHERE id = ?");
            selectAccommodationRoomFairStatement.setObject(1, id);
            ResultSet accommodationRoomFairResultSet = selectAccommodationRoomFairStatement.executeQuery();

            if (accommodationRoomFairResultSet.next()){
                AccommodationRoomFair accommodationRoomFair =
                        //AccommodationRoomFair object
                        new AccommodationRoomFair(UUID.fromString(accommodationRoomFairResultSet.getObject(1).toString()),
                                UUID.fromString(accommodationRoomFairResultSet.getObject(2).toString()),
                                UUID.fromString(accommodationRoomFairResultSet.getObject(3).toString())
                                );

                return accommodationRoomFair;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addAccommodationRoomFair(AccommodationRoomFair accommodationRoomFair) {
        try {
            PreparedStatement insertAccommodationRoomFairStatement = connection.prepareStatement(
                    "INSERT INTO accommodation_fair_relation VALUES (?, ?, ?)");
            insertAccommodationRoomFairStatement.setObject(1, accommodationRoomFair.getId());
            insertAccommodationRoomFairStatement.setObject(2, accommodationRoomFair.getIdAccommodation());
            insertAccommodationRoomFairStatement.setObject(3, accommodationRoomFair.getIdFair());
            insertAccommodationRoomFairStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateAccommodationRoomFair(UUID updateId, AccommodationRoomFair accommodationRoomFair) {
        try {
            PreparedStatement updateAccommodationRoomFairStatement = connection.prepareStatement(
                    "UPDATE accommodation_fair_relation SET id_accommodation = ?, id_fair = ? WHERE id = ?");
            updateAccommodationRoomFairStatement.setObject(1, accommodationRoomFair.getIdAccommodation());
            updateAccommodationRoomFairStatement.setObject(2, accommodationRoomFair.getIdFair());
            updateAccommodationRoomFairStatement.setObject(3, updateId);
            updateAccommodationRoomFairStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAccommodationRoomFair(UUID id) {
        try {
            PreparedStatement deleteAccommodationRoomFairStatement = connection.prepareStatement(
                    "DELETE from accommodation_fair_relation WHERE id = ?");
            deleteAccommodationRoomFairStatement.setObject(1, id);
            deleteAccommodationRoomFairStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<AccommodationRoomFair> listAccommodationRoomFair() {
        List<AccommodationRoomFair> accRooms = new ArrayList<>();
        try {
            Statement selectAccommodationRoomFairStatement = connection.createStatement();
            ResultSet accommodationRoomFairResultSet = selectAccommodationRoomFairStatement.executeQuery(
                    "SELECT * from accommodation_fair_relation");
            while(accommodationRoomFairResultSet.next()){
                AccommodationRoomFair accommodationRoomFair =
                        new AccommodationRoomFair(UUID.fromString(accommodationRoomFairResultSet.getObject(1).toString()),
                                UUID.fromString(accommodationRoomFairResultSet.getObject(2).toString()),
                                UUID.fromString(accommodationRoomFairResultSet.getObject(3).toString())
                        );
                accRooms.add(accommodationRoomFair);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accRooms;
    }
}

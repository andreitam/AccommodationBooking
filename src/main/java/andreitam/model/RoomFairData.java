package andreitam.model;

import andreitam.entity.RoomFair;
import andreitam.enums.Season;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomFairData {
    private Connection connection = ConnectionData.getInstance().getConnection();

    public RoomFairData() throws SQLException {
    }

    public RoomFair getRoomFair(UUID id) {
        try {
            PreparedStatement selectRoomFairStatement = connection.prepareStatement(
                    "SELECT * from room_fair WHERE id = ?");
            selectRoomFairStatement.setObject(1, id);
            ResultSet roomFairResultSet = selectRoomFairStatement.executeQuery();

            if (roomFairResultSet.next()){
                RoomFair roomFair =
                        new RoomFair(UUID.fromString(roomFairResultSet.getObject(1).toString()),
                                roomFairResultSet.getDouble(2),
                                Season.valueOf(roomFairResultSet.getString(3)));
                return roomFair;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addRoomFair(RoomFair roomFair) {
        try {
            PreparedStatement insertRoomFairStatement = connection.prepareStatement(
                    "INSERT INTO room_fair VALUES (?, ?, ?)");
            insertRoomFairStatement.setObject(1, roomFair.getId());
            insertRoomFairStatement.setDouble(2, roomFair.getValue());
            insertRoomFairStatement.setString(3, roomFair.getSeason().toString());
            insertRoomFairStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRoomFair(UUID updateId, RoomFair updatedRoomFair) {
        try {
            PreparedStatement updateRoomFairStatement = connection.prepareStatement(
                    "UPDATE room_fair SET value = ?, season = ? WHERE id = ?");
            updateRoomFairStatement.setDouble(1, updatedRoomFair.getValue());
            updateRoomFairStatement.setString(2, updatedRoomFair.getSeason().toString());
            updateRoomFairStatement.setObject(3, updateId);
            updateRoomFairStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteRoomfair(UUID id) {
        try {
            PreparedStatement deleteRoomFairStatement = connection.prepareStatement(
                    "DELETE from room_fair WHERE id = ?");
            deleteRoomFairStatement.setObject(1, id);
            deleteRoomFairStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<RoomFair> listRoomFair() {
        List<RoomFair> roomFairs = new ArrayList<>();
        try {
            Statement selectRoomFairsStatement = connection.createStatement();
            ResultSet roomFairResultSet = selectRoomFairsStatement.executeQuery(
                    "SELECT * from room_fair");
            while(roomFairResultSet.next()){
                RoomFair roomFair =
                        new RoomFair(UUID.fromString(roomFairResultSet.getObject(1).toString()),
                                roomFairResultSet.getDouble(2),
                                Season.valueOf(roomFairResultSet.getString(3)));
                roomFairs.add(roomFair);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomFairs;
    }
}

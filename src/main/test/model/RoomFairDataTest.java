package model;

import andreitam.entity.RoomFair;
import andreitam.enums.Season;
import andreitam.model.ConnectionData;
import andreitam.model.RoomFairData;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class RoomFairDataTest {
    @Before
    public void createRoomFair() throws SQLException {
        Connection connection = ConnectionData.getInstance().getConnection();
        try {
            //drop table
            connection.prepareStatement(
                    "DROP TABLE IF EXISTS room_fair CASCADE;").execute();
            //create table
            connection.prepareStatement(
                    "CREATE TABLE room_fair (\n" +
                            "  \tid uuid,\n" +
                            "  \tvalue float,\n" +
                            "\tseason character varying(32),\n" +
                            "\tPRIMARY KEY (id)\n" +
                            ");").execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testRoomFair() throws SQLException {
        RoomFairData roomFairData = new RoomFairData();
        //add entries
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("c42e0d3e-beaf-492c-91c3-0231a0b0c44b"),50.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("3ab055a2-c00e-40a1-9bc1-68b00148e848"),60.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("40d20d7f-fab5-44ff-a2b4-e97f7d412c6a"),75.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("adf14ac3-9796-4558-aa52-7027a2cbe805"),100.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982"),90.0, Season.in_season));
        //update entry
        roomFairData.updateRoomFair(UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982"),new RoomFair(85, Season.in_season));
        //get entry and assert
        assertEquals(85.0, roomFairData.getRoomFair(UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982")).getValue(),0);
        //delete entry
        roomFairData.deleteRoomfair(UUID.fromString("3ab055a2-c00e-40a1-9bc1-68b00148e848"));
        //list entries and assert
        List<RoomFair> fairs = roomFairData.listRoomFair();
        assertEquals(4, fairs.size());
        fairs.stream().forEach(System.out::println);


    }
}
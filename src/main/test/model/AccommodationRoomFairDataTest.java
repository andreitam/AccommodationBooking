package model;

import andreitam.entity.Accommodation;
import andreitam.entity.AccommodationRoomFair;
import andreitam.entity.RoomFair;
import andreitam.enums.BedType;
import andreitam.enums.RoomType;
import andreitam.enums.Season;
import andreitam.model.AccommodationData;
import andreitam.model.AccommodationRoomFairData;
import andreitam.model.ConnectionData;
import andreitam.model.RoomFairData;
import andreitam.service.PrintPrices;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class AccommodationRoomFairDataTest {
    @Before
    public void createAccommodation() throws SQLException {
        Connection connection = ConnectionData.getInstance().getConnection();
        try {
            //drop table
            connection.prepareStatement(
                    "DROP TABLE IF EXISTS accommodation CASCADE;").execute();
            //create table
            connection.prepareStatement(
                    "CREATE TABLE accommodation (\n" +
                            "  \tid uuid,\n" +
                            "  \ttype character varying(32),\n" +
                            "\tbed_type character varying(32),\n" +
                            "\tmax_guests int,\n" +
                            "\tdescription character varying(512),\n" +
                            "\tPRIMARY KEY (id)\n" +
                            ");").execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        AccommodationData accommodationData = new AccommodationData();
        //add entries
        accommodationData.addAccommodation(new Accommodation(UUID.fromString("246ed9e4-4fa5-46be-b9e2-70354fe05d86"),
                RoomType.Single_Room, BedType.one_single_bed,1,"park view"));
        accommodationData.addAccommodation(new Accommodation(UUID.fromString("3300d5a4-04f6-4cd0-8177-305fce3dde53"),
                RoomType.Double_Room, BedType.two_single_beds,2,"park view"));
        accommodationData.addAccommodation(new Accommodation(UUID.fromString("0d21af67-656d-44b6-a807-1ad4ae0cc823"),
                RoomType.Double_Room_Superior, BedType.one_large_double_bed,2,"sea view"));
        accommodationData.addAccommodation(new Accommodation(UUID.fromString("60068424-e82f-4d4d-88ac-ff85714386b9"),
                RoomType.Suite, BedType.one_double_bed_one_single_bed,3,"sea view"));
        accommodationData.addAccommodation(new Accommodation(UUID.fromString("e7467812-4753-4dfe-a436-d12ccbe62726"),
                RoomType.Double_Room, BedType.three_single_beds,2,"park view"));
    }

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

        RoomFairData roomFairData = new RoomFairData();
        //add entries
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("c42e0d3e-beaf-492c-91c3-0231a0b0c44b"),50.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("3ab055a2-c00e-40a1-9bc1-68b00148e848"),60.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("40d20d7f-fab5-44ff-a2b4-e97f7d412c6a"),75.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("adf14ac3-9796-4558-aa52-7027a2cbe805"),100.0, Season.in_season));
        roomFairData.addRoomFair(new RoomFair(UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982"),90.0, Season.in_season));
    }

    @Before
    public void createAccommodationRoomFair() throws SQLException {
        Connection connection = ConnectionData.getInstance().getConnection();
        try {
            //drop table
            connection.prepareStatement(
                    "DROP TABLE IF EXISTS accommodation_fair_relation CASCADE;").execute();
            //create table
            connection.prepareStatement(
                    "CREATE TABLE accommodation_fair_relation (\n" +
                            "  \tid uuid,\n" +
                            "  \tid_accommodation uuid,\n" +
                            "\tid_fair uuid,\n" +
                            "\tPRIMARY KEY (id),\n" +
                            "\tFOREIGN KEY (id_accommodation)\n" +
                            "      REFERENCES accommodation (id),\n" +
                            "\tFOREIGN KEY (id_fair)\n" +
                            "      REFERENCES room_fair (id)\n" +
                            ");").execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Test
    public void testAccommodationRoomFair() throws SQLException {
        AccommodationRoomFairData accommodationRoomFairData = new AccommodationRoomFairData();
        AccommodationData accommodationData = new AccommodationData();
        RoomFairData roomFairData = new RoomFairData();
        //add entries
        accommodationRoomFairData.addAccommodationRoomFair(new AccommodationRoomFair(UUID.fromString("28820412-2c9e-4f30-bcd0-7b822b5daabf"),
                UUID.fromString("246ed9e4-4fa5-46be-b9e2-70354fe05d86"), UUID.fromString("c42e0d3e-beaf-492c-91c3-0231a0b0c44b")));
        accommodationRoomFairData.addAccommodationRoomFair(new AccommodationRoomFair(UUID.fromString("926397b0-c056-49f1-a602-1f6e29799286"),
                UUID.fromString("3300d5a4-04f6-4cd0-8177-305fce3dde53"), UUID.fromString("3ab055a2-c00e-40a1-9bc1-68b00148e848")));
        accommodationRoomFairData.addAccommodationRoomFair(new AccommodationRoomFair(UUID.fromString("8f2dee16-ba30-4efb-b368-fe0c60bac45e"),
                UUID.fromString("0d21af67-656d-44b6-a807-1ad4ae0cc823"), UUID.fromString("40d20d7f-fab5-44ff-a2b4-e97f7d412c6a")));
        accommodationRoomFairData.addAccommodationRoomFair(new AccommodationRoomFair(UUID.fromString("f7dded58-30c0-4077-b3bf-3c0f9ef02900"),
                UUID.fromString("60068424-e82f-4d4d-88ac-ff85714386b9"), UUID.fromString("adf14ac3-9796-4558-aa52-7027a2cbe805")));
        accommodationRoomFairData.addAccommodationRoomFair(new AccommodationRoomFair(UUID.fromString("083b9914-63dc-4ab1-bb79-912f737a95ef"),
                UUID.fromString("e7467812-4753-4dfe-a436-d12ccbe62726"), UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982")));
        //update entry
        accommodationRoomFairData.updateAccommodationRoomFair(UUID.fromString("083b9914-63dc-4ab1-bb79-912f737a95ef"), new AccommodationRoomFair(
                UUID.fromString("e7467812-4753-4dfe-a436-d12ccbe62726"), UUID.fromString("e4c2f202-15b6-4c4b-8e2c-444039a4d982")));
        //get entry and assert
        assertEquals(UUID.fromString("e7467812-4753-4dfe-a436-d12ccbe62726"), accommodationRoomFairData.getAccommodationRoomFair(UUID.fromString("083b9914-63dc-4ab1-bb79-912f737a95ef")).getIdAccommodation());
        //delete entry
        accommodationRoomFairData.deleteAccommodationRoomFair(UUID.fromString("083b9914-63dc-4ab1-bb79-912f737a95ef"));
        //list entries and assert
        List<AccommodationRoomFair> relations = accommodationRoomFairData.listAccommodationRoomFair();
        assertEquals(4,relations.size());
        relations.stream().forEach(System.out::println);

        //finally print prices of rooms to console
        PrintPrices.printPrices();
    }
}
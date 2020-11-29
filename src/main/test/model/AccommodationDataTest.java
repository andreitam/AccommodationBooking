package model;


import andreitam.entity.Accommodation;
import andreitam.enums.BedType;
import andreitam.enums.RoomType;
import andreitam.model.AccommodationData;
import andreitam.model.ConnectionData;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class AccommodationDataTest {
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
    }

    @Test
    public void testAccommodation() throws SQLException {
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
        //update entries
        accommodationData.updateAccommodation(UUID.fromString("e7467812-4753-4dfe-a436-d12ccbe62726"),
                new Accommodation(RoomType.Double_Room, BedType.three_single_beds,3,"park view"));
        //get entry and assert
        assertEquals( RoomType.Single_Room,
                accommodationData.getAccommodation(UUID.fromString("246ed9e4-4fa5-46be-b9e2-70354fe05d86")).getType());
        //delete entry
        accommodationData.deleteAccomodation(UUID.fromString("3300d5a4-04f6-4cd0-8177-305fce3dde53"));
        //list entries and assert
        List<Accommodation> acc = accommodationData.listAccommodation();
        assertEquals(4, acc.size());
        acc.stream().forEach(System.out::println);
    }
}
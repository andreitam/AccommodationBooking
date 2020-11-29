package andreitam.service;

import andreitam.enums.BedType;
import andreitam.enums.RoomType;
import andreitam.enums.Season;
import andreitam.model.ConnectionData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintPrices {
    private static Connection connection;

    static {
        try {
            connection = ConnectionData.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printPrices() {
        try {
            PreparedStatement selectJoinAccommodationFair = connection.prepareStatement(
                    "SELECT\n" +
                            "\tacc.type, acc.bed_type, acc.max_guests,\n" +
                            "\trf.value, rf.season\n" +
                            "FROM accommodation_fair_relation afr\n" +
                            "INNER JOIN accommodation acc ON acc.id = afr.id_accommodation\n" +
                            "INNER JOIN room_fair rf ON rf.id = afr.id_fair;");
            ResultSet joinAccommodationFairResultSet = selectJoinAccommodationFair.executeQuery();
            System.out.println("-------Room Prices Offer---------");
            while(joinAccommodationFairResultSet.next()){
                System.out.println(
                        "Room type: "+ RoomType.valueOf(joinAccommodationFairResultSet.getString(1))+
                        " Bed type: "+ BedType.valueOf(joinAccommodationFairResultSet.getString(2))+
                        " Max guests: "+joinAccommodationFairResultSet.getInt(3)+
                        " Romm price in $: "+joinAccommodationFairResultSet.getDouble(4)+
                        " "+ Season.valueOf(joinAccommodationFairResultSet.getString(5)));
            }
            System.out.println("----------------------------------");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

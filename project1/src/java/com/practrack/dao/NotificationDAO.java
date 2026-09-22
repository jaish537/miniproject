package com.practrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDAO {

    public int addNotification(String userType,
                               long userId,
                               String message)
            throws SQLException {

        String sql =
                "INSERT INTO notifications " +
                "(notification_id, user_type, user_id, message) " +
                "VALUES (notification_seq.NEXTVAL, ?, ?, ?)";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, userType);
        ps.setLong(2, userId);
        ps.setString(3, message);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    public ResultSet getNotifications(String userType,
                                      long userId)
            throws SQLException {

        String sql =
                "SELECT * FROM notifications " +
                "WHERE user_type = ? AND user_id = ? " +
                "ORDER BY created_at DESC";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, userType);
        ps.setLong(2, userId);

        return ps.executeQuery();
    }

    public int markAsRead(long notificationId)
            throws SQLException {

        String sql =
                "UPDATE notifications " +
                "SET is_read = 1 " +
                "WHERE notification_id = ?";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setLong(1, notificationId);

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }
}
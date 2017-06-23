package com.solitudecraft.solitudeessentials.messages;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nolan on 6/21/2017.
 */
public class MessageMySQL {

    private Connection connection;

    public MessageMySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMessage(Message message) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
            String date = dateFormat.format(message.messageDate);
            PreparedStatement statement = connection.prepareStatement("insert into Messages (SENDER, RECEIVER, MESSAGE, DATE)\nvalues ('" + message.UUID1 + "', '" + message.UUID2 + "', '" + message.messageContent + "', '" + date + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateMessageDatabase() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `Messages`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String date = resultSet.getString("DATE");
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
                Date newDate = dateFormat.parse(date);
                Message message = new Message(resultSet.getString("SENDER"), resultSet.getString("RECEIVER"), resultSet.getString("MESSAGE"), newDate);
                MessageLookupCommand.previousMessages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package org.softwire.training.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.softwire.training.properties.Creds;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectAccess {

    List<String> getAllEmployeeNames() throws SQLException, java.io.IOException {
        Creds creds = new Creds().getCreds();

        try
        {
            Connection connection = DriverManager.getConnection(creds.getUrl(),creds.getUser(),creds.getPassword());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT first_name FROM employees");
            List<String> names = new ArrayList<>();
            while (resultSet.next()) {
                names.add(resultSet.getString("first_name"));
            }
            return names;
        }
        catch (Exception e)
        {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
            throw e;
        }
    }

//    public Creds getCreds() throws IOException {
//// Instantiating a new ObjectMapper as a YAMLFactory
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        return mapper.readValue(new File("src/main/resources/creds.yml"), Creds.class);
//    }
}

package ru.sanctio.dao;

import jakarta.ejb.Singleton;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DBManagerSelectImpl implements DBManagerSelect {
    @Override
    public List<Address> getAllInformation() {
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT a.id,a.ip,a.mac, a.model, a.address, c.clientid, c.clientname, c.type, c.added " +
                     "FROM address as a join client c on c.clientid = a.clientid")) {

            return readProductsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Address> readProductsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Address> res = new ArrayList<>();
        while (resultSet.next()) {
            res.add(new Address(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5),
                    new Client(resultSet.getInt(6), resultSet.getString(7),
                            resultSet.getString(8), resultSet.getDate(9).toString())));
        }
        return res;
    }
}

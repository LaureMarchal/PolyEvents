package persistence;

import business.dao.UserDAO;
import business.model.User;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PostgreSQL DAO for the user model
 */
public class UserDAOPG implements UserDAO {

    @Override
    public User read(String pseudo) {
        try {
            String query = "SELECT * FROM \"User\" WHERE pseudo = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            connection.close();
            if (!rs.next()) {
                return null;
            } else {
                return new User(rs.getString("pseudo"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return true;
    }

}

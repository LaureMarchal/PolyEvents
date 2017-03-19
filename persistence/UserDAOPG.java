package persistence;

import bl.dao.UserDAO;
import bl.model.Role;
import bl.model.User;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PostgreSQL DAO for the user model
 */
public class UserDAOPG extends UserDAO {

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
                return new User(rs.getString("pseudo"), rs.getString("password"), rs.getString("email"), Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User create(User user) {
        try {
            String query = "INSERT INTO \"User\" (pseudo, password, email, role) VALUES (?, ?, ?, ?::\"Role\")";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getPseudo());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole().name());
            ps.execute();
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

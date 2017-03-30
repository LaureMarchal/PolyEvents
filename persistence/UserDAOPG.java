package persistence;

import bl.dao.UserDAO;
import bl.model.*;
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
            Connection connection = Connector.getInstance().getConnection();
            String query = "SELECT * FROM \"User\" WHERE pseudo = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            } else {
                User user = new User(rs.getString("pseudo"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role")));

                // Specify the user in his role by do a more specified request
                query = "SELECT * FROM " + user.getRole().name() + " WHERE userID = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getPseudo());
                rs = ps.executeQuery();
                rs.next();
                connection.close();

                switch (user.getRole()) {
                    case CONSUMER:
                        return new Consumer(user.getPseudo(), user.getPassword(), user.getEmail(),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("comments"));
                    case PROVIDER:
                        return new Provider(user.getPseudo(), user.getPassword(), user.getEmail(),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("phone"),
                                rs.getString("website"),
                                rs.getString("officeLocation"));
                    case ADMINISTRATOR:
                        return new Administrator(user.getPseudo(), user.getPassword(), user.getEmail());
                    default:
                        return null;
                }
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
            return createAssociatedRole(user);
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
        try {
            String query = "DELETE FROM \"User\" WHERE userID = ? ";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getPseudo());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User createAssociatedRole(User user) {
        try {
            String query;
            PreparedStatement ps;
            Connection connection = Connector.getInstance().getConnection();
            switch (user.getRole()) {
                case CONSUMER:
                    query = "INSERT INTO Consumer (firstName, lastName, comments, userID) VALUES (?, ?, ?, ?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, ((Consumer) user).getFirstName());
                    ps.setString(2, ((Consumer) user).getLastName());
                    ps.setString(3, ((Consumer) user).getComments());
                    ps.setString(4, user.getPseudo());
                    ps.execute();
                    break;
                case PROVIDER:
                    query = "INSERT INTO Provider (name, description, phone, website, officeLocation, userID) VALUES (?, ?, ?, ?, ?, ?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, ((Provider) user).getName());
                    ps.setString(2, ((Provider) user).getDescription());
                    ps.setString(3, ((Provider) user).getPhone());
                    ps.setString(4, ((Provider) user).getWebsite());
                    ps.setString(5, ((Provider) user).getOfficeLocation());
                    ps.setString(6, user.getPseudo());
                    ps.execute();
                    break;
                case ADMINISTRATOR:
                    query = "INSERT INTO administrator (userID) VALUES (?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, user.getPseudo());
                    ps.execute();
                    break;
            }
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

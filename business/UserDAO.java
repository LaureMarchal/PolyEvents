package business;

import db.Connector;
import db.UserDAOPG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDAO extends Connector {

    private static UserDAO instance;

    protected UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAOPG();
        }
        return instance;
    }

    public User read(String pseudo) {
        try {
            String query = "SELECT * FROM \"User\" WHERE pseudo = ?";
            PreparedStatement ps = this.getConnection().prepareStatement(query);
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
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

}

package persistence;

import bl.dao.ProviderReviewDAO;
import bl.model.Consumer;
import bl.model.Provider;
import bl.model.ProviderReview;
import persistence.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PostgreSQL DAO for the provider review model
 */
public class ProviderReviewDAOPG extends ProviderReviewDAO {

    @Override
    public ProviderReview create(Provider provider, Consumer consumer, ProviderReview providerReview) {
        try {
            String query = "INSERT INTO Provider_review (consumerID, providerID, rate, content) VALUES (?, ?, ?, ?)";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, consumer.getPseudo());
            ps.setString(2, provider.getPseudo());
            ps.setInt(3, providerReview.getRate());
            ps.setString(4, providerReview.getContent());
            ps.execute();
            connection.close();
            return providerReview;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProviderReview> getAllReviewsForProvider(Provider provider) {
        try {
            List<ProviderReview> providerReviews = new ArrayList<ProviderReview>();
            String query = "SELECT * FROM Provider_review WHERE providerID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, provider.getPseudo());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String content = rs.getString("content");
                int rate = rs.getInt("rate");
                //TODO fix when ConsumerDAOPG is done
                // Unused given the current constructor of Provider : ProviderReview(String content, int rate)
                //Consumer consumer = (Consumer)DAOFactory.getInstance().createUserDAO().read(rs.getString("consumerID"));
                ProviderReview providerReview = new ProviderReview(content, rate);
                providerReviews.add(providerReview);
            }
            connection.close();
            return providerReviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProviderReview getReviewForProviderAndConsumer(Provider provider, Consumer consumer) {
        try {
            String query = "SELECT * FROM Provider_review WHERE providerID = ? AND consumerID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, provider.getPseudo());
            ps.setString(2, consumer.getPseudo());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                connection.close();
                return null;
            } else {
                connection.close();
                int rate = rs.getInt("rate");
                String content = rs.getString("content");
                return new ProviderReview(content, rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Provider provider, Consumer consumer) {
        try {
            String query = "DELETE FROM Provider_review WHERE providerID = ? AND consumerID = ?";
            Connection connection = Connector.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, provider.getPseudo());
            ps.setString(2, consumer.getPseudo());
            ps.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

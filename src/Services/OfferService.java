package Services;

import Models.Offer;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class OfferService {
    public static Offer getOffer(int offerId) throws SQLException {
        Offer result = null;
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_ogloszenia, tytul, opis, do_kiedy, od_kiedy, cena FROM ogloszenia WHERE id_ogloszenia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, offerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new Offer(resultSet.getInt("id_ogloszenia"), resultSet.getString("tytul"), resultSet.getString("opis"), resultSet.getDate("od_kiedy"), resultSet.getDate("do_kiedy"), resultSet.getDouble("cena"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    public static List<Offer> getUserOffers(int userID) throws SQLException {
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT o.* FROM reservation r INNER JOIN ogloszenia o ON r.id_ogloszenia = o.id_ogloszenia WHERE r.id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                offers.add(new Offer(resultSet.getInt("id_ogloszenia"), resultSet.getString("tytul"), resultSet.getString("opis"), resultSet.getDate("od_kiedy"), resultSet.getDate("do_kiedy"), resultSet.getDouble("cena")));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return offers;
    }


    public static List<Offer> getOffers() throws SQLException {
        List<Offer> offers = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_ogloszenia, tytul, opis, do_kiedy, od_kiedy, cena FROM ogloszenia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                offers.add(new Offer(resultSet.getInt("id_ogloszenia"), resultSet.getString("tytul"), resultSet.getString("opis"), resultSet.getDate("od_kiedy"), resultSet.getDate("do_kiedy"), resultSet.getDouble("cena")));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return offers;
    }

    public static void updateOffer(int offerID, Offer offer) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "UPDATE ogloszenia SET tytul= ? ,opis= ? ,od_kiedy= ? ,do_kiedy= ? ,cena= ? WHERE id_ogloszenia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, offer.getName());
            preparedStatement.setString(2, offer.getDescription());
            preparedStatement.setDate(3, offer.getDateFrom());
            preparedStatement.setDate(4, offer.getDateTo());
            preparedStatement.setDouble(5, offer.getPrice());
            preparedStatement.setInt(6, offerID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int createOffer(Offer offer) throws SQLException {
        int id = -1;
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO ogloszenia (tytul, opis, od_kiedy, do_kiedy, cena) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, offer.getName());
            preparedStatement.setString(2, offer.getDescription());
            preparedStatement.setDate(3, offer.getDateFrom());
            preparedStatement.setDate(4, offer.getDateTo());
            preparedStatement.setDouble(5, offer.getPrice());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }

            preparedStatement.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        }

        return id;
    }

    public static void removeOffer(int offerId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM ogloszenia WHERE id_ogloszenia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, offerId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

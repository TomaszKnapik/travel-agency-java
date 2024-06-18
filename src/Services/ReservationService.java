package Services;

import Models.Offer;
import Models.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    public static Reservation getReservation(int reservationID) throws SQLException {
        Reservation result = null;
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_reservation, id_ogloszenia, id_user, aktywne FROM reservation WHERE id_reservation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("id_ogloszenia"), resultSet.getInt("id_user"), resultSet.getInt("aktywne"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static boolean userHasReservation(int userId, int offerId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM reservation WHERE id_user = ? AND id_ogloszenia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, offerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<Reservation> getReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT id_reservation, id_ogloszenia, id_user, aktywne FROM reservation WHERE id_reservation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet.getInt("id_reservation"), resultSet.getInt("id_ogloszenia"), resultSet.getInt("id_user"), resultSet.getInt("aktywne")));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return reservations;
    }

    public static void updateReservation(int reservationID, Reservation reservation) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "UPDATE reservation SET id_ogloszenia= ? ,id_user= ?, aktywne WHERE id_reservation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservation.getOfferID());
            preparedStatement.setInt(2, reservation.getUserID());
            preparedStatement.setInt(3, reservation.getActive());
            preparedStatement.setInt(4, reservationID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int createReservation(Reservation reservation) throws SQLException {
        int id = -1;
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO reservation (id_user, id_ogloszenia) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, reservation.getUserID());
            preparedStatement.setInt(2, reservation.getOfferID());

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
    public static void removeReservation(int reservationID) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM reservation WHERE id_reservation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reservationID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
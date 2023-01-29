package com.epam.exhibitions.database;
import com.epam.exhibitions.database.DAO.ExhibitionsDAO;
import com.epam.exhibitions.database.Entities.Exhibition;
import com.epam.exhibitions.database.Entities.Hall;
import com.epam.exhibitions.database.Entities.Queries;
import com.epam.exhibitions.database.Entities.User;
import com.epam.exhibitions.services.UserService;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ExhibitionsDAOmySQLImpl implements ExhibitionsDAO {

    final static Logger logger = Logger.getLogger(ExhibitionsDAOmySQLImpl.class);
    private final UserService userService = new UserService();

    @Override
    public Exhibition getById(int id) {
        Exhibition exhibition = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_BY_ID);
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if(rs.next()){
                exhibition = extractExhibition(rs);
            }
            DataSource.close(conn);
        } catch (SQLException e){
            System.out.println("Exhibition retrievement by ID failed.");
        }
        return exhibition;
    }

    @Override
    public void addExhibition(Exhibition exhibition) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_ADD, Statement.RETURN_GENERATED_KEYS);
            setProperties(pstmt, exhibition);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                exhibition.setExhibitionId(id);
            }
            conn.commit();
            logger.info("Exhibition (id: " + exhibition.getExhibitionId() + ") was added to the database.");
        } catch (SQLException e){
            System.out.println("Exhibition addition failed.");
            logger.error(e);
        }
    }

    @Override
    public List<Exhibition> getAll() {
        List<Exhibition> exhibitions = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.EXHIBITION_GET_ALL);
            while(rs.next()){
                Exhibition exhibition = extractExhibition(rs);
                exhibitions.add(exhibition);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Exhibition list retrievement failed.");
        }
        return exhibitions;
    }

    @Override
    public List<Hall> getHalls(int id) {
        List<Hall> halls = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_HALLS);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Hall hall = new HallsDAOmySQLImpl().extractHall(rs);
                halls.add(hall);
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("List of exhibitions' halls retrievement failed.");
        }
        return halls;
    }

    @Override
    public void setHalls(int id, String[] ids) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_SET_HALLS);
            pstmt.setInt(1, id);

            for(String hallId : ids){
                pstmt.setInt(2, Integer.parseInt(hallId));
                pstmt.executeUpdate();
                conn.commit();
                logger.info("Hall (id: " + Integer.parseInt(hallId) + ") was successfully assigned to exhibition (id: " + id + ").");
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Hall assignment failed.");
        }
    }

    @Override
    public int getNumberOfTicketsSold(int id) {
        int ticketsSold = 0;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_TICKETS_SOLD);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ticketsSold = rs.getInt("number");
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Number of tickets sold retrievement failed.");
        }
        return ticketsSold;
    }

    @Override
    public Map<String, Integer> getStatisticsById(int id) {
        Map<String, Integer> statistics = new HashMap<>();
        List<User> userList = userService.getAllUsers();
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_STATS_BY_ID);
            ResultSet rs;
            for(User users : userList){
                pstmt.setInt(1, users.getId());
                pstmt.setInt(2, id);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    statistics.put(users.getLogin(), rs.getInt("ticket_purchases"));
                }
            }
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Unable to retrieve statistics (exhibition id: " + id);
        }
        return statistics.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prevValue, newValue) -> prevValue, HashMap::new));
    }

    @Override
    public int getNumberOfExhibitions() {
        int numberOfExhibitions = 0;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_NUMBER);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                numberOfExhibitions = rs.getInt("number");
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return numberOfExhibitions;
    }

    @Override
    public int getNumberOfExhibitionsSortByDate(LocalDate date) {
        int numberOfExhibitions = 0;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_NUMBER_BY_DATE);
            pstmt.setDate(1, Date.valueOf(date));
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                numberOfExhibitions = rs.getInt("number");
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return numberOfExhibitions;
    }

    @Override
    public List<Exhibition> getByHalls(int page, int id) {
        List<Exhibition> exhibitions = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_BY_HALLS);
            pstmt.setInt(1, id);
            pstmt.setInt(2, (page - 1) * 4);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                exhibitions.add(extractExhibition(rs));
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return exhibitions;
    }

    @Override
    public Exhibition getByName(String name) {
        Exhibition exhibition = null;
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_BY_NAME);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                exhibition = extractExhibition(rs);
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return exhibition;
    }

    @Override
    public List<Exhibition> getByDate(int page, LocalDate date) {
        List<Exhibition> exhibitions = new ArrayList<>();

        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_GET_BY_DATE_SORT);
            pstmt.setDate(1, Date.valueOf(date));
            pstmt.setInt(2, (page - 1) * 4);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                exhibitions.add(extractExhibition(rs));
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return exhibitions;
    }

    @Override
    public List<Exhibition> getBySortType(int page, String type) {
        List<Exhibition> exhibitions = new ArrayList<>();
        String stmt;
        switch (type) {
            case "ticket_price":
                stmt = Queries.EXHIBITION_GET_BY_PRICE_SORT;
                break;
            case "theme":
                stmt = Queries.EXHIBITION_GET_BY_THEME_SORT;
                break;
            case "halls":
                stmt = Queries.EXHIBITION_GET_BY_HALLS;
                break;
            default:
                stmt = Queries.EXHIBITION_DEFAULT_SORT;
                break;
        }

        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(stmt);
            pstmt.setInt(1, (page - 1) * 4);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                exhibitions.add(extractExhibition(rs));
            }
        } catch (SQLException e){
            logger.error(e);
            e.printStackTrace();
        }
        return exhibitions;
    }

    @Override
    public void deleteExhibition(int id) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_DELETE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
            logger.info("Exhibition (id: " + id + ") was deleted from the database.");
        } catch (SQLException e){
            System.out.println("Exhibition deletion failed.");
            e.printStackTrace();
            logger.error(e);
        }
    }

    @Override
    public void updateExhibition(Exhibition exhibition) {
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_UPDATE);
            setProperties(pstmt, exhibition);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e){
            System.out.println("Exhibition update failed.");
            logger.error(e);
        }
    }

    @Override
    public Exhibition extractExhibition(ResultSet rs) {
        Exhibition exhibition = null;
        try{
            exhibition = new Exhibition(rs.getString("name"), rs.getString("theme"),
                    rs.getString("description"), rs.getDate("date_from").toLocalDate(), rs.getDate("date_to").toLocalDate(),
                    rs.getTime("working_from").toLocalTime(), rs.getTime("working_to").toLocalTime(),
                    rs.getDouble("ticket_price"));
            exhibition.setExhibitionId(rs.getInt("id"));
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Exhibition extraction failed.");
        }
        return exhibition;
    }

    @Override
    public List<Exhibition> exhibitionSorting(String name, Date date_from, Date date_to, BigDecimal price_min, BigDecimal price_max) {
        List<Exhibition> exhibitionList = new ArrayList<>();
        try(Connection conn = DataSource.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(Queries.EXHIBITION_SORT);
            pstmt.setBigDecimal(1, price_min);
            pstmt.setBigDecimal(2, price_max);
            pstmt.setDate(3, date_from);
            pstmt.setDate(4, date_to);
            pstmt.setString(5, name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Exhibition exhibition = extractExhibition(rs);
                exhibition.setExhibitionId(rs.getInt("id"));
                exhibitionList.add(exhibition);
            }
            logger.info("Sorted list of exhibitions was returned.");
            return exhibitionList;
        } catch (SQLException e){
            logger.error(e);
            System.out.println("Exhibition sorting failed.");
        }
        return null;
    }

    private static void setProperties(PreparedStatement pstmt, Exhibition exhibition){
        try{
            pstmt.setString(1, exhibition.getName());
            pstmt.setString(2, exhibition.getTheme());
            pstmt.setString(3, exhibition.getDescription());
            pstmt.setDate(4, Date.valueOf(exhibition.getDate_from()));
            pstmt.setDate(5, Date.valueOf(exhibition.getDate_to()));
            pstmt.setTime(6, Time.valueOf(exhibition.getWorking_from()));
            pstmt.setTime(7, Time.valueOf(exhibition.getWorking_to()));
            pstmt.setDouble(8, exhibition.getTicket_price());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

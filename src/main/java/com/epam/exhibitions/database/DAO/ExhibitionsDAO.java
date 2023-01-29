package com.epam.exhibitions.database.DAO;

import com.epam.exhibitions.database.Entities.Exhibition;
import com.epam.exhibitions.database.Entities.Hall;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ExhibitionsDAO {
    Exhibition getById(int id);
    void addExhibition(Exhibition exhibition);
    List<Exhibition> getAll();
    List<Hall> getHalls(int id);
    void setHalls(int id, String[] ids);
    int getNumberOfTicketsSold(int id);
    Map<String, Integer> getStatisticsById(int id);
    int getNumberOfExhibitions();
    int getNumberOfExhibitionsSortByDate(LocalDate date);
    List<Exhibition> getByHalls(int page, int id);
    Exhibition getByName(String name);
    List<Exhibition> getByDate(int page, LocalDate date);
    List<Exhibition> getBySortType(int page, String type);
    void deleteExhibition(int exhibition_id);
    void updateExhibition(Exhibition exhibition);
    Exhibition extractExhibition(ResultSet rs);
    List<Exhibition> exhibitionSorting(String name, Date date_from, Date date_to, BigDecimal price_min, BigDecimal price_max);
}

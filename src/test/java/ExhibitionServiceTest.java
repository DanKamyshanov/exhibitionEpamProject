//import com.epam.exhibitions.database.DAO.DAOFactory;
//import com.epam.exhibitions.database.DAO.ExhibitionsDAO;
//import com.epam.exhibitions.database.DAOFactoryMySQLImpl;
//import com.epam.exhibitions.database.DataSource;
//import com.epam.exhibitions.database.Entities.Exhibition;
//import com.epam.exhibitions.database.Entities.Hall;
//import com.epam.exhibitions.database.ExhibitionsDAOmySQLImpl;
//import com.epam.exhibitions.services.ExhibitionService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.mockito.Mock;
//import org.mockito.MockedConstruction;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.swing.text.html.Option;
//import java.sql.*;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ExhibitionServiceTest {
//
//    @Mock
//    ExhibitionsDAOmySQLImpl mockExhibitionsDAO;
//
//    private ExhibitionService exhibitionService;
//    private Exhibition exhibition = new Exhibition("unit test", "test theme", "test description",
//            LocalDate.now(), LocalDate.now().plusMonths(1), LocalTime.NOON, LocalTime.MIDNIGHT,
//            50.50);
//    private Hall hall = new Hall("unit test hall", "hall description");
//
//    @Before
//    public void setUp() {
//    }
//
//    @Test
//    public void exhibitionCreatePositive() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            doNothing().when(mockExhibitionsDAO).addExhibition(eq(exhibition));
//            exhibitionService.addExhibition(exhibition);
//            verify(mockExhibitionsDAO, times(1)).addExhibition(eq(exhibition));
//        }
//    }
//
//    @Test
//    public void exhibitionCreatePositive2() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            doNothing().when(mockExhibitionsDAO).addExhibition(any(Exhibition.class));
//            exhibitionService.addExhibition(exhibition.getName(), exhibition.getTheme(), exhibition.getDescription(),
//                    exhibition.getDate_from(), exhibition.getDate_to(), exhibition.getWorking_from(),
//                    exhibition.getWorking_to(), exhibition.getTicket_price());
//            verify(mockExhibitionsDAO, times(1)).addExhibition(eq(exhibition));
//        }
//    }
//
//    @Test
//    public void getAllExhibitionsPositive() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            when(mockExhibitionsDAO.getAll()).thenReturn(List.of(exhibition));
//            Assert.assertEquals(List.of(exhibition), exhibitionService.getAllExhibitions());
//            verify(mockExhibitionsDAO, times(1)).getAll();
//        }
//    }
//
//
////    public void setHalls(int id, String[] ids){
////        exhibitionsDAO.setHalls(id, ids);
////    }
////
////    public void updateExhibition(Exhibition exhibition){
////        exhibitionsDAO.updateExhibition(exhibition);
////    }
////
////    public void deleteExhibition(int id){
////        exhibitionsDAO.deleteExhibition(id);
////    }
////
////    public int getNumberOfTicketsSold(int id){
////        return exhibitionsDAO.getNumberOfTicketsSold(id);
////    }
////
////    public Optional<Exhibition> getByName(String name){
////        return Optional.ofNullable(exhibitionsDAO.getByName(name));
////    }
////
////    public List<Exhibition> getByDate(int page, LocalDate date){
////        return exhibitionsDAO.getByDate(page, date);
////    }
////
////    public List<Exhibition> getBySortType(int page, String type){
////        if(type.equals("default") || type.equals("theme") || type.equals("ticket_price")){
////            return exhibitionsDAO.getBySortType(page, type);
////        } else{
////            return null;
////        }
////    }
////
////    public int getNumber(){
////        return exhibitionsDAO.getNumberOfExhibitions();
////    }
////
////    public Map<String, Integer> getStatisticsById(int id){
////        return exhibitionsDAO.getStatisticsById(id);
////    }
////
////    public int getNumberByDate(LocalDate date){
////        return exhibitionsDAO.getNumberOfExhibitionsSortByDate(date);
////    }
//
//    @Test
//    public void getExhibitionByIdPositive() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            when(mockExhibitionsDAO.getById(1)).thenReturn(exhibition);
//            Assert.assertEquals(Optional.of(exhibition), exhibitionService.getById(1));
//            verify(mockExhibitionsDAO, times(1)).getById(eq(1));
//        }
//    }
//
//    @Test
//    public void getHallsByIdPositive() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            doNothing().when(mockExhibitionsDAO).setHalls(anyInt(), anyIterable());
//            Assert.assertEquals(List.of(hall), exhibitionService.getHalls(1));
//            verify(mockExhibitionsDAO, times(1)).getHalls(eq(1));
//        }
//    }
//
//    @Test
//    public void setHallsByIdPositive() {
//        try (MockedConstruction<DAOFactoryMySQLImpl> mockDaoFactory = mockConstruction(DAOFactoryMySQLImpl.class,
//                (daoFactoryMySQL, context) -> {
//                    when(daoFactoryMySQL.getExhibitionsDAO()).thenReturn(mockExhibitionsDAO);
//                }))
//        {
//            exhibitionService = new ExhibitionService();
//            when(mockExhibitionsDAO.getHalls(1)).thenReturn(List.of(hall));
//            Assert.assertEquals(List.of(hall), exhibitionService.getHalls(1));
//            verify(mockExhibitionsDAO, times(1)).getHalls(eq(1));
//        }
//    }
//}

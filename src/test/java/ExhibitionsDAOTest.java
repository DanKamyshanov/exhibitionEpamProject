import com.epam.exhibitions.database.DAO.ExhibitionsDAO;
import com.epam.exhibitions.database.DataSource;
import com.epam.exhibitions.database.Entities.Exhibition;
import com.epam.exhibitions.database.ExhibitionsDAOmySQLImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExhibitionsDAOTest {

    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        doNothing().when(mockConnection).commit();
        doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStatement).setDate(anyInt(), any(Date.class));
        doNothing().when(mockPreparedStatement).setTime(anyInt(), any(Time.class));
        doNothing().when(mockPreparedStatement).setDouble(anyInt(), anyDouble());
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
    }

    @Test
    public void exhibitionCreatePositive() throws SQLException {
        try (MockedStatic<DataSource> mockedDataSource = Mockito.mockStatic(DataSource.class)) {
            //mock
            mockedDataSource.when(DataSource::getConnection).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS)))
                    .thenReturn(mockPreparedStatement);
            when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
            when(mockResultSet.getInt(1)).thenReturn(1);

            ExhibitionsDAO instance = new ExhibitionsDAOmySQLImpl();
            Exhibition exhibition = new Exhibition("unit test", "test theme", "test description",
                    LocalDate.now(), LocalDate.now().plusMonths(1), LocalTime.NOON, LocalTime.MIDNIGHT,
                    50.50);
            instance.addExhibition(exhibition);

            //verify and assert
            verify(mockConnection, times(1)).prepareStatement(anyString(), anyInt());
            verify(mockPreparedStatement, times(3)).setString(anyInt(), anyString());
            verify(mockPreparedStatement, times(2)).setDate(anyInt(), any(Date.class));
            verify(mockPreparedStatement, times(2)).setTime(anyInt(), any(Time.class));
            verify(mockPreparedStatement, times(1)).setDouble(anyInt(), anyDouble());
            verify(mockPreparedStatement, times(1)).executeUpdate();
            verify(mockConnection, times(1)).commit();
            verify(mockPreparedStatement, times(1)).getGeneratedKeys();
            verify(mockResultSet, times(1)).next();
        }
    }

    @Test
    public void exhibitionCreateSqlException() throws SQLException, IOException {
        try (MockedStatic<DataSource> mockedDataSource = Mockito.mockStatic(DataSource.class)) {
            //mock
            when(mockConnection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());
            mockedDataSource.when(DataSource::getConnection).thenReturn(mockConnection);

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(byteStream));
            ExhibitionsDAO instance = new ExhibitionsDAOmySQLImpl();
            Exhibition exhibition = new Exhibition("unit test", "test theme", "test description",
                    LocalDate.now(), LocalDate.now().plusMonths(1), LocalTime.NOON, LocalTime.MIDNIGHT,
                    50.50);
            instance.addExhibition(exhibition);
            byteStream.flush();
            String allWrittenLines = byteStream.toString();
            assertTrue(allWrittenLines.contains("Exhibition addition failed."));

            //verify and assert
            verify(mockConnection, times(1)).prepareStatement(anyString(), anyInt());
            verify(mockPreparedStatement, times(0)).setString(anyInt(), anyString());
            verify(mockPreparedStatement, times(0)).setDate(anyInt(), any(Date.class));
            verify(mockPreparedStatement, times(0)).setTime(anyInt(), any(Time.class));
            verify(mockPreparedStatement, times(0)).setDouble(anyInt(), anyDouble());
            verify(mockPreparedStatement, times(0)).executeUpdate();
            verify(mockConnection, times(0)).commit();
            verify(mockPreparedStatement, times(0)).getGeneratedKeys();
            verify(mockResultSet, times(0)).next();
        }
    }
}
package sql.demo;

import org.h2.tools.DeleteDbFiles;
import sql.demo.repository.ShareRates;
import sql.demo.repository.Shares;
import sql.demo.repository.TraiderShareActions;
import sql.demo.repository.Traiders;

import java.sql.*;

public class StockExchangeDB {
    // Блок объявления констант
    public static final String DB_DIR = "C:/Users/79054/IdeaProjects/SQL/db/sql.demo";
    public static final String DB_FILE = "sql.demo.StockExchangeDB";
    public static final String DB_URL = "jdbc:h2:C:/Users/79054/IdeaProjects/SQL/db/sql.demo/sql.demo.StockExchangeDB";
    public static final String DB_Driver = "org.h2.Driver";

    // Таблицы СУБД
    Traiders traiders;
    ShareRates shareRates;
    Shares shares;
    TraiderShareActions traiderShareActions;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public StockExchangeDB(boolean renew) throws SQLException, ClassNotFoundException {
        if (renew)
            DeleteDbFiles.execute(DB_DIR, DB_FILE, false);
        Class.forName(DB_Driver);
        // Инициализируем таблицы
        traiders = new Traiders();
        shares = new Shares();
        shareRates = new ShareRates();
        traiderShareActions = new TraiderShareActions();
    }

    // Инициализация по умолчанию, без удаления файла БД
    public StockExchangeDB() throws SQLException, ClassNotFoundException {
        this(false);
    }

    public void createTablesAndForeignKeys() throws SQLException {
        shares.createTable();
        shareRates.createTable();
        traiders.createTable();
        traiderShareActions.createTable();
        // Создание ограничений на поля таблиц
        traiderShareActions.createExtraConstraints();
        shares.createExtraConstraints();
        // Создание внешних ключей (связи между таблицами)
        shareRates.createForeignKeys();
        traiderShareActions.createForeignKeys();
    }

    public static void main(String[] args) {
        try {
            StockExchangeDB stockExchangeDB = new StockExchangeDB(true);
            stockExchangeDB.createTablesAndForeignKeys();
        } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
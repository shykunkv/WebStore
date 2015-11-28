package manager;

import dao.DaoFactory;
import dao.mysql.MySqlDaoFactory;
import ents.CardItem;

import java.sql.Connection;
import java.util.List;

public class CardItemManager {
    private static DaoFactory<Connection> factory = MySqlDaoFactory.getInstance();

    List<CardItem>
}

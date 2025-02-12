package TpAccountTaskCategory;

import TpTabCollectionEtSQL.Env;
import TpTabCollectionEtSQL.Model.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Database {
    //Connexion à la BDD
    public static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(Env.DB_URL, Env.DB_LOGIN,
                    Env.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

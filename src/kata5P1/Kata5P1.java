package kata5P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    public static void main(String[] args) {
        new Kata5P1().selectAllPeople();
        new Kata5P1().createTableEmail();
        
    }
    private Connection connect(String database){
        Connection conn = null;
        
        String url = "jdbc:sqlite:"+database;
        try {
            conn = DriverManager.getConnection(url);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
    
    private void selectAllPeople(){
        try {
            Connection conn = connect("Kata5.db");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PEOPLE");
            while(rs.next()){
                System.out.println( rs.getString("id") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Apellidos") + "\t" +
                        rs.getString("Departamento") + "\t");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void createTableEmail(){
        Connection conn = connect("Kata5.db");
        String sql = "CREATE TABLE \"EMAIL\" (\n" +
        "	\"Id\"	INTEGER,\n" +
        "	\"Mail\"	TEXT NOT NULL,\n" +
        "	PRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
        ")";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

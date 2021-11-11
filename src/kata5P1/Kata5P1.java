package kata5P1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kata5P1 {

    public static void main(String[] args) {
        new Kata5P1().selectAllPeople();
        new Kata5P1().createTableEmail();
        new Kata5P1().addEmails("email.txt");
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
        String sql = "CREATE TABLE IF NOT EXISTS \"EMAIL\" (\n" +
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
    
    private void addEmails(String ruta){
            List<String> list = null;
            try {
                list = MailListReader.read(ruta);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            String sql = "INSERT INTO EMAIL(mail) VALUES (?)";
            Connection conn= connect("KATA5.db");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (String string : list) {
                pstmt.setString(1, string);
                pstmt.executeUpdate();
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

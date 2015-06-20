package jksiazkuch;

//TEST
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//TEST

public class JKsiazKuch {

    public static void main(String[] args) {
        // TODO code application logic here
        
        //TES
        Connection con;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:../DB/cookbook.db");
            Statement state = con.createStatement();
            ResultSet rsl = state.executeQuery("SELECT * FROM przepis");
            
            while(rsl.next()){
                System.out.println(rsl.getInt(1));
                System.out.println(rsl.getString(2));
                System.out.println(rsl.getInt(3));
                System.out.println(rsl.getBoolean(4));
                System.out.println(rsl.getInt(5));
                System.out.println(rsl.getString(6));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //TEST
    }
    
}

package jksiazkuch;

//TEST
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//TEST

public class JKsiazKuch {

    public static void main(String[] args) {
        // TODO code application logic here
        
        //TEST
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            try{
                con = DriverManager.getConnection("jdbc:sqlite:../DB/cookbook.db");
                Statement state = con.createStatement();
            
                ArrayList<Przepis> przepiss = Przepis.getObjects(state);
            
                for(int i = 0; i < przepiss.size(); ++i){
                    System.out.println(przepiss.get(i).getId());
                    System.out.println(przepiss.get(i).getNazwa());
                    System.out.println(przepiss.get(i).getCzasPrzygotowania());
                    System.out.println(przepiss.get(i).getTrudnosc());
                    System.out.println(przepiss.get(i).getInstrukcja());
                    System.out.println(przepiss.get(i).isUlubione());
                }
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                con.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        //TEST
    }
    
}

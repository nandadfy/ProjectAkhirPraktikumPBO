/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOkonser;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datakonserimplement;
/**
 *
 * @author asus
 */
public class datakonserDAO implements datakonserimplement {
    Connection connection;
    
    final String select = "SELECT * FROM tiket";
    final String insert = "INSERT INTO tiket (id_tiket, seat, harga) VALUES (?,?,?);";
    final String update = "UPDATE tiket set id_tiket=?, seat=?, harga=?";
    final String delete = "DELETE from tiket where id_tiket=?";
    
    public datakonserDAO(){
        connection = connector.connection();
        
    }

    @Override
    public void insert(datakonser k) {
       PreparedStatement statement = null;
       try{
           statement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
           statement.setInt(1, k.getId_konser());
           statement.setString(2, k.getSeat());
           statement.setInt(3, k.getHarga());
           statement.execute();
           ResultSet rs = statement.getGeneratedKeys();
       }catch(SQLException ex){
          System.out.println("Input Gagal(" + ex.getMessage() + ")");
       }finally{
           try{
               statement.close();
           }catch(SQLException ex){
               System.out.println("Input gagal");
           }
       }
    }

    @Override
    public void update(datakonser k) {
        PreparedStatement statement = null;
       try{
           statement = connection.prepareStatement(update);
           statement.setInt(1, k.getId_konser());
           statement.setString(2, k.getSeat());
           statement.setInt(3, k.getHarga());
           statement.execute();
       }catch(SQLException ex){
           System.out.println("Update Gagal(" + ex.getMessage() + ")");
       }finally{
           try{
               statement.close();
           }catch(SQLException ex){
               System.out.println("Update gagal");
           }
       }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.execute();
        }catch(SQLException ex){
           System.out.println("Delete Gagal (" + ex.getMessage() + ")");
       }finally{
           try{
               statement.close();
           }catch(SQLException ex){
               System.out.println("Delete Gagal");
           }
       }
        
    }

    @Override
    public List<datakonser> getAll() {
        List<datakonser> dk = null;
        try{
            dk = new ArrayList<datakonser>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
           while (rs.next()){
               datakonser konser = new datakonser();
               konser.setId_konser(rs.getInt("id_tiket"));
               konser.setSeat(rs.getString("seat"));
               konser.setHarga(rs.getInt("harga"));
               dk.add(konser);
           }
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        return dk;
    }
}

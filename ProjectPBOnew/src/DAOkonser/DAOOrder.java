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
import DAOImplement.interfaceOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author asus
 */
public class DAOOrder implements interfaceOrder{
    Connection connection;
    
    final String select = "SELECT * FROM order";
    final String insert = "INSERT INTO `order` (`nama`, `seat`, `harga`) VALUES (?,?,?)";
    
    public DAOOrder(){
        connection = connector.connection();
        
    }

    @Override
    public void insert(dataOrder o) {
        PreparedStatement statement = null;
       try{
           statement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
           statement.setString(1, o.getNama());
           statement.setString(2, o.getSeat());
           statement.setString(3, Integer.toString(o.getHarga()));
           statement.execute();
       }catch(SQLException ex){
           System.out.println("Input Gagal("+ ex.getMessage()+")");
       }finally{
           try{
               statement.close();
           }catch(SQLException ex){
               System.out.println("Input gagal");
           }
       }
    }

    @Override
    public List<dataOrder> getAll() {
     List<dataOrder> dor = null;
        try{
            dor = new ArrayList<dataOrder>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
           while (rs.next()){
               dataOrder order = new dataOrder();
               order.setId(rs.getInt("id"));
               order.setNama(rs.getString("nama"));
               order.setSeat(rs.getString("seat"));
               order.setHarga(rs.getInt("harga"));
               dor.add(order);
           }
        }catch(SQLException ex){
            Logger.getLogger(datakonserDAO.class.getName()).log(Level.SEVERE, null,ex);
            System.out.println("Error :" + ex.getMessage());
        }
        
        return dor;   
    }
}

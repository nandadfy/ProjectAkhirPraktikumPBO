/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import DAOImplement.datakonserimplement;
import java.util.List;
import DAOkonser.DAOOrder;
import DAOImplement.interfaceOrder;
import DAOkonser.datakonserDAO;
import javax.swing.JOptionPane;
import model.*;
import view.BeliTiket;
/**
 *
 * @author asus
 */
public class controllerOrder {
    BeliTiket frame;
    interfaceOrder impldataorder;
    datakonserimplement impldatakonser;
    
    List<dataOrder> dor;
    List<datakonser> dk;
    String nama, seat;
    Integer harga;
    
    public controllerOrder (BeliTiket frame){
        this.frame = frame;
        
        impldataorder = new DAOOrder();
        impldatakonser = new datakonserDAO();
          
    }
    
     public void readDataTiket() {
        dk = impldatakonser.getAll();
        modeltabelkonser mo = new modeltabelkonser(dk);
        frame.getTabeldata().setModel(mo);
    }
    
    public void readFieldTiket(int row) {
        seat = dk.get(row).getSeat();
        harga = dk.get(row).getHarga();
        
        frame.setSeat(seat);
        frame.setHarga(harga);
    }
    
    public void inserttable(){
        nama = frame.getNama().getText();
        try {
            dataOrder order = new dataOrder();
            order.setNama(nama);
            order.setSeat(seat);
            order.setHarga(harga);
            impldataorder.insert(order);
            JOptionPane.showMessageDialog(null, "Order Success");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        
    }
}

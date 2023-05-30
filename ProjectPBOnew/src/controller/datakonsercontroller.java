/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOkonser.datakonserDAO;
import DAOImplement.datakonserimplement;
import javax.swing.JOptionPane;
import model.*;
import view.EditTiket;

/**
 *
 * @author asus
 */
public class datakonsercontroller {
    EditTiket frame;
    datakonserimplement impldatakonser;
    List<datakonser> dk;
    
    public datakonsercontroller (EditTiket frame){
        this.frame = frame;
        impldatakonser = new datakonserDAO();   
    }
    
    public void readData(){
        dk = impldatakonser.getAll();
        modeltabelkonser mk = new modeltabelkonser(dk);
        frame.getTabeldata().setModel(mk);
        
    }
    
    public void isiField(int row)
    {
    frame.getId_tiket().setText(dk.get(row).getId_konser().toString());
    frame.getSeat().setText(dk.get(row).getSeat());
    frame.getHarga().setText(dk.get(row).getHarga().toString());
    
    }
    
    public void reset()
    {
       frame.getId_tiket().setText("");
       frame.getSeat().setText("");
       frame.getHarga().setText("");
    }
    
    public void insert(){
        datakonser dkonser = new datakonser();
        dkonser.setId_konser(Integer.parseInt(frame.getId_tiket().getText()));
        dkonser.setSeat(frame.getSeat().getText());
        dkonser.setHarga(Integer.parseInt(frame.getHarga().getText()));
        impldatakonser.insert(dkonser);
    }
    
    public void update(){
        datakonser dkonser = new datakonser();
        dkonser.setId_konser(Integer.parseInt(frame.getId_tiket().getText()));
        dkonser.setSeat(frame.getSeat().getText());
        dkonser.setHarga(Integer.parseInt(frame.getHarga().getText()));
        impldatakonser.update(dkonser);
    }
    
    public void delete(){
        datakonser dkonser = new datakonser();
        int id = Integer.parseInt(frame.getId_tiket().getText());
        impldatakonser.delete(id);
        JOptionPane.showMessageDialog(null, "Delete Berhasil");
        
    }
}

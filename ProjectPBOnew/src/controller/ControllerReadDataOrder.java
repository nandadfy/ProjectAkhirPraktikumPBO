/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import DAOkonser.DAOOrder;
import DAOImplement.datakonserimplement;
import DAOImplement.interfaceOrder;
import java.util.List;
import model.dataOrder;
import model.datakonser;
import view.BeliTiket;
import view.ShowTicket;
/**
 *
 * @author asus
 */
public class ControllerReadDataOrder {
    BeliTiket frame;
    ShowTicket viewTicket;
    datakonserimplement iTiket;
    interfaceOrder iOrder;
    List<datakonser> dk;

    public ControllerReadDataOrder(ShowTicket viewTicket) {
        this.viewTicket = viewTicket;
    }

    public void readDataOrder(String nama, String seat, String harga) {
        iOrder = new DAOOrder();
        viewTicket.setVisible(true);
        viewTicket.getNama().setText(nama);
        viewTicket.getSeat().setText(seat);
        viewTicket.getHarga().setText(harga);
    }
}

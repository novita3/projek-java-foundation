/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobadatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author vita
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField textId;

    @FXML
    private TextField textPemesan;

    @FXML
    private TextField textAlamat;

    @FXML
    private TextField textNama;

    @FXML
    private TextField textJumlah;

    @FXML
    private TextField textHarga;

    @FXML
    private void btnSimpanClicked(ActionEvent event) {
        Connection c = koneksi.koneksi();
        PreparedStatement ps;
        try {
            String sql = "insert into data_pesanan values(?,?,?,?,?,?);";
            ps = c.prepareStatement(sql);
            ps.setString(1, textId.getText());
            ps.setString(2, textPemesan.getText());
            ps.setString(3, textAlamat.getText());
            ps.setString(4, textNama.getText());
            ps.setString(5, textJumlah.getText());
            ps.setString(6, textHarga.getText());
            ps.execute();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void update(ActionEvent event) {
       try{
            koneksi koneksi= new koneksi();
            Connection conn = koneksi.koneksi();
            Statement st = conn.createStatement();
            String sql1 = "update data_pesanan set  nama='"+textPemesan.getText()+"' ,alamat='"+textAlamat.getText()+"',nama_makanan='"+textNama.getText()+"',jumlah='"+textJumlah.getText()+"',harga='"+textHarga.getText()+"'  where no='"+textId.getText()+"'";
            int edit = st.executeUpdate(sql1);
            if (edit == 1){
                    JOptionPane.showMessageDialog(null, "Update berhasil");        
            }	
	} catch(SQLException e){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Peringatan");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Gagal");
                    alert.showAndWait();
        }    
    }

    @FXML
    private void lihatData(KeyEvent evt) {
        try {
            koneksi koneksi = new koneksi();
            Connection conn = koneksi.koneksi();
            Statement st = conn.createStatement();
            String sql1 = "select*from data_pesanan where no='" + textId.getText() + "'";
            ResultSet ps = st.executeQuery(sql1);

            if (ps.next()) {
                textId.setText(ps.getString(1));
                textPemesan.setText(ps.getString(2));
                textAlamat.setText(ps.getString(3));
                textNama.setText(ps.getString(4));
                textJumlah.setText(ps.getString(5));
                textHarga.setText(ps.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            PreparedStatement st = koneksi.koneksi().prepareStatement("delete from data_pesanan where no=? ");
            st.setString(1, textId.getText());
            int delete = st.executeUpdate();
            if (delete == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Peringatan");
                    alert.setHeaderText(null);
                    alert.setContentText("Data Berhasil dihapus");
                    alert.showAndWait();
            }
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        textId.setText("");
        textPemesan.setText("");
        textAlamat.setText("");
        textNama.setText("");
        textJumlah.setText("");
        textHarga.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void enterlog() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package View;

import Helper.connectionHelper;
import Model.appModel;
import Model.logSession;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class KasKeluar extends javax.swing.JFrame {
    Connection con;
    Statement stm;
    ResultSet rs;
    
    String tanggal = "";
    String ID = logSession.getNama_user();
    public static List<appModel> showData;

    public KasKeluar() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        viewpane.setEnabled(false);
        viewpane.setVisible(false);
        log1.setEnabled(false);
        log1.setVisible(false);
        userName.setText(logSession.getUsername());
        
        bEdit.setEnabled(false);
        bDelete.setEnabled(false); 
        initTable();
        showData();
    }
    
    //Mengatur Tabel
    public static DefaultTableModel tableModel;
    private void initTable(){
        String[] bookColumns = new String[]{"Id","Tanggal", "No Nota", "Keperluan", "Jumlah"};
        int[] columnWidth = {50, 100, 90, 140, 140}; //mengatur lebar kolom
        tableModel = new DefaultTableModel (bookColumns, 0);
        FormTable.setModel(tableModel);
        FormTable.setRowHeight(20);
        int i =0;
        for (int width : columnWidth){
            TableColumn column = FormTable.getColumnModel().getColumn(i++);
            column.setMaxWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
    }
    
    //Create Data
    public void tambahData() {
         try {
            Connection con = connectionHelper.getConnection();
            Statement stm = con.createStatement();
            String tanggal = ((JTextField)tDate.getDateEditor().getUiComponent()).getText();
            String query = "INSERT INTO tb_keluar (tanggal_keluar, no_nota, keperluan, jumlah) "
                    + "VALUES ('"+ tanggal+
                               "', '"+ tNota.getText()+
                               "', '"+ tTerima.getText()+
                               "', '"+ tTotal.getText()+
                               "')";       
            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Yeay Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    //EndCreateData
    
    //Read Data
    public void showData(){
        Object header[] = {"id", "Tanggal", "No Nota", "Keperluan", "Jumlah"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        FormTable.setModel(data);
        String sql_data = "Select * from tb_keluar";
        try {
            Connection con = connectionHelper.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql_data);
            while (rs.next()) {
                String d1 = rs.getString(1);
                String d2 = rs.getString(2);
                String d3 = rs.getString(3);
                String d4 = rs.getString(4);
                String d5 = rs.getString(5);

                String d[] = {d1, d2, d3, d4, d5};
                data.addRow(d);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //EndReadData
    
    //Update Data
    public void updateData() {
        String tampilan ="yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tDate.getDate()));
         try {
            Connection con = connectionHelper.getConnection();
            Statement stm = con.createStatement();

            String query = "UPDATE tb_keluar set tanggal_keluar = '"+tanggal+
                    "',no_nota = '"+tNota.getText()+
                    "',keperluan = '"+tTerima.getText()+
                    "',jumlah = '"+tTotal.getText()+
                    "'WHERE id_keluar ='"+ tId.getText()+"'";
            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Data berhasil diedit", "Yeay Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    //EndUpdateData
    
    //Delete Data
    private void deleteData(){
        int selectedRow = FormTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Pililah baris terlebih dahulu", 2);
        } else {
            int option = JOptionPane.showConfirmDialog(this, "Apakah anda yakin akan menhapus data ini?", "Hapus Data", JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Connection con = connectionHelper.getConnection();
                    Statement stm = con.createStatement();
                    appModel kasKeluar = showData.get(selectedRow);
                    stm.executeUpdate("DELETE FROM tb_keluar WHERE id_keluar = " + kasKeluar.getId_keluar());
                    showData();
                } catch (Exception e) {
                    System.out.println("Error:" + e.getMessage());
                }
            }
        }
    }
    //EndDeleteData
    
    //Reset Data setelah Input/Edit/Delete
    public void Clear(){
        tDate.setDate(null);
        tNota.setText(null);
        tTerima.setText(null);
        tTotal.setText(null);
        tId.setText(null);
    }
    //End Reset Data
    
    //getTanggal
    public static Date getTanggalFromtable(JTable table, int kolom){
        JTable tabel = table;
        String str_tgl = String.valueOf(tabel.getValueAt(tabel.getSelectedRow(),kolom));
        Date tanggal = null;
        try{
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tgl);
        }catch(ParseException ex){
            Logger.getLogger(KasKeluar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }
    //endGetTangga;
    
    public void showMessage(String message, int type) {
        if (type == 1) {
            JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userName = new javax.swing.JLabel();
        log2 = new javax.swing.JLabel();
        log1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JLabel();
        btnKasmasuk = new javax.swing.JLabel();
        btnKaskeluar = new javax.swing.JLabel();
        X = new javax.swing.JLabel();
        viewpane = new javax.swing.JPanel();
        viewlogout = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tTerima = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();
        tNota = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        bDelete = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        FormTable = new javax.swing.JTable();
        tDate = new com.toedter.calendar.JDateChooser();
        tId = new javax.swing.JTextField();
        bRefresh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        userName.setText("asd");
        getContentPane().add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        log2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log2MouseClicked(evt);
            }
        });
        getContentPane().add(log2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 40, 40));

        log1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log1MouseClicked(evt);
            }
        });
        getContentPane().add(log1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 40, 40));

        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashboardMouseClicked(evt);
            }
        });
        getContentPane().add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 178, 145, 40));

        btnKasmasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKasmasukMouseClicked(evt);
            }
        });
        getContentPane().add(btnKasmasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 219, 145, 40));

        btnKaskeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKaskeluarMouseClicked(evt);
            }
        });
        getContentPane().add(btnKaskeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 145, 40));

        X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XMouseClicked(evt);
            }
        });
        getContentPane().add(X, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 0, 38, 38));

        viewpane.setBackground(new java.awt.Color(249, 249, 249));
        viewpane.setLayout(null);

        viewlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Logout copy.png"))); // NOI18N
        viewpane.add(viewlogout);
        viewlogout.setBounds(0, 0, 105, 40);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        viewpane.add(jLabel3);
        jLabel3.setBounds(0, 0, 105, 40);

        getContentPane().add(viewpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 105, 40));

        jLabel13.setText("Tanggal");

        jLabel14.setText("No Nota");

        bSubmit.setBackground(new java.awt.Color(52, 152, 219));
        bSubmit.setText("Submit");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
            }
        });

        jLabel15.setText("Keperluan");

        jLabel16.setText("Jumlah");

        bDelete.setBackground(new java.awt.Color(255, 0, 0));
        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bEdit.setBackground(new java.awt.Color(255, 255, 0));
        bEdit.setText("Edit");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        FormTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        FormTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(FormTable);

        tDate.setDateFormatString("yyyy-MM-dd");

        bRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/refresh.png"))); // NOI18N
        bRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bRefreshMouseClicked(evt);
            }
        });

        jLabel1.setText("Id");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bRefresh)
                        .addGap(20, 20, 20)
                        .addComponent(bEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSubmit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tNota)
                    .addComponent(tTerima)
                    .addComponent(tTotal)
                    .addComponent(tDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(tDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tTerima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bDelete)
                        .addComponent(bEdit)
                        .addComponent(tId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(bRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 98, 415, 290));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mockup kaskeluar.JPG"))); // NOI18N
        Background.setMaximumSize(new java.awt.Dimension(600, 500));
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for(double i = 0.0 ; i <=1.0 ; i = i + 0.1){
            String val = 1+"";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }catch(Exception e){
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void log1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log1MouseClicked
        viewpane.setEnabled(false);
        viewpane.setVisible(false);
        log1.setEnabled(false);
        log1.setVisible(false);
        log2.setEnabled(true);
        log2.setVisible(true);
    }//GEN-LAST:event_log1MouseClicked

    private void log2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log2MouseClicked
        viewpane.setEnabled(true);
        viewpane.setVisible(true);
        log1.setEnabled(true);
        log1.setVisible(true);
        log2.setEnabled(false);
        log2.setVisible(false);
    }//GEN-LAST:event_log2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnKasmasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKasmasukMouseClicked
        new KasMasuk().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKasmasukMouseClicked

    private void btnKaskeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKaskeluarMouseClicked
        new KasKeluar().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKaskeluarMouseClicked

    private void btnDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashboardMouseClicked
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardMouseClicked

    private void XMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseClicked
        this.dispose();
    }//GEN-LAST:event_XMouseClicked

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        tambahData();
        showData();
        Clear();
    }//GEN-LAST:event_bSubmitActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        deleteData();
        Clear();
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        updateData();
        showData();
        Clear();
    }//GEN-LAST:event_bEditActionPerformed

    private void FormTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormTableMouseClicked
        int i = FormTable.getSelectedRow();
        TableModel model = FormTable.getModel();
        tId.setText(model.getValueAt(i, 0).toString());
        tDate.setDate(getTanggalFromtable(FormTable, 1));
        tNota.setText(model.getValueAt(i, 2).toString());
        tTerima.setText(model.getValueAt(i, 3).toString());
        tTotal.setText(model.getValueAt(i, 4).toString());
        
        bSubmit.setEnabled(false);
        bEdit.setEnabled(true);
        bDelete.setEnabled(true);
        tId.setEditable(false);
    }//GEN-LAST:event_FormTableMouseClicked

    private void bRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRefreshMouseClicked
        Clear();
    }//GEN-LAST:event_bRefreshMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KasKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KasKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KasKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KasKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KasKeluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JTable FormTable;
    private javax.swing.JLabel X;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JLabel bRefresh;
    private javax.swing.JButton bSubmit;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnKaskeluar;
    private javax.swing.JLabel btnKasmasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel log1;
    private javax.swing.JLabel log2;
    private com.toedter.calendar.JDateChooser tDate;
    private javax.swing.JTextField tId;
    private javax.swing.JTextField tNota;
    private javax.swing.JTextField tTerima;
    private javax.swing.JTextField tTotal;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel viewlogout;
    private javax.swing.JPanel viewpane;
    // End of variables declaration//GEN-END:variables
}

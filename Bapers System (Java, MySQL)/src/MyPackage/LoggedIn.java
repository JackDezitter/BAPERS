/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPackage;

import pers.dbutils.DBConnection;
import pers.dbutils.PaymentDBOperations;
import pers.dbutils.TaskAndJobDBOperations;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author Amirk
 */
public class LoggedIn extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public LoggedIn() throws SQLException {
        String role = Home.getRole();
        Connection connection = DBConnection.getConnection();
        initComponents();
        ImageIcon myimage=new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("TP_Logo.jpg")));
        
        Image img1=myimage.getImage();
        Image img2=img1.getScaledInstance(LOGO.getWidth(),LOGO.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(img2);
        LOGO.setIcon(i);

        PreparedStatement jobQuery = connection.prepareStatement("SELECT JobID FROM Job WHERE JobStatus != 'Complete'");
        ResultSet jobSet =  jobQuery.executeQuery();
        while (jobSet.next()){
            boolean overdue = TaskAndJobDBOperations.CalculateOverdue(jobSet.getInt(1));
            if (overdue == true && role.equals("office manager")){
                JOptionPane.showMessageDialog(this, "Job: " +  jobSet.getInt(1) + " may overrun");
            }
        }
        PreparedStatement paymentQuery = connection.prepareStatement("SELECT JobID, CustomerID FROM Job WHERE PaidStatus = 'Unpaid'");
        ResultSet paymentSet =  paymentQuery.executeQuery();
        while (paymentSet.next()){
            boolean overdue = PaymentDBOperations.CalculateOverduePayment(paymentSet.getInt(1));
            if (overdue == true && role.equals("office manager")) {
                JOptionPane.showMessageDialog(this, "Payment for Job: " + paymentSet.getInt(1)+ " is overdue. \n Please contact CustomerID Number: " + paymentSet.getInt(2));
            }
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

        jPanel1 = new javax.swing.JPanel();
        LogoutBttn = new javax.swing.JButton();
        Exit = new javax.swing.JLabel();
        LOGO = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        CustomerAccountBttn = new javax.swing.JButton();
        JobsBttn = new javax.swing.JButton();
        BackupBttn = new javax.swing.JButton();
        StaffBttn = new javax.swing.JButton();
        PaymentBttn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        LogoutBttn2 = new javax.swing.JButton();
        LoggedInTitle = new javax.swing.JLabel();
        FName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LogoutBttn.setText("Logout");
        LogoutBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBttnActionPerformed(evt);
            }
        });

        Exit.setText("X");
        Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitMouseClicked(evt);
            }
        });

        LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MyPackage/TP_Logo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoutBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Exit)
                .addGap(5, 5, 5)
                .addComponent(LogoutBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CustomerAccountBttn.setText("Customer");
        CustomerAccountBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerAccountBttnActionPerformed(evt);
            }
        });

        JobsBttn.setText("Jobs");
        JobsBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JobsBttnActionPerformed(evt);
            }
        });

        BackupBttn.setText("Back-up/Restore");
        BackupBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackupBttnActionPerformed(evt);
            }
        });

        StaffBttn.setText("Staff");
        StaffBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffBttnActionPerformed(evt);
            }
        });

        PaymentBttn.setText("Payment");
        PaymentBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CustomerAccountBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JobsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BackupBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StaffBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PaymentBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(CustomerAccountBttn)
                .addGap(55, 55, 55)
                .addComponent(StaffBttn)
                .addGap(68, 68, 68)
                .addComponent(JobsBttn)
                .addGap(65, 65, 65)
                .addComponent(PaymentBttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(BackupBttn)
                .addGap(78, 78, 78))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        LogoutBttn2.setText("Logout");
        LogoutBttn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBttn2ActionPerformed(evt);
            }
        });

        LoggedInTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LoggedInTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LoggedInTitle.setText("Welcome,");

        FName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        FName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FName.setText("Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(LoggedInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(LogoutBttn2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoggedInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FName, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutBttn2)
                .addGap(93, 93, 93))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked

    private void LogoutBttn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBttn2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Home().setVisible(true);
    }//GEN-LAST:event_LogoutBttn2ActionPerformed

    private void BackupBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackupBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Backup().setVisible(true);
    }//GEN-LAST:event_BackupBttnActionPerformed

    private void LogoutBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Home().setVisible(true);
    }//GEN-LAST:event_LogoutBttnActionPerformed

    private void CustomerAccountBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerAccountBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageCustomers().setVisible(true);
    }//GEN-LAST:event_CustomerAccountBttnActionPerformed

    private void JobsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobsBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Jobs().setVisible(true);
    }//GEN-LAST:event_JobsBttnActionPerformed

    private void StaffBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageStaff().setVisible(true);
    }//GEN-LAST:event_StaffBttnActionPerformed

    private void PaymentBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Payment().setVisible(true);
    }//GEN-LAST:event_PaymentBttnActionPerformed

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
            java.util.logging.Logger.getLogger(LoggedIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoggedIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoggedIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoggedIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoggedIn().setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackupBttn;
    private javax.swing.JButton CustomerAccountBttn;
    private javax.swing.JLabel Exit;
    private javax.swing.JLabel FName;
    private javax.swing.JButton JobsBttn;
    private javax.swing.JLabel LOGO;
    private javax.swing.JLabel LoggedInTitle;
    private javax.swing.JButton LogoutBttn;
    private javax.swing.JButton LogoutBttn2;
    private javax.swing.JButton PaymentBttn;
    private javax.swing.JButton StaffBttn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

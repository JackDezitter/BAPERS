/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPackage;

import pers.beans.Flexible;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author Amirk
 */
public class FXDiscount extends javax.swing.JFrame {
    int customerID;
    /**
     * Creates new form Home
     */
    public FXDiscount(int customerID) {
        String role = Home.getRole();
        this.customerID = customerID;
        initComponents();
        ImageIcon myimage=new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("TP_Logo.jpg")));
        
        Image img1=myimage.getImage();
        Image img2=img1.getScaledInstance(LOGO.getWidth(),LOGO.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(img2);
        LOGO.setIcon(i);
        
        
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
        jPanel3 = new javax.swing.JPanel();
        ChangesTitle = new javax.swing.JLabel();
        BackBttn = new javax.swing.JButton();
        Band1 = new javax.swing.JTextField();
        D1 = new javax.swing.JTextField();
        Band2 = new javax.swing.JTextField();
        D2 = new javax.swing.JTextField();
        Amount = new javax.swing.JTextField();
        ApplyBttn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        CustomerAccountBttn = new javax.swing.JButton();
        JobsBttn = new javax.swing.JButton();
        BackupBttn = new javax.swing.JButton();
        StaffBttn = new javax.swing.JButton();
        PaymentBttn = new javax.swing.JButton();

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
                        .addGap(21, 21, 21))))
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ChangesTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ChangesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChangesTitle.setText("Flex Discount");

        BackBttn.setText("Back");
        BackBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBttnActionPerformed(evt);
            }
        });


        Band1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Band1MouseClicked(evt);
            }
        });
        Band1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Band1ActionPerformed(evt);
            }
        });


        D1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D1MouseClicked(evt);
            }
        });
        D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D1ActionPerformed(evt);
            }
        });


        Band2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Band2MouseClicked(evt);
            }
        });


        D2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                D2MouseClicked(evt);
            }
        });
        D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D2ActionPerformed(evt);
            }
        });


        Amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AmountMouseClicked(evt);
            }
        });

        ApplyBttn.setText("Apply");
        ApplyBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ApplyBttnActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        jLabel1.setText("Band1 Max");

        jLabel2.setText("Discount");

        jLabel3.setText("Band 2 Max");

        jLabel4.setText("Discount");

        jLabel5.setText("Band 3 Discount");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(ChangesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackBttn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(Band2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(Band1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Amount)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(ApplyBttn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ChangesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BackBttn)
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Band1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Band2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(ApplyBttn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(CustomerAccountBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JobsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BackupBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
            .addComponent(StaffBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PaymentBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(CustomerAccountBttn)
                .addGap(44, 44, 44)
                .addComponent(StaffBttn)
                .addGap(79, 79, 79)
                .addComponent(JobsBttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(PaymentBttn)
                .addGap(66, 66, 66)
                .addComponent(BackupBttn)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_ExitMouseClicked
    
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

    private void JobsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobsBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Jobs().setVisible(true);
    }//GEN-LAST:event_JobsBttnActionPerformed

    private void BackBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageCustomers().setVisible(true);
    }//GEN-LAST:event_BackBttnActionPerformed

    private void CustomerAccountBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerAccountBttnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new ManageCustomers().setVisible(true);
    }//GEN-LAST:event_CustomerAccountBttnActionPerformed

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

    private void Band1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Band1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Band1ActionPerformed

    private void D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_D1ActionPerformed

    private void D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_D2ActionPerformed

    private void Band1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Band1MouseClicked
        // TODO add your handling code here:
        Band1.setText("");
    }//GEN-LAST:event_Band1MouseClicked

    private void D1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D1MouseClicked
        // TODO add your handling code here:
        D1.setText("");
    }//GEN-LAST:event_D1MouseClicked

    private void Band2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Band2MouseClicked
        // TODO add your handling code here:
        Band2.setText("");
    }//GEN-LAST:event_Band2MouseClicked

    private void D2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D2MouseClicked
        // TODO add your handling code here:
        D2.setText("");
    }//GEN-LAST:event_D2MouseClicked

    private void AmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AmountMouseClicked
        // TODO add your handling code here:
        Amount.setText("");
    }//GEN-LAST:event_AmountMouseClicked

    private void ApplyBttnActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_ApplyBttnActionPerformed
        // TODO add your handling code here:
        float band1 = Float.parseFloat(Band1.getText());
        float band2 = Float.parseFloat(Band2.getText());

        float band1Discount = Float.parseFloat(D1.getText());
        float band2Discount = Float.parseFloat(D2.getText());
        float band3Discount = Float.parseFloat(Amount.getText());

        Flexible discount = new Flexible(customerID, band1, band2, band1Discount, band2Discount, band3Discount);

        this.dispose();
        new ManageCustomers().setVisible(true);
    }//GEN-LAST:event_ApplyBttnActionPerformed

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
            java.util.logging.Logger.getLogger(FXDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FXDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FXDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FXDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Amount;
    private javax.swing.JButton ApplyBttn;
    private javax.swing.JButton BackBttn;
    private javax.swing.JButton BackupBttn;
    private javax.swing.JTextField Band1;
    private javax.swing.JTextField Band2;
    private javax.swing.JLabel ChangesTitle;
    private javax.swing.JButton CustomerAccountBttn;
    private javax.swing.JTextField D1;
    private javax.swing.JTextField D2;
    private javax.swing.JLabel Exit;
    private javax.swing.JButton JobsBttn;
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton LogoutBttn;
    private javax.swing.JButton PaymentBttn;
    private javax.swing.JButton StaffBttn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

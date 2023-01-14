/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPackage;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import pers.beans.CustomerInfoBean;
import pers.beans.Discount;
import pers.beans.DiscountType;
import pers.common.ApplicationConstants;
import pers.dbutils.CustomerDBOperations;
import pers.dbutils.DiscountDBOperations;
import pers.dbutils.TaskAndJobDBOperations;

/**
 *
 * @author Alia
 */
public class ManageCustomers extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    String role;
    public ManageCustomers() {
        role = Home.getRole();
        initComponents();
        ImageIcon myimage=new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("TP_Logo.jpg")));
        
        Image img1=myimage.getImage();
        Image img2=img1.getScaledInstance(LOGO.getWidth(),LOGO.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(img2);
        LOGO.setIcon(i);
        pnlEditCustomer.setVisible(false);
        fillPageData();

        if (!role.equals("office manager") && !role.equals("receptionist") && !role.equals("shift manager")){
            registerCustomer.hide();
        }
    }
    private long selectedCustomerId;
    private void fillPageData(){
        CustomerDBOperations dBOperations = new CustomerDBOperations();
        
        DefaultComboBoxModel<DiscountType> model = (DefaultComboBoxModel)listDiscounts.getModel();
        Collection<DiscountType> discountsList =  ApplicationConstants.DISCOUNT_TYPES.values();
        
        Iterator<DiscountType> itr = discountsList.iterator();
        while(itr.hasNext()){
            DiscountType dis = itr.next();
            model.addElement(dis);
        }
        
        List<CustomerInfoBean> list = dBOperations.getCustomersList("", new ArrayList<>());
        updateBookingTable(dBOperations, list);


        customerDataTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (role.equals("office manager")) {

                int selectedIndex = customerDataTable.getSelectedRow();
                if (selectedIndex >= 0) {
                    CustomerInfoBean customerInfo = list.get(selectedIndex);
                    txtCustomerNumber.setText(String.valueOf(customerInfo.getCustomerID()));
                    DiscountType discountType = ApplicationConstants.DISCOUNT_TYPES.get(customerInfo.getDiscountTypeId());
                    if (discountType != null)
                        listDiscounts.getModel().setSelectedItem(discountType);
                    else
                        listDiscounts.setSelectedIndex(0);

                    selectedCustomerId = customerInfo.getCustomerID();
                    System.out.println("MyPackage.ManageCustomers.fillPageData() " + selectedCustomerId);
                    pnlEditCustomer.setVisible(true);

                }
            }
        });
    }
    
    
    public void refreshTable(CustomerDBOperations dBOperations){
        List<CustomerInfoBean> list = dBOperations.getCustomersList("", new ArrayList<>());
        updateBookingTable(dBOperations, list);
    }
    public void refreshTable(CustomerDBOperations dBOperations, ResultSet set){
        List<CustomerInfoBean> list = dBOperations.getCustomersList(set);
        updateBookingTable(dBOperations, list);
    }
    
    public void updateBookingTable(CustomerDBOperations dBOperations, List<CustomerInfoBean> list){
        Object[][] data = new Object[list.size()][];
        
        int count=1;
        String[] tableHeader = new String[]{"Customer ID", "First Name", "Last Name", "Email", "Phone", "Address", "Discount Type", "Customer Type"};
        for(int i=0;i<list.size();i++){
            CustomerInfoBean bean = list.get(i);
            Object[] obj = new Object[tableHeader.length];
            obj[0]= bean.getCustomerID();
            obj[1]= bean.getFirstName();
            obj[2]= bean.getSurname();
            obj[3]= bean.getEmail();
            obj[4]= bean.getPhoneNumber();
            obj[5]= bean.getAddress();
            DiscountType discountType = ApplicationConstants.DISCOUNT_TYPES.get(bean.getDiscountTypeId());
            obj[6]= discountType!=null?discountType.getDiscountType():"None";
            obj[7]= bean.getCustomerTypeID();
            data[i] = obj;
        }
        
        DefaultTableModel tableModel =  new DefaultTableModel(data,tableHeader);
        customerDataTable.setModel(tableModel);
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
        manageStaff = new javax.swing.JButton();
        PaymentBttn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerDataTable = new javax.swing.JTable();
        registerCustomer = new javax.swing.JButton();
        pnlEditCustomer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCustomerNumber = new javax.swing.JTextField();
        listDiscounts = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        upgrade = new javax.swing.JButton();
        deleteCustomer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CID = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        SName = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        CT = new javax.swing.JTextField();
        DT = new javax.swing.JTextField();
        SearchBttn = new javax.swing.JButton();

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
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Exit)
                .addGap(8, 8, 8)
                .addComponent(LogoutBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CustomerAccountBttn.setText("Customers");
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

        manageStaff.setText("Staff");
        manageStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStaffActionPerformed(evt);
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
                    .addComponent(manageStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JobsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BackupBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustomerAccountBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PaymentBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(CustomerAccountBttn)
                .addGap(47, 47, 47)
                .addComponent(manageStaff)
                .addGap(64, 64, 64)
                .addComponent(JobsBttn)
                .addGap(81, 81, 81)
                .addComponent(PaymentBttn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackupBttn)
                .addGap(78, 78, 78))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        customerDataTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(customerDataTable);

        registerCustomer.setText("Register Customer");
        registerCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerCustomerActionPerformed(evt);
            }
        });

        jLabel1.setText("Customer Number");

        txtCustomerNumber.setEditable(false);

        jLabel2.setText("Discount Type");

        upgrade.setText("Upgrade");
        upgrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    upgradeActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        deleteCustomer.setText("Delete Customer");
        deleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditCustomerLayout = new javax.swing.GroupLayout(pnlEditCustomer);
        pnlEditCustomer.setLayout(pnlEditCustomerLayout);
        pnlEditCustomerLayout.setHorizontalGroup(
            pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(upgrade, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listDiscounts, 0, 149, Short.MAX_VALUE)
                    .addComponent(txtCustomerNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteCustomer)
                .addGap(43, 43, 43))
        );
        pnlEditCustomerLayout.setVerticalGroup(
            pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCustomerLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCustomerNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addComponent(deleteCustomer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listDiscounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(upgrade)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Please click on a record to update discount types.");

        jLabel4.setText("CustomerID");

        jLabel5.setText("Name");

        jLabel6.setText("Surname");

        jLabel7.setText("Email");

        jLabel8.setText("CustomerType");

        jLabel9.setText("DiscountType");

        CID.setText("CustomerID");
        CID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CIDMouseClicked(evt);
            }
        });

        Name.setText("Name");
        Name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NameMouseClicked(evt);
            }
        });

        SName.setText("Surname");
        SName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SNameMouseClicked(evt);
            }
        });

        Email.setText("Email");
        Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmailMouseClicked(evt);
            }
        });
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        CT.setText("CustomerType");
        CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CTMouseClicked(evt);
            }
        });

        DT.setText("DiscountType");
        DT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTMouseClicked(evt);
            }
        });

        SearchBttn.setText("Search");
        SearchBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SearchBttnActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(pnlEditCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(registerCustomer)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(CID, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SName, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(CT, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(DT, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(SearchBttn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(registerCustomer))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(SearchBttn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlEditCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        Backup bu=new Backup();
        bu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackupBttnActionPerformed

    private void LogoutBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBttnActionPerformed
        // TODO add your handling code here:
        Home Login=new Home();
        Login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogoutBttnActionPerformed

    private void JobsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JobsBttnActionPerformed
        // TODO add your handling code here:
        Jobs J=new Jobs();
        J.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JobsBttnActionPerformed


    private void manageStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStaffActionPerformed
        ManageStaff page = new ManageStaff();
        page.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_manageStaffActionPerformed

    private void registerCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerCustomerActionPerformed
        setVisible(false);
        new RegisterC().setVisible(true);
    }//GEN-LAST:event_registerCustomerActionPerformed
// this function is called when the user will click on upgrade button
// it will be used to add discount to customer accounts
    private void upgradeActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_upgradeActionPerformed
        // add discount by calling updateDiscountTyper function of CustomerDBOpertion class
        CustomerDBOperations dBOperations = new CustomerDBOperations();
        if(dBOperations.updateDiscountType(selectedCustomerId, ((DiscountType)listDiscounts.getSelectedItem()).getDiscountTypeId())){
            JOptionPane.showMessageDialog(this, "Discount Type Updated Successfully.");
            refreshTable(dBOperations);
            pnlEditCustomer.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Unable to update discount type.");
        }
        DiscountType discounts = (DiscountType) listDiscounts.getSelectedItem();
        DiscountDBOperations.DeleteVariableDiscount((int) selectedCustomerId);
        DiscountDBOperations.DeleteFixedDiscount((int) selectedCustomerId);
        DiscountDBOperations.DeleteFlexibleDiscount((int) selectedCustomerId);
        if (discounts.getDiscountTypeId() == 1){
        setVisible(false);
        new FIXDiscount((int) selectedCustomerId).setVisible(true);
        }
        if (discounts.getDiscountTypeId() == 2){
        setVisible(false);
        new VDiscount((int) selectedCustomerId).setVisible(true);
        }
        if (discounts.getDiscountTypeId() == 3){
        setVisible(false);
        new FXDiscount((int) selectedCustomerId).setVisible(true);
        }
        
    }//GEN-LAST:event_upgradeActionPerformed
    // this function is called when the user will click on deleted customer
    // it is user to delete an individuals customer
    private void deleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerActionPerformed
        CustomerDBOperations dBOperations = new CustomerDBOperations();
        if(dBOperations.deleteCustomer(selectedCustomerId)>0){
            JOptionPane.showMessageDialog(this, "Customer deleted Successfully");
            refreshTable(dBOperations);
            pnlEditCustomer.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Unable to delete Customer.");
        }
    }//GEN-LAST:event_deleteCustomerActionPerformed

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void CIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CIDMouseClicked
        // TODO add your handling code here:
        CID.setText("");
    }//GEN-LAST:event_CIDMouseClicked

    private void NameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameMouseClicked
        // TODO add your handling code here:
        Name.setText("");
    }//GEN-LAST:event_NameMouseClicked

    private void SNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SNameMouseClicked
        // TODO add your handling code here:
        SName.setText("");
    }//GEN-LAST:event_SNameMouseClicked

    private void EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmailMouseClicked
        // TODO add your handling code here:
        Email.setText("");
    }//GEN-LAST:event_EmailMouseClicked

    private void CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CTMouseClicked
        // TODO add your handling code here:
        CT.setText("");
    }//GEN-LAST:event_CTMouseClicked

    private void DTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTMouseClicked
        // TODO add your handling code here:
        DT.setText("");
    }//GEN-LAST:event_DTMouseClicked

    private void SearchBttnActionPerformed(java.awt.event.ActionEvent evt)  throws SQLException  {//GEN-FIRST:event_SearchBttnActionPerformed
        // TODO add your handling code here:

        int customerID = -1;
        String name = null;
        String surname = null;
        String email = null;
        String customerT = null;
        String discountT = null;

        if(!CID.getText().equals("")) {customerID = Integer.parseInt(CID.getText());}
        if(!Name.getText().equals("")) {name = Name.getText();}
        if(!SName.getText().equals("")) {surname = SName.getText();}
        if(!Email.getText().equals("")) {email = Email.getText();}
        if(!CT.getText().equals("")) {customerT = CT.getText();}
        if(!DT.getText().equals("")) {discountT = DT.getText();}

        CustomerDBOperations dBOperations = new CustomerDBOperations();

        ResultSet set = dBOperations.SearchCustomer(customerID,name,surname,email,customerT,discountT);
        refreshTable(dBOperations ,set);

    }//GEN-LAST:event_SearchBttnActionPerformed

    private void CustomerAccountBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerAccountBttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerAccountBttnActionPerformed

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
            java.util.logging.Logger.getLogger(ManageCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageCustomers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageCustomers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackupBttn;
    private javax.swing.JTextField CID;
    private javax.swing.JTextField CT;
    private javax.swing.JButton CustomerAccountBttn;
    private javax.swing.JTextField DT;
    private javax.swing.JTextField Email;
    private javax.swing.JLabel Exit;
    private javax.swing.JButton JobsBttn;
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton LogoutBttn;
    private javax.swing.JTextField Name;
    private javax.swing.JButton PaymentBttn;
    private javax.swing.JTextField SName;
    private javax.swing.JButton SearchBttn;
    private javax.swing.JTable customerDataTable;
    private javax.swing.JButton deleteCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<DiscountType> listDiscounts;
    private javax.swing.JButton manageStaff;
    private javax.swing.JPanel pnlEditCustomer;
    private javax.swing.JButton registerCustomer;
    private javax.swing.JTextField txtCustomerNumber;
    private javax.swing.JButton upgrade;
    // End of variables declaration//GEN-END:variables
}

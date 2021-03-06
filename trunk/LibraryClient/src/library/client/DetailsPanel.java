package library.client;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import library.common.*;
import javax.swing.JOptionPane;
import java.rmi.RemoteException;

/**
 * @author  Karolina
 */
public class DetailsPanel extends javax.swing.JPanel {
    
    private SessionData sData;
    private ResultPanel parentPanel;
    private Map<Integer, ReservationDetails> details;
    private int availablesCount = 0;
    private int parentRow = 0;
    
    /** Creates new form DetailsPanel */
    public DetailsPanel(SessionData s) {
        initComponents();
        this.setVisible(false);
        sData = s;
        details = new HashMap<Integer, ReservationDetails>();
        detailsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void handleTableSelectionChange() {
        if (sData.isAnonymous()) return;
        int rowSelected = this.detailsTable.getSelectedRow();
        if (rowSelected == -1)
            return;
        switch(details.get(rowSelected).bookCopy.state) {
            case AVAIL:
                reserveButton.setEnabled(true);
                reserveButton.setText("Zam�w");
                break;
            case BORROWED:
            case REQUESTED:
               if (details.get(rowSelected).youHaveReserved()){
                    reserveButton.setEnabled(false);
                }else{
                    reserveButton.setEnabled(true);
                }
                reserveButton.setText("Zarezerwuj");
                break;
            case UNAVAIL:
                reserveButton.setEnabled(false);
                break;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        upperPanel = new javax.swing.JPanel();
        titleLabel1 = new javax.swing.JLabel();
        authorLabel = new javax.swing.JLabel();
        titleLabel2 = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        titleLabel3 = new javax.swing.JLabel();
        publisherLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailsTable = new javax.swing.JTable();
        reserveButton = new javax.swing.JButton();

        titleLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        titleLabel1.setText("Autor:");

        authorLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        titleLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        titleLabel2.setText("Tytu\u0142:");

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        titleLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        titleLabel3.setText("Wydawca:");

        publisherLabel.setFont(new java.awt.Font("Tahoma", 1, 11));

        returnButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        returnButton.setText("Powr\u00f3t");
        returnButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                returnButtonKeyPressed(evt);
            }
        });
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout upperPanelLayout = new org.jdesktop.layout.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(upperPanelLayout.createSequentialGroup()
                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(upperPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(upperPanelLayout.createSequentialGroup()
                                .add(titleLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(publisherLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                            .add(upperPanelLayout.createSequentialGroup()
                                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(titleLabel1)
                                    .add(titleLabel2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(titleLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                                    .add(authorLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))))
                    .add(upperPanelLayout.createSequentialGroup()
                        .add(265, 265, 265)
                        .add(returnButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(upperPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(titleLabel1)
                    .add(authorLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(titleLabel2)
                    .add(titleLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(upperPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(publisherLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(titleLabel3))
                .add(15, 15, 15)
                .add(returnButton)
                .add(51, 51, 51))
        );

        detailsTable.setFont(new java.awt.Font("Arial", 0, 12));
        detailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sygnatura", "Stan", "Data Zwrotu", "W kolejce"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailsTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        detailsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                detailsTableKeyReleased(evt);
            }
        });
        detailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailsTableMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(detailsTable);

        reserveButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        reserveButton.setText("Zam\u00f3w");
        reserveButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reserveButtonKeyPressed(evt);
            }
        });
        reserveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reserveButtonMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout tablePanelLayout = new org.jdesktop.layout.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tablePanelLayout.createSequentialGroup()
                .add(tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(tablePanelLayout.createSequentialGroup()
                        .add(265, 265, 265)
                        .add(reserveButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(tablePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(tablePanelLayout.createSequentialGroup()
                .add(reserveButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(41, 41, 41))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(upperPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(tablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(upperPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 148, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(tablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void detailsTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detailsTableKeyReleased
        handleTableSelectionChange();
    }//GEN-LAST:event_detailsTableKeyReleased
    
    private void detailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailsTableMouseClicked
        if (sData.isAnonymous()) return;
        if (!details.get(this.detailsTable.getSelectedRow()).youHaveReserved()){
            if (evt.getClickCount() == 2)
                reserveButtonAccept();
        }
        handleTableSelectionChange();
    }//GEN-LAST:event_detailsTableMouseClicked
    
    private void reserveButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reserveButtonKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            this.reserveButtonAccept();
        }
    }//GEN-LAST:event_reserveButtonKeyPressed
    
    private void returnButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnButtonKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER){
            this.returnButtonAccept();
        }
    }//GEN-LAST:event_returnButtonKeyPressed
    
    private void returnButtonAccept(){
        this.clearFields();
        parentPanel.displayParent(parentRow, (availablesCount > 0));
        this.availablesCount = 0;
        this.parentRow = 0;
    }
    
    private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
        this.returnButtonAccept();
    }//GEN-LAST:event_returnButtonMouseClicked
    
    private void reserveButtonAccept(){
        if (this.sData.isAnonymous()) return;
        if (!this.reserveButton.isEnabled()) return;
        
        int rowSelected = this.detailsTable.getSelectedRow();
        if (rowSelected == -1){
            JOptionPane.showMessageDialog(this, "Wybierz pozycj�, kt�r� chcesz zam�wi�!","Uwaga",JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                switch(details.get(Integer.valueOf(rowSelected)).bookCopy.state){
                    case AVAIL://AVAILABLE
                        this.sData.getLibrary().requestBooks(
                                this.sData.getSession(),new int[]{
                            this.details.get(Integer.valueOf(rowSelected)).bookCopy.copyId});
                        JOptionPane.showMessageDialog(this,"Ksi��ka zosta�a zam�wiona!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        ((DefaultTableModel)this.detailsTable.getModel()).setValueAt(
                                "ZAM�WIONA",rowSelected, 1);
                        details.get(Integer.valueOf(rowSelected)).youInQueue = 1;
                        details.get(Integer.valueOf(rowSelected)).bookCopy.state = BookCopy.State.REQUESTED;
                        availablesCount--;
                        break;
                    case BORROWED:
                    case REQUESTED:
                        if ((details.get(Integer.valueOf(rowSelected)).youHaveReserved())
                        &&(details.get(Integer.valueOf(rowSelected)).youInQueue>0)){
                            JOptionPane.showMessageDialog(this,"Ksi��ka zosta�a ju� przez Ciebie zam�wiona!","Uwaga",JOptionPane.WARNING_MESSAGE);
                        }else{
                            this.sData.getLibrary().reserveBooks(
                                    this.sData.getSession(),new int[]{
                                this.details.get(Integer.valueOf(rowSelected)).bookCopy.copyId});
                            JOptionPane.showMessageDialog(this,"Ksi��ka zosta�a zarezerwowana!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                            ((DefaultTableModel)this.detailsTable.getModel()).setValueAt(
                                    details.get(Integer.valueOf(rowSelected)).queueSize+1,rowSelected, 3);
                            details.get(Integer.valueOf(rowSelected)).youInQueue = 1;
                        }
                        break;
                    case UNAVAIL://UNAVAILABLED
                        JOptionPane.showMessageDialog(this,"Ksi��ka jest niedost�pna!","Uwaga",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }catch (RemoteException e){
                JOptionPane.showMessageDialog(this, e.detail.getLocalizedMessage(),"Uwaga",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void reserveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reserveButtonMouseClicked
        this.reserveButtonAccept();
    }//GEN-LAST:event_reserveButtonMouseClicked
    
    public void setParentPanel(ResultPanel r) { parentPanel = r; }
    
    public void displayResults(Collection<ReservationDetails> rdetails, Book b, int parentRow){
        this.parentRow = parentRow;
        this.authorLabel.setText(b.getAuthor());
        this.titleLabel.setText(b.getTitle());
        this.publisherLabel.setText(b.getPublisher()+", "+Integer.toString(b.getPublishYear()));
        this.reserveButton.setEnabled(!this.sData.isAnonymous());
        
        if (rdetails.size() <= 0) {
            this.tablePanel.setVisible(false);
        }else{
            int i = 0;
            for (ReservationDetails r : rdetails) {
                Object[] obj = new Object[4];
                obj[0] = r.bookCopy.copyId;
                switch(r.bookCopy.state){
                    case AVAIL: obj[1] = "DOST�PNA";
                    obj[2] = "-";
                    obj[3] = 0;
                    availablesCount++;
                    break;
                    case BORROWED: obj[1] = "WYPO�YCZONA";
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    obj[2] = df.format(r.bookCopy.returnDate.getTime());
                    obj[3] = r.queueSize;
                    break;
                    case REQUESTED: obj[1] = "ZAM�WIONA";
                    obj[2] = "-";
                    obj[3] = r.queueSize;
                    break;
                    default: obj[1] = "NIEDOST�PNA";
                    obj[2] = "-";
                    obj[3] = 0;
                }
                this.details.put(Integer.valueOf(i), r);
                i++;
                ((DefaultTableModel)this.detailsTable.getModel()).addRow(obj);
            }
        }
        this.setVisible(true);
    }
    
    private void clearFields(){
        this.setVisible(false);
        
        this.authorLabel.setText("");
        this.titleLabel.setText("");
        this.publisherLabel.setText("");
        this.reserveButton.setText("Zam�w");
        this.reserveButton.setEnabled(true);
        
        int j = this.detailsTable.getRowCount();
        for (int i = 0 ; i < j; i++){
            ((DefaultTableModel)(this.detailsTable.getModel())).removeRow(0);
        }
        this.details.clear();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JTable detailsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JButton reserveButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel titleLabel1;
    private javax.swing.JLabel titleLabel2;
    private javax.swing.JLabel titleLabel3;
    private javax.swing.JPanel upperPanel;
    // End of variables declaration//GEN-END:variables
    
}

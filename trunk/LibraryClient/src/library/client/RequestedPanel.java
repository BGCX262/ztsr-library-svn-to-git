package library.client;

import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import library.common.BookCopy;

/**
 * @author  Karolina
 */
public class RequestedPanel extends javax.swing.JPanel {

    private SessionData sData;
    private StatePanel parentPanel;
    
    /** Creates new form RequestedPanel */
    public RequestedPanel(SessionData s) {
        initComponents();
        this.setVisible(false);
        sData = s;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        codePanel = new javax.swing.JPanel();
        tmpLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        titlePanel = new javax.swing.JPanel();
        titileLabel = new javax.swing.JLabel();
        requestedLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestedTable = new javax.swing.JTable();

        codePanel.setBackground(new java.awt.Color(0, 204, 204));
        tmpLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        tmpLabel.setText("Numer karty:");

        numberLabel.setFont(new java.awt.Font("Tahoma", 2, 12));

        org.jdesktop.layout.GroupLayout codePanelLayout = new org.jdesktop.layout.GroupLayout(codePanel);
        codePanel.setLayout(codePanelLayout);
        codePanelLayout.setHorizontalGroup(
            codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(codePanelLayout.createSequentialGroup()
                .add(34, 34, 34)
                .add(tmpLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(numberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        codePanelLayout.setVerticalGroup(
            codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(codePanelLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(codePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tmpLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        titileLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        titileLabel.setText("Ksi\u0105\u017cki zam\u00f3wione:");

        requestedLabel.setFont(new java.awt.Font("Tahoma", 1, 12));

        org.jdesktop.layout.GroupLayout titlePanelLayout = new org.jdesktop.layout.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(titlePanelLayout.createSequentialGroup()
                .add(55, 55, 55)
                .add(titileLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(requestedLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(425, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(titileLabel)
                    .add(requestedLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

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

        requestedTable.setFont(new java.awt.Font("Arial", 0, 12));
        requestedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Autor", "Tytu�", "Wydawnictwo", "Sygnatura"
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
        requestedTable.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jScrollPane1.setViewportView(requestedTable);

        org.jdesktop.layout.GroupLayout tablePanelLayout = new org.jdesktop.layout.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, tablePanelLayout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 332, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(39, 39, 39)
                .add(codePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, Short.MAX_VALUE)
                .add(returnButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(titlePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(tablePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(codePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(returnButton))
                .add(23, 23, 23)
                .add(titlePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(tablePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void returnButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnButtonKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER){
            this.returnButtonAccept();
        }
    }//GEN-LAST:event_returnButtonKeyPressed

    private void returnButtonAccept(){
        this.setVisible(false);
        this.clearFields();
        parentPanel.setVisible(true);
    }
    
    private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
        this.returnButtonAccept();
    }//GEN-LAST:event_returnButtonMouseClicked
    
    public void displayResults(Collection<BookCopy> books){
        this.numberLabel.setText(sData.getLogin());
        this.requestedLabel.setText(Integer.toString(books.size()));
        this.tablePanel.setVisible(books.size()>0);
        for (BookCopy b : books) {
            Object[] obj = new Object[4];
            obj[0] = b.book.getAuthor();
            obj[1] = b.book.getTitle();
            obj[2] = b.book.getPublisher() + ", " + Integer.toString(b.book.getPublishYear());
            obj[3] = b.copyId;
            ((DefaultTableModel)this.requestedTable.getModel()).addRow(obj);
        }
    }
    
    private void clearFields(){
        this.numberLabel.setText("");
        this.requestedLabel.setText("");
        this.tablePanel.setVisible(true);
        int j = this.requestedTable.getRowCount();
        for(int i = 0 ; i < j ; i++){
            ((DefaultTableModel)this.requestedTable.getModel()).removeRow(0);
        }
    }
    
    public void setParentPanel(StatePanel s) { parentPanel = s; }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel codePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel requestedLabel;
    private javax.swing.JTable requestedTable;
    private javax.swing.JButton returnButton;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel titileLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel tmpLabel;
    // End of variables declaration//GEN-END:variables
    
}

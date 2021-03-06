/*
 * AdminWindow.java
 *
 * Created on 25 marzec 2007, 19:32
 */

package library.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.ResourceBundle;
import library.common.Book;
import library.common.BookCopy;
import library.common.LibraryException;
import library.common.LoginData;
import library.common.ReservationDetails;
import library.common.SearchPattern;

/**
 *
 * @author  Piotrek
 */
public class AdminWindow extends javax.swing.JFrame {
    
    private Library library;
    
    private static final long serialVersionUID = -6259394259174912289L;
    
    /** Creates new form AdminWindow */
    public AdminWindow() {
        initComponents();
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    
    /** dumps exception */
    private void handleException(Exception e) {
        StringWriter s = new StringWriter();
        e.printStackTrace(new PrintWriter(s));
        jTextArea1.append(s.toString());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPubYear = new javax.swing.JTabbedPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jAuthor = new javax.swing.JTextField();
        jTitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPublisher = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPublishYear = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jAddBook = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jBookId = new javax.swing.JTextField();
        jAddCopy = new javax.swing.JButton();
        jState = new javax.swing.JComboBox();
        jDeleteBookButton = new javax.swing.JButton();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jUserLogin = new javax.swing.JTextField();
        jUserPassword = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jDeleteUserButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jUsersTextArea = new javax.swing.JTextArea();
        jDumpUsersButton = new javax.swing.JButton();
        jBorrowButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setTitle(ResourceBundle.getBundle("library/server/server").getString("Library_administration")); // NOI18N
        jPubYear.setOpaque(true);
        jLabel3.setText(ResourceBundle.getBundle("library/server/server").getString("Author")); // NOI18N

        jLabel4.setText(ResourceBundle.getBundle("library/server/server").getString("Title")); // NOI18N

        jLabel5.setText(ResourceBundle.getBundle("library/server/server").getString("Publisher")); // NOI18N

        jLabel6.setText(ResourceBundle.getBundle("library/server/server").getString("Publish_year")); // NOI18N

        jAddBook.setText(ResourceBundle.getBundle("library/server/server").getString("Add_book")); // NOI18N
        jAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddBookActionPerformed(evt);
            }
        });

        jLabel7.setText(ResourceBundle.getBundle("library/server/server").getString("State")); // NOI18N

        jLabel8.setText(ResourceBundle.getBundle("library/server/server").getString("Book_ID")); // NOI18N

        jAddCopy.setText(ResourceBundle.getBundle("library/server/server").getString("Add_copy")); // NOI18N
        jAddCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddCopyActionPerformed(evt);
            }
        });

        jState.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ResourceBundle.getBundle("library/server/server").getString("Available"),
		      ResourceBundle.getBundle("library/server/server").getString("Borrowed"),
				ResourceBundle.getBundle("library/server/server").getString("Unavailable"),
				ResourceBundle.getBundle("library/server/server").getString("Requested") }));

        jDeleteBookButton.setText(ResourceBundle.getBundle("library/server/server").getString("Delete_book")); // NOI18N
        jDeleteBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteBookButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame2Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame2Layout.createSequentialGroup()
                .add(104, 104, 104)
                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jInternalFrame2Layout.createSequentialGroup()
                        .add(jLabel8)
                        .addContainerGap())
                    .add(jInternalFrame2Layout.createSequentialGroup()
                        .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .add(jInternalFrame2Layout.createSequentialGroup()
                                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jLabel4)
                                    .add(jLabel5)
                                    .add(jLabel6))
                                .add(43, 43, 43)
                                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jPublishYear)
                                    .add(jPublisher)
                                    .add(jTitle)
                                    .add(jAuthor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 76, Short.MAX_VALUE)
                                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jBookId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 85, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(32, 32, 32)
                        .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jDeleteBookButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jAddBook, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jAddCopy, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(81, Short.MAX_VALUE))))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jAuthor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jAddBook))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jInternalFrame2Layout.createSequentialGroup()
                        .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel4)
                            .add(jTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel5)
                            .add(jPublisher, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jInternalFrame2Layout.createSequentialGroup()
                                .add(11, 11, 11)
                                .add(jLabel6))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPublishYear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jDeleteBookButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jAddCopy))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jBookId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPubYear.addTab(ResourceBundle.getBundle("library/server/server").getString("Books"), jInternalFrame2); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setRows(1);
        jScrollPane2.setViewportView(jTextArea2);

        jButton4.setText(ResourceBundle.getBundle("library/server/server").getString("Show")); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame3Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame3Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton4)
                .addContainerGap())
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton4)
                .addContainerGap(228, Short.MAX_VALUE))
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );
        jPubYear.addTab(ResourceBundle.getBundle("library/server/server").getString("Dump_books"), jInternalFrame3); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setRows(1);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel9.setText(ResourceBundle.getBundle("library/server/server").getString("Book_ID")); // NOI18N

        jButton5.setText(ResourceBundle.getBundle("library/server/server").getString("Show")); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame4Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame4Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame4Layout.createSequentialGroup()
                        .add(jLabel9)
                        .add(34, 34, 34))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame4Layout.createSequentialGroup()
                        .add(jButton5)
                        .add(10, 10, 10))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton5)
                .addContainerGap(183, Short.MAX_VALUE))
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );
        jPubYear.addTab(ResourceBundle.getBundle("library/server/server").getString("Dump_copies"), jInternalFrame4); // NOI18N

        jLabel1.setText(ResourceBundle.getBundle("library/server/server").getString("Login")); // NOI18N

        jLabel2.setText(ResourceBundle.getBundle("library/server/server").getString("Password")); // NOI18N

        jButton1.setText(ResourceBundle.getBundle("library/server/server").getString("Add")); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jDeleteUserButton.setText(ResourceBundle.getBundle("library/server/server").getString("Delete")); // NOI18N
        jDeleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteUserButtonActionPerformed(evt);
            }
        });

        jUsersTextArea.setColumns(20);
        jUsersTextArea.setRows(5);
        jScrollPane4.setViewportView(jUsersTextArea);

        jDumpUsersButton.setText(ResourceBundle.getBundle("library/server/server").getString("Dump")); // NOI18N
        jDumpUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDumpUsersButtonActionPerformed(evt);
            }
        });

        jBorrowButton.setText(ResourceBundle.getBundle("library/server/server").getString("Borrow")); // NOI18N
        jBorrowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBorrowButtonActionPerformed(evt);
            }
        });

        jLabel10.setText(ResourceBundle.getBundle("library/server/server").getString("Borrow_all_books_requested_by_this_user")); // NOI18N

        org.jdesktop.layout.GroupLayout jInternalFrame1Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jInternalFrame1Layout.createSequentialGroup()
                            .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jInternalFrame1Layout.createSequentialGroup()
                                    .add(38, 38, 38)
                                    .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel1)
                                        .add(jLabel2))
                                    .add(55, 55, 55))
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jDeleteUserButton)
                                    .add(17, 17, 17)))
                            .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jButton1)
                                .add(jUserLogin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                .add(jUserPassword)))
                        .add(jInternalFrame1Layout.createSequentialGroup()
                            .add(jBorrowButton)
                            .add(112, 112, 112)
                            .add(jDumpUsersButton)))
                    .add(jInternalFrame1Layout.createSequentialGroup()
                        .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .add(8, 8, 8)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .add(jInternalFrame1Layout.createSequentialGroup()
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jUserLogin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(17, 17, 17)
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jUserPassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(37, 37, 37)
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton1)
                            .add(jDeleteUserButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 85, Short.MAX_VALUE)
                        .add(jLabel10)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jDumpUsersButton)
                            .add(jBorrowButton))
                        .addContainerGap())))
        );
        jPubYear.addTab(ResourceBundle.getBundle("library/server/server").getString("Users"), jInternalFrame1); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
            .add(jPubYear, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jPubYear, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBorrowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBorrowButtonActionPerformed
        try {
            int readerId = Integer.parseInt(jUserLogin.getText());
            Collection<BookCopy> requested = SQLBook.getRequestedBooks(readerId, library.getDatabase());
            int len=requested.size();
            int[] toBorrow = new int[len];
            int i=0;
            for(BookCopy bc : requested) {
                toBorrow[i++] = bc.copyId;
            }
            library.borrowBooks(readerId, toBorrow);
            jUsersTextArea.append("\n"+ResourceBundle.getBundle("library/server/server").getString("borrowed_books")+": ");
            for (i=0; i<len; ++i)
                jUsersTextArea.append(Integer.toString(toBorrow[i])+" ");
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jBorrowButtonActionPerformed

    private void jDeleteBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteBookButtonActionPerformed
        try {
            library.deleteBook(Integer.parseInt(jBookId.getText()));
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jDeleteBookButtonActionPerformed

    private void jDeleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteUserButtonActionPerformed
        try {
            library.deleteReader(new LoginData(Integer.parseInt(jUserLogin.getText()),""));
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jDeleteUserButtonActionPerformed

    private void jDumpUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDumpUsersButtonActionPerformed
        try {
            jUsersTextArea.setText("");
            Collection<Integer> readers = library.getReaders();
            for(Integer login : readers) {
                jUsersTextArea.append(login.toString()+"\n");
            }
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jDumpUsersButtonActionPerformed

    private void jAddCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddCopyActionPerformed
        try {
            BookCopy copy = new BookCopy(BookCopy.State.values()[ jState.getSelectedIndex() ]);
            library.addBookCopy(Integer.parseInt(jBookId.getText()), copy);
        } catch (Exception ex) {
            handleException(ex);
        }
    }//GEN-LAST:event_jAddCopyActionPerformed

    private void jAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddBookActionPerformed
        try {
            Book book = new Book(jTitle.getText(),jAuthor.getText(),jPublisher.getText(),
                    Integer.parseInt(jPublishYear.getText()));
            BookCopy copy = new BookCopy(BookCopy.State.values()[ jState.getSelectedIndex() ]);
            library.addBook(book, copy);
        } catch (Exception ex) {
            handleException(ex);
        }
    }//GEN-LAST:event_jAddBookActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            LoginData ld = new LoginData(Integer.parseInt(jUserLogin.getText()),jUserPassword.getText());
            library.addReader(ld);
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jButton1MouseClicked
    
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        try {
            int bookId = Integer.parseInt(jTextField9.getText());
            Collection<ReservationDetails> list = library.getBookCopies(bookId);
            BookDumper dumper = new BookDumper();
            jTextArea3.setText(dumper.dumpReservations( list));
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jButton5MouseClicked
    
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try {
            Collection<Book> list = library.findBooks(new SearchPattern());
            BookDumper dumper = new BookDumper();
            jTextArea2.setText(dumper.dumpBooks(list));
        } catch(Exception e) {
            handleException(e);
        }
    }//GEN-LAST:event_jButton4MouseClicked
    
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminWindow().setVisible(true);
            }
        });
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddBook;
    private javax.swing.JButton jAddCopy;
    private javax.swing.JTextField jAuthor;
    private javax.swing.JTextField jBookId;
    private javax.swing.JButton jBorrowButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jDeleteBookButton;
    private javax.swing.JButton jDeleteUserButton;
    private javax.swing.JButton jDumpUsersButton;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTabbedPane jPubYear;
    private javax.swing.JTextField jPublishYear;
    private javax.swing.JTextField jPublisher;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox jState;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTitle;
    private javax.swing.JTextField jUserLogin;
    private javax.swing.JTextField jUserPassword;
    private javax.swing.JTextArea jUsersTextArea;
    // End of variables declaration//GEN-END:variables
    
}

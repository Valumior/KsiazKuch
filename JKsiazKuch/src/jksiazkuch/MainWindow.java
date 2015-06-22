/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jksiazkuch;

/**
 *
 * @author valumior
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addPrzepisButton = new javax.swing.JButton();
        addSkladnikButton = new javax.swing.JButton();
        showPrzepisyButton = new javax.swing.JButton();
        showSkaldnikiButton = new javax.swing.JButton();
        editSkladnikButton = new javax.swing.JButton();
        editPrzepisButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addPrzepisButton.setText("Dodaj Przepis");
        addPrzepisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrzepisButtonActionPerformed(evt);
            }
        });

        addSkladnikButton.setText("Dodaj Skadnik");
        addSkladnikButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSkladnikButtonActionPerformed(evt);
            }
        });

        showPrzepisyButton.setText("Przegladaj przepisy");
        showPrzepisyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPrzepisyButtonActionPerformed(evt);
            }
        });

        showSkaldnikiButton.setText("Przegladaj skladniki");
        showSkaldnikiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSkaldnikiButtonActionPerformed(evt);
            }
        });

        editSkladnikButton.setText("Edytuj skladniki");
        editSkladnikButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSkladnikButtonActionPerformed(evt);
            }
        });

        editPrzepisButton.setText("Edytuj przepisy");
        editPrzepisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPrzepisButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Ksiazka Kucharska. Teraz w Javie!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(editPrzepisButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(editSkladnikButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addPrzepisButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addSkladnikButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showPrzepisyButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showSkaldnikiButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPrzepisButton)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addSkladnikButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPrzepisyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showSkaldnikiButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editSkladnikButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editPrzepisButton)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPrzepisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrzepisButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addPrzepisButtonActionPerformed

    private void addSkladnikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSkladnikButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSkladnikButtonActionPerformed

    private void showPrzepisyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPrzepisyButtonActionPerformed
        ShowPrzepisy showPrzepisy = new ShowPrzepisy(this, true);
        showPrzepisy.setVisible(true);
    }//GEN-LAST:event_showPrzepisyButtonActionPerformed

    private void showSkaldnikiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSkaldnikiButtonActionPerformed
        ShowSkladniki showSkladniki = new ShowSkladniki(this, true);
        showSkladniki.setVisible(true);
    }//GEN-LAST:event_showSkaldnikiButtonActionPerformed

    private void editSkladnikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSkladnikButtonActionPerformed
        EditSkladnik editSkladnik = new EditSkladnik(this, true);
        editSkladnik.setVisible(true);
    }//GEN-LAST:event_editSkladnikButtonActionPerformed

    private void editPrzepisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPrzepisButtonActionPerformed
        EditPrzepis editPrzepis = new EditPrzepis(this, true);
        editPrzepis.setVisible(true);
    }//GEN-LAST:event_editPrzepisButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPrzepisButton;
    private javax.swing.JButton addSkladnikButton;
    private javax.swing.JButton editPrzepisButton;
    private javax.swing.JButton editSkladnikButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton showPrzepisyButton;
    private javax.swing.JButton showSkaldnikiButton;
    // End of variables declaration//GEN-END:variables
}

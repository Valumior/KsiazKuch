/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jksiazkuch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Krzysztof
 */
public class AddSkladnik extends javax.swing.JDialog {

    /**
     * Creates new form AddSkladnik
     */
    
    private boolean isSkladnikBeingEdited;
    private Skladnik editedSkladnik;
    
    public AddSkladnik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initEvents();
        
        this.isSkladnikBeingEdited = false;
    }
    
    private void initEvents(){
        this.tluszczeNascyconeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Integer tluszczeCalkowite = (Integer) tluszczeCalkowiteSpinner.getValue();
                Integer tluszczeNienasycone = (Integer) tluszczeNienasyconeSpinner.getValue();

                int tluszczeNasycone = (Integer) ((JSpinner)e.getSource()).getValue();
                if(tluszczeNasycone + tluszczeNienasycone > tluszczeCalkowite) {
                    showMessageDialog("Suma tłuszczów nasyconych i nienasyconych nie może być większa niż tłuszcze całkowite!");
                    tluszczeNascyconeSpinner.setValue(tluszczeCalkowite - tluszczeNienasycone);
                }
            }
        });
        
        this.tluszczeNienasyconeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Integer tluszczeCalkowite = (Integer) tluszczeCalkowiteSpinner.getValue();
                Integer tluszczeNasycone = (Integer) tluszczeNascyconeSpinner.getValue();

                int tluszczeNienasycone = (Integer) ((JSpinner)e.getSource()).getValue();
                if(tluszczeNienasycone + tluszczeNasycone > tluszczeCalkowite) {
                    showMessageDialog("Suma tłuszczów nasyconych i nienasyconych nie może być większa niż tłuszcze całkowite!");
                    tluszczeNienasyconeSpinner.setValue(tluszczeCalkowite - tluszczeNasycone);
                }
            }
        });
        
        this.blonnikSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Integer cukry = (Integer) cukrySpinner.getValue();
                Integer weglowodany = (Integer) weglowodanySpinner.getValue();

                int blonnik = (Integer) ((JSpinner)e.getSource()).getValue();
                if(blonnik + cukry > weglowodany) {
                    showMessageDialog("Suma cukrów i błonnika nie może być większa niż węglowodany!");
                    blonnikSpinner.setValue(weglowodany - cukry);
                }
            }
        });
        
        this.cukrySpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                Integer blonnik = (Integer) blonnikSpinner.getValue();
                Integer weglowodany = (Integer) weglowodanySpinner.getValue();
                   
                int cukry = (Integer) ((JSpinner)e.getSource()).getValue();
                if(cukry + blonnik > weglowodany) {
                    showMessageDialog("Suma cukrów i błonnika nie może być większa niż węglowodany!");
                    cukrySpinner.setValue(weglowodany - blonnik);
                }
            }
        });
        
        this.miaraComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                changeDescLabelBySelection(comboBox.getSelectedIndex());
            }
        });
        
        this.stworzSkladnikButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveSkladnik();
            }
        });
    }
    
    private void changeDescLabelBySelection(int index) {
        switch(index) {
            case 0:
                this.wartosciOdzywczeLabel.setText("Wartości odżywcze dla 100 ml");
                break;
            case 1:
                this.wartosciOdzywczeLabel.setText("Wartości odżywcze dla 100 g");
                break;
            case 2:
                this.wartosciOdzywczeLabel.setText("Wartości odżywcze dla 1 sztuki");
                break;
            default:
                break;
        }
    }
    
    public AddSkladnik(java.awt.Frame parent, boolean modal, Skladnik skladnik) {
        this(parent, modal);
        
        this.isSkladnikBeingEdited = true;
        this.editedSkladnik = skladnik;
        this.stworzSkladnikButton.setText("Edytuj składnik!");

        this.miaraComboBox.setSelectedIndex(skladnik.getMiara().ordinal());
        this.changeDescLabelBySelection(skladnik.getMiara().ordinal());
        this.nazwaTextField.setText(skladnik.getNazwa());

        this.kalorieSpinner.setValue(skladnik.getWartosciOdzywcze().getKalorie());
        this.tluszczeCalkowiteSpinner.setValue(skladnik.getWartosciOdzywcze().getTluszczeCalkowite());
        this.tluszczeNascyconeSpinner.setValue(skladnik.getWartosciOdzywcze().getTluszczeNienasycone());
        this.tluszczeNienasyconeSpinner.setValue(skladnik.getWartosciOdzywcze().getTluszczeNienasycone());
        this.cholesterolSpinner.setValue(skladnik.getWartosciOdzywcze().getWeglowodany());
        this.sodSpinner.setValue(skladnik.getWartosciOdzywcze().getSod());
        this.weglowodanySpinner.setValue(skladnik.getWartosciOdzywcze().getWeglowodany());
        this.blonnikSpinner.setValue(skladnik.getWartosciOdzywcze().getBlonnik());
        this.cukrySpinner.setValue(skladnik.getWartosciOdzywcze().getCukry());
        this.bialkaSpinner.setValue(skladnik.getWartosciOdzywcze().getBialka());
    }
    
    private void saveSkladnik() {
        String nazwaString = this.nazwaTextField.getText();
        if(nazwaString.length() == 0)
        {
            showMessageDialog("Upewnij się, czy podałeś nazwę składnika!");
            return;
        }

        //- y u no constructor
        //= dunno, gr8 b8 m8 i r8 8/8
        WartosciOdzywcze wartosciOdzywcze = new WartosciOdzywcze(
                (Integer) this.kalorieSpinner.getValue(), 
                (Integer) this.tluszczeCalkowiteSpinner.getValue(), 
                (Integer) this.tluszczeNascyconeSpinner.getValue(), 
                (Integer) this.tluszczeNienasyconeSpinner.getValue(), 
                (Integer) this.cholesterolSpinner.getValue(), 
                (Integer) this.sodSpinner.getValue(), 
                (Integer) this.weglowodanySpinner.getValue(), 
                (Integer) this.blonnikSpinner.getValue(), 
                (Integer) this.cukrySpinner.getValue(), 
                (Integer) this.bialkaSpinner.getValue());

        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:../DB/cookbook.db");
                Statement state = conn.createStatement();
                
                if(this.isSkladnikBeingEdited) {
                    this.editedSkladnik.setNazwa(nazwaString);
                    this.editedSkladnik.setMiara(Skladnik.Miara.values()[this.miaraComboBox.getSelectedIndex()]);
                    this.editedSkladnik.setWartosciOdzywcze(wartosciOdzywcze);
                    
                    this.editedSkladnik.updateDb(state);
                }
                else {
                    Skladnik skladnik = new Skladnik(
                            0, 
                            nazwaString,
                            Skladnik.Miara.values()[this.miaraComboBox.getSelectedIndex()], 
                            wartosciOdzywcze);

                    skladnik.insertToDb(state);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        this.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nazwaTextField = new javax.swing.JTextField();
        miaraComboBox = new javax.swing.JComboBox();
        wartosciOdzywczeLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        stworzSkladnikButton = new javax.swing.JButton();
        kalorieSpinner = new javax.swing.JSpinner();
        tluszczeCalkowiteSpinner = new javax.swing.JSpinner();
        tluszczeNascyconeSpinner = new javax.swing.JSpinner();
        tluszczeNienasyconeSpinner = new javax.swing.JSpinner();
        cholesterolSpinner = new javax.swing.JSpinner();
        sodSpinner = new javax.swing.JSpinner();
        weglowodanySpinner = new javax.swing.JSpinner();
        blonnikSpinner = new javax.swing.JSpinner();
        cukrySpinner = new javax.swing.JSpinner();
        bialkaSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nazwa:");

        jLabel2.setText("Miara:");

        nazwaTextField.setText("Twój składnik");
        nazwaTextField.setToolTipText("");

        miaraComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MILILITRY", "GRAMY", "SZTUKI" }));

        wartosciOdzywczeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wartosciOdzywczeLabel.setText("Wartości odżywcze dla 100 ml");

        jLabel4.setText("Kalorie:");

        jLabel5.setText("Tłuszcze całkowite (mg):");

        jLabel6.setText("w tym tłuszcze nasycone (mg):");

        jLabel7.setText("w tym tłuszcze nienasycone (mg):");

        jLabel8.setText("Cholesterol (mg):");

        jLabel9.setText("Sód (mg):");

        jLabel10.setText("Węglowodany (mg):");

        jLabel11.setText("w tym błonnik (mg):");

        jLabel12.setText("w tym cukry (mg):");

        jLabel13.setText("Białka (mg):");

        stworzSkladnikButton.setText("Stwórz składnik!");

        kalorieSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        tluszczeCalkowiteSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        tluszczeNascyconeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        tluszczeNienasyconeSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        cholesterolSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        sodSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        weglowodanySpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        blonnikSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        cukrySpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        bialkaSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stworzSkladnikButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wartosciOdzywczeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nazwaTextField)
                            .addComponent(miaraComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kalorieSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(tluszczeCalkowiteSpinner)
                            .addComponent(tluszczeNascyconeSpinner)
                            .addComponent(tluszczeNienasyconeSpinner)
                            .addComponent(cholesterolSpinner)
                            .addComponent(sodSpinner)
                            .addComponent(weglowodanySpinner)
                            .addComponent(blonnikSpinner)
                            .addComponent(cukrySpinner)
                            .addComponent(bialkaSpinner))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nazwaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(miaraComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wartosciOdzywczeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kalorieSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tluszczeCalkowiteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tluszczeNascyconeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tluszczeNienasyconeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cholesterolSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(sodSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(weglowodanySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(blonnikSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cukrySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(bialkaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stworzSkladnikButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AddSkladnik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSkladnik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSkladnik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSkladnik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddSkladnik dialog = new AddSkladnik(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner bialkaSpinner;
    private javax.swing.JSpinner blonnikSpinner;
    private javax.swing.JSpinner cholesterolSpinner;
    private javax.swing.JSpinner cukrySpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner kalorieSpinner;
    private javax.swing.JComboBox miaraComboBox;
    private javax.swing.JTextField nazwaTextField;
    private javax.swing.JSpinner sodSpinner;
    private javax.swing.JButton stworzSkladnikButton;
    private javax.swing.JSpinner tluszczeCalkowiteSpinner;
    private javax.swing.JSpinner tluszczeNascyconeSpinner;
    private javax.swing.JSpinner tluszczeNienasyconeSpinner;
    private javax.swing.JLabel wartosciOdzywczeLabel;
    private javax.swing.JSpinner weglowodanySpinner;
    // End of variables declaration//GEN-END:variables
}

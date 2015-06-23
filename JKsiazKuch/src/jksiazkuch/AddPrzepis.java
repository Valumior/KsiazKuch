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
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author valumior
 */
public class AddPrzepis extends javax.swing.JDialog {

    private ArrayList<Skladnik> skladniks;
    private Przepis editedPrzepis;
    boolean isPrzepisBeingEdited;
    
    /**
     * Creates new form AddPrzepis
     */
    public AddPrzepis(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.isPrzepisBeingEdited = false;
        this.skladniks = new ArrayList<>();
        
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            try{
                con = DriverManager.getConnection("jdbc:sqlite:../DB/cookbook.db");
                Statement state = con.createStatement();
            
                skladniks = Skladnik.getObjects(state);
                for(Skladnik skladnik : skladniks) {
                    this.skladnikiComboBox.addItem(skladnik.getNazwa());
                }
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                con.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        this.skladnikiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox sender = (JComboBox) e.getSource();
                Skladnik skladnik = skladniks.get(sender.getSelectedIndex());
                switch(skladnik.getMiara())
                {
                    case Mililitry:
                        jednostkaLabel.setText("ml");
                        break;
                    case Gramy:
                        jednostkaLabel.setText("g");
                        break;
                    case Sztuki:
                        jednostkaLabel.setText("sztuk");
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    public AddPrzepis(java.awt.Frame parent, boolean modal, Przepis przepis) {
        this(parent, modal);
        
        this.isPrzepisBeingEdited = true;
        this.editedPrzepis = przepis;
        
        this.stworzPrzepisPushButton.setText("Edytuj przepis!");
        this.nazwaTextField.setText(przepis.getNazwa());
        this.czasPrzygotowaniaSpinner.setValue(przepis.getCzasPrzygotowania());
        this.trudnoscComboBox.setSelectedIndex(przepis.getTrudnosc().ordinal());
        this.ulubioneCheckBox.setSelected(przepis.isUlubione());
        this.przygotowanieTextArea.setText(przepis.getInstrukcja());
        
        for(PrzepisSkladnik przepisSkladnik : przepis.getSkladniki()) {
            String newEntry = przepisSkladnik.getSkladnik().getNazwa() + ": "
                                + przepisSkladnik.getLiczba();
            switch(przepisSkladnik.getSkladnik().getMiara()) {
                case Mililitry:
                    newEntry += " ml";
                    break;
                case Gramy:
                    newEntry += " g";
                    break;
                case Sztuki:
                    newEntry += " sztuk";
                    break;
                default:
                    break;
            }
            ((DefaultListModel) this.skladnikiList.getModel()).addElement(newEntry);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        przygotowanieTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        skladnikiList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        nazwaTextField = new javax.swing.JTextField();
        czasPrzygotowaniaSpinner = new javax.swing.JSpinner();
        trudnoscComboBox = new javax.swing.JComboBox();
        ulubioneCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        skladnikiComboBox = new javax.swing.JComboBox();
        jednostkaSpinner = new javax.swing.JSpinner();
        jednostkaLabel = new javax.swing.JLabel();
        dodajSkladnikButton = new javax.swing.JButton();
        stworzPrzepisPushButton = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nazwa:");

        jLabel2.setText("Czas przygotowania:");

        jLabel3.setText("Trudnosc:");

        przygotowanieTextArea.setColumns(20);
        przygotowanieTextArea.setRows(5);
        jScrollPane2.setViewportView(przygotowanieTextArea);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Opisz przygotowanie");

        jScrollPane3.setViewportView(skladnikiList);

        jLabel5.setText("Ulubione:");

        trudnoscComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LATWE", "SREDNIE", "TRUDNE", "MISTRZ KUCHNI" }));

        ulubioneCheckBox.setText("czy ulubione?");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Skladniki");

        jednostkaLabel.setText("sztuk");

        dodajSkladnikButton.setText("Dodaj/Usun");
        dodajSkladnikButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajSkladnikButtonActionPerformed(evt);
            }
        });

        stworzPrzepisPushButton.setText("Stworz przepis");
        stworzPrzepisPushButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stworzPrzepisPushButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stworzPrzepisPushButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(skladnikiComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jednostkaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jednostkaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dodajSkladnikButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ulubioneCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(czasPrzygotowaniaSpinner)
                                    .addComponent(nazwaTextField)
                                    .addComponent(trudnoscComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(nazwaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(czasPrzygotowaniaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(trudnoscComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ulubioneCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(skladnikiComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jednostkaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jednostkaLabel)
                            .addComponent(dodajSkladnikButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stworzPrzepisPushButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dodajSkladnikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajSkladnikButtonActionPerformed
        Skladnik skladnik = this.skladniks.get(this.skladnikiComboBox.getSelectedIndex());

        ArrayList<String> foundSkladniks = new ArrayList<>();
        DefaultListModel listModel = (DefaultListModel)this.skladnikiList.getModel();
        for(int i = 0; i < listModel.getSize(); ++i) {
            if(((String)listModel.getElementAt(i)).contains(skladnik.getNazwa())) {
                foundSkladniks.add((String)listModel.getElementAt(i));
            }
        }
        
        if(foundSkladniks.size() > 0) {
            listModel.removeElement(foundSkladniks.get(0));
        }
        else
        {
            listModel.addElement(skladnik.getNazwa() + ": " +
                                 this.jednostkaSpinner.getValue() + " " +
                                 this.jednostkaLabel.getText());
        }
    }//GEN-LAST:event_dodajSkladnikButtonActionPerformed

    private void stworzPrzepisPushButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stworzPrzepisPushButtonActionPerformed
        if(this.nazwaTextField.getText().length() == 0)
        {
            showMessageDialog("Upewnij się że podałeś nazwę przepisu!");
            return;
        }

        if(this.przygotowanieTextArea.getText().length() == 0)
        {
            showMessageDialog("Upewnij się że podałeś sposób przyrządzenia!");
            return;
        }

        if(((DefaultListModel)this.skladnikiList.getModel()).isEmpty())
        {
            showMessageDialog("Upewnij się że dodałeś do co najmniej jeden składnik!");
            return;
        }



        ArrayList<PrzepisSkladnik> skladniksList = new ArrayList<>();
        for(Skladnik skladnik : skladniks) {
            ArrayList<String> foundSkladniks = new ArrayList<>();
            DefaultListModel listModel = (DefaultListModel)this.skladnikiList.getModel();
            for(int i = 0; i < listModel.getSize(); ++i) {
                if(((String)listModel.getElementAt(i)).contains(skladnik.getNazwa())) {
                    foundSkladniks.add((String)listModel.getElementAt(i));
                }
            }
            
            if(foundSkladniks.size() > 0) {
                int skladnikCount = Integer.parseInt(foundSkladniks.get(0).split(":")[1].trim().split(" ")[0]);
                PrzepisSkladnik newSkladnik = new PrzepisSkladnik(skladnikCount, skladnik);
                skladniksList.add(newSkladnik);
            }
        }
        
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            try{
                con = DriverManager.getConnection("jdbc:sqlite:../DB/cookbook.db");
                Statement state = con.createStatement();
            
                if(this.isPrzepisBeingEdited) {
                    this.editedPrzepis.setNazwa(this.nazwaTextField.getText());
                    this.editedPrzepis.setCzasPrzygotowania((Integer) this.czasPrzygotowaniaSpinner.getValue());
                    this.editedPrzepis.setTrudnosc(Przepis.Trudnosc.values()[this.trudnoscComboBox.getSelectedIndex()]);
                    this.editedPrzepis.setUlubione(this.ulubioneCheckBox.isSelected());
                    this.editedPrzepis.setInstrukcja(this.przygotowanieTextArea.getText());
                    this.editedPrzepis.setSkladniki(skladniksList);

                    editedPrzepis.updateDb(state);
                    showMessageDialog("Przepis poprawnie edytowany! Smacznego!");
                }
                else
                {
                    Przepis przepis = new Przepis(0, 
                                                  this.nazwaTextField.getText(),
                                                  (Integer) this.czasPrzygotowaniaSpinner.getValue(),
                                                  Przepis.Trudnosc.values()[this.trudnoscComboBox.getSelectedIndex()],
                                                  this.ulubioneCheckBox.isSelected(),
                                                  this.przygotowanieTextArea.getText());
                    przepis.setSkladniki(skladniksList);

                    przepis.insertToDb(state);
                    showMessageDialog("Przepis poprawnie dodany! Smacznego!");
                }
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                con.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_stworzPrzepisPushButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddPrzepis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPrzepis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPrzepis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPrzepis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddPrzepis dialog = new AddPrzepis(new javax.swing.JFrame(), true);
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
    private javax.swing.JSpinner czasPrzygotowaniaSpinner;
    private javax.swing.JButton dodajSkladnikButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jednostkaLabel;
    private javax.swing.JSpinner jednostkaSpinner;
    private javax.swing.JTextField nazwaTextField;
    private javax.swing.JTextArea przygotowanieTextArea;
    private javax.swing.JComboBox skladnikiComboBox;
    private javax.swing.JList skladnikiList;
    private javax.swing.JButton stworzPrzepisPushButton;
    private javax.swing.JComboBox trudnoscComboBox;
    private javax.swing.JCheckBox ulubioneCheckBox;
    // End of variables declaration//GEN-END:variables
}

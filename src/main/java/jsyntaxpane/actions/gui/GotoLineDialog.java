/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jsyntaxpane.actions.gui;

import java.lang.ref.WeakReference;
import jsyntaxpane.actions.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;
import jsyntaxpane.util.SwingUtils;

/**
 * A simple dialog to prompt for a line number and go to it
 * @author Ayman Al-Sairafi
 */
public class GotoLineDialog 
	extends javax.swing.JDialog implements EscapeListener {

    private static final String PROPERTY_KEY = "GOTOLINE_DIALOG";
    private WeakReference<JTextComponent> text;

    /** 
     * Creates new form GotoLineDialog
     * @param text
     */
    private GotoLineDialog(JTextComponent text) {
		super(SwingUtilities.getWindowAncestor(text), ModalityType.APPLICATION_MODAL);
        initComponents();
        this.text = new WeakReference<JTextComponent>(text);
        setLocationRelativeTo(text.getRootPane());
        getRootPane().setDefaultButton(jBtnOk);
        text.getDocument().putProperty(PROPERTY_KEY, this);
        SwingUtils.addEscapeListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCmbLineNumbers = new javax.swing.JComboBox();
        jBtnOk = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jsyntaxpane/Bundle"); // NOI18N
        setTitle(bundle.getString("GotoLineDialog.title")); // NOI18N
        setModal(true);
        setName(""); // NOI18N
        setResizable(false);

        jCmbLineNumbers.setEditable(true);
        jCmbLineNumbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbLineNumbersActionPerformed(evt);
            }
        });

        jBtnOk.setAction(jCmbLineNumbers.getAction());
        jBtnOk.setText(bundle.getString("GotoLineDialog.jBtnOk.text")); // NOI18N
        jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCmbLineNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbLineNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setTextPos() {
        Object line = jCmbLineNumbers.getSelectedItem();
        if (line != null) {
            try {
                int lineNr = Integer.parseInt(line.toString());
                ActionUtils.insertIntoCombo(jCmbLineNumbers, line);
                ActionUtils.setCaretPosition(text.get(), lineNr, 0);
                setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Number: " + line,
                        "Number Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jCmbLineNumbersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbLineNumbersActionPerformed
        if (evt.getActionCommand().equals("comboBoxEdited")) {
            setTextPos();
        }
    }//GEN-LAST:event_jCmbLineNumbersActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        setTextPos();
    }//GEN-LAST:event_jBtnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnOk;
    private javax.swing.JComboBox jCmbLineNumbers;
    // End of variables declaration//GEN-END:variables

    /**
     * Create or return the GotoLine dialog for a given ext component
     * @param text
     */
    public static void showForEditor(JTextComponent text) {
        GotoLineDialog dlg = null;
        if (text.getDocument().getProperty(PROPERTY_KEY) == null) {
            dlg = new GotoLineDialog(text);
        } else {
            dlg = (GotoLineDialog) text.getDocument().getProperty(PROPERTY_KEY);
        }
        dlg.jCmbLineNumbers.requestFocusInWindow();
        dlg.setVisible(true);
        
    }

	@Override
	public void escapePressed() {
		setVisible(false);
	}
}

package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Recursos.Control;
import Recursos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtContraseña;
	private JTextField txtConfirm;
	private JComboBox cbxTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUser dialog = new RegUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUser() {
		setTitle("Registrar Usuario");
		setBounds(100, 100, 450, 246);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre de Usuario:");
			lblNewLabel.setBounds(19, 21, 125, 16);
			panel.add(lblNewLabel);
			
			txtUsername = new JTextField();
			txtUsername.setBounds(19, 46, 145, 26);
			panel.add(txtUsername);
			txtUsername.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Contraseña:");
			lblNewLabel_1.setBounds(225, 21, 92, 16);
			panel.add(lblNewLabel_1);
			
			txtContraseña = new JTextField();
			txtContraseña.setBounds(225, 46, 145, 26);
			panel.add(txtContraseña);
			txtContraseña.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Tipo:");
			lblNewLabel_2.setBounds(19, 94, 61, 16);
			panel.add(lblNewLabel_2);
			
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administrador", "Comercial"}));
			cbxTipo.setBounds(19, 122, 145, 27);
			panel.add(cbxTipo);
			
			JLabel lblNewLabel_3 = new JLabel("Confirmar Contraseña:");
			lblNewLabel_3.setBounds(225, 94, 145, 16);
			panel.add(lblNewLabel_3);
			
			txtConfirm = new JTextField();
			txtConfirm.setBounds(225, 121, 145, 26);
			panel.add(txtConfirm);
			txtConfirm.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtContraseña.getText().equals(txtConfirm.getText())) {
							Usuario user = new Usuario(cbxTipo.getSelectedItem().toString(), txtUsername.getText(), txtContraseña.getText());
							if (Control.getInstance().RegistrarUsuario(user)) {
								clean();
								JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Registro fallido", "Información", JOptionPane.WARNING_MESSAGE);
							}
						} 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void clean() {
		txtUsername.setText("");
		txtContraseña.setText("");
		txtConfirm.setText("");
		cbxTipo.setSelectedIndex(0);
		
	}
}

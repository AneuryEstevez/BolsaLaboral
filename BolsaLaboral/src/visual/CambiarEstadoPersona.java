package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Recursos.Control;
import Recursos.Obrero;
import Recursos.Persona;
import Recursos.SolicitudPersona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class CambiarEstadoPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private Persona p = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarEstadoPersona dialog = new CambiarEstadoPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiarEstadoPersona() {
		setTitle("Cambiar Estado Persona");
		setBounds(100, 100, 450, 157);
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
			
			JLabel lblNewLabel = new JLabel("Cédula:");
			lblNewLabel.setBounds(12, 11, 61, 16);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(71, 6, 130, 26);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p = Control.getInstance().BuscarPersona(txtCedula.getText());
					if (p != null && p.isStatus()) {
						txtNombre.setText(p.getNombre());
					} else if (p == null){
						JOptionPane.showMessageDialog(null, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Esta persona está desempleada", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}

			});
			btnBuscar.setBounds(213, 6, 91, 29);
			panel.add(btnBuscar);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(12, 39, 61, 16);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(71, 34, 329, 26);
			panel.add(txtNombre);
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
		
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cambiar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Control.getInstance().getCon().UpdatePersonaToDesempleado(p);
						JOptionPane.showMessageDialog(null, "Cambiado a desempleado", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
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
		txtCedula.setText("");
		txtNombre.setText("");
	}

}

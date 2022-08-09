package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Recursos.Control;
import Recursos.Empresa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RegistroEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtCodigo;
	private JComboBox cbxCategoria;
	private JComboBox cbxProvincia;
	private JComboBox cbxMunicipio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroEmpresa dialog = new RegistroEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroEmpresa() {
		setTitle("Registro Empresa");
		setBounds(100, 100, 450, 297);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel_1, BorderLayout.CENTER);
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				lblNewLabel_1.setBounds(6, 72, 61, 16);
				panel_1.add(lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(79, 67, 355, 26);
				panel_1.add(txtNombre);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Teléfono:");
				lblNewLabel_2.setBounds(6, 100, 72, 16);
				panel_1.add(lblNewLabel_2);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(79, 95, 355, 26);
				panel_1.add(txtTelefono);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Dirección:");
				lblNewLabel_5.setBounds(6, 128, 72, 16);
				panel_1.add(lblNewLabel_5);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(79, 123, 355, 26);
				panel_1.add(txtDireccion);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Categoria:");
				lblNewLabel_3.setBounds(6, 42, 72, 16);
				panel_1.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Provincia:");
				lblNewLabel_7.setBounds(6, 156, 61, 16);
				panel_1.add(lblNewLabel_7);
			}
			{
				cbxProvincia = new JComboBox();
				cbxProvincia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ArrayList<String> m = new ArrayList<>();
						m.add("<Seleccione>");
						m.addAll(Control.getInstance().getMunicipiosProvincia(cbxProvincia.getSelectedItem().toString()));
						cbxMunicipio.setModel(new DefaultComboBoxModel(m.toArray()));
						cbxMunicipio.setEnabled(true);
					}
				});
//				cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajabón", "Distrito Nacional", "Duarte", "Elías Piña", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "María Trinidad Sánchez", "Monseñor Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Samaná", "Sánchez Ramírez", "San Cristóbal", "San José de Ocoa", "San Juan", "San Pedro de Macorís", "Santiago", "Santiago Rodríguez", "Santo Domingo", "Valverde"}));
				ArrayList<String> p = new ArrayList<>();
				p.add("<Seleccione>");
				p.addAll(Control.getInstance().getProvincias());
				cbxProvincia.setModel(new DefaultComboBoxModel(p.toArray()));
				cbxProvincia.setMaximumRowCount(5);
				cbxProvincia.setBounds(79, 151, 172, 27);
				panel_1.add(cbxProvincia);
			}
			{
				cbxCategoria = new JComboBox();
				ArrayList<String> c = new ArrayList<>();
				c.add("<Seleccione>");
				c.addAll(Control.getInstance().getCategorias());
				cbxCategoria.setModel(new DefaultComboBoxModel(c.toArray()));
//				cbxCategoria.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Turismo", "Salud", "Educación", "Comercio", "Industrial", "Finanzas", "Telecomunicaciones", "Transporte"}));
				cbxCategoria.setBounds(79, 37, 172, 27);
				panel_1.add(cbxCategoria);
			}
			{
				JLabel lblNewLabel = new JLabel("Código:");
				lblNewLabel.setBounds(6, 10, 61, 16);
				panel_1.add(lblNewLabel);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setBounds(79, 5, 355, 26);
				panel_1.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			
			JLabel lblNewLabel_4 = new JLabel("Municipio:");
			lblNewLabel_4.setBounds(6, 184, 72, 16);
			panel_1.add(lblNewLabel_4);
			
			cbxMunicipio = new JComboBox();
			cbxMunicipio.setEnabled(false);
			cbxMunicipio.setBounds(79, 180, 172, 27);
			panel_1.add(cbxMunicipio);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Empresa aux = new Empresa(txtCodigo.getText(), txtNombre.getText(), cbxCategoria.getSelectedItem().toString(), txtDireccion.getText(), txtTelefono.getText(), cbxMunicipio.getSelectedItem().toString());
						if (check()) {
							Control.getInstance().RegistrarEmpresa(aux);
							JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos!", "Error", JOptionPane.ERROR_MESSAGE);
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
		txtCodigo.setText("");
		cbxCategoria.setSelectedIndex(0);
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxProvincia.setSelectedIndex(0);
		cbxMunicipio.setEnabled(false);
	}
	
	private boolean check() {
		boolean completo = true;
		
		if (txtCodigo.getText().equalsIgnoreCase("") || txtNombre.getText().equalsIgnoreCase("")) {
			completo = false;
		} else if (txtTelefono.getText().equalsIgnoreCase("") || txtDireccion.getText().equalsIgnoreCase("")) {
			completo = false;
		} else if(cbxCategoria.getSelectedIndex() == 0 || cbxProvincia.getSelectedIndex() == 0 || cbxMunicipio.getSelectedIndex() == 0) {
			completo = false;
		}
		return completo;
	}
}

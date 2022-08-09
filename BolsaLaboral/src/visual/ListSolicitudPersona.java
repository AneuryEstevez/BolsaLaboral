package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Recursos.Control;
import Recursos.Obrero;
import Recursos.SolicitudPersona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListSolicitudPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object rows[];
	private SolicitudPersona selected = null;
	private JTable table;
	private JComboBox cbxTipo;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListSolicitudPersona dialog = new ListSolicitudPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListSolicitudPersona() {
		setTitle("Listado de Solicitudes");
		setBounds(100, 100, 597, 369);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de Solicitudes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Tipo de Solicitud:");
				lblNewLabel.setBounds(16, 29, 125, 16);
				panel.add(lblNewLabel);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selection = cbxTipo.getSelectedIndex();
						loadtable(selection);
					}
				});
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Todas>", "Activas", "En espera", "Completadas"}));
				cbxTipo.setBounds(131, 25, 115, 27);
				panel.add(cbxTipo);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(6, 57, 573, 224);
			panel.add(scrollPane);
			{
				model = new DefaultTableModel();
				String headers[] = {"Código", "Persona", "Título", "Sueldo Mínimo", "Estado"};
				model.setColumnIdentifiers(headers);
				loadtable(0);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = -1;
						index = table.getSelectedRow();
						if (index != -1) {
//							btnEliminar.setEnabled(true);
//							String id = (String)(model.getValueAt(index, 0));
//							selected = Control.getInstance().BuscarSoliPersona(id);
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected.getEstado().equalsIgnoreCase("Activa") || selected.getEstado().equalsIgnoreCase("En espera")) {
						    int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la solicitud seleccionada: "+ selected.getId(), "Eliminar Solicitud Persona", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
							Control.getInstance().getSoliPersonas().remove(selected);
							loadtable(0);
							btnEliminar.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Esta solicitud no puede ser eliminada", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
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

	private static void loadtable(int selection) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		switch (selection) {
		case 0:
			for (SolicitudPersona sp: Control.getInstance().getCon().SelectSolicitudPersonas()) {
				rows[0] = sp.getId();
				rows[1] = sp.getPerson().getNombre();
				if (sp.getPerson() instanceof Universitario) {
					rows[2] = ((Universitario) sp.getPerson()).getTitulo();
				}
				if (sp.getPerson() instanceof Tecnico) {
					rows[2] = ((Tecnico) sp.getPerson()).getArea();
				}
				if (sp.getPerson() instanceof Obrero) {
					rows[2] = ((Obrero) sp.getPerson()).getSkills();
				}
				rows[3] = sp.getSueldoMinimo();
				rows[4] = sp.getEstado();
				
				model.addRow(rows);
			}
			break;

		case 1:
			for (SolicitudPersona sp: Control.getInstance().getCon().SelectSolicitudPersonas()) {
				if (sp.getEstado().equalsIgnoreCase("Activa")) {
					rows[0] = sp.getId();
					rows[1] = sp.getPerson().getNombre();
					if (sp.getPerson() instanceof Universitario) {
						rows[2] = ((Universitario) sp.getPerson()).getTitulo();
					}
					if (sp.getPerson() instanceof Tecnico) {
						rows[2] = ((Tecnico) sp.getPerson()).getArea();
					}
					if (sp.getPerson() instanceof Obrero) {
						rows[2] = ((Obrero) sp.getPerson()).getSkills();
					}
					rows[3] = sp.getSueldoMinimo();
					rows[4] = sp.getEstado();
					
					model.addRow(rows);
				}
			}
			break;
			
		case 2:
			for (SolicitudPersona sp: Control.getInstance().getCon().SelectSolicitudPersonas()) {
				if (sp.getEstado().equalsIgnoreCase("En espera")) {
					rows[0] = sp.getId();
					rows[1] = sp.getPerson().getNombre();
					if (sp.getPerson() instanceof Universitario) {
						rows[2] = ((Universitario) sp.getPerson()).getTitulo();
					}
					if (sp.getPerson() instanceof Tecnico) {
						rows[2] = ((Tecnico) sp.getPerson()).getArea();
					}
					if (sp.getPerson() instanceof Obrero) {
						rows[2] = ((Obrero) sp.getPerson()).getSkills();
					}
					rows[3] = sp.getSueldoMinimo();
					rows[4] = sp.getEstado();
					
					model.addRow(rows);
				}
			}
			break;
			
		case 3:
			for (SolicitudPersona sp: Control.getInstance().getCon().SelectSolicitudPersonas()) {
				if (sp.getEstado().equalsIgnoreCase("Completada")) {
					rows[0] = sp.getId();
					rows[1] = sp.getPerson().getNombre();
					if (sp.getPerson() instanceof Universitario) {
						rows[2] = ((Universitario) sp.getPerson()).getTitulo();
					}
					if (sp.getPerson() instanceof Tecnico) {
						rows[2] = ((Tecnico) sp.getPerson()).getArea();
					}
					if (sp.getPerson() instanceof Obrero) {
						rows[2] = ((Obrero) sp.getPerson()).getSkills();
					}
					rows[3] = sp.getSueldoMinimo();
					rows[4] = sp.getEstado();
					
					model.addRow(rows);
				}
			}
			break;
		}		
		
	}
}

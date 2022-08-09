package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Recursos.Control;
import Recursos.Empresa;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream control2;
				ObjectOutputStream controlWrite;
				try {
					control2 = new FileOutputStream("Control.dat");
					controlWrite = new ObjectOutputStream(control2);
					controlWrite.writeObject(Control.getInstance());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Bolsa de Ubicacion Laboral");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-95);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
	
		
		JMenu mnNewMenu_3 = new JMenu("Personas");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroPersona re = new RegistroPersona();
				re.setModal(true);
				re.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPersona lp = new ListPersona();
				lp.setModal(true);
				lp.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_4 = new JMenu("Empresas");
		mnNewMenu.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registrar");
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_2.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				RegistroEmpresa regE = new RegistroEmpresa();
				regE.setModal(true);
				regE.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_0 = new JMenuItem("Listar");
		mntmNewMenuItem_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEmpresa le = new ListEmpresa();
				le.setModal(true);
				le.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_0);
		
		JMenu mnNewMenu_1 = new JMenu("Solicitudes");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_5 = new JMenu("Persona");
		mnNewMenu_1.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Realizar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarSolicitudPersona sp = new RealizarSolicitudPersona();
				sp.setModal(true);
				sp.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listar");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSolicitudPersona lista = new ListSolicitudPersona();
				lista.setModal(true);
				lista.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_6 = new JMenu("Empresa");
		mnNewMenu_1.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Realizar");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarSolicitudEmpresa soliEmp = new RealizarSolicitudEmpresa();
				soliEmp.setModal(true);
				soliEmp.setVisible(true);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSolicitudEmpresa lista = new ListSolicitudEmpresa();
				lista.setModal(true);
				lista.setVisible(true);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2 = new JMenu("Administración");
		if (!Control.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			mnNewMenu_2.setEnabled(false);
		}
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_7 = new JMenu("Usuarios");
		mnNewMenu_2.add(mnNewMenu_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUser registar = new RegUser();
				registar.setModal(true);
				registar.setVisible(true);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Listar");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUser lista = new ListUser();
				lista.setModal(true);
				lista.setVisible(true);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Realizar match");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarMatch match = new RealizarMatch();
				match.setModal(true);
				match.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cambiar estado persona");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarEstadoPersona ce = new CambiarEstadoPersona();
				ce.setModal(true);
				ce.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Listar Matches");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListMatches lm = new ListMatches();
				lm.setModal(true);
				lm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_11);
		mnNewMenu_2.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

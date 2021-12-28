package ar.com.zeni.admin.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ar.com.zeni.admin.app.components.JLimitedTextField;
import ar.com.zeni.zeniadminwsdl.AdminClienteType;
import ar.com.zeni.zeniadminwsdl.AdminCuentaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminClienteType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminCuentaType;
import ar.com.zeni.zeniadminwsdl.ClienteCompletoType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;

public class UserPanel extends JPanel {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	private final JTextField tClientNumber;
	private final JTextField tUserName;
	private final JTable table;
	private JTextField	tName;
	private JTextField	tLastname;
	private JTextField	tEmail;
	private JTable	tableCtasTodasAdd;
	private JList	lCtas;
	private DefaultTableModel	mmodelTodas;
	private HashMap<String, AdminCuentaType> ctasTodas;
	private DefaultComboBoxModel	mmodel;
	private JButton bCan;
	private JButton bAcc;
	private JButton bRem;
	private JButton bResetPass;
	private JButton	bAdd;
	private JButton	bRefresh;
	private JButton	bDelUser;
	private JButton	bClone;
	private JButton	bAddUser;
	private DefaultTableModel	modelAllData;
	private JSplitPane	splitPane;
	private JPanel	panel;
	private boolean	modeAdd;
	private JDialog	jdDialog;
	private JPanel panel_4;
	private JComboBox comboBox;
	private JTextField filterField;
	private JButton bFilter;
	private JButton bEraseFilter;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JToggleButton tglbtnFiltro;
	private JSplitPane splitPane_1;

	/**
	 * Create the panel.
	 */
	public UserPanel() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		final JPanel panelTabla = new JPanel();
		GridBagLayout gbl_panelTabla = new GridBagLayout();
		gbl_panelTabla.columnWidths = new int[]{454, 0};
		gbl_panelTabla.rowHeights = new int[]{31, 0, 0};
		gbl_panelTabla.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTabla.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelTabla.setLayout(gbl_panelTabla);

		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panelTabla.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblNewLabel = new JLabel("Filtro:");
		panel_4.add(lblNewLabel);

		comboBox = new JComboBox();
		panel_4.add(comboBox);

		filterField = new JTextField();
		panel_4.add(filterField);
		filterField.setColumns(10);

		bFilter = new JButton("Aplicar Filtro");
		panel_4.add(bFilter);

		bEraseFilter = new JButton("Borrar Filtro");
		panel_4.add(bEraseFilter);
		splitPane.setLeftComponent(panelTabla);

		final JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelTabla.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edicion De Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		bAcc = new JButton("Aceptar Cambios");

		bCan = new JButton("Cancelar Cambios");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JSeparator separator = new JSeparator();
		panel_1.add(separator);
		panel_1.add(bAcc);
		panel_1.add(bCan);

		splitPane_1 = new JSplitPane();
		panel.add(splitPane_1);

		tUserName = new JLimitedTextField(20);
		final GridBagConstraints gbc_tUserName = new GridBagConstraints();
		gbc_tUserName.insets = new Insets(0, 0, 5, 0);
		gbc_tUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tUserName.gridx = 1;
		gbc_tUserName.gridy = 0;
		tUserName.setColumns(10);

		tName = new JLimitedTextField(100);
		final GridBagConstraints gbc_rName = new GridBagConstraints();
		gbc_rName.insets = new Insets(0, 0, 5, 0);
		gbc_rName.fill = GridBagConstraints.HORIZONTAL;
		gbc_rName.gridx = 1;
		gbc_rName.gridy = 2;
		tName.setColumns(10);

		tLastname = new JLimitedTextField(100);
		final GridBagConstraints gbc_tLastname = new GridBagConstraints();
		gbc_tLastname.anchor = GridBagConstraints.NORTH;
		gbc_tLastname.insets = new Insets(0, 0, 5, 0);
		gbc_tLastname.fill = GridBagConstraints.HORIZONTAL;
		gbc_tLastname.gridx = 1;
		gbc_tLastname.gridy = 3;
		tLastname.setColumns(10);

		tEmail = new JLimitedTextField(150);
		final GridBagConstraints gbc_tEmail = new GridBagConstraints();
		gbc_tEmail.insets = new Insets(0, 0, 5, 0);
		gbc_tEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tEmail.gridx = 1;
		gbc_tEmail.gridy = 4;
		tEmail.setColumns(10);

		final JPanel pCtas = new JPanel();
		splitPane_1.setRightComponent(pCtas);
		pCtas.setBorder(new TitledBorder(null, "Cuentas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		final GridBagLayout gbl_pCtas = new GridBagLayout();
		gbl_pCtas.columnWidths = new int[]{51, 0, 77, 9, 0};
		gbl_pCtas.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pCtas.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pCtas.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		pCtas.setLayout(gbl_pCtas);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		pCtas.add(textField, gbc_textField);
		textField.setColumns(10);

		tglbtnFiltro = new JToggleButton("Activar Filtro:");

				GridBagConstraints gbc_tglbtnFiltro = new GridBagConstraints();
				gbc_tglbtnFiltro.gridwidth = 2;
				gbc_tglbtnFiltro.insets = new Insets(0, 0, 5, 5);
				gbc_tglbtnFiltro.gridx = 1;
				gbc_tglbtnFiltro.gridy = 0;
				pCtas.add(tglbtnFiltro, gbc_tglbtnFiltro);

				final JScrollPane scrollPane_2 = new JScrollPane();
				final GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
				gbc_scrollPane_2.gridwidth = 2;
				gbc_scrollPane_2.gridheight = 4;
				gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
				gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_2.gridx = 0;
				gbc_scrollPane_2.gridy = 1;
				pCtas.add(scrollPane_2, gbc_scrollPane_2);

				tableCtasTodasAdd = new JTable();
				scrollPane_2.setViewportView(tableCtasTodasAdd);

				final JScrollPane scrollPane_1 = new JScrollPane();
				final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
				gbc_scrollPane_1.gridheight = 5;
				gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_1.gridx = 3;
				gbc_scrollPane_1.gridy = 0;
				pCtas.add(scrollPane_1, gbc_scrollPane_1);

				lCtas = new JList();
				scrollPane_1.setViewportView(lCtas);

				bAdd = new JButton("Agregar ->");
				final GridBagConstraints gbc_bAdd = new GridBagConstraints();
				gbc_bAdd.insets = new Insets(0, 0, 5, 5);
				gbc_bAdd.gridx = 2;
				gbc_bAdd.gridy = 1;
				pCtas.add(bAdd, gbc_bAdd);

				bRem = new JButton("<- Quitar");
				final GridBagConstraints gbc_bRem = new GridBagConstraints();
				gbc_bRem.insets = new Insets(0, 0, 5, 5);
				gbc_bRem.gridx = 2;
				gbc_bRem.gridy = 3;
				pCtas.add(bRem, gbc_bRem);

				final JPanel pUser = new JPanel();
				splitPane_1.setLeftComponent(pUser);
				pUser.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Usuario y Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				final GridBagLayout gbl_pUser = new GridBagLayout();
				gbl_pUser.columnWidths = new int[]{70, 114};
				gbl_pUser.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
				gbl_pUser.columnWeights = new double[]{0.0, 1.0};
				gbl_pUser.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				pUser.setLayout(gbl_pUser);

				final JLabel lUserName = new JLabel("Usuario");
				final GridBagConstraints gbc_lUserName = new GridBagConstraints();
				gbc_lUserName.anchor = GridBagConstraints.EAST;
				gbc_lUserName.insets = new Insets(0, 0, 5, 5);
				gbc_lUserName.gridx = 0;
				gbc_lUserName.gridy = 0;
				pUser.add(lUserName, gbc_lUserName);
				pUser.add(tUserName, gbc_tUserName);

				final JLabel lClientNumber = new JLabel("Cliente Web");
				final GridBagConstraints gbc_lClientNumber = new GridBagConstraints();
				gbc_lClientNumber.insets = new Insets(0, 0, 5, 5);
				gbc_lClientNumber.anchor = GridBagConstraints.EAST;
				gbc_lClientNumber.gridx = 0;
				gbc_lClientNumber.gridy = 1;
				pUser.add(lClientNumber, gbc_lClientNumber);

				tClientNumber = new JTextField();
				tClientNumber.setEditable(false);
				final GridBagConstraints gbc_tClientNumber = new GridBagConstraints();
				gbc_tClientNumber.fill = GridBagConstraints.HORIZONTAL;
				gbc_tClientNumber.insets = new Insets(0, 0, 5, 0);
				gbc_tClientNumber.anchor = GridBagConstraints.NORTH;
				gbc_tClientNumber.gridx = 1;
				gbc_tClientNumber.gridy = 1;
				pUser.add(tClientNumber, gbc_tClientNumber);
				tClientNumber.setColumns(10);

				final JLabel lName = new JLabel("Nombre");
				final GridBagConstraints gbc_lName = new GridBagConstraints();
				gbc_lName.insets = new Insets(0, 0, 5, 5);
				gbc_lName.anchor = GridBagConstraints.EAST;
				gbc_lName.gridx = 0;
				gbc_lName.gridy = 2;
				pUser.add(lName, gbc_lName);
				pUser.add(tName, gbc_rName);

				final JLabel lLastname = new JLabel("Apellido");
				final GridBagConstraints gbc_lLastname = new GridBagConstraints();
				gbc_lLastname.insets = new Insets(0, 0, 5, 5);
				gbc_lLastname.anchor = GridBagConstraints.EAST;
				gbc_lLastname.gridx = 0;
				gbc_lLastname.gridy = 3;
				pUser.add(lLastname, gbc_lLastname);
				pUser.add(tLastname, gbc_tLastname);

				final JLabel lEmail = new JLabel("Email");
				final GridBagConstraints gbc_lEmail = new GridBagConstraints();
				gbc_lEmail.insets = new Insets(0, 0, 5, 5);
				gbc_lEmail.anchor = GridBagConstraints.EAST;
				gbc_lEmail.gridx = 0;
				gbc_lEmail.gridy = 4;
				pUser.add(lEmail, gbc_lEmail);
				pUser.add(tEmail, gbc_tEmail);

				bResetPass = new JButton("ResetPass");
				final GridBagConstraints gbc_bResetPass = new GridBagConstraints();
				gbc_bResetPass.gridx = 1;
				gbc_bResetPass.gridy = 5;
				pUser.add(bResetPass, gbc_bResetPass);

		final JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		bAddUser = new JButton("Agregar Usuario");
		toolBar.add(bAddUser);

		bClone = new JButton("Copiar Usuario");
		toolBar.add(bClone);

		bDelUser = new JButton("Borrar Usuario");
		toolBar.add(bDelUser);

		final JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);

		bRefresh = new JButton("Load...");
		toolBar.add(bRefresh);
		init();
	}

	private void init() {
		modelAllData = new DefaultTableModel() {
		    /**
			 *
			 */
			private static final long	serialVersionUID	= 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		table.setModel(modelAllData);
		table.setSelectionModel(new DefaultListSelectionModel(){
			/**
			 *
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void setSelectionInterval(final int index0, final int index1) {
				if (table.getSelectedRow() != index0) {
					super.setSelectionInterval(index0, index0);
					final Rectangle rect = table.getCellRect(index0, index0, true);
					table.scrollRectToVisible(rect);
					repaint();
//					int row = table.getSelectedRow();
					int row = index0;
					if ( row >= 0 ) {
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1);
						try {
							loadClient(uname);
						} catch (FaultType_Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});


		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if ( row >= 0 ) {
//						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToView(row), 1);
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1);
						modeAdd = false;
						try {
							loadClient(uname);
						} catch (FaultType_Exception e1) {
							e1.printStackTrace();
						}
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] {"Usuario", "Cliente Web"});
		final TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(modelAllData);
		table.setRowSorter(rowSorter);
		for (int y = 0; y < modelAllData.getColumnCount(); y++){
			comboBox.addItem(modelAllData.getColumnName(y));
		}
		mmodel = new SortedComboBoxModel();
		lCtas.setModel(mmodel);
		mmodelTodas = new DefaultTableModel() {
		    /**
			 *
			 */
			private static final long	serialVersionUID	= 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
		        return false;
		    }
		};
		mmodelTodas.setColumnIdentifiers(new String[] {"Cuenta"});
		tableCtasTodasAdd.setModel(mmodelTodas);
		final TableRowSorter<TableModel> rowSorterModelTodas = new TableRowSorter<TableModel>(mmodelTodas);
		tableCtasTodasAdd.setRowSorter(rowSorterModelTodas);
		tableCtasTodasAdd.setSelectionModel(new DefaultListSelectionModel(){
			/**
			 *
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void setSelectionInterval(final int index0, final int index1) {
//				if (table.getSelectedRow() != index0) {
					super.setSelectionInterval(index0, index0);
//				}
			}
		});
		tglbtnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnFiltro.isSelected()) {
					tglbtnFiltro.setText("Desactivar Filtro:");
//					Pattern p = Pattern.compile(textField.getText(), Pattern.CASE_INSENSITIVE);
					Pattern p = Pattern.compile(textField.getText(), Pattern.UNICODE_CASE);
					rowSorterModelTodas.setRowFilter(RowFilter.regexFilter("(?i)" + p.toString(), 0));
				} else {
					tglbtnFiltro.setText("Activar Filtro:");
					rowSorterModelTodas.setRowFilter(RowFilter.regexFilter("", 0));
				}
			}
		});
		ctasTodas = new HashMap<String, AdminCuentaType>();
		bResetPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					resetPassword();
				} catch (FaultType_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				int idx = tableCtasTodasAdd.getSelectedIndex();
				final int idx = tableCtasTodasAdd.getSelectedRow();
				if (idx>=0) {
//					mmodel.addElement(mmodelTodas.getElementAt(idx));
//					mmodelTodas.removeElement(mmodelTodas.getElementAt(idx));
					final int idxMdl = tableCtasTodasAdd.convertRowIndexToModel(idx);
					mmodel.addElement(mmodelTodas.getValueAt(idxMdl, 0));
					mmodelTodas.removeRow(idxMdl);
				}
			}
		});
		bRem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = lCtas.getSelectedIndex();
				if (idx>=0) {
//					mmodelTodas.addElement(mmodel.getElementAt(idx));
//					mmodel.removeElement(mmodel.getElementAt(idx));
					mmodelTodas.addRow( new Object[] {mmodel.getElementAt(idx)});
					mmodel.removeElement(mmodel.getElementAt(idx));
				}
			}
		});
		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					switchPanel(false);
					updateClient(tClientNumber.getText());
				} catch (FaultType_Exception e1) {
					createMsgWarning("Ocurrio un error al guardar la informacion." + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		bCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadClient(tClientNumber.getText());
					switchPanel(false);
				} catch (FaultType_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bRefresh.setText("Refresh");
					loadAllData();
					loadClient("");
				} catch (FaultType_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addUser();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delUser();
				} catch (FaultType_Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bClone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cloneUser();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bEraseFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rowSorter.setRowFilter(RowFilter.regexFilter("", comboBox.getSelectedIndex()));
				filterField.setText("");
			}
		});
		bFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rowSorter.setRowFilter(RowFilter.regexFilter(filterField.getText(), comboBox.getSelectedIndex()));
			}
		});
		jdDialog = new JDialog();
		jdDialog.setTitle("Edicion De Fichero");
		jdDialog.setLocationRelativeTo(null);
		jdDialog.setModal(true);
		jdDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		jdDialog.setSize(600, 400);
	}

	/**
	 * Muestra un msg de error.
	 * @param msg
	 */
	private static void createMsgWarning(String msg) {
		JOptionPane.showMessageDialog(
			    new JFrame(),
			    "<html><body><p style='width: 200px;'>"+msg+"</p></body></html>",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

    private void switchPanel(boolean b) {
    	if (b) {
    		jdDialog.setContentPane(panel);
    		jdDialog.setVisible(true);
    	} else {
    		jdDialog.setVisible(false);
    		splitPane.setRightComponent(panel);
    	}
	}
	private void cloneUser() throws FaultType_Exception {
		String us = tClientNumber.getText();
		if (us==null||us.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				us = (String) table.getModel().getValueAt(row, 1);
			}
		}
		if (us!=null&&!us.equals("")) {
			String u = ProxyPort.getInstance().cloneUsuario(us);
			loadAllData();
			ClienteCompletoType ud = ProxyPort.getInstance().obtClienteCompleto(u);
			loadClient(ud.getId());
			JOptionPane.showConfirmDialog (null, "Se clono el cliente " + ud.getId() + " llamado " + ud.getUsername() + ".\n Recuerde cambiar los datos pertinentes y resetear el password.");
		}
	}
	/**
	 *
	 * @throws FaultType_Exception
	 */
	private void addUser() throws FaultType_Exception {
		modeAdd = true;
		tClientNumber.setText("");
		tUserName.setText("");
		tName.setText("");
		tLastname.setText("");
		tEmail.setText("");
		switchPanel(true);
	}

	private void resetPassword() throws FaultType_Exception {
		String us = tClientNumber.getText();
		if (us==null||us.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				us = (String) table.getModel().getValueAt(row, 1);
			}
		}
		if (us!=null&&!us.equals("")) {
				ClienteCompletoType ud = ProxyPort.getInstance().obtClienteCompleto(us);
				int option = JOptionPane.showConfirmDialog (null, "Desea resetear el password del cliente " + ud.getId() + " llamado " + ud.getUsername() + "?");
				if (option == JOptionPane.YES_OPTION ) {
					String newPass = ProxyPort.getInstance().resetPassCliente(us);
					JOptionPane.showConfirmDialog (null, "El password nuevo es: " + newPass);
				} else if (option == JOptionPane.NO_OPTION) {
				} else {
				}
		}
	}

	private void delUser() throws FaultType_Exception {
		String us = tClientNumber.getText();
		if (us==null||us.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				us = (String) table.getModel().getValueAt(row, 1);
			}
		}
		if (us!=null&&!us.equals("")) {
			loadClient(us);
			int option = JOptionPane.showConfirmDialog (null, "Desea eliminar el cliente " + us + " llamado " + tUserName.getText() + "?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().delCliente(us);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadAllData();
			loadClient("");
		}
	}
	private void loadAllData() throws FaultType_Exception {
		table.setModel(new DefaultTableModel());
		tableCtasTodasAdd.setModel(new DefaultTableModel());
//		tableCtasTodasAdd.setModel(new DefaultComboBoxModel());
		NewsApplication.startGP();
		new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					for (int row = 0; row < modelAllData.getRowCount();) {
						modelAllData.removeRow(row);
					}
					for (int row = 0; row < mmodelTodas.getRowCount();) {
//						for (int row = 0; row < mmodelTodas.getSize();) {
//						mmodelTodas.removeElementAt(row);
						mmodelTodas.removeRow(row);
					}
					ctasTodas.clear();
					ArrayOfAdminClienteType ci;
					ci = ProxyPort.getInstance().obtClientes("");
					ArrayOfAdminCuentaType ac;
					ac = ProxyPort.getInstance().obtCuentas("");
					for (AdminClienteType dds : ci.getClientes()) {
						modelAllData.addRow(new String[] { dds.getUsername(), dds.getId() });
					}
					for (AdminCuentaType cta : ac.getCuentas()) {
//						mmodelTodas.addElement(cta.getDesc() + " (" + cta.getNro() + ")");
						mmodelTodas.addRow( new String[] { cta.getDesc() + " (" + cta.getNro() + ")" }) ;
						ctasTodas.put(cta.getDesc() + " (" + cta.getNro() + ")", cta);
					}
				} catch (FaultType_Exception e) {
					e.printStackTrace();
				} finally {
					table.setModel(modelAllData);
					tableCtasTodasAdd.setModel(mmodelTodas);
					final TableColumn col = tableCtasTodasAdd.getColumnModel().getColumn(0);
					for (int i = 0; i < tableCtasTodasAdd.getColumnCount(); i++) {
						final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
							private static final long	serialVersionUID	= 1L;
							@Override
							public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
								super.setToolTipText((String) value);
								return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							}
						};
						col.setCellRenderer(renderer);
					}
					NewsApplication.stopGP();
					for (int row = 0; row < modelAllData.getRowCount(); row++) {
						if (tClientNumber.getText().equals(modelAllData.getValueAt(row, 1))) {
							table.getSelectionModel().setSelectionInterval(table.convertRowIndexToView(row), table.convertRowIndexToView(row));
						}
					}
				}
			}
		}).start();
	}
	private void updateClient(String clientid) throws FaultType_Exception {
		//validacion
		if (tUserName.getText().equals("") ||tName.getText().equals("") ||tLastname.getText().equals("") ||tEmail.getText().equals("") ) {
			String campo = "Falta el Campo: "
					+ (tUserName.getText().equals("") ? "Usuario" : (tName.getText().equals("") ? "Nombre" : (tLastname.getText().equals("") ? "Apellido" : (tEmail.getText()
							.equals("") ? "Email" : "Desconocido"))));
			JOptionPane.showConfirmDialog (null, "Todos los campos son obligatorios!!!\n"+campo);
		} else {
			if (modeAdd) {
				String cod = ProxyPort.getInstance().addUsuario(tClientNumber.getText(), tUserName.getText(), tName.getText(), tLastname.getText(), tEmail.getText());
				if (cod!=null&&!cod.equals("")) {
					tClientNumber.setText(cod);
					String newPass = "new"+cod;

					ArrayOfAdminCuentaType ac = new ArrayOfAdminCuentaType();
					for ( int i = 0; i < mmodel.getSize(); i++) {
						AdminCuentaType cta = ctasTodas.get(mmodel.getElementAt(i));
						ac.getCuentas().add(cta);
					}
					ProxyPort.getInstance().modCliente(cod, tUserName.getText(), tName.getText(), tLastname.getText(), tEmail.getText(), ac);
					loadAllData();
					loadClient(cod);
					JOptionPane.showMessageDialog(null, "El password nuevo es: " + newPass);
				}
			} else {
				if (!clientid.equals("")) {
					ArrayOfAdminCuentaType ac = new ArrayOfAdminCuentaType();
					for ( int i = 0; i < mmodel.getSize(); i++) {
						AdminCuentaType cta = ctasTodas.get(mmodel.getElementAt(i));
						ac.getCuentas().add(cta);
					}
					ProxyPort.getInstance().modCliente(tClientNumber.getText(), tUserName.getText(), tName.getText(), tLastname.getText(), tEmail.getText(), ac);
				}
			}
			modeAdd = false;
		}
	}

	/**
	 *
	 * @param clietid
	 * @throws FaultType_Exception
	 */
	private void loadClient(String clietid) throws FaultType_Exception {
		for (int i = 0; i < mmodel.getSize();) {
			mmodelTodas.addRow(new String[] { (String) mmodel.getElementAt(i) });
			mmodel.removeElement(mmodel.getElementAt(i));
		}
		if (!clietid.equals("")) {
			ClienteCompletoType u = ProxyPort.getInstance().obtClienteCompleto(clietid);
			tClientNumber.setText(setText(u.getId()));
			tUserName.setText(setText(u.getUsername()));
			tName.setText(setText(u.getName()));
			tLastname.setText(setText(u.getLastName()));
			tEmail.setText(setText(u.getEmail()));
			if (u.getCuentas() != null) {
				for (AdminCuentaType cta : u.getCuentas().getCuentas()) {
					for (int i = 0; i < mmodelTodas.getRowCount(); i++) {
						if (((String) mmodelTodas.getValueAt(i, 0))
								.equalsIgnoreCase(cta.getDesc() + " (" + cta.getNro() + ")")) {
							mmodelTodas.removeRow(i);
							break;
						}
					}
					mmodel.addElement(cta.getDesc() + " (" + cta.getNro() + ")");
				}
			}
		} else {
			tClientNumber.setText("");
			tUserName.setText("");
			tName.setText("");
			tLastname.setText("");
			tEmail.setText("");
		}
	}

	private static  String setText(String text) {
		if (text == null)
			return "";
		else return text;
	}
}

class SortedComboBoxModel extends DefaultComboBoxModel {
	Vector<Object> items;
	private static final long	serialVersionUID	= 1L;
	private Comparator<Object> comparator;
	/*
	 *  Static method is required to make sure the data is in sorted order
	 *  before it is added to the model
	 */
	protected static Vector<?> sortVector(Vector<?> items, Comparator<Object> comparator)
	{
		Collections.sort(items, comparator);
		return items;
	}

	/*
	 *  Static method is required to make sure the data is in sorted order
	 *  before it is added to the model
	 */
	protected static Object[] sortArray(Object[] items, Comparator<Object> comparator)
	{
		Arrays.sort(items, comparator);
		return items;
	}

	/*
	 *  Create an empty model that will use the natural sort order of the item
	 */
	public SortedComboBoxModel()
	{
		super();
	}

	/*
	 *  Create an empty model that will use the specified Comparator
	 */
	public SortedComboBoxModel(Comparator<Object> comparator)
	{
		super();
		this.comparator = comparator;
	}

	/*
	 *	Create a model with data and use the nature sort order of the items
	 */
	public SortedComboBoxModel(Object[] items)
	{
		super( sortArray(items, null) );
	}

	/*
	 *  Create a model with data and use the specified Comparator
	 */
	public SortedComboBoxModel(Object[] items, Comparator<Object> comparator)
	{
		super( sortArray(items, comparator) );
		this.comparator = comparator;
	}

	/*
	 *	Create a model with data and use the nature sort order of the items
	 */
	public SortedComboBoxModel(Vector<?> items)
	{
		this( items, null );
	}

	/*
	 *  Create a model with data and use the specified Comparator
	 */

	public SortedComboBoxModel(Vector<?> items, Comparator<Object> comparator)
	{
		super( sortVector(items, comparator) );
		this.comparator = comparator;
	}

	@Override
	public void addElement(Object element)
	{
		insertElementAt(element, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertElementAt(Object element, int index)
	{
		int size = getSize();

		//  Determine where to insert element to keep model in sorted order

		for (index = 0; index < size; index++)
		{
			if (comparator != null)
			{
				Object o = getElementAt( index );

				if (comparator.compare(o, element) > 0)
					break;
			}
			else
			{
				Comparable<Object> c = (Comparable<Object>)getElementAt( index );

				if (c.compareTo(element) > 0)
					break;
			}
		}

		super.insertElementAt(element, index);
	}
}

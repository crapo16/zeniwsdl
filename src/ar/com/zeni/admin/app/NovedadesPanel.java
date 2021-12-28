package ar.com.zeni.admin.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.zeni.admin.app.components.JLimitedTextArea;
import ar.com.zeni.admin.app.components.JLimitedTextField;
import ar.com.zeni.admin.app.components.calendar.JDateChooser;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.zeniadminwsdl.ArrayOfNovedadType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.NovedadType;
import ar.com.zeni.zeniwsdl.FechaTimeType;

public class NovedadesPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField tTitle;
	private final JTable table;
	private final JButton bCan;
	private final JButton bAcc;
	private final JButton bRefresh;
	private final JButton bDelHelp;
	private final JButton bClone;
	private final JButton bAddHelp;
	private DefaultTableModel modelAllData;
	private JTextArea tDescription;
	private JDateChooser tDate;
	private HashMap<String, NovedadType> ayTodas;
	private String ididid = "";
	private JDialog jdDialog;
	private JPanel panel;
	private JSplitPane splitPane;

	private boolean modeAdd;

	/**
	 * Create the panel.
	 */
	public NovedadesPanel() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);

		final JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edicion", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		splitPane.setRightComponent(panel);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(panel_2, BorderLayout.WEST);

		bAcc = new JButton("Aceptar Cambios");
		bCan = new JButton("Cancelar Cambios");

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		final JSeparator separator = new JSeparator();
		panel_1.add(separator);
		panel_1.add(bAcc);
		panel_1.add(bCan);

		panel.add(panel_1, BorderLayout.SOUTH);

		final JPanel pUser = new JPanel();
		panel.add(pUser, BorderLayout.CENTER);

		final GridBagLayout gbl_pUser = new GridBagLayout();
		gbl_pUser.columnWidths = new int[] { 70, 114, 0 };
		gbl_pUser.rowHeights = new int[] { 0, 30, 0, 0, 0, 0, 0 };
		gbl_pUser.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_pUser.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		pUser.setLayout(gbl_pUser);

		final JLabel lQuestion = new JLabel("Titulo");
		final GridBagConstraints gbc_lQuestion = new GridBagConstraints();
		gbc_lQuestion.anchor = GridBagConstraints.EAST;
		gbc_lQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_lQuestion.gridx = 0;
		gbc_lQuestion.gridy = 0;
		pUser.add(lQuestion, gbc_lQuestion);

		tTitle = new JLimitedTextField(200);
		final GridBagConstraints gbc_tTitle = new GridBagConstraints();
		gbc_tTitle.insets = new Insets(0, 0, 5, 0);
		gbc_tTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tTitle.gridx = 1;
		gbc_tTitle.gridy = 0;
		pUser.add(tTitle, gbc_tTitle);
		tTitle.setColumns(10);

		final JLabel lOrdin = new JLabel("Fecha");
		final GridBagConstraints gbc_lOrdin = new GridBagConstraints();
		gbc_lOrdin.insets = new Insets(0, 0, 0, 5);
		gbc_lOrdin.anchor = GridBagConstraints.EAST;
		gbc_lOrdin.gridx = 0;
		gbc_lOrdin.gridy = 1;
		pUser.add(lOrdin, gbc_lOrdin);

		tDate = new JDateChooser();
		final GridBagConstraints gbc_tDate = new GridBagConstraints();
		gbc_tDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tDate.gridx = 1;
		gbc_tDate.gridy = 1;
		pUser.add(tDate, gbc_tDate);

		final JLabel lAnsw = new JLabel("Descripcion");
		final GridBagConstraints gbc_lAnsw = new GridBagConstraints();
		gbc_lAnsw.insets = new Insets(0, 0, 5, 5);
		gbc_lAnsw.anchor = GridBagConstraints.EAST;
		gbc_lAnsw.gridx = 0;
		gbc_lAnsw.gridy = 2;
		pUser.add(lAnsw, gbc_lAnsw);

		final JScrollPane scrollPane_1 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 2;
		pUser.add(scrollPane_1, gbc_scrollPane_1);

		tDescription = new JLimitedTextArea(400);
		scrollPane_1.setViewportView(tDescription);

		// Agregando ToolBar.
		final JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);

		bAddHelp = new JButton("Agregar Novedad");
		toolBar.add(bAddHelp);

		bClone = new JButton("Copiar Novedad");
		toolBar.add(bClone);

		bDelHelp = new JButton("Borrar Novedad");
		toolBar.add(bDelHelp);

		final JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);

		bRefresh = new JButton("Cargar...");
		toolBar.add(bRefresh);

		init();
	}

	private void init() {
		ayTodas = new HashMap<String, NovedadType>();
		bAddHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addNovedad();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bClone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cloneNovedad("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delNovedad("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bRefresh.setText("Refresh");
					loadAllNovedades("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateNovedad(ididid);
					switchPanel(false);
				} catch (FaultType_Exception e1) {
					createMsgWarning("Ocurrio un error al guardar la informacion." + e1.getMessage());
					e1.printStackTrace();
				} catch (Exception e1) {
					createMsgWarning(e1.getMessage());
				}

			}
		});
		bCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(false);
				loadNovedad(ididid);
			}
		});
		modelAllData = new DefaultTableModel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table.setModel(modelAllData);
		table.setAutoCreateRowSorter(true);
		table.setSelectionModel(new DefaultListSelectionModel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void setSelectionInterval(final int index0, final int index1) {
				if (table.getSelectedRow() != index0) {
					super.setSelectionInterval(index0, index0);
					final Rectangle rect = table.getCellRect(index0, index0, true);
					table.scrollRectToVisible(rect);
					repaint();
					int row = table.getSelectedRow();
					if (row >= 0) {
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
						loadNovedad(uname);
						switchPanel(false);
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if (row >= 0) {
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
						loadNovedad(uname);
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] { "Id", "Fecha", "Titulo", "Descripcion" });
		table.removeColumn(table.getColumnModel().getColumn(0));

		jdDialog = new JDialog();
		jdDialog.setTitle("Cargar Novedad");
		jdDialog.setLocationRelativeTo(null);
		jdDialog.setModal(true);
		jdDialog.setResizable(false);
		jdDialog.setSize(600, 400);
		jdDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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

	/**
	 *
	 * @param uname
	 * @throws FaultType_Exception
	 */
	private void loadAllNovedades(String uname) throws FaultType_Exception {
		System.out.println("Cargando Todas las novedades");
		ArrayOfNovedadType aNovedad = ProxyPort.getInstance().obtNovedad("");

		for (int row = 0; row < modelAllData.getRowCount();) {
			modelAllData.removeRow(row);
		}
		ayTodas.clear();

		if (aNovedad != null && aNovedad.getNovedades() != null) {

			for (NovedadType a : aNovedad.getNovedades()) {
				modelAllData.addRow(new String[] { a.getId(), DateUtil.ToString.yyyyMMdd(a.getDate().getFecha()),
						a.getTitle(), a.getDescription() });
				ayTodas.put(a.getId(), a);
			}
		}
		ididid = null;
		tDescription.setText("");
		tTitle.setText("");
		tDate.setDate(null);
	}

	private void addNovedad() throws FaultType_Exception {
		modeAdd = true;
		tDescription.setText("");
		tTitle.setText("");
		tDate.setDate(null);
		switchPanel(true);
	}

	private NovedadType loadNovedad(String helpid) {
		modeAdd = false;
		ididid = helpid;

		if (helpid == null || helpid.equals("")) {
			int row = table.getSelectedRow();
			if (row >= 0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}

		if (ididid != null && !ididid.equals("") && ayTodas.get(ididid) != null) {
			NovedadType hh = ayTodas.get(ididid);
			FechaTimeType d = new FechaTimeType();
			d.setFecha(hh.getDate().getFecha());
			tDescription.setText(hh.getDescription());
			tTitle.setText(hh.getTitle());
			tDate.setDate(DateUtil.Converters.FechaTimeTypeToDate(d));
			return hh;
		} else {
			ididid = null;
			tDescription.setText("");
			tTitle.setText("");
			tDate.setDate(null);
			return null;
		}
	}

	private void cloneNovedad(String idNovedad) throws FaultType_Exception {
		ididid = idNovedad;
		if (idNovedad == null || idNovedad.equals("")) {
			int row = table.getSelectedRow();
			if (row >= 0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid != null && !ididid.equals("")) {
			NovedadType helpi = loadNovedad(ididid);
			int option = JOptionPane.showConfirmDialog(null, "Desea clonar la novedad:  " + tTitle.getText() + " ?");

			if (option == JOptionPane.YES_OPTION) {
				ProxyPort.getInstance().addNovedad(helpi);
				JOptionPane.showMessageDialog(null, "Novedad Clonada ");
			}

			loadAllNovedades(ididid);
			loadNovedad(ididid);
		}
	}

	// ELimina la ayuda
	private void delNovedad(String idNovedad) throws FaultType_Exception {
		ididid = idNovedad;
		if (idNovedad == null || idNovedad.equals("")) {
			int row = table.getSelectedRow();
			if (row >= 0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid != null && !ididid.equals("")) {
			loadNovedad(ididid);
			int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la ayuda " + tTitle.getText() + " ?");
			if (option == JOptionPane.YES_OPTION) {
				ProxyPort.getInstance().delNovedad(ididid);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadAllNovedades(ididid);
			loadNovedad("");
		}
	}

	// TODO VER
	private void updateNovedad(String helpid) throws FaultType_Exception, Exception {
		ididid = helpid;

		System.out.println("updateNovedad ididid{"+ididid+"} helpid{"+helpid+"}");
		if (modeAdd) {

			validarData();

			NovedadType novedad = new NovedadType();
			novedad.setId(ididid);

			ar.com.zeni.zeniadminwsdl.FechaTimeType d = new ar.com.zeni.zeniadminwsdl.FechaTimeType();
			d.setFecha(DateUtil.Converters.DateToFechaTimeType(tDate.getDate()).getFecha());

			novedad.setDate(d);
			novedad.setTitle(tTitle.getText());
			novedad.setDescription(tDescription.getText());
			System.out.println("Guardando ayuda");

			String dd = ProxyPort.getInstance().addNovedad(novedad);
			loadAllNovedades(dd);
			loadNovedad(dd);
		} else {

			if (helpid == null || helpid.equals("")) {
				int row = table.getSelectedRow();
				if (row >= 0) {
					ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
				}
			}

			if (ididid != null && !ididid.equals("") && ayTodas.get(ididid) != null) {
				System.out.println("Viene a validar data");
				validarData();

				NovedadType novedad = new NovedadType();
				novedad.setId(ididid);
				ar.com.zeni.zeniadminwsdl.FechaTimeType d = new ar.com.zeni.zeniadminwsdl.FechaTimeType();
				d.setFecha(DateUtil.Converters.DateToFechaTimeType(tDate.getDate()).getFecha());

				novedad.setDate(d);
				novedad.setTitle(tTitle.getText());
				novedad.setDescription(tDescription.getText());

				System.out.println("Viene a guardar el registro");
				ProxyPort.getInstance().modNovedad(novedad);

				loadAllNovedades(ididid);
				loadNovedad(ididid);
			} else {
				ididid = null;
				tDescription.setText("");
				tTitle.setText("");
				tDate.setDate(null);
				loadAllNovedades("");
				loadNovedad("");
			}
		}
	}

	/**
	 *
	 * @throws Exception
	 */
	private void validarData() throws Exception{

		if (tDate  == null || tDate.getDate() ==null)
			throw new Exception("Por favor seleccione una Fecha.");

		if (tTitle  == null || tTitle.getText() ==null || "".equals(tTitle.getText().trim()))
			throw new Exception("Por favor Ingrese un Titulo.");

		if (tDescription == null || tDescription.getText() ==null || "".equals(tDescription.getText().trim()))
			throw new Exception("Por favor Ingrese una Descripcion.");


	}

	private static void createMsgWarning(String msg) {
		JOptionPane.showMessageDialog(new JFrame(),
				"<html><body><p style='width: 200px;'>" + msg + "</p></body></html>", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}

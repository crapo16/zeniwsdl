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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.zeni.admin.app.components.JLimitedTextArea;
import ar.com.zeni.admin.app.components.JLimitedTextField;
import ar.com.zeni.zeniadminwsdl.ArrayOfAyudaType;
import ar.com.zeni.zeniadminwsdl.AyudaType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;

public class HelpPanel extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final JTextField tQuest;
	private final JTable table;
	private final JButton bCan;
	private final JButton bAcc;
	private final JButton	bRefresh;
	private final JButton	bDelHelp;
	private final JButton	bClone;
	private final JButton	bAddHelp;
	private DefaultTableModel	modelAllData;
	private JTextArea	tAnsw;
	private JSpinner	sOrdin;
	private HashMap<String, AyudaType>	ayTodas;
	private String	ididid = "";
	private JDialog	jdDialog;
	private JPanel	panel;
	private JSplitPane	splitPane;
	private boolean	modeAdd;

	/**
	 * Create the panel.
	 */
	public HelpPanel() {
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edicion De Ayuda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		final JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		bAcc = new JButton("Aceptar Cambios");
		
		bCan = new JButton("Cancelar Cambios");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JSeparator separator = new JSeparator();
		panel_1.add(separator);
		panel_1.add(bAcc);
		panel_1.add(bCan);
		
		final JPanel pUser = new JPanel();
		panel.add(pUser, BorderLayout.CENTER);
		final GridBagLayout gbl_pUser = new GridBagLayout();
		gbl_pUser.columnWidths = new int[]{70, 114, 0};
		gbl_pUser.rowHeights = new int[]{0, 30, 0, 0, 0, 0, 0};
		gbl_pUser.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pUser.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		pUser.setLayout(gbl_pUser);
		
		final JLabel lQuestion = new JLabel("Pregunta");
		final GridBagConstraints gbc_lQuestion = new GridBagConstraints();
		gbc_lQuestion.anchor = GridBagConstraints.EAST;
		gbc_lQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_lQuestion.gridx = 0;
		gbc_lQuestion.gridy = 0;
		pUser.add(lQuestion, gbc_lQuestion);
		
		tQuest = new JLimitedTextField(200);
		final GridBagConstraints gbc_tQuest = new GridBagConstraints();
		gbc_tQuest.insets = new Insets(0, 0, 5, 0);
		gbc_tQuest.fill = GridBagConstraints.HORIZONTAL;
		gbc_tQuest.gridx = 1;
		gbc_tQuest.gridy = 0;
		pUser.add(tQuest, gbc_tQuest);
		tQuest.setColumns(10);
		
		final JLabel lAnsw = new JLabel("Respuesta");
		final GridBagConstraints gbc_lAnsw = new GridBagConstraints();
		gbc_lAnsw.insets = new Insets(0, 0, 5, 5);
		gbc_lAnsw.anchor = GridBagConstraints.EAST;
		gbc_lAnsw.gridx = 0;
		gbc_lAnsw.gridy = 1;
		pUser.add(lAnsw, gbc_lAnsw);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		pUser.add(scrollPane_1, gbc_scrollPane_1);
		
		tAnsw = new JLimitedTextArea(400);
		scrollPane_1.setViewportView(tAnsw);
		
		final JLabel lOrdin = new JLabel("Orden");
		final GridBagConstraints gbc_lOrdin = new GridBagConstraints();
		gbc_lOrdin.insets = new Insets(0, 0, 0, 5);
		gbc_lOrdin.anchor = GridBagConstraints.EAST;
		gbc_lOrdin.gridx = 0;
		gbc_lOrdin.gridy = 5;
		pUser.add(lOrdin, gbc_lOrdin);
		
		sOrdin = new JSpinner();
		final GridBagConstraints gbc_sOrdin = new GridBagConstraints();
		gbc_sOrdin.fill = GridBagConstraints.HORIZONTAL;
		gbc_sOrdin.gridx = 1;
		gbc_sOrdin.gridy = 5;
		pUser.add(sOrdin, gbc_sOrdin);
		
		final JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		bAddHelp = new JButton("Agregar Ayuda");
		toolBar.add(bAddHelp);
		
		bClone = new JButton("Copiar Ayuda");
		toolBar.add(bClone);
		
		bDelHelp = new JButton("Borrar Ayuda");
		toolBar.add(bDelHelp);
		
		final JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		bRefresh = new JButton("Load...");
		toolBar.add(bRefresh);

		init();
	}

	private void init() {
		ayTodas = new HashMap<String, AyudaType>();
		bAddHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addHelp();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bClone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cloneHelp("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delHelp("");
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
					loadHelps("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateHelp("");
					switchPanel(false);
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(false);
				loadHelp(ididid);
			}
		});
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
		table.setAutoCreateRowSorter(true);
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
					int row = table.getSelectedRow();
					if ( row >= 0 ) {
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
						loadHelp(uname);
						switchPanel(false);
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if ( row >= 0 ) {
						String uname = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
						loadHelp(uname);
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] { "Id", "Orden", "Pregunta", "Respuesta"});
		table.removeColumn(table.getColumnModel().getColumn(0));
//		try {
//			loadHelps("");
//		} catch (FaultType_Exception e1) {
//			e1.printStackTrace();
//		}
		jdDialog = new JDialog();
		jdDialog.setTitle("Edicion De Ayudas");
		jdDialog.setLocationRelativeTo(null);
		jdDialog.setModal(true);
		jdDialog.setSize(600, 400);
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
	private void loadHelps(String uname) throws FaultType_Exception {
		ArrayOfAyudaType ay = ProxyPort.getInstance().obtAyuda("");
		for (int row = 0; row < modelAllData.getRowCount(); ){
			modelAllData.removeRow(row);
		}
		ayTodas.clear();
		for ( AyudaType a : ay.getAyus() ){
		    modelAllData.addRow(new String[] {a.getId(), a.getOrden()+"", a.getPregunta(), a.getRespuesta()});
		    ayTodas.put(a.getId(), a);
		}
		ididid = null;
		tAnsw.setText("");
		tQuest.setText("");
		sOrdin.setValue(0);
	}

	private void addHelp() throws FaultType_Exception {
		modeAdd = true;
		tAnsw.setText("");
		tQuest.setText("");
		sOrdin.setValue(0);
		switchPanel(true);
	}

	private AyudaType loadHelp(String helpid) {
		modeAdd = false;
		ididid = helpid;
		if (helpid==null||helpid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
			AyudaType hh = ayTodas.get(ididid);
			tAnsw.setText(hh.getRespuesta());
			tQuest.setText(hh.getPregunta());
			sOrdin.setValue(hh.getOrden());
//			for (int row = 0; row < modelAllData.getRowCount(); row++){
//				if (ididid.equals(modelAllData.getValueAt(row, 0))){
//					table.setRowSelectionInterval(table.convertRowIndexToView(row), table.convertRowIndexToView(row));
//				}
//			}
			return hh;
		} else {
			ididid = null;
			tAnsw.setText("");
			tQuest.setText("");
			sOrdin.setValue(0);
			return null;
		}
	}

	private void cloneHelp(String helpid) throws FaultType_Exception {
		ididid = helpid;
		if (helpid==null||helpid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")) {
			AyudaType helpi = loadHelp(ididid);
			int option = JOptionPane.showConfirmDialog (null, "Desea clonar la ayuda " + tQuest.getText() + " ?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().addAyuda(helpi);
				option = JOptionPane.showConfirmDialog (null, "Clonada la ayuda " + tQuest.getText());
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadHelps(ididid);
			loadHelp(ididid);
		}
	}

	private void delHelp(String helpid) throws FaultType_Exception {
		ididid = helpid;
		if (helpid==null||helpid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")) {
			loadHelp(ididid);
			int option = JOptionPane.showConfirmDialog (null, "Desea eliminar la ayuda " + tQuest.getText() + " ?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().delAyuda(ididid);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadHelps(ididid);
			loadHelp("");
		}
	}

	private void updateHelp(String helpid) throws FaultType_Exception {
		ididid = helpid;
		if ( modeAdd ) { 
			AyudaType ayuda = new AyudaType();
			ayuda.setId(ididid);
			ayuda.setOrden((Integer) sOrdin.getValue());
			ayuda.setPregunta(tQuest.getText());
			ayuda.setRespuesta(tAnsw.getText());
			String dd = ProxyPort.getInstance().addAyuda(ayuda);
			loadHelps(dd);
			loadHelp(dd);
		} else {
			if (helpid==null||helpid.equals("")) {
				int row = table.getSelectedRow();
				if (row>=0) {
					ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
				}
			}
			if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
				AyudaType ayuda = new AyudaType();
				ayuda.setId(ididid);
				ayuda.setOrden((Integer) sOrdin.getValue());
				ayuda.setPregunta(tQuest.getText());
				ayuda.setRespuesta(tAnsw.getText());
				ProxyPort.getInstance().modAyuda(ayuda);
				loadHelps(ididid);
				loadHelp(ididid);
			} else {
				ididid = null;
				tAnsw.setText("");
				tQuest.setText("");
				sOrdin.setValue(0);
				loadHelps("");
				loadHelp("");
			}
		}
	}
}

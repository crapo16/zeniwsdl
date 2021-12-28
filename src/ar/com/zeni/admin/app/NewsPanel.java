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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.zeni.admin.app.components.calendar.JDateChooser;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.zeniadminwsdl.ArrayOfNewsType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.NewsType;
import ar.com.zeni.zeniwsdl.FechaTimeType;

public class NewsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final JTable table;
	private final JButton bCan;
	private final JButton bAcc;
	private final JButton	bRefresh;
	private final JButton	bDelHelp;
	private final JButton	bAddHelp;
	private DefaultTableModel	modelAllData;
	private JTextArea	tAnsw;
	private HashMap<String, NewsType>	ayTodas;
	private String	ididid = "";
	private JDateChooser tFecha;
	private boolean	modeAdd;
	private final JSplitPane	splitPane;
	private final JPanel	panel;
	private JDialog	jdDialog;

	/**
	 * Create the panel.
	 */
	public NewsPanel() {
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edicion De Noticia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		gbl_pUser.rowHeights = new int[]{30, 0, 0};
		gbl_pUser.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pUser.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		pUser.setLayout(gbl_pUser);
		
		final JLabel lAnsw = new JLabel("Noticia");
		final GridBagConstraints gbc_lAnsw = new GridBagConstraints();
		gbc_lAnsw.insets = new Insets(0, 0, 5, 5);
		gbc_lAnsw.anchor = GridBagConstraints.EAST;
		gbc_lAnsw.gridx = 0;
		gbc_lAnsw.gridy = 0;
		pUser.add(lAnsw, gbc_lAnsw);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		final GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		pUser.add(scrollPane_1, gbc_scrollPane_1);
		
		tAnsw = new JTextArea();
		scrollPane_1.setViewportView(tAnsw);
		
		final JLabel lOrdin = new JLabel("Fecha");
		final GridBagConstraints gbc_lOrdin = new GridBagConstraints();
		gbc_lOrdin.insets = new Insets(0, 0, 0, 5);
		gbc_lOrdin.anchor = GridBagConstraints.EAST;
		gbc_lOrdin.gridx = 0;
		gbc_lOrdin.gridy = 1;
		pUser.add(lOrdin, gbc_lOrdin);
		
		tFecha = new JDateChooser();
		final GridBagConstraints gbc_tFecha = new GridBagConstraints();
		gbc_tFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFecha.gridx = 1;
		gbc_tFecha.gridy = 1;
		pUser.add(tFecha, gbc_tFecha);
//		tFecha.setColumns(10);
		
		final JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		bAddHelp = new JButton("Agregar Noticia");
		toolBar.add(bAddHelp);
		
		bDelHelp = new JButton("Borrar Noricia");
		toolBar.add(bDelHelp);
		
		final JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		bRefresh = new JButton("Load...");
		toolBar.add(bRefresh);
		init();
	}

	private void init() {
		ayTodas = new HashMap<String, NewsType>();
		bAddHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addNews();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delNews("");
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
					loadNiuses("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateNews("");
					switchPanel(false);
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadNews(ididid);
				switchPanel(false);
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
						modeAdd = false;
						loadNews(uname);
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
						modeAdd = false;
						loadNews(uname);
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] { "Id", "Fecha", "Noticia"});
		table.removeColumn(table.getColumnModel().getColumn(0));
//		try {
//			loadNiuses("");
//		} catch (FaultType_Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		jdDialog = new JDialog();
		jdDialog.setTitle("Edicion De Novedades");
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

	private void loadNiuses(String uname) throws FaultType_Exception {
		table.setModel(new DefaultTableModel());
		NewsApplication.startGP();
		new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					ArrayOfNewsType ay = ProxyPort.getInstance().obtNews("");
					for (int row = 0; row < modelAllData.getRowCount(); ){
						modelAllData.removeRow(row);
					}
					ayTodas.clear();
					for ( NewsType a : ay.getNews() ){
						FechaTimeType d = new FechaTimeType();
						d.setFecha(a.getFecha().getFecha());
					    modelAllData.addRow(new String[] {a.getId(), DateUtil.ToString.yyyyMMdd(d), a.getDesc()});
					    ayTodas.put(a.getId(), a);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					table.setModel(modelAllData);
					table.removeColumn(table.getColumnModel().getColumn(0));
					NewsApplication.stopGP();	
				}
			}
		}).start();
	}

	private void addNews() throws FaultType_Exception {
		modeAdd = true;
		ididid = null;
		tAnsw.setText("");
		tFecha.setDate(null);
		switchPanel(true);
	}

	private NewsType loadNews(String nid) {
		ididid = nid;
		if (nid==null||nid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
			NewsType hh = ayTodas.get(ididid);
			FechaTimeType d = new FechaTimeType();
			d.setFecha(hh.getFecha().getFecha());
			tAnsw.setText(hh.getDesc());
			tFecha.setDate(DateUtil.Converters.FechaTimeTypeToDate(d));
//			for (int row = 0; row < modelAllData.getRowCount(); row++){
//				if (ididid.equals(modelAllData.getValueAt(row, 0))){
//					table.setRowSelectionInterval(table.convertRowIndexToView(row), table.convertRowIndexToView(row));
//				}
//			}
			return hh;
		} else {
			ididid = null;
			tAnsw.setText("");
			tFecha.setDate(null);
			return null;
		}
	}

	private void delNews(String niusuid) throws FaultType_Exception {
		ididid = niusuid;
		if (niusuid==null||niusuid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")) {
			loadNews(ididid);
			int option = JOptionPane.showConfirmDialog (null, "Desea eliminar la noticia " + tAnsw.getText() + " ?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().delNews(ididid);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadNiuses(ididid);
			loadNews("");
		}
	}

	private void updateNews(String niusid) throws FaultType_Exception {
		ididid = niusid;
		if ( modeAdd ) {
			NewsType ayuda = new NewsType();
			ayuda.setId(ididid);
			ar.com.zeni.zeniadminwsdl.FechaTimeType d = new ar.com.zeni.zeniadminwsdl.FechaTimeType();
			d.setFecha(DateUtil.Converters.DateToFechaTimeType(tFecha.getDate()).getFecha());
			ayuda.setFecha(d);
			ayuda.setDesc(tAnsw.getText());
			String dd = ProxyPort.getInstance().addNews(ayuda);
			loadNiuses(dd);
			loadNews(dd);
		} else {
			if (niusid==null||niusid.equals("")) {
				int row = table.getSelectedRow();
				if (row>=0) {
					ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
				}
			}
			if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
				NewsType a = new NewsType();
				a.setId(ididid);
				ar.com.zeni.zeniadminwsdl.FechaTimeType d = new ar.com.zeni.zeniadminwsdl.FechaTimeType();
				d.setFecha(DateUtil.Converters.DateToFechaTimeType(tFecha.getDate()).getFecha());
				a.setFecha(d);
				a.setDesc(tAnsw.getText());
				ProxyPort.getInstance().modNews(a);
				loadNiuses(ididid);
				loadNews(ididid);
			} else {
				ididid = null;
				tAnsw.setText("");
				tFecha.setDate(null);
				loadNiuses("");
				loadNews("");
			}
		}
	}
}

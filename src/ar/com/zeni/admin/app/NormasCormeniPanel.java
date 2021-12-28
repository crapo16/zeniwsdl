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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.zeniadminwsdl.ArrayOfFilesType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNormCromType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.FilesType;
import ar.com.zeni.zeniadminwsdl.NormCromType;
import ar.com.zeni.zeniwsdl.FechaTimeType;

public class NormasCormeniPanel extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private DialogSearchFile ddm;
	private final JTable table;
	private final JButton bCan;
	private final JButton bAcc;
	private final JButton	bRefresh;
	private final JButton	bDelFile;
	private final JButton	bAddFile;
	private DefaultTableModel	modelAllData;
	private final JTextField	tFirTMPS;
	private final JTextField	tFinrom;
	private final JTextField	tNormVig;
	private final JTextField	tProd;
	private String	ididid;
	private HashMap<String, NormCromType>	ayTodas;
	private JDialog jdDialog;
	private final JPanel	panel;
	private boolean	modeAdd;
	private final JSplitPane	splitPane;
	private final JButton bUplFileTMPS;
	private final JButton bUplFileNor;
	private IdDesc	dataFA;
	private IdDesc	dataFM;

	/**
	 * Create the panel.
	 */
	public NormasCormeniPanel() {
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Edicion De Archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		final JPanel pUser = new JPanel();
		panel.add(pUser, BorderLayout.NORTH);
		final GridBagLayout gbl_pUser = new GridBagLayout();
		gbl_pUser.columnWidths = new int[]{70, 0, 114, 0};
		gbl_pUser.rowHeights = new int[]{0, 30, 0, 0, 0, 0};
		gbl_pUser.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pUser.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pUser.setLayout(gbl_pUser);
		
		final JLabel lProd = new JLabel("Producto");
		final GridBagConstraints gbc_lProd = new GridBagConstraints();
		gbc_lProd.anchor = GridBagConstraints.EAST;
		gbc_lProd.insets = new Insets(0, 0, 5, 5);
		gbc_lProd.gridx = 0;
		gbc_lProd.gridy = 0;
		pUser.add(lProd, gbc_lProd);
		
		tProd = new JTextField();
		final GridBagConstraints gbc_tName = new GridBagConstraints();
		gbc_tName.gridwidth = 2;
		gbc_tName.insets = new Insets(0, 0, 5, 0);
		gbc_tName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tName.gridx = 1;
		gbc_tName.gridy = 0;
		pUser.add(tProd, gbc_tName);
		tProd.setColumns(10);
		
		final JLabel lNormVig = new JLabel("NormaVigente");
		final GridBagConstraints gbc_lNormVig = new GridBagConstraints();
		gbc_lNormVig.anchor = GridBagConstraints.EAST;
		gbc_lNormVig.insets = new Insets(0, 0, 5, 5);
		gbc_lNormVig.gridx = 0;
		gbc_lNormVig.gridy = 1;
		pUser.add(lNormVig, gbc_lNormVig);
		
		tNormVig = new JTextField();
		final GridBagConstraints gbc_tExt = new GridBagConstraints();
		gbc_tExt.gridwidth = 2;
		gbc_tExt.insets = new Insets(0, 0, 5, 0);
		gbc_tExt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tExt.gridx = 1;
		gbc_tExt.gridy = 1;
		pUser.add(tNormVig, gbc_tExt);
		tNormVig.setColumns(10);
		
		final JLabel lFiloe = new JLabel("Archivo Norma");
		final GridBagConstraints gbc_lFiloe = new GridBagConstraints();
		gbc_lFiloe.insets = new Insets(0, 0, 5, 5);
		gbc_lFiloe.anchor = GridBagConstraints.EAST;
		gbc_lFiloe.gridx = 0;
		gbc_lFiloe.gridy = 2;
		pUser.add(lFiloe, gbc_lFiloe);
		
		tFinrom = new JTextField();
		tFinrom.setEditable(false);
		final GridBagConstraints gbc_tFinrom = new GridBagConstraints();
		gbc_tFinrom.insets = new Insets(0, 0, 5, 5);
		gbc_tFinrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFinrom.gridx = 1;
		gbc_tFinrom.gridy = 2;
		pUser.add(tFinrom, gbc_tFinrom);
		tFinrom.setColumns(10);
		
		bUplFileNor = new JButton("Seleccionar Archivo");
		
		final GridBagConstraints gbc_bUplFileNor = new GridBagConstraints();
		gbc_bUplFileNor.insets = new Insets(0, 0, 5, 0);
		gbc_bUplFileNor.gridx = 2;
		gbc_bUplFileNor.gridy = 2;
		pUser.add(bUplFileNor, gbc_bUplFileNor);
		
		final JLabel lTMPS = new JLabel("Archivo TMPS");
		final GridBagConstraints gbc_lTMPS = new GridBagConstraints();
		gbc_lTMPS.insets = new Insets(0, 0, 5, 5);
		gbc_lTMPS.anchor = GridBagConstraints.EAST;
		gbc_lTMPS.gridx = 0;
		gbc_lTMPS.gridy = 3;
		pUser.add(lTMPS, gbc_lTMPS);
		
		tFirTMPS = new JTextField();
		tFirTMPS.setEditable(false);
		final GridBagConstraints gbc_tFirTMPS = new GridBagConstraints();
		gbc_tFirTMPS.insets = new Insets(0, 0, 5, 5);
		gbc_tFirTMPS.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFirTMPS.gridx = 1;
		gbc_tFirTMPS.gridy = 3;
		pUser.add(tFirTMPS, gbc_tFirTMPS);
		tFirTMPS.setColumns(10);
		
		bUplFileTMPS = new JButton("Seleccionar Archivo");
		final GridBagConstraints gbc_bUplFileTMPS = new GridBagConstraints();
		gbc_bUplFileTMPS.insets = new Insets(0, 0, 5, 0);
		gbc_bUplFileTMPS.gridx = 2;
		gbc_bUplFileTMPS.gridy = 3;
		pUser.add(bUplFileTMPS, gbc_bUplFileTMPS);

		final JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		bAddFile = new JButton("Agregar Archivo");
		toolBar.add(bAddFile);
		
		bDelFile = new JButton("Borrar Archivo");
		toolBar.add(bDelFile);
		
		final JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);
		
		bRefresh = new JButton("Load...");
		toolBar.add(bRefresh);
		init();
	}
	private void init() {
		ayTodas = new HashMap<String, NormCromType>();
		bAddFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addNorm();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delNorm("");
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
					loadNorms("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateNorm(ididid);
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
				loadNorm(ididid);
			}
		});
		bUplFileTMPS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IdDesc fd = ddm.showDialog();
				if (fd!=null){
					tFirTMPS.setText(fd.toString());
				}
				setDataFA(fd);
			}
		});
		bUplFileNor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IdDesc fd = ddm.showDialog();
				if (fd!=null){
					tFinrom.setText(fd.toString());
				}
				setDataFM(fd);
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
						loadNorm(uname);
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
						loadNorm(uname);
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] { "Id", "NormaId", "NormaTMPSId", "Producto", "Norma Vigente", "Norma", "Tabla de Merma Por secado"});
		table.removeColumn(table.getColumnModel().getColumn(0));
//		try {
//			loadFiles("");
//		} catch (FaultType_Exception e1) {
//			e1.printStackTrace();
//		}
		jdDialog = new JDialog();
		jdDialog.setTitle("Edicion De Normas");
		jdDialog.setLocationRelativeTo(null);
		jdDialog.setModal(true);
		jdDialog.setSize(600, 400);
		ddm = new DialogSearchFile();
		ddm.setTitle("Seleccion De Normas");
		ddm.setLocationRelativeTo(null);
		ddm.setModal(true);
		ddm.setSize(600, 400);
//		switchPanel(false);
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

	private void loadNorms(String uname) throws FaultType_Exception {
		NewsApplication.startGP();
		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayOfNormCromType ay;
				try {
					ay = ProxyPort.getInstance().obtNormCrom("");
					for (int row = 0; row < modelAllData.getRowCount();) {
						modelAllData.removeRow(row);
					}
					ayTodas.clear();
					for (NormCromType a : ay.getNormas()) {
						modelAllData.addRow(new String[] { a.getId(), a.getFileNormaId(), a.getFileTMPSId(), a.getProd(), a.getNormvig(), a.getFileNorma(), a.getFileTMPS() });
						ayTodas.put(a.getId(), a);
					}
					if (ddm!=null){
						ddm.loadFiles("");
					}
				} catch (Exception e) {
					e.printStackTrace();
//					NewsApplication.stopGP();
//					e.printStackTrace();
				} finally {
					NewsApplication.stopGP();
				}
			}
		}).start();
	}

	private void addNorm() throws FaultType_Exception {
		modeAdd = true;
		tProd.setText("");
		tNormVig.setText("");
		tFinrom.setText("");
		tFirTMPS.setText("");
		switchPanel(true);
	}

	private NormCromType loadNorm(String fid) {
		ididid = fid;
		if (fid==null||fid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
			NormCromType hh = ayTodas.get(ididid);
			tNormVig.setText(hh.getNormvig());
			tProd.setText(hh.getProd());
			setDataFA(new IdDesc(hh.getFileTMPS(), hh.getFileTMPSId()));
			setDataFM(new IdDesc(hh.getFileNorma(),hh.getFileNormaId()));
			tFinrom.setText(hh.getFileNorma());
			tFirTMPS.setText(hh.getFileTMPS());
//			for (int row = 0; row < modelAllData.getRowCount(); row++){
//				if (ididid.equals(modelAllData.getValueAt(row, 0))){
//					table.setRowSelectionInterval(table.convertRowIndexToView(row), table.convertRowIndexToView(row));
//				}
//			}
			return hh;
		} else {
			ididid = null;
			tProd.setText("");
			tNormVig.setText("");
			tFinrom.setText("");
			tFirTMPS.setText("");
			return null;
		}
	}

	private void delNorm(String fid) throws FaultType_Exception {
		ididid = fid;
		if (fid==null||fid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")) {
			loadNorm(ididid);
			int option = JOptionPane.showConfirmDialog (null, "Desea eliminar la norma para " + tProd.getText() + " " + tNormVig.getText() + "?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().delNormCrom(ididid);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadNorms(ididid);
			loadNorm("");
		}
	}

	private void updateNorm(String fid) throws FaultType_Exception {
		ididid = fid;
		if ( modeAdd) {
			NewsApplication.startGP();
			new Thread( new Runnable() {
				@Override
				public void run() {
					try {
						final NormCromType fifi = new NormCromType();
						fifi.setProd(tProd.getText());
						fifi.setNormvig(tNormVig.getText());
						fifi.setFileTMPSId(getDataFA().getID());
						fifi.setFileNormaId(getDataFM().getID());
						String dd = ProxyPort.getInstance().addNormCrom(fifi);
						loadNorms(dd);
						loadNorm(dd);
					} catch (FaultType_Exception e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						NewsApplication.stopGP();
					}
				}
			}).start();
		} else {
			if (fid==null||fid.equals("")) {
				int row = table.getSelectedRow();
				if (row>=0) {
					ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
				}
			}
			if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
				final NormCromType fifi = new NormCromType();
				fifi.setId(ididid);
				fifi.setProd(tProd.getText());
				fifi.setNormvig(tNormVig.getText());
				fifi.setFileTMPSId(getDataFA().getID());
				fifi.setFileNormaId(getDataFM().getID());
				ProxyPort.getInstance().modNormCrom(fifi);
				loadNorms(ididid);
				loadNorm(ididid);
			} else {
				ididid = null;
				tProd.setText("");
				tNormVig.setText("");
				tFinrom.setText("");
				tFirTMPS.setText("");
				loadNorms("");
				loadNorm("");
			}
		}
	}

	private IdDesc getDataFM() {
		return dataFM;
	}

	private IdDesc setDataFM(IdDesc fd) {
		return dataFM = fd;
	}

	private IdDesc getDataFA() {
		return dataFA;
	}

	private IdDesc setDataFA(IdDesc fd) {
		return dataFA = fd;
	}
}

class DialogSearchFile extends JDialog {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel	modelAllData;
	private IdDesc	select;
	public IdDesc showDialog() {
	    setVisible(true);
	    return select;
	}
	/**
	 * Create the dialog.
	 */
	public DialogSearchFile() {
		setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			table = new JTable();
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

			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						acceptFile();
					}
				}
			});
			modelAllData.setColumnIdentifiers(new String[] { "Id", "Nombre Con Extension", "Categoria", "Descripcion", "Mensaje", "Fecha Modificacion"});
			table.removeColumn(table.getColumnModel().getColumn(0));
			JScrollPane js = new JScrollPane();
			js.setViewportView(table);
			contentPanel.add(js);
			try {
				loadFiles("");
			} catch (FaultType_Exception e1) {
				e1.printStackTrace();
			}
		}
		{
			final JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						acceptFile();
					}
				});
			}
			{
				final JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cancelFile();
					}
				});
			}
		}
	}
	public void loadFiles(String uname) throws FaultType_Exception {
		ArrayOfFilesType ay = ProxyPort.getInstance().obtFiles("NC");
		for (int row = 0; row < modelAllData.getRowCount(); ){
			modelAllData.removeRow(row);
		}
		for ( FilesType a : ay.getFiles() ){
			FechaTimeType d = new FechaTimeType();
			d.setFecha(a.getFecha().getFecha());
		    modelAllData.addRow(new String[] {a.getId(), a.getName() + "." + a.getExt(), a.getCatId(), a.getDesc(), a.getMess(), DateUtil.ToString.yyyyMMdd(d)});
		}
	}

	private void cancelFile(){
		select = null;
		setVisible(false);
	}

	private void acceptFile() {
		int row = table.getSelectedRow();
		if ( row >= 0 ) {
			select = new IdDesc( (String) table.getModel().getValueAt(row, 3),  (String) table.getModel().getValueAt(row, 0));
		} else {
			select = null;
		}
		setVisible(false);
	}
}

class IdDesc {
    private String desc;
    private String id ;
   
    public IdDesc(String desc , String id ) {
      this.desc=desc;
      this.id=id;
    }
   
    public String getID(){
      return id ;
    }
   
    public String toString() {
      return desc ;
    }
  }
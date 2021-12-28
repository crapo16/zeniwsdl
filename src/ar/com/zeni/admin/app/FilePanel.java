package ar.com.zeni.admin.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ar.com.zeni.admin.app.components.JLimitedTextField;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.zeniadminwsdl.ArrayOfCategoryType;
import ar.com.zeni.zeniadminwsdl.ArrayOfFilesType;
import ar.com.zeni.zeniadminwsdl.CategoryType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.FileType;
import ar.com.zeni.zeniadminwsdl.FilesType;
import ar.com.zeni.zeniwsdl.FechaTimeType;

public class FilePanel extends JPanel {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	private final JTable table;
	private final JButton bCan;
	private final JButton bAcc;
	private final JButton	bRefresh;
	private final JButton	bDelFile;
	private final JButton	bAddFile;
	private DefaultTableModel	modelAllData;
	private JButton	bUplFile;
	private JComboBox	cCateg;
	private JTextField	tMsg;
	private JTextField	tDesc;
	private JTextField	tExt;
	private JTextField	tName;
	private JProgressBar	progressBar;
	private String	ididid;
	private HashMap<String, FilesType>	ayTodas;
	private DefaultComboBoxModel	cbm;
	private JDialog jdDialog;
	private JPanel	panel;
	private boolean	modeAdd;
	private boolean	isUploadNew;
	private JSplitPane	splitPane;
	private byte[]	data;

	/**
	 * Create the panel.
	 */
	public FilePanel() {
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
		gbl_pUser.columnWidths = new int[]{70, 114, 0};
		gbl_pUser.rowHeights = new int[]{0, 30, 0, 0, 0, 0, 0, 0, 0};
		gbl_pUser.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pUser.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pUser.setLayout(gbl_pUser);

		final JLabel lName = new JLabel("Nombre");
		final GridBagConstraints gbc_lName = new GridBagConstraints();
		gbc_lName.anchor = GridBagConstraints.EAST;
		gbc_lName.insets = new Insets(0, 0, 5, 5);
		gbc_lName.gridx = 0;
		gbc_lName.gridy = 0;
		pUser.add(lName, gbc_lName);

		tName = new JLimitedTextField(50);
		final GridBagConstraints gbc_tName = new GridBagConstraints();
		gbc_tName.insets = new Insets(0, 0, 5, 0);
		gbc_tName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tName.gridx = 1;
		gbc_tName.gridy = 0;
		pUser.add(tName, gbc_tName);
		tName.setColumns(10);

		final JLabel lExt = new JLabel("Extension");
		final GridBagConstraints gbc_lExt = new GridBagConstraints();
		gbc_lExt.anchor = GridBagConstraints.EAST;
		gbc_lExt.insets = new Insets(0, 0, 5, 5);
		gbc_lExt.gridx = 0;
		gbc_lExt.gridy = 1;
		pUser.add(lExt, gbc_lExt);

		tExt = new JLimitedTextField(10);
		final GridBagConstraints gbc_tExt = new GridBagConstraints();
		gbc_tExt.insets = new Insets(0, 0, 5, 0);
		gbc_tExt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tExt.gridx = 1;
		gbc_tExt.gridy = 1;
		pUser.add(tExt, gbc_tExt);
		tExt.setColumns(10);

		final JLabel lDesc = new JLabel("Descripcion");
		final GridBagConstraints gbc_lDesc = new GridBagConstraints();
		gbc_lDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lDesc.anchor = GridBagConstraints.EAST;
		gbc_lDesc.gridx = 0;
		gbc_lDesc.gridy = 2;
		pUser.add(lDesc, gbc_lDesc);

		tDesc = new JLimitedTextField(200);
		final GridBagConstraints gbc_tDesc = new GridBagConstraints();
		gbc_tDesc.insets = new Insets(0, 0, 5, 0);
		gbc_tDesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_tDesc.gridx = 1;
		gbc_tDesc.gridy = 2;
		pUser.add(tDesc, gbc_tDesc);
		tDesc.setColumns(10);

		final JLabel lMsg = new JLabel("Mensaje");
		final GridBagConstraints gbc_lMsg = new GridBagConstraints();
		gbc_lMsg.insets = new Insets(0, 0, 5, 5);
		gbc_lMsg.anchor = GridBagConstraints.EAST;
		gbc_lMsg.gridx = 0;
		gbc_lMsg.gridy = 3;
		pUser.add(lMsg, gbc_lMsg);

		tMsg = new JLimitedTextField(200);
		final GridBagConstraints gbc_tMsg = new GridBagConstraints();
		gbc_tMsg.insets = new Insets(0, 0, 5, 0);
		gbc_tMsg.fill = GridBagConstraints.HORIZONTAL;
		gbc_tMsg.gridx = 1;
		gbc_tMsg.gridy = 3;
		pUser.add(tMsg, gbc_tMsg);
		tMsg.setColumns(10);

		JLabel lCat = new JLabel("Categoria");
		final GridBagConstraints gbc_lCat = new GridBagConstraints();
		gbc_lCat.anchor = GridBagConstraints.EAST;
		gbc_lCat.insets = new Insets(0, 0, 5, 5);
		gbc_lCat.gridx = 0;
		gbc_lCat.gridy = 4;
		pUser.add(lCat, gbc_lCat);

		cCateg = new JComboBox();
		final GridBagConstraints gbc_cCateg = new GridBagConstraints();
		gbc_cCateg.insets = new Insets(0, 0, 5, 0);
		gbc_cCateg.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCateg.gridx = 1;
		gbc_cCateg.gridy = 4;
		pUser.add(cCateg, gbc_cCateg);

		bUplFile = new JButton("Seleccionar Archivo");

		final GridBagConstraints gbc_bUplFile = new GridBagConstraints();
		gbc_bUplFile.insets = new Insets(0, 0, 5, 0);
		gbc_bUplFile.gridx = 1;
		gbc_bUplFile.gridy = 5;
		pUser.add(bUplFile, gbc_bUplFile);

		JLabel lblNewLabel = new JLabel("Progreso de Carga");
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		pUser.add(lblNewLabel, gbc_lblNewLabel);

		progressBar = new JProgressBar( 0, 100);
		final GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 6;
		pUser.add(progressBar, gbc_progressBar);

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

	private void loadFileFromDisk() throws IOException, InterruptedException {
		JFileChooser fileChooser = new JFileChooser();
		int seleccion = fileChooser.showOpenDialog(bUplFile);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fileChooser.getSelectedFile();
			Runtime runtime = Runtime.getRuntime();
			int prev = 0;
			long filesize = fichero.length();
			long aver = runtime.freeMemory() - filesize;
			if ( aver > 0) {
				progressBar.setValue(prev);
				Runnable rune = new Runnable() {
					@Override
					public void run() {
						int num = 0;
						bUplFile.setText("Subiendo, espere...");
						while (progressBar.getValue() < 95) {
							progressBar.setValue(num);
							try {
								Thread.sleep(250);
							} catch (InterruptedException sss) {
							}
							num += 13;
						}
						bUplFile.setText("Cambiar Archivo");
					}
				};
				new Thread(rune).start();
				FileInputStream fis = new FileInputStream(fichero);
				String filename = fichero.getName();
				String filenameOnly = filename.substring(0, (filename.lastIndexOf(".")>-1)?filename.lastIndexOf("."):filename.length());;
			    int punto = filename.lastIndexOf(".");
			    String ext = punto>-1?filename.substring(punto + 1):"";
			    tName.setText(filenameOnly);
			    tExt.setText(ext);
				final BufferedInputStream bifin = new BufferedInputStream(fis);
				final ByteArrayOutputStream bais = new ByteArrayOutputStream();
				final byte data[] = new byte[1024];
				int count;
				final byte[] _returnData;
				try {
					while ((count = bifin.read(data, 0, 1024)) != -1) {
						bais.write(data, 0, count);
						prev += count;
//						progressBar.setValue((int) (((prev)  * 100 ) / filesize));
					}
				} finally {
					fis.close();
					bifin.close();
					bais.flush();
				}
				cleanData(null);
	   			_returnData = bais.toByteArray();
	   			setData(_returnData);
			} else {
				JOptionPane.showConfirmDialog (null, "Debe seleccionar otro archivo, es muy grande para la aplicacion");
			}
		}
	}

	private void init() {
		ayTodas = new HashMap<String, FilesType>();
		bAddFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addFile();
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bDelFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					delFile("");
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
					loadFiles("");
				} catch (FaultType_Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bAcc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateFile("");
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
				loadFile(ididid);
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
						loadFile(uname);
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
						loadFile(uname);
						switchPanel(true);
					}
				}
			}
		});
		modelAllData.setColumnIdentifiers(new String[] { "Id", "Nombre Con Extension", "Categoria", "Descripcion", "Mensaje", "Fecha Modificacion"});
//		table.removeColumn(table.getColumnModel().getColumn(0));
		bUplFile.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					isUploadNew = true;
					loadFileFromDisk();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
//		try {
//			loadFiles("");
//		} catch (FaultType_Exception e1) {
//			e1.printStackTrace();
//		}
		cbm = new DefaultComboBoxModel();
		cCateg.setModel( cbm );
		try {
			loadCategories("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		jdDialog = new JDialog();
		jdDialog.setTitle("Edicion De Fichero");
		jdDialog.setLocationRelativeTo(null);
		jdDialog.setModal(true);
		jdDialog.setSize(600, 400);
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

	private void loadCategories(String string) throws Exception, FaultType_Exception {
		ArrayOfCategoryType ay = ProxyPort.getInstance().obtCategories("");
		for ( CategoryType a : ay.getCategs() ){
			cbm.addElement(new IdDesc(a.getDesc(), a.getId()));
		}
	}
	public class IdDesc {
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
	private void loadFiles(String uname) throws FaultType_Exception {
		ArrayOfFilesType ay = ProxyPort.getInstance().obtFiles("");
		for (int row = 0; row < modelAllData.getRowCount(); ){
			modelAllData.removeRow(row);
		}
		ayTodas.clear();
		for ( FilesType a : ay.getFiles() ){
			FechaTimeType d = new FechaTimeType();
			d.setFecha(a.getFecha().getFecha());
		    modelAllData.addRow(new String[] {a.getId(), a.getName() + "." + a.getExt(), a.getCatId(), a.getDesc(), a.getMess(), DateUtil.ToString.yyyyMMdd(d)});
		    ayTodas.put(a.getId(), a);
		}
	}

	private void addFile() throws FaultType_Exception {
		modeAdd = true;
		tExt.setText("");
		tDesc.setText("");
		tName.setText("");
		tMsg.setText("");
		cCateg.setSelectedIndex(0);
		bUplFile.setText("Subir Archivo");
		switchPanel(true);
	}

	private FilesType loadFile(String fid) {
		ididid = fid;
		isUploadNew=false;
		if (fid==null||fid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")&&ayTodas.get(ididid)!=null) {
			FilesType hh = ayTodas.get(ididid);
			tExt.setText(hh.getExt());
			tDesc.setText(hh.getDesc());
			tName.setText(hh.getName());
			tMsg.setText(hh.getMess());
			for ( int i = 0; i < cbm.getSize(); i++) {
				IdDesc s = (IdDesc) cbm.getElementAt(i);
				if (s.getID().equals(hh.getCatId())) {
					cCateg.setSelectedIndex(i);
				}
			}
//			for (int row = 0; row < modelAllData.getRowCount(); row++){
//				if (ididid.equals(modelAllData.getValueAt(row, 0))){
//					table.setRowSelectionInterval(table.convertRowIndexToView(row), table.convertRowIndexToView(row));
//				}
//			}
			bUplFile.setText("Cambiar Archivo");
			return hh;
		} else {
			ididid = null;
			tExt.setText("");
			tDesc.setText("");
			tName.setText("");
			tMsg.setText("");
			cCateg.setSelectedIndex(0);
			return null;
		}
	}

	private void delFile(String fid) throws FaultType_Exception {
		ididid = fid;
		if (fid==null||fid.equals("")) {
			int row = table.getSelectedRow();
			if (row>=0) {
				ididid = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0);
			}
		}
		if (ididid!=null&&!ididid.equals("")) {
			loadFile(ididid);
			int option = JOptionPane.showConfirmDialog (null, "Desea eliminar el archivo " + tName.getText() + " ?");
			if (option == JOptionPane.YES_OPTION ) {
				ProxyPort.getInstance().delFile(ididid);
			} else if (option == JOptionPane.NO_OPTION) {
			} else {
			}
			loadFiles(ididid);
			loadFile("");
		}
	}

	private void updateFile(String fid) throws FaultType_Exception {
		ididid = fid;
		if ( modeAdd || isUploadNew) {
			NewsApplication.startGP();
			new Thread( new Runnable() {
				@Override
				public void run() {
					final FileType fifi = new FileType();
					fifi.setNombre(tName.getText());
					fifi.setNombreConExtension(tName.getText() + "." + tExt.getText());
					fifi.setExtension(tExt.getText());
					fifi.setDescription(tDesc.getText());
					fifi.setMessage(tMsg.getText());
					fifi.setId(modeAdd?"noexiste":ididid);
					fifi.setData(getData());
					final String category = ((IdDesc) cCateg.getSelectedItem()).getID();
					try {
						isUploadNew = false;
						String dd = ProxyPort.getInstance().uploadFile(fifi, category);
						loadFiles(dd);
						loadFile(dd);
					} catch (FaultType_Exception e) {
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
				ar.com.zeni.zeniadminwsdl.FechaTimeType d = new ar.com.zeni.zeniadminwsdl.FechaTimeType();
				d.setFecha(DateUtil.Converters.DateToFechaTimeType(new Date()).getFecha());
				FilesType filenito = new FilesType();
				filenito.setId(ididid);
				filenito.setDesc(tDesc.getText());
				filenito.setCatId(((IdDesc) cCateg.getSelectedItem()).getID());
				filenito.setExt(tExt.getText());
				filenito.setFecha(d);
				filenito.setMess(tMsg.getText());
				filenito.setName(tName.getText());
				filenito.setNameExt(tName.getText() + "." + tExt.getText());
				ProxyPort.getInstance().modFiles(filenito);
				loadFiles(ididid);
				loadFile(ididid);
			} else {
				ididid = null;
				tExt.setText("");
				tDesc.setText("");
				tName.setText("");
				tMsg.setText("");
				cCateg.setSelectedIndex(0);
				loadFiles("");
				loadFile("");
			}
		}
	}

	private byte[] getData() {
		return data;
	}

	private byte[] cleanData(byte[] fd) {
		data = null;
		return getData();
	}

	private byte[] setData(byte[] fd) {
		return data = fd;
	}
}

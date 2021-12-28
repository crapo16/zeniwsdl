package ar.com.zeni.admin.app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import ar.com.zeni.admin.app.components.GUIProperties;
import ar.com.zeni.admin.app.components.InfiniteProgressPanel;

public class NewsApplication {

	private JFrame frame;
	private static InfiniteProgressPanel glassPane;
	private static void lookAndFeel() {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true); // to decorate frames
			JDialog.setDefaultLookAndFeelDecorated(true); // to decorate dialogs
			UIManager.setLookAndFeel(GUIProperties.PLAF_GRAPHITE);
//			setUIFont (new javax.swing.plaf.FontUIResource(new Font("courier",Font.PLAIN, 12)));
		} catch (final Exception ex) {
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					try {
						lookAndFeel();
					} catch (Exception e) {
					    // If Nimbus is not available, you can set the GUI to another look and feel.
					}
					final NewsApplication window = new NewsApplication();
					if (window != null)
						window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewsApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		glassPane = new InfiniteProgressPanel();
		frame.setGlassPane(glassPane);
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Archivos", new FilePanel());
		tabbedPane.addTab("Noticias",new NewsPanel());
		tabbedPane.addTab("Ayudas",new HelpPanel());
		tabbedPane.addTab("Usuarios",new UserPanel());
		tabbedPane.addTab("Normas De Comercialcializacion",new NormasCormeniPanel());
		tabbedPane.addTab("Novedades",new NovedadesPanel());

		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);

	}
	public static void startGP(){
        glassPane.start();
	}
	public static void stopGP(){
        glassPane.stop();
	}
}

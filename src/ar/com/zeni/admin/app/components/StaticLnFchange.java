package ar.com.zeni.admin.app.components;

import java.awt.Component;
import java.util.EventListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.EventListenerList;


public class StaticLnFchange {
	public static void updateUiListeners() {
		fireListDataListeners();
	}
	public static void addListDataListener(final UpdateUiListener l) {
		getUpdateUiListenerList().add(UpdateUiListener.class, l);
	}

	public static void removeListDataListener(final UpdateUiListener l) {
		getUpdateUiListenerList().remove(UpdateUiListener.class, l);
	}
	static EventListenerList listUpdateUiListenerList;
	private static EventListenerList getUpdateUiListenerList() {
		if (listUpdateUiListenerList == null) {
			listUpdateUiListenerList = new EventListenerList();
		}
		return listUpdateUiListenerList;
	}

	private static void fireListDataListeners(){
		final Object[] listeners = getUpdateUiListenerList().getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == UpdateUiListener.class) {
				((UpdateUiListener) listeners[i + 1]).LnFChanged();
			}
		}
	}
	private void removeAllListeners() {
		final Object[] listeners = getUpdateUiListenerList().getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			getUpdateUiListenerList().remove(UpdateUiListener.class, (UpdateUiListener) listeners[i + 1]);
		}
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		removeAllListeners();
	}
	/**
	 * Provides support to look and feel changes.
	 * @author rmolina
	 *
	 */
	public interface UpdateUiListener extends EventListener {
		/**
		 * Must refresh UI for every thing inside this
		 */
		public void LnFChanged();
	}


	public static void installLnF() {
		try {
			UIManager.setLookAndFeel(GUIProperties.PLAF_ACRYL);
//			updateLookAndFeel(GUIProperties.PLAF_ACRYL);
		} catch (final ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void updateLookAndFeel(final String lf, Component component) {
		try {
			// reset to default theme
			if (lf.equals(GUIProperties.PLAF_METAL)) {
				javax.swing.plaf.metal.MetalLookAndFeel
						.setCurrentTheme(new javax.swing.plaf.metal.DefaultMetalTheme());
			} else if (lf.equals(GUIProperties.PLAF_FAST)) {
				com.jtattoo.plaf.fast.FastLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.fast.FastLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.fast.FastLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_SMART)) {
				com.jtattoo.plaf.smart.SmartLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.smart.SmartLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_ACRYL)) {
				com.jtattoo.plaf.acryl.AcrylLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.acryl.AcrylLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_AERO)) {
				com.jtattoo.plaf.aero.AeroLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.aero.AeroLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_BERNSTEIN)) {
				com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
						.getThemeProperties(
								com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
										.getTheme().getName()).setProperty(
								"logoString", "Apertura");
				com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
						.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_ALUMINIUM)) {
				com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
						.getThemeProperties(
								com.jtattoo.plaf.luna.LunaLookAndFeel
										.getTheme().getName()).setProperty(
								"logoString", "Apertura");
				com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
						.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_MCWIN)) {
				com.jtattoo.plaf.mcwin.McWinLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.luna.LunaLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_MINT)) {
				com.jtattoo.plaf.mint.MintLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.luna.LunaLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.mint.MintLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_HIFI)) {
				com.jtattoo.plaf.hifi.HiFiLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.luna.LunaLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_NOIRE)) {
				com.jtattoo.plaf.noire.NoireLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.luna.LunaLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Default");
			} else if (lf.equals(GUIProperties.PLAF_LUNA)) {
				com.jtattoo.plaf.luna.LunaLookAndFeel.getThemeProperties(
						com.jtattoo.plaf.luna.LunaLookAndFeel.getTheme()
								.getName()).setProperty("logoString",
						"Apertura");
				com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme("Default");
			}
			UIManager.setLookAndFeel(lf);
			Component frame = SwingUtilities.getRoot(component);
			SwingUtilities.updateComponentTreeUI(frame);
			updateUiListeners();
		} catch (final Exception ex) {
			System.out.println("Failed loading L&F: " + " Exception: "
					+ ex.getMessage());
		}
	}

}

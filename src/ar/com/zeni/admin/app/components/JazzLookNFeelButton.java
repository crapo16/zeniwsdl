package ar.com.zeni.admin.app.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class JazzLookNFeelButton extends JMenuBar {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public JazzLookNFeelButton() {
		final JMenu menu = new JMenu("Look & Feel");
		menu.setMnemonic('L');
		// final JFrame hyperParent = (JFrame) getParent();
		JRadioButtonMenuItem radioMenuItem = new JRadioButtonMenuItem("Metal");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_METAL, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isMetalLook());
		final ButtonGroup plafGroup = new ButtonGroup();
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Motif");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_MOTIF, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isMotifLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Mac");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_MAC, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isMacLook());
		radioMenuItem.setEnabled(false);// isSupportedLookAndFeel(GUIProperties.PLAF_MAC));
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		menu.addSeparator();

		radioMenuItem = new JRadioButtonMenuItem("Acryl");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_ACRYL, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isAcrylLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Aero");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_AERO, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isAeroLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Aluminium");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_ALUMINIUM, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isAluminiumLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Bernstein");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_BERNSTEIN, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isBernsteinLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Fast");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_FAST, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isFastLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Graphite");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_GRAPHITE, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isGraphiteLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("HiFi");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_HIFI, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isHiFiLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Luna");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_LUNA, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isLunaLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("McWin");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_MCWIN, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isMcWinLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Mint");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_MINT, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isMintLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Noire");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_NOIRE, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isNoireLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		radioMenuItem = new JRadioButtonMenuItem("Smart");
		radioMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_SMART, menu);
			}
		});
		// radioMenuItem.setSelected(hyperParent.getGuiProps().isSmartLook());
		plafGroup.add(radioMenuItem);
		menu.add(radioMenuItem);

		// if (GUIProperties.isCustomEnabled()) {
		// menu.addSeparator();
		// radioMenuItem = new JRadioButtonMenuItem("Custom");
		// radioMenuItem.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(final ActionEvent event) {
		// demoApp.StaticLnFchange.updateLookAndFeel(GUIProperties.PLAF_CUSTOM);
		// }
		// });
		// radioMenuItem.setSelected(demoApp.getGuiProps().isCustomLook());
		// plafGroup.add(radioMenuItem);
		// menu.add(radioMenuItem);
		// }
		this.add(menu);
		this.setPreferredSize(new Dimension(200, 36));//TODO variables y colmenas.
	}
}

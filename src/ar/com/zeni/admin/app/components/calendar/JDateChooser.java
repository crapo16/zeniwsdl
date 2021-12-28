/*
 *  JDateChooser.java  - A bean for choosing a date
 *  Copyright (C) 2011 Kai Toedter
 *  kai@toedter.com
 *  www.toedter.com
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package ar.com.zeni.admin.app.components.calendar;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

/**
 * A date chooser containig a date spinner and a button, that makes a JCalendar visible for
 * choosing a date.
 *
 * @author Kai Toedter
 * @version 1.2.2
 */

public class JDateChooser extends JPanel implements ActionListener,
    PropertyChangeListener, FocusListener, JDateChooserFormat {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2529789514679358015L;
protected JButton             calendarButton;
  protected JFormattedTextField jtext;
  protected JCalendar           jcalendar;
  protected JPopupMenu          popup;
  protected boolean             dateSelected;
  protected Date                lastSelectedDate;
  protected boolean             startEmpty;
  private String[]              monthNames, dayNames;
  private int                   dateFormat;

  // Para reimplementar
  public void assignDate() {}
  
  /**
   * Creates a new JDateChooser object.
   */
  public JDateChooser() {
    this(null, FORMAT_LATIN, false, null);
  }

  /**
   * Creates a new JDateChooser object.
   *
   * @param icon the new icon
   */
  public JDateChooser(ImageIcon icon) {
    this(null, FORMAT_LATIN, false, icon);
  }

  /**
   * Creates a new JDateChooser object.
   *
   * @param startEmpty true, if the date field should be empty
   */
  public JDateChooser(boolean startEmpty) {
    this(null, FORMAT_LATIN, startEmpty, null);
  }

  /**
   * Creates a new JDateChooser object with given date format string. The default date format
   *
   * @param dateFormatString the date format string
   * @param startEmpty true, if the date field should be empty
   */
  public JDateChooser(int dateFormat, boolean startEmpty) {
    this(null, dateFormat, startEmpty, null);
  }

  /**
   * Creates a new JDateChooser object from a given JCalendar.
   *
   * @param jcalendar the JCalendar
   */
  public JDateChooser(JCalendar jcalendar) {
    this(jcalendar, FORMAT_LATIN, false, null);
  }

  public JDateChooser(String[] days, String[] months) {
    monthNames = months;
    dayNames = days;
    init(null, FORMAT_LATIN, false, null);
  }

  public JDateChooser(int formatDate, String[] months, String[] days) {
    monthNames = months;
    dayNames = days;
    init(null, formatDate, false, null);
  }

  /**
   * Creates a new JDateChooser.
   *
   * @param jcalendar the jcalendar or null
   * @param dateFormat the date format string or null (then "MMMMM d, yyyy" is used)
   * @param startEmpty true, if the date field should be empty
   * @param icon the icon or null (then an internal icon is used)
   */
  public JDateChooser(JCalendar jcalendar, int dateFormat, boolean startEmpty,
      ImageIcon icon) {
    init(jcalendar, dateFormat, startEmpty, icon);
  }

  private void init(JCalendar jcalendar, int dateFormat, boolean startEmpty,
      ImageIcon icon) {
    if (jcalendar == null) {
      jcalendar = new JCalendar(dayNames, monthNames);
    }

    setDateFormat(dateFormat);

    this.jcalendar = jcalendar;
    setMonthNames(monthNames);
    setDayNames(dayNames);

    this.startEmpty = startEmpty;

    setLayout(new BorderLayout());

    jcalendar.getDayChooser().addPropertyChangeListener(this);
    jcalendar.getDayChooser().setAlwaysFireDayProperty(true); // always fire "day" property even if the user selects the already selected day again

    MaskFormatter mf1;
    try {
      mf1 = new MaskFormatter(getMaskFormat());
      mf1.setAllowsInvalid(false);
      mf1.setOverwriteMode(true);
      mf1.setPlaceholderCharacter('_');
      jtext = new JFormattedTextField(mf1) {
        /**
		 * 
		 */
		private static final long serialVersionUID = -4291872002189134056L;

		public void setEnabled(boolean enabled) {
          super.setEnabled(enabled);
          calendarButton.setEnabled(enabled);
        }
      };
    }
    catch (ParseException e) {
      e.printStackTrace();
    }

    jtext.addFocusListener(this);
    add(jtext, BorderLayout.CENTER);

    // Display a calendar button with an icon
    if (icon == null) {
      URL iconURL = getClass().getResource("images/JDateChooserIcon.gif");
      icon = new ImageIcon(iconURL);
    }

    calendarButton = new JButton(icon);
    calendarButton.setMargin(new Insets(0, 0, 0, 0));
    calendarButton.addActionListener(this);

    // Alt + 'C' selects the calendar.
    calendarButton.setMnemonic(KeyEvent.VK_C);

    add(calendarButton, BorderLayout.EAST);

    calendarButton.setMargin(new Insets(0, 0, 0, 0));
    popup = new JPopupMenu() {
      /**
		 * 
		 */
		private static final long serialVersionUID = -4054298655016418148L;

	public void setVisible(boolean b) {
        Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");

        if (b || (!b && dateSelected)
            || ((isCanceled != null) && !b && isCanceled.booleanValue())) {
          super.setVisible(b);
        }
      }
    };

    popup.setLightWeightPopupEnabled(true);

    popup.add(jcalendar);
    lastSelectedDate = getDate();
  }

  public void focusGained(FocusEvent e) {
	jtext.requestFocus();
	jtext.grabFocus();
  }

  public void focusLost(FocusEvent e) {
    refreshDate();
  }

  public void refreshDate() {
    String dd = jtext.getText();
    SimpleDateFormat sdf = new SimpleDateFormat(getParseFormat());
    String empty = getEmptyFormat();

    Date test;
    boolean ok = true;
    try {
      test = new Date(sdf.parse(dd).getTime());
      String tests = sdf.format(test);
      if (!tests.equals(dd)) {
        setDate(null);
        ok = false;
      }
    }
    catch (ParseException e) {
      setDate(null);
      ok = false;
    }

    if (ok) {
      if (dd != null && !dd.equals("") && !dd.equals(empty)) {
        try {
          setDate(new Date(sdf.parse(dd).getTime()));
        }
        catch (ParseException a) {
          setDate(null);
        }
      }
      else {
        setDate(null);
      }
    }
    lastSelectedDate = getDate();
    if (lastSelectedDate == null) {
      jtext.setText("");
    }
  }

  /**
   * Called when the jalendar button was pressed.
   *
   * @param e the action event
   */
  public void actionPerformed(ActionEvent e) {
    int x = calendarButton.getWidth()
        - (int) popup.getPreferredSize().getWidth();
    int y = calendarButton.getY() + calendarButton.getHeight();

    Calendar calendar = Calendar.getInstance();
    if (getDate() != null) {
      calendar.setTime(getDate());
    }
    else {
      calendar.setTime(Calendar.getInstance().getTime());
    }
    jcalendar.setCalendar(calendar);
    popup.show(calendarButton, x, y);
    dateSelected = false;
  }

  /**
   * Listens for a "date" property change or a "day" property change event from the JCalendar.
   * Updates the dateSpinner and closes the popup.
   *
   * @param evt the event
   */
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("day")) {
      dateSelected = true;
      popup.setVisible(false);
      java.sql.Date ddd = new java.sql.Date(jcalendar.getCalendar().getTime()
          .getTime());
      setDate(ddd);
      assignDate();
    }
    else if (evt.getPropertyName().equals("date")) {
      setDate((Date) evt.getNewValue());
      assignDate();
    }
  }

  /**
   * Updates the UI of itself and the popup.
   */
  public void updateUI() {
    super.updateUI();

    if (jcalendar != null) {
      SwingUtilities.updateComponentTreeUI(popup);
    }
  }

  public JTextField getField() {
    return jtext;
  }

  public void setLocale(Locale l) {
    jcalendar.setLocale(l);
  }

  public void setMonthNames(String[] months) {
    jcalendar.setMonthNames(months);
  }

  public void setDayNames(String[] days) {
    jcalendar.setDayNames(days);
  }

  /**
   * Returns "JDateChooser".
   *
   * @return the name value
   */
  public String getName() {
    return "JDateChooser";
  }

  /**
   * Returns the date.
   *
   * @return the current date
   */
  public Date getDate() {
    Date date = null;
    String dd = jtext.getText();

    SimpleDateFormat sdf = new SimpleDateFormat(getParseFormat());
    String empty = getEmptyFormat();

    if (dd != null && !dd.equals("") && !dd.equals(empty)) {
      try {
        date = new java.sql.Date(sdf.parse(dd).getTime());
      }
      catch (ParseException e) {}
    }

    return date;
  }

  /**
   * Sets the date. Fires the property change "date".
   *
   * @param date the new date.
   */
  public void setDate(Date date) {
    if (date != null) {
      SimpleDateFormat sdf = new SimpleDateFormat(getParseFormat());

      String dd = sdf.format(date);
      jtext.setText(dd);
    }
    else {
      jtext.setText("");
    }

    if (getParent() != null) {
      getParent().validate();
    }
  }

  protected String getEmptyFormat() {
    String ret = "";
    switch (getDateFormat()) {
      case FORMAT_LATIN:
        ret = EMPTY_LATIN;
        break;
      case FORMAT_USA:
        ret = EMPTY_USA;
        break;
      case FORMAT_JAPAN:
        ret = EMPTY_JAPAN;
        break;
    }
    return ret;
  }

  protected String getMaskFormat() {
    String ret = "";
    switch (getDateFormat()) {
      case FORMAT_LATIN:
        ret = MASK_LATIN;
        break;
      case FORMAT_USA:
        ret = MASK_USA;
        break;
      case FORMAT_JAPAN:
        ret = MASK_JAPAN;
        break;
    }
    return ret;
  }

  protected String getParseFormat() {
    String ret = "";
    switch (getDateFormat()) {
      case FORMAT_LATIN:
        ret = DATE_LATIN;
        break;
      case FORMAT_USA:
        ret = DATE_USA;
        break;
      case FORMAT_JAPAN:
        ret = DATE_JAPAN;
        break;
    }
    return ret;
  }

  public void setDateFormat(int sdf) {
    dateFormat = sdf;
  }

  public int getDateFormat() {
    return dateFormat;
  }

  public void setEnabled(boolean enable) {
    jtext.setEnabled(enable);
    calendarButton.setEnabled(enable);
  }
}
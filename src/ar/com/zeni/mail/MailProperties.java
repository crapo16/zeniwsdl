package ar.com.zeni.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que contiene la informacion para construir el mail. to, cc, subject ,
 * from , body
 *
 * @author ogagli
 * @version 1.0
 * @created 13-Marzo-2013 10:38:29
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MailProperties {

	private Map<String, String> data;
	private List<String> tabla;

	/**
	 *
	 */
	public MailProperties() {
		this.data = new HashMap<String, String>();
	}

	/**
	 *
	 * @param data
	 */
	public MailProperties(Map<String, String> data) {
		this.data = data;
	}

	/**
	 *
	 * @return
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 *
	 * @param template
	 */
	public void setTemplate(String template) {
		this.data.put(MailPropertiesConstant.MAIL_TEMPLATE, template);
	}

	/**
	 *
	 * @param from
	 */
	public void setFrom(String from) {
		this.data.put(MailPropertiesConstant.MAIL_FROM, from);
	}

	/**
	 *
	 * @param from
	 */
	public void setIdMail(String id) {
		this.data.put(MailPropertiesConstant.MAIL_ID, id);
	}

	/**
	 *
	 * @param to
	 */
	public void setTo(String to) {
		this.data.put(MailPropertiesConstant.MAIL_TO, to);
	}

	/**
	 *
	 * @param to
	 * @param delimiter
	 */
	public void setTo(String[] to, String delimiter) {

		StringBuffer _to = null;

		if (to == null || to.length == 0) {
			this.data.put(MailPropertiesConstant.MAIL_TO, null);
		} else {
			_to = new StringBuffer();
			for (String to_param : to) {
				_to.append(to_param + delimiter);
			}
			this.data.put(MailPropertiesConstant.MAIL_TO, _to.toString().substring(0, _to.toString().length() - 1));
		}
	}

	/**
	 *
	 * @param cc
	 */
	public void setCC(String cc) {
		this.data.put(MailPropertiesConstant.MAIL_CC, cc);
	}

	/**
	 *
	 * @param cco
	 */
	public void setCCO(String cco) {
		this.data.put(MailPropertiesConstant.MAIL_CCO, cco);
	}

	/**
	 *
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.data.put(MailPropertiesConstant.MAIL_SUBJECT, subject);
	}

	/**
	 *
	 * @param bodyTitle
	 */
	public void setBodyTitle(String bodyTitle) {
		this.data.put(MailPropertiesConstant.BODY_TITLE, bodyTitle);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection1Title(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_1_TITLE, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection1(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_1, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection2Title(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_2_TITLE, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection2(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_2, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection3Title(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_3_TITLE, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setBodySection3(String data) {
		this.data.put(MailPropertiesConstant.BODY_SECTION_3, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setTableTH(String data) {
		this.data.put(MailPropertiesConstant.MAIL_TABLE_TH, data);
	}

	/**
	 *
	 * @param data
	 */
	public void setTableTD(String data) {
		this.data.put(MailPropertiesConstant.MAIL_TABLE_TD, data);
	}

	/**
	 *
	 * @return
	 */
	public List<String> getTabla() {
		return tabla;
	}

	/**
	 *
	 * @param tabla
	 */
	public void setTabla(List<String> tabla) {
		this.tabla = tabla;
	}

	@Override
	public String toString() {
		if (data != null) {
			if (tabla != null)
				return "data{" + data.toString() + "} tabla{" + tabla.toString() + "}";
			else
				return data.toString();
		}

		else
			return null;
	}
}
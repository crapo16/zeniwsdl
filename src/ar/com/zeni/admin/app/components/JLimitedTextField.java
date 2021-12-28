package ar.com.zeni.admin.app.components;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class JLimitedTextField extends JTextField {
	private static final long	serialVersionUID	= 1L;

	public JLimitedTextField(int MAX_CHARACTERS) {
		super();
		AbstractDocument doc;
		Document styledDoc = this.getDocument();
		if (styledDoc instanceof AbstractDocument) {
		    doc = (AbstractDocument)styledDoc;
		    doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		}
	}
}

package ar.com.zeni.admin.app.components;

import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class JLimitedTextArea extends JTextArea {
	private static final long	serialVersionUID	= 1L;

	public JLimitedTextArea(int MAX_CHARACTERS) {
		super();
		AbstractDocument doc;
		Document styledDoc = this.getDocument();
		if (styledDoc instanceof AbstractDocument) {
		    doc = (AbstractDocument)styledDoc;
		    doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		}
	}
}

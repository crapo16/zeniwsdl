package ar.com.zeni.admin.app.server;

import ar.com.zeni.common.ZeniContextServer;

public interface FileAdministrationConstanst {
	public interface FileTable {
		static final String FILETABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_FILES";
		static final String FIELD_ID = "ID";
		static final String FILED_CATEGORY = "CAT";
		static final String FIELD_NAME = "NAME_FILE";
		static final String FIELD_EXTENSION = "EXTENSION";
		static final String FIELD_MODIF_DATE = "MODIF_DATE";
		static final String FIELD_DESCRIPTION = "DESCRIPTION";
		static final String FIELD_MESSAGE = "MESSAGE";
		static final String FILED_SIZE = "SIZE_FILE";
		static final String ALFILETABLE = FILETABLE + " FF";
		static final String ALFIELD_ID = "FF." + FIELD_ID;
		static final String ALFILED_CATEGORY = "FF." + FILED_CATEGORY;
		static final String ALFIELD_NAME = "FF." + FIELD_NAME;
		static final String ALFIELD_EXTENSION = "FF." + FIELD_EXTENSION;
		static final String ALFIELD_MODIF_DATE = "FF." + FIELD_MODIF_DATE;
		static final String ALFIELD_DESCRIPTION = "FF." + FIELD_DESCRIPTION;
		static final String ALFIELD_MESSAGE = "FF." + FIELD_MESSAGE;
		static final String ALFILED_SIZE = "FF." + FILED_SIZE;
	}
	public interface FileCategoriesTable {
		static final String FILE_CATEGORIES_TABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_FILES_CATEG";
		static final String FIELD_ID = "ID";
		static final String FILED_CATEGORY = "DESCRIPTION";
		static final String ALFILE_CATEGORIES_TABLE = FILE_CATEGORIES_TABLE + " FC";
		static final String ALFIELD_ID = "FC." + FIELD_ID;
		static final String ALFILED_CATEGORY = "FC." + FILED_CATEGORY;
	}
	static final String FILE_STORE_DIR = "FILE_STORE_DIR";
}

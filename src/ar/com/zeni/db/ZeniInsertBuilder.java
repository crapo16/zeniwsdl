package ar.com.zeni.db;

import ar.com.zeni.db.ZeniQueryBuilder.Operator;


/**
 * Provides simple queries builder
 * 
 * <pre>
 * insert into table 
 *   (CAMPO1, CAMPO2, CAMPO3)
 *   values 
 *   ("value1", "value2", "value3")
 * </pre>
 * <pre>
 *      new MbQueryBuilder().insertinto( "tabla1")
 *                            .fields("CAMPO1", "CAMPO2", "CAMPO3"))
 *                          .values("value1","value2","value3")
 *                         .getQuey());
 * </pre>
 * 
 * @author rmolina
 * @see Operator
 */
public class ZeniInsertBuilder {
	private final String c = ", ";
    private final String INSERT = "INSERT INTO ";
    private final String VALUES = " VALUES ";
//    private final String FROM = " FROM ";
//    private final String INNER_JOIN = " INNER JOIN ";
//    private final String AND = " AND ";
//    private final String ON = " ON ";
//    private final String OR = " OR ";
    private final String q = "\'";
    private final String op = " ( ";
    private final String cp = " ) ";
	private StringBuilder insert;

	public ZeniInsertBuilder insertinsertinto( final String table) {
		insert = new StringBuilder();
		insert.append(INSERT);
		insert.append(table);
		return this;
	}
	public ZeniInsertBuilder fields(final String ... campos){
		insert.append(op);
		boolean first = true;
		for ( final String field : campos ){
			if ( !first) {
				insert.append(c);
			} else {
				first = false;
			}
			insert.append(field);
		}
		insert.append(cp);
		return this;
	}
	public ZeniInsertBuilder values(final String ... values){
		insert.append(VALUES);
		insert.append(op);
		boolean first = true;
		for ( final String value : values ){
			if ( !first) {
				insert.append(c);
			} else {
				first = false;
			}
			insert.append(q);
			insert.append(value);
			insert.append(q);
		}
		insert.append(cp);
		return this;
	}
	public String getInsertQuery(){
		return insert.toString();
	}
}

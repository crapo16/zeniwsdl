package ar.com.zeni.db;

import ar.com.zeni.db.ZeniQueryBuilder.Operator;

/**
 * Provides simple queries builder
 * 
 * <pre>
 * update table
 *    set CAMPO1 = 'value1',
 *        campo2 = 'value2',
 *        campo3 = 'value3'
 *  where campo4 = 'valor4'
 *    and campo4 = 'valor4'
 * </pre>
 * <pre>
 *      new MbUpdateBuilder().update( "tabla1")
 *                              .set("CAMPO1", "value1"))
 *                             .coma("CAMPO2", "value2")
 *                             .coma("CAMPO3", "value3")
 *                            .where("CAMPO4",Operator.equals,"valor4")
 *                              .and("CAMPO4",Operator.equals,"valor4")
 *                   .getUpdateQuery());
 * </pre>
 * 
 * @author rmolina
 * @see Operator
 */
public class ZeniUpdateBuilder {
	private final String c = ", ";
	private final String operator = " = ";
    private final String UPDATE = "UPDATE ";
    private final String WHERE = " WHERE ";
//    private final String VALUES = " VALUES ";
//    private final String FROM = " FROM ";
//    private final String INNER_JOIN = " INNER JOIN ";
    private final String AND = " AND ";
    private final String ON = " ON ";
    private final String SET = " SET ";
    private final String OR = " OR ";
    private final String q = "\'";
    private final String op = " ( ";
    private final String cp = " ) ";
	private StringBuilder update;

	public ZeniUpdateBuilder update( final String table) {
		update = new StringBuilder();
		update.append(UPDATE);
		update.append(table);
		update.append(" ");
		return this;
	}
	public ZeniUpdateBuilder set(final String field, final String value){
		update.append(SET);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
		return this;
	}
	public ZeniUpdateBuilder coma(final String field, final String value){
		update.append(c);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
		return this;
	}
	public String getupdateQuery(){
		return update.toString();
	}
	public ZeniUpdateBuilder where(final String field, final Operator operator, final String value){
		update.append(WHERE);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
		return this;
	}
	public ZeniUpdateBuilder and(final String field, final Operator operator, final String value){
		update.append(AND);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
		return this;
	}
	public ZeniUpdateBuilder or(final String field, final Operator operator, final String value){
		update.append(OR);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
		return this;
	}
	public ZeniUpdateBuilder on(final String field, final Operator operator, final String field2){
		update.append(ON);
		update.append(field);
		update.append(operator);
		update.append(field2);
    	return this;
    }
	public ZeniUpdateBuilder openCompo(final String field, final Operator operator, final String value){
		update.append(op);
		update.append(field);
		update.append(operator);
		update.append(q);
		update.append(value);
		update.append(q);
    	return this;
    }
	public ZeniUpdateBuilder closeCompo(){
		update.append(cp);
    	return this;
    }
}

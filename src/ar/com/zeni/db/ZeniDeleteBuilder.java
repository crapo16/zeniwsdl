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
 *      new MbQueryBuilder().deleteFrom( "tabla1")
 *                            .where("CAMPO4",Operator.equals,"valor4")
 *                              .and("CAMPO3",Operator.equals,"valor3")
 *                         .getQuey());
 * </pre>
 * 
 * @author rmolina
 * @see Operator
 */
public class ZeniDeleteBuilder {
    private final String DELETE = "DELETE FROM ";
    private final String WHERE = " WHERE ";
    private final String AND = " AND ";
    private final String OR = " OR ";
    private final String q = "\'";
	private StringBuilder delete;

	public ZeniDeleteBuilder deleteFrom( final String table) {
		delete = new StringBuilder();
		delete.append(DELETE);
		delete.append(table);
		return this;
	}
	public ZeniDeleteBuilder where(final String field, final Operator operator, final String value){
		delete.append(WHERE);
		delete.append(field);
		delete.append(operator);
		delete.append(q);
		delete.append(value);
		delete.append(q);
		return this;
	}
	public ZeniDeleteBuilder and(final String field, final Operator operator, final String value){
		delete.append(AND);
		delete.append(field);
		delete.append(operator);
		delete.append(q);
		delete.append(value);
		delete.append(q);
		return this;
	}
	public ZeniDeleteBuilder or(final String field, final Operator operator, final String value){
		delete.append(OR);
		delete.append(field);
		delete.append(operator);
		delete.append(q);
		delete.append(value);
		delete.append(q);
		return this;
	}
	public String getDeleteQuery(){
		return delete.toString();
	}
}

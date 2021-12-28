package ar.com.zeni.db;


/**
 * Provides simple queries builder
 * 
 * <pre>
 * SELECT t1.CAMPO1, t1.CAMPO2, t1.CAMPO3
 *   FROM tabla1 t1
 *  INNER JOIN tabla2 t2 ON ( t1.campo1 = t2.campo1)
 *  WHERE t1.campo1 = 'value'
 *    AND t1.campo2 = 'value'
 * </pre>
 * <pre>
 *      new MbQueryBuilder().select( "t1.CAMPO1", "t1.CAMPO2", "t1.CAMPO3")
 *                            .from("tabla1 t1")
 *                       .innerJoin("tabla2 t2").on("t1.campo1", Operator.EQUALS, "t2.campo2")
 *                           .where("t1.campo1", Operator.EQUALS, "value")
 *                             .and("t2.campo2", Operator.EQUALS, "value")
 *                         .getQuey());
 * </pre>
 * 
 * @author rmolina
 * @see Operator
 */
public class ZeniQueryBuilder {

	/**
	 * Operations for sentences in oracle, very simple
	 * @author rmolina
	 *
	 */
	public enum Operator {
		EQUALS(" = "),
		LIKE(" LIKE "),
		NOT_LIKE(" NOT LIKE "),
		LESS(" < "),
		GREATER(" > ");
		private final String code;
		Operator ( final String code){
			this.code = code;
		}
		@Override
		public String toString() {
			return this.code;
		}
	}
	private final String c = ", ";
    private final String SELECT = "SELECT ";
    private final String WHERE = " WHERE ";
    private final String FROM = " FROM ";
    private final String INNER_JOIN = " INNER JOIN ";
    private final String AND = " AND ";
    private final String ON = " ON ";
    private final String OR = " OR ";
    private final String q = "\'";
    private final String op = " ( ";
    private final String cp = " ) ";
    private StringBuilder select;
	public ZeniQueryBuilder select(final String ... fields){
		select = new StringBuilder();
		select.append(SELECT);
		boolean first = true;
		for (final String field : fields){
			if ( !first) {
				select.append(c);
			} else {
				first = false;
			}
			select.append(field);
		}
		return this;
	}
	public ZeniQueryBuilder from(final String table){
		select.append(FROM);
		select.append(table);
		return this;
	}
	public ZeniQueryBuilder where(final String field, final Operator operator, final String value){
		select.append(WHERE);
		select.append(field);
		select.append(operator);
		select.append(q);
		select.append(value);
		select.append(q);
		return this;
	}
	public ZeniQueryBuilder and(final String field, final Operator operator, final String value){
		select.append(AND);
		select.append(field);
		select.append(operator);
		select.append(q);
		select.append(value);
		select.append(q);
		return this;
	}
	public ZeniQueryBuilder or(final String field, final Operator operator, final String value){
		select.append(OR);
		select.append(field);
		select.append(operator);
		select.append(q);
		select.append(value);
		select.append(q);
		return this;
	}
	public String getQuey(){
		return select.toString();
	}
	public ZeniQueryBuilder innerJoin(final String table) {
		select.append(INNER_JOIN);
		select.append(table);
		return this;
	}
	public ZeniQueryBuilder on(final String field, final Operator operator, final String field2){
		select.append(ON);
		select.append(field);
		select.append(operator);
		select.append(field2);
    	return this;
    }
	public ZeniQueryBuilder openCompo(final String field, final Operator operator, final String value){
		select.append(op);
		select.append(field);
		select.append(operator);
		select.append(q);
		select.append(value);
		select.append(q);
    	return this;
    }
	public ZeniQueryBuilder closeCompo(){
		select.append(cp);
    	return this;
    }
}

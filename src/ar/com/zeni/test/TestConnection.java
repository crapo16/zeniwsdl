package ar.com.zeni.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;

public class TestConnection {
	public static void main(String[] args) throws SQLException {
////		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
//		DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
//		String[] VECINONPASS = new String[30]; 
//		VECINONPASS[0] = "jdbc:oracle:thin:@(DESCRIPTION =(CONNECT_TIMEOUT=10)(RETRY_COUNT=3)(ADDRESS = (PROTOCOL = TCP)(HOST = 10.2.111.102)(PORT = 1521))(LOAD_BALANCE = YES)(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = ZSCDB)(FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5)))) ";
//		VECINONPASS[1] = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=zsc.central.zeni)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=ZSCDB)))";
//		VECINONPASS[2] = "jdbc:oracle:thin:@//zsc.central.zeni:1521:ZSCDB";
//		VECINONPASS[3] = "jdbc:oracle:thin:@zsc.central.zeni:1521/ZSCDB";
//		VECINONPASS[4] = "jdbc:oracle:thin:@zsc.central.zeni:1521:ZSCDB";
//		VECINONPASS[5] = "jdbc:oracle:thin:@//zsc.central.zeni:1521/ZSCDB";
//		VECINONPASS[6] = "jdbc:oracle:thin:@//zsc.central.zeni:1521:ZSCDB";
//		VECINONPASS[6] = "jdbc:oracle:thin:@//10.2.111.102:1521/ZSCDB";
//		VECINONPASS[7] = "jdbc:oracle:thin:@//10.2.111.102:1521:ZSCDB";
//		VECINONPASS[8] = "jdbc:oracle:thin:@10.2.111.102:1521/ZSCDB";
//		VECINONPASS[9] = "jdbc:oracle:thin:@10.2.111.102:1521:ZSCDB";
//		VECINONPASS[10] = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=10.2.111.102)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=ZSCDB)))";
//		VECINONPASS[11] = "jdbc:oracle:thin:@//10.2.111.111:1521/ZSCDB";
//		VECINONPASS[12] = "jdbc:oracle:thin:@//10.2.111.111:1521:ZSCDB";
//		VECINONPASS[13] = "jdbc:oracle:thin:@10.2.111.111:1521/ZSCDB";
//		VECINONPASS[14] = "jdbc:oracle:thin:@10.2.111.111:1521:ZSCDB";
//		VECINONPASS[15] = "jdbc:oracle:thin:@10.2.111.111:1521:ZSCDB1";
//		VECINONPASS[16] = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=zsc.central.zeni)(PORT=1521)))(CONNECT_DATA=(SID=ZSCDB1)))";
//		VECINONPASS[17] = "jdbc:oracle:thin:@//zsc.central.zeni:1521:ZSCDB1";
//		VECINONPASS[18] = "jdbc:oracle:thin:@zsc.central.zeni:1521/ZSCDB1";
//		VECINONPASS[19] = "jdbc:oracle:thin:@zsc.central.zeni:1521:ZSCDB1";
//		VECINONPASS[20] = "jdbc:oracle:thin:@//zsc.central.zeni:1521/ZSCDB1";
//		VECINONPASS[21] = "jdbc:oracle:thin:@//zsc.central.zeni:1521:ZSCDB1";
//		VECINONPASS[22] = "jdbc:oracle:thin:@//10.2.111.102:1521/ZSCDB1";
//		VECINONPASS[23] = "jdbc:oracle:thin:@//10.2.111.102:1521:ZSCDB1";
//		VECINONPASS[24] = "jdbc:oracle:thin:@10.2.111.102:1521/ZSCDB1";
//		VECINONPASS[25] = "jdbc:oracle:thin:@10.2.111.102:1521:ZSCDB1";
//		VECINONPASS[26] = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCPS)(HOST=10.2.111.102)(PORT=1521))(CONNECT_DATA=(SID=ZSCDB1)))";
//		VECINONPASS[27] = "jdbc:oracle:thin:@//10.2.111.111:1521/ZSCDB1";
//		VECINONPASS[28] = "jdbc:oracle:thin:@//10.2.111.111:1521:ZSCDB1";
//		VECINONPASS[29] = "jdbc:oracle:thin:@10.2.111.111:1521/ZSCDB1";
//		for ( int i = 0; i<30 && VECINONPASS[i]!=null; i++) {
//			try {
////				System.out.println(VECINONPASS[i] + " sin pass ");
//				Connection dd = DriverManager.getConnection(VECINONPASS[i], "zeniweb", "zeniweb");
//				dd.prepareStatement("select 1 from dual");
//				System.out.println(VECINONPASS[i] + " sin pass ");
//			} catch (Exception e) {
////				e.printStackTrace();
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		}
//		for ( int i = 0; i<30 && VECINONPASS[i]!=null; i++) {
//			try {
////				System.out.println(VECINONPASS[i] + " con pass mal");
//				Connection dd = DriverManager.getConnection(VECINONPASS[i], "zeniweb", "zeniapp");
//				dd.prepareStatement("select 1 from dual");
//				System.out.println(VECINONPASS[i] + " con pass mal");
//			} catch (Exception e) {
////				e.printStackTrace();
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		}
//		String[] VECINO = new String[30]; 
//		VECINO[0] = "jdbc:oracle:thin:zeniweb/zeniweb@(DESCRIPTION =(CONNECT_TIMEOUT=10)(RETRY_COUNT=3)(ADDRESS = (PROTOCOL = TCP)(HOST = 10.2.111.102)(PORT = 1521))(LOAD_BALANCE = YES)(CONNECT_DATA = (SERVER = DEDICATED)(SERVICE_NAME = ZSCDB)(FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5)))) ";
//		VECINO[1] = "jdbc:oracle:thin:zeniweb/zeniweb@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=zsc.central.zeni)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=ZSCDB)))";
//		VECINO[2] = "jdbc:oracle:thin:zeniweb/zeniweb@//zsc.central.zeni:1521:ZSCDB";
//		VECINO[3] = "jdbc:oracle:thin:zeniweb/zeniweb@//zsc.central.zeni:1521/ZSCDB";
//		VECINO[4] = "jdbc:oracle:thin:zeniweb/zeniweb@zsc.central.zeni:1521:ZSCDB";
//		VECINO[5] = "jdbc:oracle:thin:zeniweb/zeniweb@zsc.central.zeni:1521/ZSCDB";
//		VECINO[6] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.102:1521/ZSCDB";
//		VECINO[7] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.102:1521:ZSCDB";
//		VECINO[8] = "jdbc:oracle:thin:zeniweb/zeniapp@10.2.111.102:1521/ZSCDB";
//		VECINO[9] = "jdbc:oracle:thin:zeniweb/zeniapp@10.2.111.102:1521:ZSCDB";
//		VECINO[10] = "jdbc:oracle:thin:zeniweb/zeniapp@10.2.111.111:1521:ZSCDB";
//		VECINO[11] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.111:1521/ZSCDB";
//		VECINO[12] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.111:1521:ZSCDB";
//		VECINO[13] = "jdbc:oracle:thin:zeniweb/zeniapp@10.2.111.111:1521/ZSCDB";
//		VECINO[14] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.102:1521/ZSCDB";
//		VECINO[15] = "jdbc:oracle:thin:zeniweb/zeniapp@//10.2.111.102:1521:ZSCDB";
//		VECINO[16] = "jdbc:oracle:thin:zeniweb/zeniweb@10.2.111.102:1521/ZSCDB";
//		VECINO[17] = "jdbc:oracle:thin:zeniweb/zeniweb@10.2.111.102:1521:ZSCDB";
//		VECINO[18] = "jdbc:oracle:thin:zeniweb/zeniweb@10.2.111.111:1521:ZSCDB";
//		VECINO[19] = "jdbc:oracle:thin:zeniweb/zeniweb@//10.2.111.111:1521/ZSCDB";
//		VECINO[20] = "jdbc:oracle:thin:zeniweb/zeniweb@//10.2.111.111:1521:ZSCDB";
//		VECINO[21] = "jdbc:oracle:thin:zeniweb/zeniweb@10.2.111.111:1521/ZSCDB";
//		for ( int i = 0; i<30 && VECINO[i]!=null; i++) {
//			try {
////				System.out.println(VECINO[i] + " con pass ");
//				Connection dd = DriverManager.getConnection(VECINO[i]);
//				dd.prepareStatement("select 1 from dual");
//				System.out.println(VECINO[i] + " con pass ");
//			} catch (Exception e) {
////				e.printStackTrace();
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		}

		try {
//			System.out.println(VECINO[i] + " con pass ");
//			Connection dd = ZeniContextServer.getInstance().getConnectionGetter().getConnection();
			final ResulsetObjectBuilder resbCliente = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					int i=0;
					while (rset.next()) {
//						System.out.println(rset.getString("US"));
//						try {
//							Thread.sleep(50);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				}
			};
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ZeniQueryExcecutor.excecuteSelect(" select c.US, c.DSC, c.CRA from ZENIWEB.WEB_SUIO c order by c.US", resbCliente);
						System.out.println("TERMINE");
					} catch (ZeniDBExeption e) {
						e.printStackTrace();
					}
				}
			}).start();
			final Connection[] cs = new Connection[12];
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						for (int i = 0; i < 10; i++) {
							cs[i] = ZeniContextServer.getInstance().getConnectionGetter().getConnection();
						}
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ZeniDBExeption e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
						for (int i = 0; i < 12; i++) {
							if (cs[i]!=null) { 
								cs[i].close();
								cs[i]=null;
							}
						}
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
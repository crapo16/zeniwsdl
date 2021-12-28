
package ar.com.zeni.test;

import ar.com.zeni.admin.app.server.MbIdGenerator;

public class Test {

	public static void main(String[] args) {
		for (int a = 0; a<10;a++)
		System.out.println(MbIdGenerator.getInstance().nextId());
	}

}

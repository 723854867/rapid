package org.rapid.util;

import org.rapid.util.io.SFTPConnection;

public class BeanUtilTest {

	public static void main(String[] args) throws Exception {
		SFTPConnection connection = new SFTPConnection();
		connection.setPort(50022);
		connection.setHost("222.73.39.37");
		connection.setPriKeyFile(SFTPConnection.class.getResource("/conf/id_rsa").getFile());
		connection.setUsername("200004595271");
		connection.init();
	}
}

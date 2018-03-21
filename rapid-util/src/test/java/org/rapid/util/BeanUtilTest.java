package org.rapid.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.rapid.util.io.ZipWriter;

public class BeanUtilTest {

	public static void main(String[] args) throws Exception {
		ZipWriter writer = new ZipWriter(512, new FileOutputStream(new File("E:/wqj.zip")));
		writer.wrap("wqj/test.jpg", new FileInputStream(new File("E:/1.jpg")));
		writer.close();
	}
}

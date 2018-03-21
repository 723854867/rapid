package org.rapid.util.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IOUtil {
	
	public static final List<File> unzip(String filename) throws Exception {
		return unzip(new File(filename));
	}

	public static final List<File> unzip(File unzipFile) throws Exception {
		ZipInputStream zip = null;
		FileOutputStream out = null;
		byte[] buffer = new byte[1024];
		List<File> files = new ArrayList<File>();
		try {
			zip = new ZipInputStream(new BufferedInputStream(new FileInputStream(unzipFile)));
			ZipEntry entry = null;
			while ((entry = zip.getNextEntry()) != null) {
				File file = new File(unzipFile.getParent() + File.separator + entry.getName());
				if (entry.isDirectory())
					file.mkdirs();
				else {
					File parent = file.getParentFile();
					if (!parent.exists())
						parent.mkdirs();
					out = new FileOutputStream(file);
					int readCounts = 0;
					while ((readCounts = zip.read(buffer)) > 0)
						out.write(buffer, 0, readCounts);
				}
				zip.closeEntry();
				files.add(file);
			}
		} finally {
			if (null != zip) 
				zip.close();
			if (null != out) 
				out.close();
		}
		return files;
	}
}

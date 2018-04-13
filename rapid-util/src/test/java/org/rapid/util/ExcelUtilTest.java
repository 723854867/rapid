package org.rapid.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rapid.util.io.excel.ExcelUtil;

public class ExcelUtilTest {

	public static void main(String[] args) throws Exception {
		SubPojo pojo = new SubPojo();
		pojo.setAddr("杭州市万家名称2期15421时");
		pojo.setAge(200);
		pojo.setName("张三丰");
		pojo.setEmail("sdsdsdwsds@155.com");
		List<SubPojo> pojos = new ArrayList<SubPojo>();
		pojos.add(pojo);
		Workbook workbook = new XSSFWorkbook();
		ExcelUtil.writeSheet(workbook, "测试", SubPojo.class, pojos);
		workbook.write(new FileOutputStream(new File("E:/test.xlsx")));
	}
}

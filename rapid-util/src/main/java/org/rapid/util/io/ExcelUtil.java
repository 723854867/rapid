package org.rapid.util.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rapid.util.reflect.BeanUtil;

public class ExcelUtil {

	public static final <T> List<T> read(File file, Class<T> clazz) {
		ZipSecureFile.setMinInflateRatio(-1.0d);
		Workbook workbook = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(file, true);
			workbook = new HSSFWorkbook(fs);
			return _parse(workbook, clazz);
		} catch (Exception e) {
			try {
				workbook = new XSSFWorkbook(file);
				return _parse(workbook, clazz);
			} catch (InvalidFormatException | IOException e1) {
				throw new RuntimeException(file.getPath() + " load failure!", e);
			}
		} finally {
			if (null != workbook)
				try {
					workbook.close();
				} catch (IOException e) {}
		}
	}
	
	private static final <T> List<T> _parse(Workbook workbook, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		Sheet sheet = workbook.getSheetAt(0);
		String[] cols = _parseTitle(sheet);
		_parseContent(sheet, cols, list, clazz);
		return list;
	}
	
	private static final String[] _parseTitle(Sheet sheet) {
		Row row = sheet.getRow(0);
		String[] cols = new String[row.getPhysicalNumberOfCells()]; 
		for (int i = 0, len = cols.length; i < len; i++) {
			Cell cell = row.getCell(i);
			cell.setCellType(CellType.STRING);
			cols[i] = cell.getStringCellValue();
		}
		return cols;
	}
	
	private static final <T> void _parseContent(Sheet sheet, String[] cols, List<T> list, Class<T> clazz) {
		int rowNum = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rowNum; i++) {
			Row row = sheet.getRow(i);
			list.add(_parseEntity(row, cols, clazz));
		}
	}
	
	private static final <T> T _parseEntity(Row row, String[] cols, Class<T> clazz) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0, len = cols.length; i < len; i++) {
			Cell cell = row.getCell(i);
			cell.setCellType(CellType.STRING);
			map.put(cols[i], cell.getStringCellValue());
		}
		try {
			return BeanUtil.mapToBean(map, clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(clazz.getName() + " initial failure!", e);
		}
	}
}

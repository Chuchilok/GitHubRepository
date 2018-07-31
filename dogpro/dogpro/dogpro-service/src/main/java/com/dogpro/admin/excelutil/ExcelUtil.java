package com.dogpro.admin.excelutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ExcelUtil {
	/**
	 * 读取导入的exl文件
	 * 
	 * @param file
	 *            文件
	 * @param dxjl
	 *            短信记录
	 * @return
	 * @throws IOException
	 */
	public  static Map<String, Object> loadExl(CommonsMultipartFile file) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Workbook workbook = null;
		resultMap.put("code", 1);
		// 校验文件格式，工作表信息
		String fileName = file.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (".xls".equals(fileType)) {
			workbook = new HSSFWorkbook(file.getInputStream()); // 2003-
		} else if (".xlsx".equals(fileType)) {
			workbook = new XSSFWorkbook(file.getInputStream()); // 2007+
		} else {
			resultMap.put("code", -1);
			resultMap.put("msg", "非法文件格式");
			return resultMap;
		}
		Sheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			resultMap.put("code", -1);
			resultMap.put("msg", "工作表信息为空");
			return resultMap;
		}

		List<Map<String,String>> obj = new ArrayList<Map<String,String>>(); 
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			try {
				Map<String, String> map=new HashMap<String, String>();
				for (Cell cell : row) {
					 cell.setCellType(Cell.CELL_TYPE_STRING);
					if ("-".equals(cell.getStringCellValue())) {
						cell.setCellValue("");
					}
				}
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
//				区域名 areaName	地点别名 addressAlias	省 provinces	市 city	区	districts 街道townStreet 
				//周边几公里perimeter	经度	longitude纬度latitude	热度hot	排序号orders
				map.put("areaName", row.getCell(0).getStringCellValue());
				map.put("addressAlias", row.getCell(1).getStringCellValue());
				map.put("provinces", row.getCell(2).getStringCellValue());
				map.put("municipalities", row.getCell(3).getStringCellValue());
				map.put("districts", row.getCell(4).getStringCellValue());
				map.put("townStreet", row.getCell(5).getStringCellValue());
				map.put("perimeter", row.getCell(6).getStringCellValue());
				map.put("longitudeAndLatitude", row.getCell(7).getStringCellValue());
				map.put("hot", row.getCell(8).getStringCellValue());
				map.put("orders", row.getCell(9).getStringCellValue());
				obj.add(map);
			} catch (Exception e) {
				resultMap.put("code", -1);
				resultMap.put("msg", "导入文件失败");
				return resultMap;
			}
			// }
		}
		resultMap.put("result", obj);
		resultMap.put("msg", "成功导入文件");
		return resultMap;
	}
}

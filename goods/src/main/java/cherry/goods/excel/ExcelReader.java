/*
 * Copyright 2014,2015 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.goods.excel;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Microsoft Excelファイル読込み機能。<br />
 */
public class ExcelReader implements Closeable {

	private Workbook workbook;

	private Sheet sheet;

	private Iterator<Row> rowIterator;

	public ExcelReader(InputStream in) throws InvalidFormatException, IOException {
		workbook = WorkbookFactory.create(in);
		setCurrentSheet(workbook.getActiveSheetIndex());
	}

	public int getNumberOfSheets() {
		return workbook.getNumberOfSheets();
	}

	public String getSheetName(int sheetIndex) {
		return workbook.getSheetName(sheetIndex);
	}

	public void setCurrentSheet(int sheetIndex) {
		sheet = workbook.getSheetAt(sheetIndex);
		rowIterator = sheet.rowIterator();
	}

	public String[] read() throws IOException {
		if (!rowIterator.hasNext()) {
			return null;
		}
		Row row = rowIterator.next();
		String[] record = new String[row.getLastCellNum()];
		for (Cell cell : row) {
			record[cell.getColumnIndex()] = getCellValueAsString(cell);
		}
		return record;
	}

	@Override
	public void close() throws IOException {
		workbook.close();
	}

	private String getCellValueAsString(Cell cell) {
		switch (cell.getCellType()) {
		case CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case CELL_TYPE_NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case CELL_TYPE_NUMERIC:
				return String.valueOf((int) cell.getNumericCellValue());
			default:
				return null;
			}
		default:
			return null;
		}
	}

}

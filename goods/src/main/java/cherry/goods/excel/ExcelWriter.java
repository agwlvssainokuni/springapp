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

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Microsoft Excelファイル書込み機能。<br />
 */
public class ExcelWriter implements Closeable {

	private OutputStream out;

	private Workbook workbook;

	private Sheet sheet;

	private int rownum;

	public ExcelWriter(OutputStream out, Workbook workbook) {
		this.out = out;
		this.workbook = workbook;
		if (this.workbook.getNumberOfSheets() > 0) {
			setCurrentSheet(this.workbook.getActiveSheetIndex());
		}
	}

	public int getNumberOfSheets() {
		return workbook.getNumberOfSheets();
	}

	public String getSheetName(int sheetIndex) {
		return workbook.getSheetName(sheetIndex);
	}

	public void setCurrentSheet(int sheetIndex) {
		sheet = workbook.getSheetAt(sheetIndex);
		rownum = 0;
	}

	public void createSheet(String sheetname) {
		sheet = workbook.createSheet(sheetname);
		rownum = 0;
	}

	public void write(String[] record) {
		Row row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}
		rownum += 1;
		for (int i = 0; i < record.length; i++) {
			if (record[i] == null) {
				continue;
			}
			Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
			cell.setCellValue(record[i]);
		}
	}

	@Override
	public void close() throws IOException {
		workbook.write(out);
	}

}

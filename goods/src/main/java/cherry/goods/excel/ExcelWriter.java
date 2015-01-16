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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Microsoft Excelファイル書込み機能。<br />
 */
public class ExcelWriter implements Closeable {

	/** 書込み先のMicrosoft Excelブック。 */
	private final Workbook workbook;

	/** 書込み対象のシート。 */
	private Sheet currentSheet;

	/** 書込み対象の行番号。 */
	private int rownum;

	/**
	 * Microsoft Excelファイル書込み機能を生成する。<br />
	 * アクティブなシートを書込み対象とする。書込み対象のシートを切替える場合は{@link #setCurrentSheet(int)}でシート番号を指定する。
	 * 
	 * @param workbook 書込み先のMicrosoft Excelブック。
	 */
	public ExcelWriter(Workbook workbook) {
		this.workbook = workbook;
		setCurrentSheet(this.workbook.getActiveSheetIndex());
	}

	/**
	 * @return 書込み先のMicrosoft Excelブックのシートの数。
	 */
	public int getNumberOfSheets() {
		return workbook.getNumberOfSheets();
	}

	/**
	 * @param sheetIndex シート番号。
	 * @return 指定されたシート番号の名前。
	 */
	public String getSheetName(int sheetIndex) {
		return workbook.getSheetName(sheetIndex);
	}

	/**
	 * 書込み対象のシートを切替える。<br />
	 * 
	 * @param sheetIndex 書込み対象とするシートのシート番号。
	 */
	public void setCurrentSheet(int sheetIndex) {
		currentSheet = workbook.getSheetAt(sheetIndex);
		rownum = 0;
	}

	/**
	 * レコード書込み。<br />
	 * 書込み対象のシートへ1レコード(1行)書込む。
	 * 
	 * @param record 1レコード(1行)。
	 */
	public void write(String[] record) {
		Row row = currentSheet.getRow(rownum);
		if (row == null) {
			row = currentSheet.createRow(rownum);
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

	/**
	 * Microsoft Excelファイル書込み機能を閉じる。<br />
	 * 具体的には、書込み先のMicrosoft Excelブックを閉じる。
	 * 
	 * @throws IOException 書込み先のMicrosoft Excelブックを閉じる際に異常。
	 */
	@Override
	public void close() throws IOException {
		workbook.close();
	}

}

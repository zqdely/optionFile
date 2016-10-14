package com.file.option.test;

import com.file.option.optionImpl.ReadFileParame;

public class ReadFileParameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String value = ReadFileParame.fileValue("showName", "test.properties");
		System.out.println(value);
	}

}

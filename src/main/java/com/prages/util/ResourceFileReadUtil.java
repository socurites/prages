package com.prages.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 리소스 파일로 부터 내용을 읽어 문자열로 반환하는 유틸리티 클래스.
 *
 * @author lks21c
 */
public class ResourceFileReadUtil {

	public String getFileContent(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		StringBuilder sb = new StringBuilder("");
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				sb.append(line).append("\n");
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

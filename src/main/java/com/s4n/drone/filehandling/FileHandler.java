package com.s4n.drone.filehandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

import com.s4n.drone.exceptions.ApplicationException;

public class FileHandler {
	public static Stream<String> getFileLines(String fileName) throws ApplicationException {
		try {
			return Files.lines(Paths.get(fileName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new ApplicationException();
		}
	}

	public static Path createFile(String fileName) throws ApplicationException {
		try {
			Path newFilePath = Paths.get(fileName);
			return Files.createFile(newFilePath);
		} catch (IOException e) {
			throw new ApplicationException();
		}
	}
	
	public static void fileWriter(Path pathFile, String content) throws ApplicationException {
		try {
			Files.write(pathFile, content.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new ApplicationException();
		}
	}
}

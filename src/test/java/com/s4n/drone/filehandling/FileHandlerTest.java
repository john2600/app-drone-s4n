package com.s4n.drone.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class FileHandlerTest implements FileTestProvider {
	private final static String RESOURCE_FILE_NAME = "src/test/resources/sample.txt";
	private final static String FILE_CONTENT = "ADDDDA";

	@Test
	@DisplayName("Should create a file on a file system")
	void givenUnixSystem_whenCreatingFile_thenCreatedInPath() throws IOException {
		Stream<String> result = FileHandler.getFileLines(RESOURCE_FILE_NAME);    
  		assertEquals(FILE_CONTENT, result.findFirst().get());
	}
}

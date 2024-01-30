package com.tika.pdf;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.TesseractException;

public class OCRPDFTest {

	public static void main(String[] args) throws IOException, TesseractException {
		ExtractTextService service = new ExtractTextService();
		String content = service.getContent(new File("C:\\temp\\abc.pdf"));
		System.out.println("*********** Start of Content ********************");
		System.out.println(content);
		System.out.println("*********** End of Content ********************");
	}

}

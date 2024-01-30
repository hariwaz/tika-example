//https://www.tutorialspoint.com/tika/tika_extracting_pdf.htm
package com.tika.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.exception.TikaException;

public class PdfParse {

	/*public static void main(final String[] args) throws IOException,TikaException, SAXException {
        
		File file = new File("C:\\temp\\aws.pdf");
	    PDDocument document = Loader.loadPDF(file);
	    document.setAllSecurityToBeRemoved(true);
	    PDFTextStripper pdfStripper = new PDFTextStripper();
	    String text = pdfStripper.getText(document);
	    System.out.println(text);
	    document.close();
	      
   }   */
	public static void main(final String[] args) throws IOException,TikaException {
         
		    PDFTextStripper pdfStripper = null;
	        PDDocument pdDoc = null;
	        File file = new File("C:\\temp\\aws.pdf");
	        PDFParser parser = new PDFParser(new RandomAccessReadBuffer(new FileInputStream(file)));
	        PDDocument doc = parser.parse();
	        try (COSDocument cosDoc = doc.getDocument()) {
	            pdfStripper = new PDFTextStripper();
	            pdDoc = new PDDocument(cosDoc);
	            pdfStripper.setStartPage(1);
	            pdfStripper.setEndPage(1);
	            String parsedText = pdfStripper.getText(pdDoc);
	            System.out.println(parsedText);
	        }
		      
		      
	   }
	}
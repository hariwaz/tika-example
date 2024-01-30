package com.tika.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ExtractTextService {

	ITesseract tessract = null;
	public ExtractTextService() {
		tessract = new Tesseract();
		tessract.setDatapath("C:\\sw\\tessdata");
		tessract.setLanguage("eng");
		tessract.setOcrEngineMode(1);
	}
	
	public String getContent(File file) throws IOException, TesseractException {
		String content = "";
		PDDocument document = Loader.loadPDF(file);
		document.setAllSecurityToBeRemoved(true);
		content = extractTextWithTessractDocument(document);
		return content;
	}
	
	private String extractTextWithTessractDocument(PDDocument doc) throws IOException, TesseractException {
		PDFRenderer pdfRenderer = new PDFRenderer(doc);
		StringBuilder out = new StringBuilder();
		for(int pg=0,len=doc.getNumberOfPages(); pg < 1; pg++) {
			BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(pg, 300, ImageType.RGB);
			File tempFile = File.createTempFile("tempfile_"+pg, ".png");
			ImageIO.write(bufferedImage, "png", tempFile);
			String result = tessract.doOCR(tempFile);
			out.append(result);
			tempFile.delete();
		}
		return out.toString();
	}
}

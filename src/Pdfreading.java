import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Pdfreading {
	String url = "file:///C:/Users/actgo/Downloads/itr1_preview.pdf";

	@Test
	public void readPdf() throws IOException {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get(url);

		URL pdfurl=new URL(url);
		InputStream ip=pdfurl.openStream();
		PDDocument doc=PDDocument.load(ip);
		
		int pageCount=doc.getNumberOfPages();
		System.out.println(pageCount);
		
		//for page 2 content
		PDFTextStripper textStripper=new PDFTextStripper();
		textStripper.setStartPage(2);
		textStripper.setEndPage(2);
		String data=textStripper.getText(doc);
		System.out.println(data);
		Assert.assertTrue(data.contains( "9,80,832"));
		
		
	}
}

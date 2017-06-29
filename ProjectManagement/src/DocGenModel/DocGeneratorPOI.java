package DocGenModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocGeneratorPOI {

	private FileOutputStream out;
	
	public DocGeneratorPOI() {
		
		try {
			
			out = new FileOutputStream(new File("POITest.docx"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createDoc(String id) {
		
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph par = doc.createParagraph();
		
		doc.createHeaderFooterPolicy();
		XWPFHeaderFooterPolicy pol = doc.getHeaderFooterPolicy();
		
		XWPFHeader head;
		
		try {
			
			head = pol.createHeader(pol.DEFAULT);
			head.getParagraphs().get(0).createRun().setText("THIS IS A HEADER");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		XWPFRun run = par.createRun();
		run.setText(id);
		
		try {
			
			doc.write(out);
			
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

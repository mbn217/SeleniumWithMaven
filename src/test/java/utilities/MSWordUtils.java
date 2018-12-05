package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class MSWordUtils {
	String FilePath = "C:\\Users\\path to file";
	FileInputStream fis;
	File file = null;
	WordExtractor extractor = null;
	String FilePath2 = "C:\\Users\\path to file";

	//
	// XWPFHeaderFooterPolicy.getFirstPageHeader(): Provides the header of first
	// page.
	// XWPFHeaderFooterPolicy.getDefaultHeader(): Provides the default header of
	// DOCX file given to each and every page.
	// XWPFHeaderFooterPolicy.getFirstPageFooter(): Provides the footer of first
	// page.
	// XWPFHeaderFooterPolicy.getDefaultFooter(): Provides the default footer of
	// DOCX file given to each and every page.
	public static void ReadDOCXHeaderFooter() {
		try {
			FileInputStream fis = new FileInputStream("D:/docx/read-test.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc);
			// read header
			XWPFHeader header = policy.getDefaultHeader();
			System.out.println(header.getText());
			// read footer
			XWPFFooter footer = policy.getDefaultFooter();
			System.out.println(footer.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Read Table using XWPFTable
	// Apache POI provides XWPFTable class to fetch table data. We can get this
	// object by two way. First by using XWPFDocument directly.
	//
	// List<XWPFTable> tables = XWPFDocument.getTables()
	//
	// And second by using IBodyElement.
	//
	// IBodyElement.getBody().getTables()

	public static void ExtractTableDOCX() {
		try {
			FileInputStream fis = new FileInputStream("D:/docx/read-test.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
			while (bodyElementIterator.hasNext()) {
				IBodyElement element = bodyElementIterator.next();
				if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
					List<XWPFTable> tableList = element.getBody().getTables();
					for (XWPFTable table : tableList) {
						System.out.println("Total Number of Rows of Table:" + table.getNumberOfRows());
						System.out.println(table.getText());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//
	// Read Paragraph using XWPFParagraph
	// Apache POI provides XWPFParagraph class to fetch paragraph text.
	// Using XWPFDocument.getParagraphs(), we get the list of all paragraphs of the
	// document. Find the example.
	public static void ExtractParagraphDOCX() {
		try {
			FileInputStream fis = new FileInputStream("D:/docx/read-test.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
			for (XWPFParagraph paragraph : paragraphList) {
				System.out.println(paragraph.getText());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Extract Complete Data using XWPFWordExtractor
	// Apache POI provides XWPFWordExtractor class to fetch complete data of every
	// page of a DOCX.

	public static void BasicTextExtractor() {
		try {
			FileInputStream fis = new FileInputStream("D:/docx/read-test.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
			System.out.println(extractor.getText());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Alternate between the two to check what works.
		// String FilePath = "D:\\Users\\username\\Desktop\\Doc1.docx";

		// if(FilePath.substring(FilePath.length() -1).equals("x")){ //is a docx
		// try {
		// fis = new FileInputStream(new File(FilePath));
		// XWPFDocument doc = new XWPFDocument(fis);
		// XWPFWordExtractor extract = new XWPFWordExtractor(doc);
		// System.out.println(extract.getText());
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		// } else { //is not a docx
		// try {
		// fis = new FileInputStream(new File(FilePath));
		// HWPFDocument doc = new HWPFDocument(fis);
		// WordExtractor extractor = new WordExtractor(doc);
		// System.out.println(extractor.getText());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }

		//////////////////////////////// read the template line by line

//		try {
//
//			file = new File(FilePath2);
//			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//			HWPFDocument document = new HWPFDocument(fis);
//			extractor = new WordExtractor(document);
//			String[] fileData = extractor.getParagraphText();
//			for (int i = 0; i < fileData.length; i++) {
//				if (fileData[i].startsWith("Application"))// != null
//					System.out.println(fileData[i + 1]);
//			}
//		} catch (Exception exep) {
//			exep.printStackTrace();
//		}
		
		
//		 private HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
//		        Range r = doc.getRange();
//		        for (int i = 0; i < r.numSections(); ++i) {
//		            Section s = r.getSection(i);
//		            for (int j = 0; j < s.numParagraphs(); j++) {
//		                Paragraph p = s.getParagraph(j);
//		                for (int k = 0; k < p.numCharacterRuns(); k++) {
//		                    CharacterRun run = p.getCharacterRun(k);
//		                    String text = run.text();
//		                    if (text.contains(findText)) {
//		                        run.replaceText(findText, replaceText);
//		                    }
//		                }
//		            }
//		        }
//		        return doc;
//		    }
//
//		    private HWPFDocument openDocument(String file) throws Exception {
//		        URL res = getClass().getClassLoader().getResource(file);
//		        HWPFDocument document = null;
//		        if (res != null) {
//		            document = new HWPFDocument(new POIFSFileSystem(
//		                    new File(res.getPath())));
//		        }
//		        return document;
//		    }
//
//		    private void saveDocument(HWPFDocument doc, String file) {
//		        try (FileOutputStream out = new FileOutputStream(file)) {
//		            doc.write(out);
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//		    }
//	
	}
}

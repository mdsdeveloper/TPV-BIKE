package PantallaVentas;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Configuracion;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FooterFactura extends  PdfPageEventHelper{
	private PdfPTable footer;
	private Phrase phrase;

	public FooterFactura(PdfPTable footer, Configuracion configuracion) {
		this.footer = footer;
		
		if(configuracion.getPieFactura() != null){
    		phrase = new Phrase(configuracion.getPieFactura());
    	}
	}
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		
		footer.writeSelectedRows(0, -1, 36, 115, writer.getDirectContent());
	     
	        
		ColumnText.showTextAligned(
			 writer.getDirectContent(), 
			 Element.ALIGN_CENTER, 
			 phrase != null ? phrase : new Phrase(ConstantesUtil.saludo),
			 300,30,0);
	}
}

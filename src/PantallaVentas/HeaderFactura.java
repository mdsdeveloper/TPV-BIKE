package PantallaVentas;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Configuracion;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class HeaderFactura extends PdfPageEventHelper {
		private Phrase phrase;
		private Font fontTitulos = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 15, Font.UNDERLINE,BaseColor.BLACK);
    	
		
        public HeaderFactura(Configuracion configuracion) {
        	
        	if(configuracion.getCabeceraFactura() != null){
        		phrase = new Phrase(configuracion.getCabeceraFactura() , fontTitulos);
        	}
            
        }
 
        
 
        public void onEndPage(PdfWriter writer, Document document) {
        	
        	fontTitulos.setStyle(Font.BOLD);
        	ColumnText.showTextAligned(
       			 writer.getDirectContent(), 
       			 Element.ALIGN_CENTER, 
       			 new Phrase(ConstantesUtil.factura, fontTitulos),
       			 document.right() - 160,document.top(),0);
        	
        	ColumnText.showTextAligned(
          			 writer.getDirectContent(), 
          			 Element.ALIGN_CENTER, 
          			 phrase,
          			 document.right() - 160,document.top() - 30,0);
        }
	
}

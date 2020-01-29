package PantallaVentas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.Venta;
import util.ConstantesUtil;
import util.UtilService;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class CierreImpreso {
		private String path;
		private List<Venta> lista;
		private Date ahora;
		private GestionService gestionService = new GestionServiceImpl();
		private BigDecimal sumaTotal = BigDecimal.ZERO;
		private BigDecimal totalTarjeta = BigDecimal.ZERO;
		private BigDecimal totalEfectivo = BigDecimal.ZERO;
		private String descuadre,recuento;
		private BigDecimal totalDescuadre = BigDecimal.ZERO;
		private BigDecimal tatalRecuento = BigDecimal.ZERO;
		private Configuracion configuracion;
		private String titulo;
		
		public CierreImpreso(List<Venta> lista, Date ahora, String recuento, String cierre){
			this.lista = lista;
			this.ahora = ahora;
			configuracion = gestionService.getConfiguracion();
			if(configuracion != null){
				path = configuracion.getDirectorioFactura()+"/"+ cierre + ahora.toString()+ ".pdf";
			}
			titulo = cierre.equals(ConstantesUtil.cierre) ? ConstantesUtil.tituloCierre : ConstantesUtil.tituloBalance;
			this.recuento = recuento;
			hacerInforme();
		}
		public void hacerInforme() {
		List<Long> listaIdLVR = new ArrayList<>();
			
		try {
		       Document document = new Document(PageSize.A4, 35, 35, 50, 30);
		       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		        document.open();		        
		     // Crear las fuentes para el contenido y los titulos
				
				Font fontContenido = FontFactory.getFont(FontFactory.TIMES_ROMAN.toString(), 11, Font.NORMAL,BaseColor.DARK_GRAY);
				Font fontTitulos = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 15, Font.UNDERLINE,BaseColor.BLACK);
				fontTitulos.setStyle(Font.UNDERLINE | Font.BOLD);
				if(configuracion.getIcono() != null){
					Image imagen = Image.getInstance(configuracion.getIcono());  
					imagen.setAlignment(Element.ALIGN_LEFT);
					document.add(imagen);
				}
				
		        Paragraph paragraph = new Paragraph();
		        // Agregar un titulo con su respectiva fuente
				paragraph.add(new Phrase(titulo, fontTitulos));
			   // Agregar saltos de linea
				paragraph.add(new Phrase(Chunk.NEWLINE));
				paragraph.add(new Phrase(Chunk.NEWLINE));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				
				Date desde;
				if(ConstantesUtil.getFechaCierre() == null){					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					calendar.add(Calendar.DAY_OF_YEAR, -1);
					desde = calendar.getTime();
				}else{
					desde = ConstantesUtil.getFechaCierre();
				}
				paragraph.add(new Phrase("Desde: " + UtilService.obtenerFecha(desde) +
		        		"      Hasta: " + UtilService.obtenerFecha(ahora)));
				paragraph.add(new Phrase(Chunk.NEWLINE));
				paragraph.add(new Phrase(Chunk.NEWLINE));
				// Agregar el parrafo al documento
				document.add(paragraph);
		        
		        PdfPTable table = new PdfPTable(7);
		 
		        table.setWidthPercentage(100);
		        table.setSpacingBefore(0f);
		        table.setSpacingAfter(0f);
		        
		        // first row  		        
		        List<String> listaCabecera = new ArrayList<>();
		        listaCabecera.add("Fecha");
		        listaCabecera.add("Nº");
		        listaCabecera.add("Empleado");
		        listaCabecera.add("F. Pago");
		        listaCabecera.add("Importe");
		        listaCabecera.add("IVA");
		        listaCabecera.add("Total");
		        
		        
		        for (String texto : listaCabecera) {
		        	PdfPCell celda = new PdfPCell(new Phrase(texto));
		        	celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	celda.setPadding(10.0f);
		        	celda.setBorderWidthRight(0);
		        	celda.setBorderWidthLeft(0);
		        	celda.setBorderWidthTop(0);
			        table.addCell(celda);
				}
		        int i = 1;
		        
		        for (Venta venta : lista) {
					listaIdLVR.add(venta.getIdLineaVentaRealizada());
					PdfPCell celda = new PdfPCell(new Phrase(UtilService.obtenerFecha(venta.getFecha())));
			        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			        celda.setPadding(5.0f);
			        celda.setBorderWidthRight(0);
			        celda.setBorderWidthLeft(0);
			        celda.setBorderWidthTop(0);
			        celda.setBorderWidthBottom(0);
			        table.addCell(celda);
			        PdfPCell celda1 = new PdfPCell(new Phrase(String.valueOf(i)));
			        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			        celda1.setPadding(5.0f);
			        celda1.setBorderWidthRight(0);
			        celda1.setBorderWidthLeft(0);
			        celda1.setBorderWidthTop(0);
			        celda1.setBorderWidthBottom(0);
			        table.addCell(celda1);
			        Empleado empleado = gestionService.getEmpleadoById(venta.getEmpleado());
			        PdfPCell celda2 = new PdfPCell(new Phrase(empleado != null ? empleado.getNombre() : "Empleado"));
			        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			        celda2.setPadding(5.0f);
			        celda2.setBorderWidthRight(0);
			        celda2.setBorderWidthLeft(0);
			        celda2.setBorderWidthTop(0);
			        celda2.setBorderWidthBottom(0);
			        table.addCell(celda2);
			        PdfPCell celda3 = new PdfPCell(new Phrase(!venta.getFormaPago() ? "Efectivo" : "Tarjeta"));
			        celda3.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celda3.setPadding(5.0f);
			        celda3.setBorderWidthRight(0);
			        celda3.setBorderWidthLeft(0);
			        celda3.setBorderWidthTop(0);
			        celda3.setBorderWidthBottom(0);
			        table.addCell(celda3);
			        PdfPCell celda4 = new PdfPCell(new Phrase(venta.getImporte().toString() + " €"));
			        celda4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda4.setPadding(5.0f);
			        celda4.setBorderWidthRight(0);
			        celda4.setBorderWidthLeft(0);
			        celda4.setBorderWidthTop(0);
			        celda4.setBorderWidthBottom(0);
			        table.addCell(celda4);
			        PdfPCell celda5 = new PdfPCell(new Phrase(venta.getIVA().toString() + " %"));
			        celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
			        celda5.setPadding(5.0f);
			        celda5.setBorderWidthRight(0);
			        celda5.setBorderWidthLeft(0);
			        celda5.setBorderWidthTop(0);
			        celda5.setBorderWidthBottom(0);
			        table.addCell(celda5);
			        PdfPCell celda6 = new PdfPCell(new Phrase(venta.getTotal().toString() + " €"));
			        celda6.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda6.setPadding(5.0f);
			        celda6.setBorderWidthRight(0);
			        celda6.setBorderWidthLeft(0);
			        celda6.setBorderWidthTop(0);
			        celda6.setBorderWidthBottom(0);
			        table.addCell(celda6);
			        sumaTotal = sumaTotal.add(venta.getTotal());
			        if(!venta.getFormaPago()){
			        	totalEfectivo = totalEfectivo.add(venta.getTotal());
			        }else{
			        	totalTarjeta = totalTarjeta.add(venta.getTotal());
			        }
					i++;
					
				}
		        
		        document.add(table);
		       
		        Paragraph paragraph1 = new Paragraph();
		        paragraph1.add(new Phrase(Chunk.NEWLINE));
				paragraph1.add(new Phrase(Chunk.NEWLINE));				
				document.add(paragraph1);			
				
				PdfPTable table1 = new PdfPTable(7);
				 
		        table1.setWidthPercentage(100);
		        table1.setSpacingBefore(0f);
		        table1.setSpacingAfter(0f);
				PdfPCell celdaTotalCaja = new PdfPCell(new Phrase("Total Caja"));
				celdaTotalCaja.setColspan(6);
		        celdaTotalCaja.setHorizontalAlignment(Element.ALIGN_LEFT);
		        celdaTotalCaja.setPadding(5.0f);
		        celdaTotalCaja.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table1.addCell(celdaTotalCaja);
		        PdfPCell celda8 = new PdfPCell(new Phrase(sumaTotal.toString() + " €"));
				celda8.setColspan(6);
		        celda8.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        celda8.setPadding(5.0f);
		        celda8.setBackgroundColor(BaseColor.LIGHT_GRAY);
		        table1.addCell(celda8);
		        
		        document.add(table1);
		        LineSeparator sep = new LineSeparator(1.f, 100.f, BaseColor.BLACK, 0, 0);
		        Paragraph paragraph2 = new Paragraph();
		        paragraph2.add(sep);			
				document.add(paragraph2);
				
				Paragraph paragraph3 = new Paragraph();
		        paragraph3.add(new Phrase(Chunk.NEWLINE));	
		        paragraph3.add(new Phrase(Chunk.NEWLINE));	
				document.add(paragraph3);
				
				PdfPTable table2 = new PdfPTable(7); 
		        table2.setWidthPercentage(100);
		        table2.setSpacingBefore(0f);
		        table2.setSpacingAfter(0f);
				PdfPCell celdaInicial = new PdfPCell(new Phrase("Efectivo inicial caja"));
				celdaInicial.setColspan(6);
				celdaInicial.setHorizontalAlignment(Element.ALIGN_LEFT);
				celdaInicial.setPadding(5.0f);
		        table2.addCell(celdaInicial);
		        PdfPCell celdaInicial1 = new PdfPCell(new Phrase(configuracion.getInicialCaja() != null ? configuracion.getInicialCaja()  + " €" :  "0 €"));
				celdaInicial1.setColspan(6);
		        celdaInicial1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        celdaInicial1.setPadding(5.0f);
		        table2.addCell(celdaInicial1);		
		        
		        
		        PdfPCell celdaEfectivo = new PdfPCell(new Phrase("Cobros en efectivo"));
				celdaEfectivo.setColspan(6);
				celdaEfectivo.setHorizontalAlignment(Element.ALIGN_LEFT);
				celdaEfectivo.setPadding(5.0f);
		        table2.addCell(celdaEfectivo);
				PdfPCell celdaEfectivo1 = new PdfPCell(new Phrase(totalEfectivo + " €"));
				celdaEfectivo1.setColspan(6);
				celdaEfectivo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				celdaEfectivo1.setPadding(5.0f);
		        table2.addCell(celdaEfectivo1);
				
		        PdfPCell celdaTarjeta = new PdfPCell(new Phrase("Cobros con tarjeta"));
		        celdaTarjeta.setColspan(6);
		        celdaTarjeta.setHorizontalAlignment(Element.ALIGN_LEFT);
		        celdaTarjeta.setPadding(5.0f);
		        table2.addCell(celdaTarjeta);
		        
				PdfPCell celdaTarjeta1 = new PdfPCell(new Phrase(totalTarjeta + " €"));
				celdaTarjeta1.setColspan(6);
				celdaTarjeta1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				celdaTarjeta1.setPadding(5.0f);
		        table2.addCell(celdaTarjeta1);
		        
				if(!recuento.equals("") && Validador.isDecimal(recuento)){
					tatalRecuento = new BigDecimal(recuento).subtract(configuracion.getInicialCaja());
					totalDescuadre = tatalRecuento.subtract(totalEfectivo);
								        
					PdfPCell celdaRecuento = new PdfPCell(new Phrase("Recuento"));
					celdaRecuento.setColspan(6);
					celdaRecuento.setHorizontalAlignment(Element.ALIGN_LEFT);
					celdaRecuento.setPadding(5.0f);
			        table2.addCell(celdaRecuento);
					PdfPCell celdaRecuento1 = new PdfPCell(new Phrase(recuento+ " €"));
					celdaRecuento1.setColspan(6);
					celdaRecuento1.setHorizontalAlignment(Element.ALIGN_RIGHT);
					celdaRecuento1.setPadding(5.0f);
			        table2.addCell(celdaRecuento1);
			        PdfPCell celdaDescuadre = new PdfPCell(new Phrase("Descuadre"));
			        celdaDescuadre.setColspan(6);
			        celdaDescuadre.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celdaDescuadre.setPadding(5.0f);
			        table2.addCell(celdaDescuadre);
					PdfPCell celdaDescuadre1 = new PdfPCell(new Phrase(totalDescuadre.toString() + " €"));
					celdaDescuadre1.setColspan(6);
					celdaDescuadre1.setHorizontalAlignment(Element.ALIGN_RIGHT);
					celdaDescuadre1.setBackgroundColor(BaseColor.RED);
					celdaDescuadre1.setPadding(5.0f);
			        table2.addCell(celdaDescuadre1);
			        
				}
			document.add(table2);
		    document.close();
	        File file = new File(path);
			Desktop.getDesktop().open(file);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}

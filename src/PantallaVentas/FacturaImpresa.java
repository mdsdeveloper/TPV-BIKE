package PantallaVentas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.FacturaHecha;
import modelo.LineaVentaRealizada;
import util.ConstantesUtil;
import util.IVAEnum;
import util.UtilService;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FacturaImpresa {
	private FacturaHecha facturaHecha;
	private String path;
	private AppService appService = new AppServiceImpl();
	private LineaVentaRealizada lineaVentaReaizada;
	private BigDecimal precio;
	private HashMap<Long, Integer> listaProductoCantidad;
	private HashMap<Long, BigDecimal> listaProductoPrecio;
	private List<Long> listaIdProductos = new ArrayList<>();
	private List<TuplaDatos> ListaTuplaDatos;
	private IVAEnum iva;
	private Image imagen = null;
	private GestionService gestionService = new GestionServiceImpl();
	private Configuracion configuracion;
	
	public FacturaImpresa(FacturaHecha facturaHecha){
		this.facturaHecha = facturaHecha;
		ListaTuplaDatos = appService.obtenerIdProductoCantidadById(facturaHecha.getIdVentaRealizada());
		String fecha = UtilService.obtenerFecha(facturaHecha.getFecha());
		iva = IVAEnum.VEINTIUNO;
		configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			path = configuracion.getDirectorioFactura()+"/"+ "Factura"+facturaHecha.getNumero()+ ".pdf";
		}else{
			path = "Factura1.pdf";
		}
//		path = ConstantesUtil.getPathPDF() != null ? ConstantesUtil.getPathPDF()+"/"+ "Factura"+ 1+ ".pdf" : "Factura"+ 1 + ".pdf";
		hacerInforme();
		
	}
	public void hacerInforme() {
		
		try {
		       Document document = new Document(PageSize.A4, 35, 35, 50, 72);
		       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
//		       ***************************************Cabecera********************************************
		       
		       HeaderFactura event = new HeaderFactura(configuracion);
		       writer.setPageEvent(event);
//		       ***************************************fin Cabecera********************************************		       
//		       ***************************************Pie de pagina***************************************
		       PdfPTable table2 = new PdfPTable(1);					 
		       table2.setTotalWidth(523);		        
		       PdfPCell celdaFinal = new PdfPCell(new Phrase(ConstantesUtil.observaciones));
		       celdaFinal.setPaddingLeft(10.0f);
		       celdaFinal.setPaddingTop(10.0f);
		       celdaFinal.setPaddingBottom(40.0f);
		       celdaFinal.setHorizontalAlignment(Element.ALIGN_LEFT);

		       table2.addCell(celdaFinal);
		       //  Obtengo una instancia de nuestro manejador de eventos
			   FooterFactura footer = new FooterFactura(table2,configuracion);
			   //Asigno el manejador de eventos al escritor.
			   writer.setPageEvent(footer);
//		       ***************************************fin Pie de pagina***************************************
		        document.open();	
		      //************************************************logo empresa***********************************************
				
			    if(configuracion.getIcono() != null){
					Image imagen = Image.getInstance(configuracion.getIcono());  
					imagen.setAlignment(Element.ALIGN_LEFT);
					document.add(imagen);
				}
			  //************************************************fin logo empresa***********************************************
		     // Crear las fuentes para el contenido y los titulos
		        Font fontContenido = FontFactory.getFont(FontFactory.TIMES_ROMAN.toString(), 11, Font.NORMAL,BaseColor.DARK_GRAY);
				Font fontTitulos = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 15, Font.UNDERLINE,BaseColor.BLACK);
				fontTitulos.setStyle(Font.BOLD);
				
				BaseColor color = new BaseColor(93, 168, 230);
				document.add(new Paragraph(Chunk.NEWLINE));
				document.add(new Paragraph(Chunk.NEWLINE));
				
				// ************************************************* mi empresa****************************************
				PdfPTable tablaDatosEmpresa = new PdfPTable(1); 
			    tablaDatosEmpresa.setWidthPercentage(40);
			    tablaDatosEmpresa.setSpacingBefore(0f);
			    tablaDatosEmpresa.setSpacingAfter(0f);
			    
			    PdfPCell cel1 = new PdfPCell(new Phrase("Empresa: " + facturaHecha.getNombre()));
			    cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
			    cel1.setPaddingTop(10f);
			    cel1.setPaddingLeft(10f);
	        	cel1.setBorderWidthBottom(0);
	        	cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			    tablaDatosEmpresa.addCell(cel1);
			    PdfPCell cel2 = new PdfPCell(new Phrase("Dirección: " + facturaHecha.getDireccion()));
			    cel2.setHorizontalAlignment(Element.ALIGN_LEFT);
			    cel2.setPaddingLeft(10f);
			    cel2.setBorderWidthTop(0);
			    cel2.setBorderWidthBottom(0);
			    cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			    tablaDatosEmpresa.addCell(cel2);
			    PdfPCell cel3 = new PdfPCell(new Phrase("CIF/ NIF: " +facturaHecha.getDniNif()));
			    cel3.setHorizontalAlignment(Element.ALIGN_LEFT);
			    cel3.setPaddingLeft(10.0f);
			    cel3.setBorderWidthTop(0);
			    cel3.setBorderWidthBottom(0);
			    cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			    tablaDatosEmpresa.addCell(cel3);
			    PdfPCell cel4 = new PdfPCell(new Phrase(configuracion.getTelefonoEmpresa() != null ? "Teléfono: " + configuracion.getTelefonoEmpresa() : "Teléfono: "));
			    cel4.setHorizontalAlignment(Element.ALIGN_LEFT);
			    cel4.setPaddingLeft(10.0f);
			    cel4.setPaddingBottom(10f);
			    cel4.setBorderWidthTop(0);
			    cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			    tablaDatosEmpresa.addCell(cel4);
			    
			    tablaDatosEmpresa.setHorizontalAlignment(Element.ALIGN_LEFT);
			    document.add(tablaDatosEmpresa);
				//************************************************fin mi empresa*********************************************
			    document.add(new Paragraph(Chunk.NEWLINE));
			
			 // *************************************************empresa cliente****************************************
				PdfPTable tablaDatosEmpresaCliente = new PdfPTable(1); 
				tablaDatosEmpresaCliente.setWidthPercentage(40);
				tablaDatosEmpresaCliente.setSpacingBefore(0f);
				tablaDatosEmpresaCliente.setSpacingAfter(0f);
				
			    
				 PdfPCell cel5 = new PdfPCell(new Phrase("Empresa: " + facturaHecha.getNombreCliente()));
				    cel5.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    cel5.setBorderColor(BaseColor.BLACK);
				    cel5.setPaddingTop(10f);
				    cel5.setPaddingLeft(10f);
		        	cel5.setBorderWidthBottom(0);
		        	cel5.setBorderWidthLeft(0);
		        	cel5.setBorderWidthRight(0);
		        	cel5.setBorderWidthTop(0);
				    PdfPCell cel6 = new PdfPCell(new Phrase("Dirección: " + facturaHecha.getDireccionCliente()));
				    cel6.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel6.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    cel6.setBorderColor(BaseColor.BLACK);
				    cel6.setPaddingLeft(10f);
				    cel6.setBorderWidthTop(0);
				    cel6.setBorderWidthBottom(0);
				    cel6.setBorderWidthLeft(0);
		        	cel6.setBorderWidthRight(0);
				   
				    PdfPCell cel7 = new PdfPCell(new Phrase("CIF/ NIF: " +facturaHecha.getDniNifCliente()));
				    cel7.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel7.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    cel7.setBorderColor(BaseColor.BLACK);
				    cel7.setPaddingLeft(10f);
				    cel7.setBorderWidthTop(0);
				    cel7.setBorderWidthBottom(0);
				    cel7.setBorderWidthLeft(0);
		        	cel7.setBorderWidthRight(0);
				   
				    PdfPCell cel8 = new PdfPCell(new Phrase(facturaHecha.getTelefonoCliente() != null ? "Teléfono: " + facturaHecha.getTelefonoCliente() : "Teléfono: " ));
				    cel8.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel8.setBackgroundColor(BaseColor.LIGHT_GRAY);
				    cel8.setBorderColor(BaseColor.BLACK);
				    cel8.setPaddingLeft(10.0f);
				    cel8.setPaddingBottom(10f);
				    cel8.setBorderWidthTop(0);
				    cel8.setBorderWidthBottom(0);
		        	cel8.setBorderWidthLeft(0);
		        	cel8.setBorderWidthRight(0);
				    
				    tablaDatosEmpresaCliente.addCell(cel5);
				    tablaDatosEmpresaCliente.addCell(cel6);
				    tablaDatosEmpresaCliente.addCell(cel7);	
				    tablaDatosEmpresaCliente.addCell(cel8);   
			    
			 // *************************************************fin empresa cliente****************************************  
			
			// *************************************************datos factura****************************************
				    PdfPTable tablaDatosFactura = new PdfPTable(1); 
				    tablaDatosFactura.setWidthPercentage(40);
				    tablaDatosFactura.setSpacingBefore(0f);
				    tablaDatosFactura.setSpacingAfter(0f);
				    
				    PdfPCell cel9 = new PdfPCell(new Phrase("Número factura: " +String.valueOf(facturaHecha.getNumero())));
				    cel9.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel9.setPaddingTop(10f);
				    cel9.setPaddingLeft(10f);
		        	cel9.setBorderWidthBottom(0);
		        	cel9.setBorderWidthLeft(0);
		        	cel9.setBorderWidthRight(0);
		        	cel9.setBorderWidthTop(0);
				    PdfPCell cel10 = new PdfPCell(new Phrase("Fecha: "+ UtilService.obtenerFecha(facturaHecha.getFecha())));
				    cel10.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel10.setPaddingLeft(10f);
				    cel10.setBorderWidthBottom(0);
		        	cel10.setBorderWidthLeft(0);
		        	cel10.setBorderWidthRight(0);
				    cel10.setBorderWidthTop(0);
				    PdfPCell cel11 = new PdfPCell();
				    cel11.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel11.setPaddingLeft(10f);
				    cel11.setBorderWidthBottom(0);
				    cel11.setBorderWidthLeft(0);
		        	cel11.setBorderWidthRight(0);
				    cel11.setBorderWidthTop(0);
				    PdfPCell cel12 = new PdfPCell();
				    cel12.setHorizontalAlignment(Element.ALIGN_LEFT);
				    cel12.setPaddingLeft(10f);
				    cel12.setBorderWidthBottom(0);
				    cel12.setBorderWidthTop(0);
				    cel12.setBorderWidthLeft(0);
		        	cel12.setBorderWidthRight(0);
				    
				    tablaDatosFactura.addCell(cel11);
				    tablaDatosFactura.addCell(cel12);
				    tablaDatosFactura.addCell(cel9);
				    tablaDatosFactura.addCell(cel10);
//				    tablaDatosFactura.setHorizontalAlignment(Element.ALIGN_LEFT);
//				    document.add(tablaDatosFactura);
				    
			// *************************************************fin datos factura****************************************
				    
				    
				    
				    PdfPTable tablaClienteDatos = new PdfPTable(2); 
				    tablaClienteDatos.setWidthPercentage(100);
				    tablaClienteDatos.setSpacingBefore(0f);
				    tablaClienteDatos.setSpacingAfter(0f);
				    tablaClienteDatos.addCell(tablaDatosFactura);			
				    tablaClienteDatos.addCell(tablaDatosEmpresaCliente);
				    
				    document.add(tablaClienteDatos);
				    
				    document.add(new Paragraph(Chunk.NEWLINE));
				    PdfPTable table = new PdfPTable(5);
					 
			        table.setWidthPercentage(100);
			        table.setSpacingBefore(0f);
			        table.setSpacingAfter(0f);
			        
			        // first row  		        
			        List<String> listaCabecera = new ArrayList<>();
			        listaCabecera.add("Producto");
			        listaCabecera.add("Importe");
			        listaCabecera.add("Cantidad");
			        listaCabecera.add("IVA");
			        listaCabecera.add("Total");
			        
			        
			        for (String texto : listaCabecera) {
			        	PdfPCell celda = new PdfPCell(new Phrase(texto));
			        	celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			        	celda.setPadding(10.0f);
			        	celda.setBorderWidthRight(0);
			        	celda.setBorderWidthLeft(0);			       
			        	celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
				        table.addCell(celda);
					}
			        
			        for (TuplaDatos tupla : ListaTuplaDatos) {
			        	PdfPCell celda = new PdfPCell(new Phrase(tupla.getNombreProducto()));
				        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
				        celda.setPadding(5.0f);
				        celda.setBorderWidthTop(0);
				        celda.setBorderWidthBottom(0);
				        table.addCell(celda);
				        BigDecimal totalIva = new BigDecimal(iva.getCodigo());
						totalIva = totalIva.divide(new BigDecimal(100));
						BigDecimal baseImponible = tupla.getPrecioProducto().subtract(tupla.getPrecioProducto().multiply(totalIva)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				        PdfPCell celda1 = new PdfPCell(new Phrase(baseImponible.toString() + " €"));
				        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
				        celda1.setPadding(5.0f);
				        celda1.setBorderWidthTop(0);
				        celda1.setBorderWidthBottom(0);
				        table.addCell(celda1);
				        PdfPCell celda2 = new PdfPCell(new Phrase(String.valueOf(tupla.getCantidadProducto())));
				        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
				        celda2.setPadding(5.0f);
				        celda2.setBorderWidthTop(0);
				        celda2.setBorderWidthBottom(0);
				        table.addCell(celda2);
				        PdfPCell celda3 = new PdfPCell(new Phrase(iva.getValor() + " %"));
				        celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
				        celda3.setPadding(5.0f);
				        celda3.setBorderWidthTop(0);
				        celda3.setBorderWidthBottom(0);
				        table.addCell(celda3);
				        PdfPCell celda4 = new PdfPCell(new Phrase(tupla.getPrecioProducto().toString() + " €"));
				        celda4.setHorizontalAlignment(Element.ALIGN_RIGHT);
				        celda4.setPadding(5.0f);
				        celda4.setBorderWidthTop(0);
				        celda4.setBorderWidthBottom(0);
				        table.addCell(celda4);
					}
			        PdfPCell celda5 = new PdfPCell();
			        celda5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda5.setBorderWidthTop(0);
			        celda5.setColspan(5);
			        table.addCell(celda5);
				    document.add(table);
				    document.add(new Paragraph(Chunk.NEWLINE));
				    
//				    ********************************************************** cuadro total**********************************************
				    PdfPTable table1 = new PdfPTable(7);					 
			        table1.setWidthPercentage(100);
			        table1.setSpacingBefore(0f);
			        table1.setSpacingAfter(0f);
					PdfPCell celdaTotalBase = new PdfPCell(new Phrase("Total base imponible"));
					celdaTotalBase.setColspan(6);
			        celdaTotalBase.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celdaTotalBase.setPadding(5.0f);
			        celdaTotalBase.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        celdaTotalBase.setBorderWidthBottom(0);
			        table1.addCell(celdaTotalBase);
			        PdfPCell celda8 = new PdfPCell(new Phrase(facturaHecha.getBaseImponible().setScale(2).toString() + " €"));
					celda8.setColspan(6);
					celda8.setBorderWidthBottom(0);
			        celda8.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda8.setPadding(5.0f);
			        celda8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        
			        table1.addCell(celda8);
			        
			        PdfPCell celdaIva = new PdfPCell(new Phrase("Total importe IVA"));
					celdaIva.setColspan(6);
					celdaIva.setBorderWidthTop(0);
					celdaIva.setBorderWidthBottom(0);
					celdaIva.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        celdaIva.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celdaIva.setPadding(5.0f);
			        table1.addCell(celdaIva);
			        PdfPCell celda9 = new PdfPCell(new Phrase(facturaHecha.getTotal().subtract(facturaHecha.getBaseImponible().setScale(2)).toString() + " €"));
					celda9.setColspan(6);
					celda9.setBorderWidthTop(0);
					celda9.setBorderWidthBottom(0);
					celda9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        celda9.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda9.setPadding(5.0f);
			        table1.addCell(celda9);
			        
			       
					PdfPCell celdaTotal = new PdfPCell(new Phrase("TOTAL"));
					celdaTotal.setColspan(6);
			        celdaTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celdaTotal.setPadding(5.0f);
			        celdaTotal.setBorderWidthTop(0);
			        celdaTotal.setBorderWidthBottom(0);
			        celdaTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        table1.addCell(celdaTotal);
			        PdfPCell celda10 = new PdfPCell(new Phrase(facturaHecha.getTotal().toString() + " €"));
					celda10.setColspan(6);
			        celda10.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celda10.setPadding(5.0f);
			        celda10.setBorderWidthTop(0);
			        celda10.setBorderWidthBottom(0);
			        celda10.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        table1.addCell(celda10);
			        
			        PdfPCell celdaFormaPago = new PdfPCell(new Phrase("Forma de pago"));
					celdaFormaPago.setColspan(6);
//					celdaFormaPago.setBorderWidthTop(0);
					celdaFormaPago.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        celdaFormaPago.setHorizontalAlignment(Element.ALIGN_LEFT);
			        celdaFormaPago.setPadding(5.0f);
			        table1.addCell(celdaFormaPago);
			        PdfPCell celdafp = new PdfPCell(new Phrase(facturaHecha.getFormaPago() ? "Tarjeta" : "Efectivo" ));
					celdafp.setColspan(6);
//					celdafp.setBorderWidthTop(0);
					celdafp.setBackgroundColor(BaseColor.LIGHT_GRAY);
			        celdafp.setHorizontalAlignment(Element.ALIGN_RIGHT);
			        celdafp.setPadding(5.0f);
			        table1.addCell(celdafp);
			        
			        document.add(table1);
			        

//				    **********************************************************fin cuadro total**********************************************
		      
			       
//			        document.add(table2);
			        
			    document.close();
		        File file = new File(path);
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.getMessage();
			}
		        
		
	}

}

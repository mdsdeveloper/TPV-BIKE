package PantallaVentas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.LineaVenta;
import util.ConstantesUtil;
import util.UtilService;


public class ImprimirTicket {
	private List<LineaVenta> lista = null;
	private Configuracion configuracion;
	private GestionService gestionService = new GestionServiceImpl();
	private AppService appService = new AppServiceImpl();
	private String direccion;
	private List<TuplaDatos> ListaTuplaDatos;
	private String path, numero, numeroDigito;
	
	public ImprimirTicket(List<LineaVenta> listaLineas, Long idLVR, boolean formaPago){
	    PrinterMatrix printer = new PrinterMatrix();	    
	    Extenso e = new Extenso();
	    numeroDigito = appService.obtenerNumeroVenta() != null ? String.valueOf(appService.obtenerNumeroVenta()): "1";
	    numero = "Número: " + numeroDigito;
	    configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			path = configuracion.getDirectorioFactura();
		}
	 
	    e.setNumber(101.85);  
	    lista = listaLineas;
	    ListaTuplaDatos = appService.obtenerIdProductoCantidadById(idLVR);
	    //Defio el tamaño del papel para la impresion  aca 25 lineas y 80 columnas
	    printer.setOutSize(60, 80);
	    //Imprimir * de la 2da linea a 25 en la columna 1;
	   // printer.printCharAtLin(2, 25, 1, "*");
	    //Imprimir * 1ra linea de la columa de 1 a 80
	   printer.printCharAtCol(1, 1, 80, "=");
	    //Imprimir Encabezado nombre del La EMpresa
	   printer.printTextWrap(2, 2, 30, 80, "TICKET DE COMPRA");
	   //printer.printTextWrap(linI, linE, colI, colE, null);
	   printer.printTextWrap(3, 3, 1, 22, numero);
	   Date hoy = new Date();
	   printer.printTextWrap(3, 3, 25, 55, "Fecha: " + UtilService.obtenerFecha(hoy));
	   printer.printTextWrap(3, 3, 60, 80, "Hora: " + UtilService.obtenerHora(hoy));
	   printer.printTextWrap(4, 4, 1, 80, "Atendido por: "+ UtilService.getEmpleadoLogado().getNombre());
	   printer.printTextWrap(5, 5, 1, 80, "CLIENTE: Cliente genérico");
	   configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			direccion = configuracion.getDireccionEmpresa();
		}
	   printer.printTextWrap(6, 6, 1, 80, direccion != null ? "DIRECCION: " + direccion : "DIRECCION: " + "");
	   printer.printCharAtCol(8, 1, 80, "=");
	   //									0						24
	   printer.printTextWrap(9, 9, 1, 80, "Nombre producto        Cantidad        Precio Producto        P.Total");
	   printer.printCharAtCol(11, 1, 80, "-");
	   
	   
	   
	   List<BigDecimal> listaBigDecimal = new ArrayList<>();
	    for (LineaVenta lineaVenta : lista) {
			BigDecimal bd = lineaVenta.getTotal();
			listaBigDecimal.add(bd);
		}
	    BigDecimal aux = BigDecimal.ZERO;
		for (BigDecimal numero : listaBigDecimal) {
			aux = aux.add(numero);
		}
		
	   int filas = ListaTuplaDatos.size();
	   int i=0;
	   for (TuplaDatos tupla : ListaTuplaDatos) {
		   printer.printTextWrap(12 + i, 12, 1, 80, tupla.getNombreProducto());
		   Integer cantidad = tupla.getCantidadProducto();
		   printer.printTextWrap(12 + i, 12, 24, 80, cantidad.toString());
		   printer.printTextWrap(12 + i, 12, 40, 80, tupla.getPrecioProducto().toString());
		   printer.printTextWrap(12 + i, 12, 63, 80, tupla.getPrecioProducto().multiply(new BigDecimal(cantidad)).toString());
		   i++;
	   }	    	    
	
	   
	    printer.printCharAtCol(13 + i, 1, 80, "=");
	    printer.printTextWrap(14 + i, 14, 1, 16, "TOTAL A PAGAR : ");
	    printer.printTextWrap(14 + i, 14, 63, 80, aux.toString());
	    
	    if(formaPago){
		    	printer.printTextWrap(16 + i, 16, 1, 80, "Pagado con tarjeta");
		    	printer.printCharAtCol(20 + i, 1, 80, "=");
		    printer.printCharAtCol(21 + i, 25, 80, "Este ticket no tiene valor fiscal, solo para uso interno.");
		    printer.printCharAtCol(24 + i, 58, 80, "Gracias por su visita!!!");
	    }else{
		    	printer.printTextWrap(16 + i, 16, 1, 21, "Efectivo entregado : ");
		    	printer.printTextWrap(16 + i, 16, 63, 80, ConstantesUtil.pagoEfectivo);
		    	printer.printTextWrap(18 + i, 18, 1, 8, "Cambio: ");
		    	printer.printTextWrap(18 + i, 18, 63, 80, ConstantesUtil.cambio);
		    	printer.printCharAtCol(21 + i, 1, 80, "=");
		    printer.printCharAtCol(22 + i, 25, 80, "Este ticket no tiene valor fiscal, solo para uso interno.");
		    printer.printCharAtCol(25 + i, 58, 80, "Gracias por su visita!!!");
	    }
	    
	    
	    printer.toFile("impresion.txt");
	
	    FileInputStream inputStream = null;
	    try {
	        inputStream = new FileInputStream("impresion.txt");
	        
	    } catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    if (inputStream == null) {
	        return;
	    }
	
	    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
	    Doc document = new SimpleDoc(inputStream, docFormat, null);
	
	    PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
	
	    PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
	
	
	    if (defaultPrintService != null) {
	        DocPrintJob printJob = defaultPrintService.createPrintJob();
	        try {
	            printJob.print(document, attributeSet);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    } else {
	    	JOptionPane.showInputDialog(this, "No hay impresoras instaladas");
	        System.err.println("No hay impresoras instaladas");
	    }
	
	 /*   try {
	   
	    	File file = new File("impresion.txt");
	    	FileInputStream inp = inputStream;
	    	FileOutputStream salida=new FileOutputStream(file);
	    	byte[] buf =new byte[1024];//Actualizado me olvide del 1024
	    	int len;
	    		while((len=inp.read(buf))>0){
	    		    salida.write(buf,0,len);
	    		}
	    	salida.close();	    
		//    file.createNewFile();
		    Desktop.getDesktop().open(file);
		    inp.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
	}
}


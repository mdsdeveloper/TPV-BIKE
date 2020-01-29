package util;

import java.util.Date;

import javax.swing.Icon;
/**
 * 
 * @author Two hands Technology
 *
 */
public class ConstantesUtil {

	public static Date dia = null;
	public static Boolean cobroTarjeta = false;
	public static String tituloCierre = "CIERRE DE CAJA";
	public static String tituloBalance = "BALANCE X";
	public static final String c = "C";
	public static final String p = "P";
	public static  double alto;
	public static  double ancho;
	public static final String passSuperAdmin = "twohands";
	public static final String  superAdmin = "SUPERADMIN";
	public static String saludo = "Gracias por su visita.";
	public static String cambio = "";
	public static String pagoEfectivo = "";
	public static final Long idProductosVarios = 1l;
	public static final String preguntaLineaPedido = "¿ Quieres editar el producto ?";
	public static final String balance = "BALANCE";
	public static final String cierre = "CIERRE CAJA";
	public static final String observaciones = "Observaciones:";
	
	
	public static final String nuevoCliente = "NUEVO CLIENTE";
	public static final String eliminarCliente = "ELIMINAR CLIENTE";
	public static final String aniadirVenta = "AÑADIR VENTA";
	
	public static final String eliminarVenta = "ELIMINAR VENTA";	
	public static final String crearFactura = "CREAR FACTURA";
	public static final String aniadirCliente = "AÑADIR CLIENTE";
	public static final String aceptar = "ACEPTAR";
	public static final String varios = "Varios";
	public static final String total = "TOTAL"; 
	public static final String tarjeta = "TARJETA";
	public static final String efectivo = "EFECTIVO";
	public static final String cerrar = "ATRAS";
	public static final String cobrar = "COBRAR";
	public static final String eliminarLinea = "ELIMINAR LINEA";
	public static final String upP = "UpProducto";
	public static final String downP = "DownProducto";
	public static final String upC = "UpCategoria";
	public static final String downC = "DownCategoria";
	public static final String productos = "PRODUCTOS";
	public static final String categorias = "CATEGORÍAS";
	public static final String empleados = "EMPLEADOS";
	public static final String ventas = "VENTAS";
	public static final String seguridad = "SEGURIDAD";
	public static final String configuracion = "CONFIGURACIÓN";
	public static final String cajas = "CAJAS";
	public static final String facturas = "FACTURAS";
	public static final String salir = "SALIR";
	public static final String tpv = "TPV";
	public static final String acceder = "ACCEDER";
	public static final String nuevo = "NUEVO";
	public static final String nueva = "NUEVA";
	public static final String modificar = "MODIFICAR";
	public static final String eliminar = "ELIMINAR";
	public static final String guardar = "GUARDAR";
	public static final String seleccionar = "SELECCIONAR";
	public static final String cancelar = "CANCELAR";
	public static final String foto = "FOTO";
	public static final String exit = "EXIT";

	public static final String opciones = "OPCIONES";
	public static final String imprimir = "IMPRIMIR";
	public static final String imprimirCierre = "Imprimir cierre";
	public static final String seleccionarCarpeta = "SELECCIONAR CARPETA";
	public static final String seleccionarIcono = "SELECCIONAR ICONO";	
	public static final String factura = "FACTURA";
	
	public static final String formularioGestion = "FORMULARIO DE GESTIÓN";
	public static final String formularioProductos = "FORMULARIO DE PRODUCTOS";
	public static final String formularioCategorias = "FORMULARIO DE CATEGORÍAS";
	public static final String formularioEmpleados = "FORMULARIO DE EMPLEADOS";
	public static final String formularioConfiguracion = "FORMULARIO DE CONFIGURACIÓN";
	public static final String formularioClientes = "FORMULARIO CLIENTES";
	
	public static final String administrador = "ADMINISTRADOR";
	public static final String empleado = "EMPLEADO";
	
	public static final String pathSalir = "/imagenes/salida.png";
	public static final String pathFlechaArriba = "/imagenes/flechaArriba.png";
	public static final String pathFlechaAbajo = "/imagenes/flechaAbajo.png";
	public static final String pathPrint = "/imagenes/print.png";
	public static final String pathCancelar = "/imagenes/cancelar.png";
	public static final String pathEliminar = "/imagenes/eliminar.png";
	public static final String pathGuardar = "/imagenes/guardar.png";
	public static final String pathNuevo = "/imagenes/nuevo.png";
	public static final String pathCobrar = "/imagenes/monedas.png";
	public static final String pathEfectivo = "/imagenes/efectivo.png";
	public static final String pathTarjeta = "/imagenes/tarjeta.png";
	public static final String pathOpciones = "/imagenes/opciones.png";
	public static final String pathBalance = "/imagenes/balance.png";
	public static final String pathExit = "/imagenes/apagar.png";
	public static final String icono = "/imagenes/icono.png";
	public static final String nombreMc = "/imagenes/nombreMc.png";
	public static final String formularioSeguridad = "FORMULARIO DE SEGURIDAD";
	public static final String imprimirTicket = "Imprimir ticket";

	
	public static String obtenerNumero(String dato){
		String numero = null;
		if(dato.contains(",")){
			numero = dato.replace(",", ".");
//			String[] s = numero.split("\\.");
//			numero = s[0];
		}
		else{
			numero = dato;
		}
		
		return numero;
	}
	
	public static String obtenerNumeroSinComa(String dato){
		String numero = dato.replace(",", ".");
		return numero;
	}
	
	public static Date getFechaCierre(){		
		return dia;
	}
	
	public static void setFechaCierre(Date hoy){
		dia = hoy;
	}
	
	public static void setCobroConTarjeta(Boolean conTarjeta){
		cobroTarjeta = conTarjeta;
	}
	public static Boolean getConTarjeta(){
		return cobroTarjeta;
	}
	
	
}

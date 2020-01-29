package PantallaVentas;

import java.math.BigDecimal;
/**
 * 
 * @author Two hands Technology
 *
 */
public class TuplaDatos {
	
	private Long idProducto;
	private BigDecimal precioProducto;
	private Integer cantidadProducto;
	private String nombreProducto;
	private Boolean productoCambiado;
	public TuplaDatos(){
		
	}
	public TuplaDatos(Long id, BigDecimal precio, Integer cantidad){
		idProducto = id;
		precioProducto = precio;
		cantidad = cantidadProducto;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public BigDecimal getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(BigDecimal precioProducto) {
		this.precioProducto = precioProducto;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public void setIsProductoCambiado(Boolean productoCambiado) {
				this.productoCambiado = productoCambiado;
	}
	public Boolean isProductoCambiado(){
		return productoCambiado;
	}
	
	

}

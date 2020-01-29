package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Two hands Technology
 *
 */
@Entity
@Table(name="lineaVentas")
public class LineaVenta implements Serializable{

	private static final long serialVersionUID = 917722140483104331L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLineaVenta;
	@Column(name="cantidad")
	private Integer cantidad;
	@Column(name="total")
	private BigDecimal total;
//	@ManyToOne
//	@JoinColumn(name="idVenta")
//	private Venta venta;
//	@OneToOne(cascade={CascadeType.PERSIST})
	@Column(name="idProducto")
	private Long idProducto;
	@Column(name="importe")
	private BigDecimal importe;
	@Column(name="productoCambiado")
	private Boolean productoCambiado;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="importeCambiadoBruto")
	private BigDecimal importeCambiadoBruto;
	@Column(name="importeCambiadoNeto")
	private BigDecimal importeCambiadoNeto;
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getIdLineaVenta() {
		return idLineaVenta;
	}
	public void setIdLineaVenta(Long idLineaVenta) {
		this.idLineaVenta = idLineaVenta;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer unidades) {
		this.cantidad = unidades;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
//	public Venta getVenta() {
//		return venta;
//	}
//	public void setVenta(Venta venta) {
//		this.venta = venta;
//	}
	public Long getProducto() {
		return idProducto;
	}
	public void setProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public void sumarUnidad(Long idLineaVenta){
		this.cantidad ++;
	}
	public void restarUnidad(Long idLineaVenta){
		this.cantidad --;
	}
	public void incrementarUnidades(Integer unidades){
		this.cantidad += unidades;
	}
	public void disminuirUnidades(Integer unidades){
		this.cantidad -= unidades;
	}
	public Boolean getProductoCambiado() {
		return productoCambiado;
	}
	public void setProductoCambiado(Boolean productoCambiado) {
		this.productoCambiado = productoCambiado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getImporteCambiadoBruto() {
		return importeCambiadoBruto;
	}
	public void setImporteCambiadoBruto(BigDecimal importeCambiadoBruto) {
		this.importeCambiadoBruto = importeCambiadoBruto;
	}
	public BigDecimal getImporteCambiadoNeto() {
		return importeCambiadoNeto;
	}
	public void setImporteCambiadoNeto(BigDecimal importeCambiadoNeto) {
		this.importeCambiadoNeto = importeCambiadoNeto;
	}	
	
	
}

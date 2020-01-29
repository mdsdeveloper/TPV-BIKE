package dao;

import java.util.Date;

import modelo.ProductosVarios;

public interface ProductosVariosDao {

	public Long createProductosVarios(ProductosVarios productosVarios);
	public ProductosVarios readProductosVarios(Long idProductosVarios);
	public void upDateProductosVarios(ProductosVarios producto);
	public void deleteProductosVarios(ProductosVarios producto);
	public modelo.ProductosVarios obtenerProductoVarios(Long idProducto, Long idVentaRealizada, Date fecha);
}

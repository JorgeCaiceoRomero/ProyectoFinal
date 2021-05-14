package com.tienda.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "id_usuario", nullable = true)
    private long usuarios;

    @Column(name = "fecha", nullable = true)
    private Timestamp fecha;

    @Column(name = "metodo_pago", nullable = true)
    private String metodoPago;

    @Column(name = "estado", nullable = true)
    private String estado;

    @Column(name = "num_factura", nullable = true)
    private String numFactura;

    @Column(name = "total", nullable = true)
    private double total;
    
    public Pedido() {
    	
    }
    
	public Pedido(long id, long usuarios, Timestamp fecha, String metodoPago, String estado, String numFactura,
			double total) {
		super();
		this.id = id;
		this.usuarios = usuarios;
		this.fecha = fecha;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(long usuarios) {
		this.usuarios = usuarios;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
    
}

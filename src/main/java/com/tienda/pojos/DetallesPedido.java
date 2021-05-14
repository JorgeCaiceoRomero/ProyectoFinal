package com.tienda.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detalles_pedido")
public class DetallesPedido implements Serializable {

		@Id
		@Column(name = "id")
		private long id;

		@Column(name = "id_pedido", nullable = true)
		private long pedidos;

		@Column(name = "id_producto", nullable = true)
		private long productos;

		@Column(name = "precio_unidad", nullable = true)
		private float precioUnidades;

		@Column(name = "unidades", nullable = true)
		private int unidades;

		@Column(name = "impuesto")
		private float impuesto;

		@Column(name = "total")
		private double total;
		
		public DetallesPedido() {
			
		}
		
		public DetallesPedido(long id, long pedidos, long productos, float precioUnidades, int unidades, float impuesto,
				double total) {
			super();
			this.id = id;
			this.pedidos = pedidos;
			this.productos = productos;
			this.precioUnidades = precioUnidades;
			this.unidades = unidades;
			this.impuesto = impuesto;
			this.total = total;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getPedidos() {
			return pedidos;
		}

		public void setPedidos(long pedidos) {
			this.pedidos = pedidos;
		}

		public long getProductos() {
			return productos;
		}

		public void setProductos(long productos) {
			this.productos = productos;
		}

		public float getPrecioUnidades() {
			return precioUnidades;
		}

		public void setPrecioUnidades(float precioUnidades) {
			this.precioUnidades = precioUnidades;
		}

		public int getUnidades() {
			return unidades;
		}

		public void setUnidades(int unidades) {
			this.unidades = unidades;
		}

		public float getImpuesto() {
			return impuesto;
		}

		public void setImpuesto(float impuesto) {
			this.impuesto = impuesto;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}
		
		

}
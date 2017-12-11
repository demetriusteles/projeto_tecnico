package com.betha.projeto_manutencao.model.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "pedidos")
@Table(name = "pedidos")
@SequenceGenerator(name = "sequencePedido", sequenceName = "seq_pedidos", allocationSize = 1, initialValue = 1)
public class Pedidos implements BaseModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencePedido")
	private Integer id;

	@Column(name = "tipo_maquina", nullable = true)
	private String tipoMaquina;

	@Column(name = "marca", nullable = true)
	private String marca;

	@Column(name = "problema", nullable = true)
	private String problema;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cliente", nullable = true)
	private Clientes idCliente;

	@Column(name = "dt_entrada", nullable = true)
	private Calendar dtEntrada;
	
	@Column(name = "foto", nullable = true)
	private String foto;
	
	@Column(name = "status", nullable = true)
	private String status;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the tipoMaquina
	 */
	public String getTipoMaquina() {
		return tipoMaquina;
	}

	/**
	 * @param tipoMaquina the tipoMaquina to set
	 */
	public void setTipoMaquina(String tipoMaquina) {
		this.tipoMaquina = tipoMaquina;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the problema
	 */
	public String getProblema() {
		return problema;
	}

	/**
	 * @param problema the problma to set
	 */
	public void setProblema(String problema) {
		this.problema = problema;
	}

	/**
	 * @return the idCliente
	 */
	public Clientes getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Clientes idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the dtEntrada
	 */
	public Calendar getDtEntrada() {
		return dtEntrada;
	}

	/**
	 * @param dtEntrada the dtEntrada to set
	 */
	public void setDtEntrada(Calendar dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos other = (Pedidos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", tipoMaquina=" + tipoMaquina + ", marca=" + marca + ", problema=" + problema
				+ ", idCliente=" + idCliente + ", dtEntrada=" + dtEntrada + ", foto=" + foto + "]";
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
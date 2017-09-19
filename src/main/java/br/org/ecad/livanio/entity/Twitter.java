package br.org.ecad.livanio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_twitter")
public class Twitter {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="codigo")
		private Integer id_texto;	
		
		@Column(name="texto")
		private String texto;

		public Integer getId_texto() {
			return id_texto;
		}

		public void setId_texto(Integer id_texto) {
			this.id_texto = id_texto;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public Twitter() {}

		public Twitter(Integer id_texto, String texto) {
			this.id_texto = id_texto;
			this.texto = texto;
		}
		
		
	
	

}

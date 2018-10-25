package hospital.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catalogos database table.
 * 
 */
@Entity
@Table(name="catalogo")
@NamedQuery(name="Catalogo.findAll", query="SELECT c FROM Catalogo c")
public class Catalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cat_codigo")
	private String id;

	@Column(name="cat_nombre")
	private String nombre;

	@Column(name="cat_descripcion")
	private String tipo;

	public Catalogo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
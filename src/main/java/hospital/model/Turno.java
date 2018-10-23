package hospital.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the turnos database table.
 * 
 */
@Entity
@Table(name="turnos")
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer historia;

	private Integer nro;

	//bi-directional many-to-one association to Profesional
	@ManyToOne
	@JoinColumn(name="profesional")
	private Profesional profesionalBean;

	public Turno() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHistoria() {
		return this.historia;
	}

	public void setHistoria(Integer historia) {
		this.historia = historia;
	}

	public Integer getNro() {
		return this.nro;
	}

	public void setNro(Integer nro) {
		this.nro = nro;
	}

	public Profesional getProfesionalBean() {
		return this.profesionalBean;
	}

	public void setProfesionalBean(Profesional profesionalBean) {
		this.profesionalBean = profesionalBean;
	}

}
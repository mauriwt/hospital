package hospital.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the discapacidades database table.
 * 
 */
@Entity
@Table(name="discapacidades")
@NamedQuery(name="Discapacidad.findAll", query="SELECT d FROM Discapacidad d")
public class Discapacidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double porcentaje;

	private String tipo;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="persona")
	private Persona personaBean;

	public Discapacidad() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Persona getPersonaBean() {
		return this.personaBean;
	}

	public void setPersonaBean(Persona personaBean) {
		this.personaBean = personaBean;
	}

}
package hospital.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the profesional database table.
 * 
 */
@Entity
@NamedQuery(name="Profesional.findAll", query="SELECT p FROM Profesional p")
public class Profesional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="profesionalBean")
	private List<Consulta> consultas;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="persona")
	private Persona personaBean;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="profesionalBean")
	private List<Turno> turnos;

	public Profesional() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setProfesionalBean(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setProfesionalBean(null);

		return consulta;
	}

	public Persona getPersonaBean() {
		return this.personaBean;
	}

	public void setPersonaBean(Persona personaBean) {
		this.personaBean = personaBean;
	}

	public List<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno addTurno(Turno turno) {
		getTurnos().add(turno);
		turno.setProfesionalBean(this);

		return turno;
	}

	public Turno removeTurno(Turno turno) {
		getTurnos().remove(turno);
		turno.setProfesionalBean(null);

		return turno;
	}

}
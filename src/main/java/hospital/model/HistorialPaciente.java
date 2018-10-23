package hospital.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the historial_pacientes database table.
 * 
 */
@Entity
@Table(name="historial_pacientes")
@NamedQuery(name="HistorialPaciente.findAll", query="SELECT h FROM HistorialPaciente h")
public class HistorialPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer nro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="historialPaciente")
	private List<Consulta> consultas;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="persona")
	private Persona personaBean;

	//bi-directional many-to-one association to SignosPaciente
	@OneToMany(mappedBy="historialPaciente")
	private List<SignosPaciente> signosPacientes;

	public HistorialPaciente() {
	}

	public Integer getNro() {
		return this.nro;
	}

	public void setNro(Integer nro) {
		this.nro = nro;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setHistorialPaciente(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setHistorialPaciente(null);

		return consulta;
	}

	public Persona getPersonaBean() {
		return this.personaBean;
	}

	public void setPersonaBean(Persona personaBean) {
		this.personaBean = personaBean;
	}

	public List<SignosPaciente> getSignosPacientes() {
		return this.signosPacientes;
	}

	public void setSignosPacientes(List<SignosPaciente> signosPacientes) {
		this.signosPacientes = signosPacientes;
	}

	public SignosPaciente addSignosPaciente(SignosPaciente signosPaciente) {
		getSignosPacientes().add(signosPaciente);
		signosPaciente.setHistorialPaciente(this);

		return signosPaciente;
	}

	public SignosPaciente removeSignosPaciente(SignosPaciente signosPaciente) {
		getSignosPacientes().remove(signosPaciente);
		signosPaciente.setHistorialPaciente(null);

		return signosPaciente;
	}

}
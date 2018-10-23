package hospital.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the consultas database table.
 * 
 */
@Entity
@Table(name="consultas")
@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_consulta")
	private Date fechaConsulta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_proxima_consulta")
	private Date fechaProximaConsulta;

	private String sintoma;

	private String tratamiento;

	//bi-directional many-to-one association to HistorialPaciente
	@ManyToOne
	@JoinColumn(name="historia")
	private HistorialPaciente historialPaciente;

	//bi-directional many-to-one association to Profesional
	@ManyToOne
	@JoinColumn(name="profesional")
	private Profesional profesionalBean;

	public Consulta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaConsulta() {
		return this.fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public Date getFechaProximaConsulta() {
		return this.fechaProximaConsulta;
	}

	public void setFechaProximaConsulta(Date fechaProximaConsulta) {
		this.fechaProximaConsulta = fechaProximaConsulta;
	}

	public String getSintoma() {
		return this.sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public String getTratamiento() {
		return this.tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public HistorialPaciente getHistorialPaciente() {
		return this.historialPaciente;
	}

	public void setHistorialPaciente(HistorialPaciente historialPaciente) {
		this.historialPaciente = historialPaciente;
	}

	public Profesional getProfesionalBean() {
		return this.profesionalBean;
	}

	public void setProfesionalBean(Profesional profesionalBean) {
		this.profesionalBean = profesionalBean;
	}

}
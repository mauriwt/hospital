package hospital.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the personas database table.
 * 
 */
@Entity
@Table(name="personas")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String apellidos;

	private String cedula;

	@Column(name="estado_civil")
	private String estadoCivil;

	private String etnia;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name="lugar_nacimiento")
	private String lugarNacimiento;

	private String nacionalidad;

	private String nombres;

	private String sexo;

	@Column(name="tipo_sangre")
	private String tipoSangre;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="personaBean")
	private List<Direccion> direcciones;

	//bi-directional many-to-one association to Discapacidad
	@OneToMany(mappedBy="personaBean")
	private List<Discapacidad> discapacidades;

	//bi-directional many-to-one association to HistorialPaciente
	@OneToMany(mappedBy="personaBean")
	private List<HistorialPaciente> historialPacientes;

	//bi-directional many-to-one association to Profesional
	@OneToMany(mappedBy="personaBean")
	private List<Profesional> profesionals;

	public Persona() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEtnia() {
		return this.etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoSangre() {
		return this.tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public List<Direccion> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Direccion addDireccione(Direccion direccione) {
		getDirecciones().add(direccione);
		direccione.setPersonaBean(this);

		return direccione;
	}

	public Direccion removeDireccione(Direccion direccione) {
		getDirecciones().remove(direccione);
		direccione.setPersonaBean(null);

		return direccione;
	}

	public List<Discapacidad> getDiscapacidades() {
		return this.discapacidades;
	}

	public void setDiscapacidades(List<Discapacidad> discapacidades) {
		this.discapacidades = discapacidades;
	}

	public Discapacidad addDiscapacidade(Discapacidad discapacidade) {
		getDiscapacidades().add(discapacidade);
		discapacidade.setPersonaBean(this);

		return discapacidade;
	}

	public Discapacidad removeDiscapacidade(Discapacidad discapacidade) {
		getDiscapacidades().remove(discapacidade);
		discapacidade.setPersonaBean(null);

		return discapacidade;
	}

	public List<HistorialPaciente> getHistorialPacientes() {
		return this.historialPacientes;
	}

	public void setHistorialPacientes(List<HistorialPaciente> historialPacientes) {
		this.historialPacientes = historialPacientes;
	}

	public HistorialPaciente addHistorialPaciente(HistorialPaciente historialPaciente) {
		getHistorialPacientes().add(historialPaciente);
		historialPaciente.setPersonaBean(this);

		return historialPaciente;
	}

	public HistorialPaciente removeHistorialPaciente(HistorialPaciente historialPaciente) {
		getHistorialPacientes().remove(historialPaciente);
		historialPaciente.setPersonaBean(null);

		return historialPaciente;
	}

	public List<Profesional> getProfesionals() {
		return this.profesionals;
	}

	public void setProfesionals(List<Profesional> profesionals) {
		this.profesionals = profesionals;
	}

	public Profesional addProfesional(Profesional profesional) {
		getProfesionals().add(profesional);
		profesional.setPersonaBean(this);

		return profesional;
	}

	public Profesional removeProfesional(Profesional profesional) {
		getProfesionals().remove(profesional);
		profesional.setPersonaBean(null);

		return profesional;
	}

}
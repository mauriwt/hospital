package hospital.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the signos_pacientes database table.
 * 
 */
@Entity
@Table(name="signos_pacientes")
@NamedQuery(name="SignosPaciente.findAll", query="SELECT s FROM SignosPaciente s")
public class SignosPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String movito;

	private double peso;

	private double presion;

	private double talla;

	private double temperatura;

	//bi-directional many-to-one association to HistorialPaciente
	@ManyToOne
	@JoinColumn(name="historia")
	private HistorialPaciente historialPaciente;

	public SignosPaciente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMovito() {
		return this.movito;
	}

	public void setMovito(String movito) {
		this.movito = movito;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPresion() {
		return this.presion;
	}

	public void setPresion(double presion) {
		this.presion = presion;
	}

	public double getTalla() {
		return this.talla;
	}

	public void setTalla(double talla) {
		this.talla = talla;
	}

	public double getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public HistorialPaciente getHistorialPaciente() {
		return this.historialPaciente;
	}

	public void setHistorialPaciente(HistorialPaciente historialPaciente) {
		this.historialPaciente = historialPaciente;
	}

}
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;

public class DadosCliente {
	private int idPessoa;
	private int idEstacao;
	private int Requisicao;
	private GregorianCalendar hora;
	

	public DadosCliente(int idPessoa, int idEstacao, int requisicao, GregorianCalendar hora) {
		this.idPessoa = idPessoa;
		this.idEstacao = idEstacao;
		this.Requisicao = requisicao;
		this.hora = hora;
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public int getIdEstacao() {
		return idEstacao;
	}
	public void setIdEstacao(int idEstacao) {
		this.idEstacao = idEstacao;
	}
	public int getRequisicao() {
		return Requisicao;
	}
	public void setRequisicao(int requisicao) {
		this.Requisicao = requisicao;
	}
	public GregorianCalendar getHora() {
		return hora;
	}
	public void setHora(GregorianCalendar hora) {
		this.hora = hora;
	}
}

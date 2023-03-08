package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
@Getter
@Setter
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private String email;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	@Embedded
	private Endereco endereco;

	private String telefone;

	private Boolean ativo;

	public Medico(DadosCadastroMedico dadosMedico) {
		this.nome = dadosMedico.nome();
		this.email = dadosMedico.email();
		this.crm = dadosMedico.crm();
		this.especialidade = dadosMedico.especialidade();
		this.endereco = new Endereco(dadosMedico.endereco());
		this.telefone = dadosMedico.telefone();
		this.ativo = true;
	}

	public void atualizarInformacoes(DadosAtualizacaoMedico dadosMedico) {

		if (dadosMedico.nome() != null) {
			this.nome = dadosMedico.nome();
		}

		if (dadosMedico.telefone() != null) {
			this.telefone = dadosMedico.telefone();
		}

		if (dadosMedico.endereco() != null) {
			this.endereco.atualizarInformacoes(dadosMedico.endereco());
		}

	}

	public void excluir() {
		this.ativo = false;
	}

}

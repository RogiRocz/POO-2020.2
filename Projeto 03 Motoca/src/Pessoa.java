public class Pessoa {
	private String nome;
	private int idade;

	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public String getNome() {
		return this.nome;
	}

	public int getIdade() {
		return this.idade;
	}

	@Override
	public String toString() {
		return "pessoa [nome=" + nome + ", idade=" + idade + "]";
	}
}
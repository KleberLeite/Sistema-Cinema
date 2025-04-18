package cinemax.backend.funcionarios;

public class Funcionario {
   private String nome;
   private String cpf;
   private CargoFuncionario cargo;
   private String telefone;
   private String senha;

   public Funcionario(String nome, String cpf, CargoFuncionario cargo, String telefone, String senha) {
       this.nome = nome;
       this.cpf = cpf;
       this.cargo = cargo;
       this.telefone = telefone;
       this.senha = senha;
   }

   public String getNome() {
       return nome;
   }

   public String getCpf() {
       return cpf;
   }

   public CargoFuncionario getCargo() {
       return cargo;
   }

   public String getTelefone() {
       return telefone;
   }

   public String getSenha() {
       return senha;
   }

   protected void setNome(String nome) {
       this.nome = nome;
   }

   protected void setCpf(String cpf) {
       this.cpf = cpf;
   }

   protected void setCargo(CargoFuncionario cargo) {
       this.cargo = cargo;
   }

   protected void setTelefone(String telefone) {
       this.telefone = telefone;
   }

   protected void setSenha(String senha) {
       this.senha = senha;
   }
}

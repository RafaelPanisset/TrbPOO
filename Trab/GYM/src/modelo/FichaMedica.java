package modelo;

import javax.swing.JOptionPane;
import persistencia.DMFichaMedica;
import persistencia.DMPlano;

public class FichaMedica {
	private int id;
	private float peso;
	private float altura;
	private float imc;
	
	private Cliente cliente; 
	private DMFichaMedica dmFichaMedica;
	
	public FichaMedica(float peso, float altura, float imc, Cliente cpfCliente) {
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.cliente = cpfCliente;
		dmFichaMedica = new DMFichaMedica();
    	dmFichaMedica.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conexão feita à tabela Cliente com sucesso!");
	}
	
	public FichaMedica(Cliente cliente) {
		this.cliente = cliente;
		dmFichaMedica = new DMFichaMedica();
    	dmFichaMedica.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conexão feita à tabela Cliente com sucesso!");
	}

	public FichaMedica() {
		dmFichaMedica = new DMFichaMedica();
    	dmFichaMedica.conectaDataBase("db_academia","root","mysql");
    	System.out.println("Conexão feita à tabela Cliente com sucesso!");
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public float getImc() {
		return imc;
	}
	
	public void setImc(float imc) {
		this.imc = imc;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void incluir(FichaMedica ficha) {
		if ( ficha.getPeso() == 0.0 && ficha.getAltura() == 0.0 && ficha.getImc() == 0.0){   
			JOptionPane.showMessageDialog(null,"Todos os campos são obrigatórios!","AVISO",JOptionPane.WARNING_MESSAGE);
            System.out.println("Os campos são obrigatórios!");
        }
        else{   
        	if (pesquisar(ficha) != null){   
        		JOptionPane.showMessageDialog(null,"Cadastro da ficha medica não realizado!\n Já existe uma cliente com este cpf!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Cadastro da ficha medica não realizado! Já existe um cliente com este cpf!"); 
            }
            else { dmFichaMedica.incluir(this); }
        }
	}
	
	public Object pesquisar(FichaMedica ficha) {
		System.out.println("Metodo Pesquisa por ID do cliente: "+ficha);
     	return (Plano) dmFichaMedica.consultar(ficha);
	}
	
	public Object pesquisar(String plano) {
		System.out.println("Metodo Pesquisa por ID do cliente: "+plano);
     	return (Plano) dmFichaMedica.consultar(plano);
	}

	public void imprimir(){	
		System.out.println("Metodo imprimir - Ficha Medica");
		JOptionPane.showMessageDialog(null,"ID: "+ this.id + "\nAltura" + this.altura + "\nPeso: " + this.peso + "\nIMC: " + this.imc +
				                       "\nCpf do Cliente = "+ this.getCliente().getCpf());
	}
	
	public Object consultar(){  
		return dmFichaMedica.consultar(this);  
	}
	
	public void excluir(Plano plano){   
		if (cliente.getCpf().equals(""))
        {   JOptionPane.showMessageDialog(null,"CPF é obrigatório!","Mensagem de alerta",JOptionPane.WARNING_MESSAGE);
            System.out.println("O cpf é obrigatório!");
        }
        else {   
        	if (dmFichaMedica.consultar(this)!= null){ 
        		dmFichaMedica.excluir(this); 
        		this.cliente = null;
            }
        
            else {   
            	JOptionPane.showMessageDialog(null,"Exclusão do cliente não realizada!\n Este cliente não existe!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
                System.out.println("Exclusão de cliente não realizada!\n Este cliente não existe!");
            }
        }    
    }
	
	public void alterar(){   
		if (dmFichaMedica.consultar(this)!= null)
        { dmFichaMedica.alterar(this); }
        else
        {   JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            System.out.println("Impossivel alterar este cliente !"); }
    }

    
    public void shutDown(){   
    	dmFichaMedica.shutDown();   
    }
}


package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Cliente;
import modelo.Registro;

public class DMRegistro extends DMGeral{
	String cpf = null;
    
    public void incluir(Object obj){  
    	Registro registro = (Registro) obj;
    	Cliente cliente;
    	cliente = new Cliente();
    	cliente = (Cliente) cliente.pesquisar(registro.getCliente().getCpf());

        try
        {   
        	Statement statement = getConnection().createStatement();
             
			
            String incluirSQL = "INSERT INTO tb_registro_treinos (" +
                                "num_semana,ano,dia_semana,cliente) VALUES ('" +
                                registro.getNumSemana()+ "', '" +
                                registro.getAno() + "', '" +
                                registro.getDiaSemana() + "', '" +
                                cliente.getId() + "')";
            
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            
            int result = statement.executeUpdate(incluirSQL);

            if (result == 1){   
            	JOptionPane.showMessageDialog(null,"registro cadastrada corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);}
            else{   
            	JOptionPane.showMessageDialog(null,"Erro!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	registro.setNumSemana(0);   
            	registro.setAno(0);
            	registro.setDiaSemana(0);
            	registro.setCliente(null);
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL!"); }
    }

    public Object consultar(Object obj){   
    	
    	Registro registro = (Registro) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_registro WHERE num_semana = '" + registro.getNumSemana() + "'  AND "
            		+ " (ano = '"+ registro.getAno()+"') AND"
            		+ " (cliente = '"+ registro.getDiaSemana()+"') AND "
            		+ " (cliente = '"+ registro.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next()){   
            	System.out.println("Cliente existente !" );
                System.out.println("Dados do CLiente");
                System.out.println("Cpf.............: "+ result.getString("cpf"));
                System.out.println("Nome...............: "+ result.getString("nome"));
                System.out.println("Data de Nascimento.: "+ result.getString("dataNascimento"));
                
                registro.setId(Integer.parseInt(result.getString("id")));
                registro.setNumSemana(Integer.parseInt(result.getString("num_semana")));   
            	registro.setAno(Integer.parseInt(result.getString("ano")));
            	registro.setDiaSemana(Integer.parseInt(result.getString("dia_semana")));
            	registro.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else{   
            	System.out.println( "Cliente não encontrada !\n" );
            	registro = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do cliente !"); }
        return registro;
    }

    public Object consultar(String cpf){   
    	
    	Registro registro = new Registro();   
        System.out.println("Metodo Consultar de DMCliente - CPF: "+cpf);
        try
        {   
        	Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_registro WHERE num_semana = '" + registro.getNumSemana() + "'  AND "
            		+ " (ano = '"+ registro.getAno()+"') AND"
            		+ " (cliente = '"+ registro.getDiaSemana()+"') AND "
            		+ " (cliente = '"+ registro.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("Registro existente !" );
                
                registro.setId(Integer.parseInt(result.getString("id")));
                registro.setNumSemana(Integer.parseInt(result.getString("num_semana")));   
            	registro.setAno(Integer.parseInt(result.getString("ano")));
            	registro.setDiaSemana(Integer.parseInt(result.getString("dia_semana")));
            	registro.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"OPS!!  Erro ao consultar registro!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	System.out.println( "Pagamento não encontrada !\n" );
            	registro = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	JOptionPane.showMessageDialog(null,"OPS!!  Erro no Banco de Dados, tente mais tarde!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        	System.out.println("Problemas com o SQL de consulta do cliente !");
        }
                
        return registro;
    }
    

    public void excluir(Object obj){
    	
    	Registro objPagamento = (Registro) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String excluirSQL = "DELETE FROM tb_registro WHERE (cliente = '"+objPagamento.getCliente().getCpf()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(excluirSQL) + "\n");
            int result = statement.executeUpdate(excluirSQL);
            if (result == 1)
            {   JOptionPane.showMessageDialog(null,"Registro excluído corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }
            else
            { 
            	System.out.println( "\nErro ao excluir cliente !\n" ); }
            
            statement.close();
        }
        catch (SQLException e)
        { 
        	JOptionPane.showMessageDialog(null,"OPS!!  Erro no Banco de Dados, tente mais tarde!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        	System.out.println("Problemas com o SQL de exclusão do cliente !"); 
        }
        
    }

    public void alterar(Object obj){  
    	
    	Registro objPagamento = (Registro) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String alterarSQL = "UPDATE tb_cliente SET num_semana = '"+ objPagamento.getNumSemana() + "', '" +
			                    "ano"+objPagamento.getAno() + "', '" +
			                    "dia_semana"+objPagamento.getDiaSemana() + "', '" +
			                    "cliente"+objPagamento.getCliente().getCpf() + "')";
            
            int result = statement.executeUpdate(alterarSQL);
            if (result == 1){	
            	JOptionPane.showMessageDialog(null,"Dados do cliente alterado!");
            	System.out.println( "Cliente alterado corretamente !"); 
            }else{   
            	JOptionPane.showMessageDialog(null,"Impossivel alterar este cliente !");
            	System.out.println( "\nErro ao alterar cliente !\n" );
            }
        }
        catch (SQLException e)
        { 
        	JOptionPane.showMessageDialog(null,"OPS!!  Erro no Banco de Dados, tente mais tarde!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
        	System.out.println("Problemas com o SQL de atualização do cliente !"); 
        }
 
    }

    public void shutDown()
    {   try
        { getConnection().close(); }
        catch (SQLException sqlex)
        {   System.err.println("Problemas ao desconectar !");
            sqlex.printStackTrace();
        }
    }
}

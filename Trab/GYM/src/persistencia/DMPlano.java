package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Plano;

public class DMPlano extends DMGeral {
	String cpf = null;
    
    public void incluir(Object obj){  
    	Plano plano = (Plano) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
            String incluirSQL = "INSERT INTO tb_plano (mensalidade, horas_diarias, cliente) VALUES ('" +
                                plano.getMensalidade() + "', '" +
                                plano.getHorasDiarias() + "', '" +
                                plano.getCliente().getId()+ "')";
            
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1){   
            	JOptionPane.showMessageDialog(null,"Plano cadastrada corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);}
            else{   
            	JOptionPane.showMessageDialog(null,"Erro ao cadastrar plano!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
            	plano.setMensalidade(0);
                plano.setHorasDiarias(0);
                plano.setCliente(null);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclusão do plano!");
        }
    }

    public Object consultar(Object obj){   
    	
    	Plano plano = (Plano) obj;
    	
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_plano "
            						+ " WHERE (cliente = '"+ plano.getCliente().getId() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            ResultSet result = statement.executeQuery(consultarSQL);
            
            if (result.next()){   
            	System.out.println("Cliente existente !" );
                System.out.println("Dados do Plano");
                System.out.println("mensalidade.............: "+ result.getString("mensalidade"));
                System.out.println("horas por dia...............: "+ result.getString("horas_diarias"));
                System.out.println("id do Cliente: "+ result.getString("cliente"));
                
                plano.setId(Integer.parseInt(result.getString("id")));
                plano.setMensalidade(Integer.parseInt(result.getString("mensalidade")));
                plano.setHorasDiarias(Integer.parseInt(result.getString("horas_diarias")));
                plano.getCliente().setId(Integer.parseInt(result.getString("cliente")));
                result.close();
            }
            else{   
            	System.out.println( "Plano não encontrada !\n" );
            	plano = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do plano !"); }
        return plano;
    }

    public Object consultar(String cpf){   
    	
        Plano plano = new Plano();   
        
        System.out.println("Metodo Consultar de DMCliente - CPF: "+cpf);
        try
        {   Statement statement = getConnection().createStatement();
            String consultarSQL = "SELECT * FROM tb_plano "
									+ " WHERE (cliente = '"+ plano.getCliente().getId() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            
            if (result.next())
            {   System.out.println("Cliente existente !" );
            	System.out.println("Id.............: "+ result.getString("id"));
                System.out.println("mensalidade.............: "+ result.getString("mensalidade"));
                System.out.println("horas por dia...............: "+ result.getString("horas_diarias"));
                System.out.println("id do Cliente: "+ result.getString("cliente"));
                
                plano.setId(Integer.parseInt(result.getString("id")));
                plano.setMensalidade(Integer.parseInt(result.getString("mensalidade")));
                plano.setHorasDiarias(Integer.parseInt(result.getString("horas_diarias")));
                result.close();
            }
            else
            {   System.out.println( "plano não encontrada !\n" );
            	plano = null;
            }
            statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de consulta do plano !"); }
                
        return plano;
    }
    

    public void excluir(Object obj){
    	
    	Plano objCliente = (Plano) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String excluirSQL = "DELETE FROM tb_plano WHERE (cliente = '"+objCliente.getCliente().getId()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(excluirSQL) + "\n");
            int result = statement.executeUpdate(excluirSQL);
            if (result == 1)
            {   JOptionPane.showMessageDialog(null,"plano excluído corretamente !","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }
            else
            {   System.out.println( "\nErro ao excluir plano !\n" );
               // objCliente.se(" ");
            }
           statement.close();
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de exclusão do plano !"); }
        
    }

    public void alterar(Object obj){  
    	
    	Plano objCliente = (Plano) obj;
        try
        {   Statement statement = getConnection().createStatement();
            String alterarSQL = "UPDATE tb_plano SET mensalidade = '"+objCliente.getMensalidade()+"' ,"+
                                "horas_diarias = '"+objCliente.getHorasDiarias()+"' " +
                                "WHERE (id = '"+objCliente.getCliente().getId()+"') ";
            
            int result = statement.executeUpdate(alterarSQL);
            if (result == 1)
            {	JOptionPane.showMessageDialog(null,"Dados do plano alterado!");
            	System.out.println( "plano alterado corretamente !"); }
            else
            {   JOptionPane.showMessageDialog(null,"Impossivel alterar este plano !");
            	System.out.println( "\nErro ao alterar cliente !\n" );
                //objCliente.setNome("");
            }
        }
        catch (SQLException e)
        { System.out.println("Problemas com o SQL de atualização do plano !"); }
 
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

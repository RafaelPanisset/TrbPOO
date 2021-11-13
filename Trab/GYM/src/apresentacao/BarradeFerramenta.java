package apresentacao;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class BarradeFerramenta
{   
	Object gui;

    public JToolBar make()
    {   
    	JToolBar BFerramenta = new JToolBar();
        BFerramenta.setFloatable(true);

        Action botao1Action = new AbstractAction("Cliente", new ImageIcon("../GYM/src/images/cliente.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jCliente"); }
        };
        
        Action botao2Action = new AbstractAction("Plano", new ImageIcon("../GYM/src/images/plano.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jPlano"); }
        };
        
        Action botao3Action = new AbstractAction("Ficha Medica", new ImageIcon("../GYM/src/images/fichaMedica.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jFichaMedica"); }
        };
        
        Action botao4Action = new AbstractAction("Pagamento", new ImageIcon("../GYM/src/images/pagamento.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jPagamento"); }
        };
        
        Action botao5Action = new AbstractAction("Registro", new ImageIcon("../GYM/src/images/registro.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jRegistro"); }
        };
        
        Action botao6Action = new AbstractAction("Relatorios", new ImageIcon("../GYM/src/images/relatorio.png"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jRelatorios"); }
        };

        Action botao7Action = new AbstractAction("Ajuda", new ImageIcon("../GYM/src/images/ajuda.png"))
        { 	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jSobre"); }
        };

        Action exitAction = new AbstractAction("Sair", new ImageIcon("../GYM/src/images/sair.gif"))
        {	private static final long serialVersionUID = 1L;
        	public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jSair"); }
        };

        ToolButton botao1  = new ToolButton(botao1Action);
        ToolButton botao2  = new ToolButton(botao2Action);
        ToolButton botao3  = new ToolButton(botao3Action);
        ToolButton botao4  = new ToolButton(botao4Action);
        ToolButton botao5  = new ToolButton(botao5Action);
        ToolButton botao6  = new ToolButton(botao6Action);
        ToolButton botao7  = new ToolButton(botao7Action);
        ToolButton botao8 = new ToolButton(exitAction);

        BFerramenta.add(botao1);
        BFerramenta.add(botao2);
        BFerramenta.add(botao3);
        BFerramenta.add(botao4);
        BFerramenta.add(botao5);
        BFerramenta.add(botao6);
        BFerramenta.add(botao7);
        BFerramenta.add(botao8);
        //contentPane.add(BFerramenta,"North");

        return BFerramenta;
}


//********************************************
//Método genérico para chamada automática dos
//métodos vinculados aos itens de menu
//********************************************

    public void add(Object gui)
    { this.gui = gui; }

    private void chamaMetodo(ActionEvent e, String xMetodo)
    {   Method metodo;
        try
        {   metodo = gui.getClass().getMethod(xMetodo);
        	//Object t = null;
            metodo.invoke(gui);
        }
        catch (NoSuchMethodException nsme)
        { JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu - ERR1"); }
        catch (IllegalAccessException iae)
        { JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu2 - ERR2"); }
        catch (InvocationTargetException ite)
        {   ite.getTargetException().printStackTrace();
            JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu - ERR3");
        }
  }
}
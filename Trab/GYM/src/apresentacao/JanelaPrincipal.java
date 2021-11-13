package apresentacao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame
{
    private JDesktopPane theDesktop;
    private BarradeMenu MenuB;
    private BarradeFerramenta FerramentaB;
    private BarradeStatus StatusB;
    private int screenHeight;
    private int screenWidth;
    private Image wallpaper;

    //CONSTRUTOR DA CLASSE
    @SuppressWarnings("deprecation")
	public JanelaPrincipal()
    {   this.setTitle("Academia de Ginástica");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        screenHeight = d.height;
        screenWidth = d.width;
        this.setSize(screenWidth,screenHeight);
        this.setResizable(false);
        Image img = tk.getImage("../GYM/src/images/executar.png");
        this.setIconImage(img);
        this.addWindowListener(new WindowAdapter()
        { public void windowClosing(WindowEvent e)
            { System.exit(0); }
        });

        wallpaper = Toolkit.getDefaultToolkit().createImage("../GYM/src/images/academia.jpg");
        JPanel fundo = new NewContentPane();
        
        Container contentPane = getContentPane();
        BorderLayout layout = new BorderLayout(5,5);
        contentPane.setLayout(layout);

        MenuB = new BarradeMenu();
        MenuB.add(this);
        this.setJMenuBar(MenuB.make());

        FerramentaB = new BarradeFerramenta();
        FerramentaB.add(this);
        contentPane.add(FerramentaB.make(),BorderLayout.NORTH);

        StatusB = new BarradeStatus();
        contentPane.add(StatusB,BorderLayout.SOUTH);
       
        theDesktop = new JDesktopPane();
        theDesktop.setSize(screenWidth/3,screenHeight);
        super.add(fundo, BorderLayout.CENTER);
        this.show();
        
    }
    
    private class NewContentPane extends JPanel
    {	protected void paintComponent(final Graphics g)
      	{	super.paintComponent(g);
            g.drawImage(wallpaper,0,0,screenWidth,screenHeight,this);
        }
    }
    
    public void jCliente()
    {  new TelaCliente();	}
    
    public void jPlano()
    {  new TelaPlano();		}
    
    public void jFichaMedica()
    {  new TelaFichaMedica();	}
    
    public void jRegistro()
    {  new TelaRegistro();	}
    
    public void jPagamento()
    {  new TelaPagamento();	}
    
    public void jRelatorios()
    {  new TelaRelatorio();		}
    
    public void jSobre()
    { JOptionPane.showMessageDialog(null,"Instituto Federal Fluminense - IFF Campos (Campus Centro)\nDisciplina de Programaçao Orientada a Objetos\nAplicação em Java usando Banco de Dados - versão 1.0\nCopyright - Todos os direitos reservados","Sobre a APLICAÇÃO",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("../Agenda/src/images/ajuda.gif")); }

    public void jSair()
    { this.dispose(); }

}
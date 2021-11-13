package apresentacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class BarradeMenu
{
    Object gui;

    public JMenuBar make()
    {           
        JMenuBar BMenu = new JMenuBar();

        JMenu menu1 = new JMenu("Gerenciamentos Básicos");
        menu1.setMnemonic(KeyEvent.VK_G); 
        menu1.setFont(new Font ("Arial",Font.PLAIN,11));

        JMenuItem menu1Item1 = new JMenuItem("Cliente...",new ImageIcon("../Agenda/src/images/cliente.gif"));
        menu1Item1.setFont(new Font ("Arial",Font.PLAIN,11));
        menu1Item1.setMnemonic(KeyEvent.VK_C);
        menu1Item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        menu1Item1.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jCliente"); }
        });

        JMenuItem menu1Item2 = new JMenuItem("Plano...",new ImageIcon("../Agenda/src/images/plano.gif"));
        menu1Item2.setFont(new Font ("Arial",Font.PLAIN,11));
        menu1Item2.setMnemonic(KeyEvent.VK_P);
        menu1Item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
        menu1Item2.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jPlano"); }
        });
 
        JMenuItem menu1Item3 = new JMenuItem("Sair",new ImageIcon("../Agenda/src/images/sair.gif"));
        menu1Item3.setFont(new Font ("Arial",Font.PLAIN,11));
        menu1Item3.setMnemonic(KeyEvent.VK_S);
        menu1Item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        menu1Item3.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent e)
            { chamaMetodo(e,"jSair"); }
        });

        menu1.add(menu1Item1); 
        menu1.add(menu1Item2); 
        menu1.addSeparator(); 
        menu1.add(menu1Item3); 

      JMenu menu2 = new JMenu("Ajuda");
      menu2.setMnemonic(KeyEvent.VK_U);
      menu2.setFont(new Font ("Arial",Font.PLAIN,11));

      JMenuItem menu2Item1 = new JMenuItem("Sobre",new ImageIcon("../Agenda/src/images/ajuda.gif"));
      menu2Item1.setFont(new Font ("Arial",Font.PLAIN,11));
      menu2Item1.setMnemonic(KeyEvent.VK_B);
      menu2Item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
      menu2Item1.addActionListener(new ActionListener()
      { public void actionPerformed(ActionEvent e)
        { chamaMetodo(e,"jSobre"); }
      });
     

      menu2.add(menu2Item1);

      BMenu.add(menu1); 
      BMenu.add(menu2);

      return BMenu;
    }

    public void add(Object gui)
    { this.gui  = gui; }

    private void chamaMetodo(ActionEvent e, String xMetodo)
    {   Method metodo;
        try
        {   metodo = gui.getClass().getMethod(xMetodo);
            metodo.invoke(gui);
        }
        catch (NoSuchMethodException nsme)
        { JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu - ERR1"); }
        catch (IllegalAccessException iae)
        {JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu - ERR2"); }
        catch (InvocationTargetException ite)
        {   ite.getTargetException().printStackTrace();
            JOptionPane.showMessageDialog(null, "Metodo não definido para este evento/menu - ERR3");
        }
    }
}
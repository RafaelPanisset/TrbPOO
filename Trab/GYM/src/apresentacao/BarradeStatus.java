package apresentacao;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarradeStatus extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JTextField productField = new JTextField("APLICAÇÃO JAVA COM BANCO DE DADOS DA DEISCIPLINA DE PROGRAMAÇÃO ORIENTADA A OBJETOS",40);
    private JTextField rightsField = new JTextField("Copyright (C) 2016 - All Rights Reserved",20);
    private JTextField dataField = new JTextField();
    private JTextField clockField = new JTextField();
    private String datafinal;

    public BarradeStatus()
    {   Data d = new Data();
        datafinal = d.getData();
        Clock c = new Clock();
        
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        productField.setEditable(false);
        rightsField.setEditable(false);
        dataField.setEditable(false);
        clockField.setEditable(false);
        productField.setBorder(BorderFactory.createLoweredBevelBorder());
        rightsField.setBorder(BorderFactory.createLoweredBevelBorder());
        dataField.setBorder(BorderFactory.createLoweredBevelBorder());
        clockField.setBorder(BorderFactory.createEmptyBorder());
        dataField.setHorizontalAlignment(JTextField.CENTER);
        dataField.setText(datafinal);        
        
        clockField.setFont(new Font("Arial",Font.PLAIN,20));
        clockField.add(c,JTextField.CENTER);
        clockField.setHorizontalAlignment(JTextField.CENTER);
        
        this.add(productField);
        this.add(rightsField);
        this.add(dataField);
        this.add(clockField);
    }
}
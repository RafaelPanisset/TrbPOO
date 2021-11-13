package apresentacao;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.FichaMedica;
import modelo.Plano;

public class TelaRelatorio extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel lBuscar, lHoraSemanais, lMensalidade, lCpfCliente, lNome, lDataNascimento, lPesoAtual, lAltura, lImc;
	private JTextField tBuscar, tHoraSemanais, tMensalidade, tCpfCliente, tNome, tDataNascimento, tPesoAtual, tAltura,tImc;
	private JButton bLimpar, bBuscar, bFechar;
	private JPanel pCliente, pPlano, pFichaMedica;
	
	Cliente cliente;
	Plano plano;
	FichaMedica fichaMedica;
	
	public TelaRelatorio() {
		this.setSize(690,480);
		this.setTitle("Tela de Relatorios");
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null); 
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("../Agenda/src/images/relatorio.png");
		this.setIconImage(img);
		
		lBuscar = new JLabel("DIGITE O CPF: ");
		lBuscar.setSize(80,30);
		lBuscar.setLocation(10,10);
		
		tBuscar = new JTextField();
		tBuscar.setSize(480,30);
		tBuscar.setLocation(90,10);
		
		pCliente = new JPanel();
		pCliente.setSize(660,90);
		pCliente.setLocation(10,45);
		pCliente.setBorder(BorderFactory.createTitledBorder("DADOS DO CLIENTE:"));
		pCliente.setLayout(null);
		
		pPlano = new JPanel();
		pPlano.setSize(660,90);
		pPlano.setLocation(10,152);
		pPlano.setBorder(BorderFactory.createTitledBorder("DADOS DO PLANO:"));
		pPlano.setLayout(null);
		
		pFichaMedica = new JPanel();
		pFichaMedica.setSize(660,90);
		pFichaMedica.setLocation(10,250);
		pFichaMedica.setBorder(BorderFactory.createTitledBorder("DADOS DO CLIENTE:"));
		pCliente.setLayout(null);
		
		lDataNascimento = new JLabel("Data de Nascimento:");
		lDataNascimento.setSize(120,20);
		lDataNascimento.setLocation(385,37);
		
		tDataNascimento = new JTextField();
		tDataNascimento.setSize(120,20);
		tDataNascimento.setLocation(510,37);
		tDataNascimento.setEditable(false);
		
		lNome = new JLabel("Nome Completo: ");
		lNome.setSize(100,20);
		lNome.setLocation(10,37);
		
		tNome = new JTextField();
		tNome.setSize(120,20);
		tNome.setLocation(115,37);
		tNome.setEditable(false);
		
		lCpfCliente = new JLabel("CPF: ");
		lCpfCliente.setSize(60,20);
		lCpfCliente.setLocation(240,37);
		
		tCpfCliente = new JTextField();
		tCpfCliente.setSize(80,20);
		tCpfCliente.setLocation(290,37);
		tCpfCliente.setEditable(false);
		
		lHoraSemanais = new JLabel("horas por dias: ");
		lHoraSemanais.setSize(160,20);
		lHoraSemanais.setLocation(20,40);
		
		tHoraSemanais = new JTextField();
		tHoraSemanais.setSize(160,20);
		tHoraSemanais.setLocation(170,40);
		tHoraSemanais.setEditable(false);

		lMensalidade = new JLabel("mensalidade do cliente: ");
		lMensalidade.setSize(100,20);
		lMensalidade.setLocation(360,40);
		
		tMensalidade = new JTextField();
		tMensalidade.setSize(160,20);
		tMensalidade.setLocation(450,40);
		tMensalidade.setEditable(false);
		
		lPesoAtual = new JLabel("Peso Atual:");
		lPesoAtual.setSize(120,20);
		lPesoAtual.setLocation(385,37);
		
		tPesoAtual = new JTextField();
		tPesoAtual.setSize(120,20);
		tPesoAtual.setLocation(510,37);
		tPesoAtual.setEditable(false);
		
		lAltura = new JLabel("Altura: ");
		lAltura.setSize(100,20);
		lAltura.setLocation(10,37);
		
		tAltura = new JTextField();
		tAltura.setSize(120,20);
		tAltura.setLocation(115,37);
		tAltura.setEditable(false);
		
		lImc = new JLabel("IMC: ");
		lImc.setSize(60,20);
		lImc.setLocation(240,37);
		
		tImc = new JTextField();
		tImc.setSize(80,20);
		tImc.setLocation(290,37);
		tImc.setEditable(false);
		
		class BatSinal extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == bBuscar)
				{	
					cliente = new Cliente();
		        	cliente = (Cliente) cliente.pesquisar(tBuscar.getText());
		        	
					if(cliente != null) {
						plano = new Plano(cliente);
						plano = (Plano) plano.pesquisar(plano);
						fichaMedica = new FichaMedica(cliente);
						fichaMedica = (FichaMedica) fichaMedica.pesquisar(fichaMedica);
					}else {
						JOptionPane.showConfirmDialog(null, "Cliente não encontrado","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
					}
					System.out.println(cliente);
					System.out.println(plano);
					System.out.println("hahahahah" + fichaMedica);
					tHoraSemanais.setText(String.valueOf(plano.getHorasDiarias()));
					tMensalidade.setText(String.valueOf(plano.getMensalidade()));
					tCpfCliente.setText(cliente.getCpf());
					tNome.setText(cliente.getNome());
					tDataNascimento.setText(cliente.getDataNascimento());
					tPesoAtual.setText(String.valueOf(fichaMedica.getPeso()));
					tAltura.setText(String.valueOf(fichaMedica.getAltura()));
					tImc.setText(String.valueOf(fichaMedica.getImc()));
				}
				
				if (e.getSource() == bLimpar) {
					tBuscar.setText("");
					tHoraSemanais.setText("");
					tMensalidade.setText("");
					tCpfCliente.setText("");
					tNome.setText("");
					tDataNascimento.setText("");
				}
				
				if (e.getSource() == bFechar) {
					dispose();  
				}
			}	
		}
		
		BatSinal batman = new BatSinal();
		
		bBuscar = new JButton("Buscar");
		bBuscar.setSize(75,30);
		bBuscar.setLocation(580,10);
		bBuscar.setForeground(Color.white);
		bBuscar.setBackground(Color.black);
		bBuscar.addMouseListener(batman);
		
		bLimpar = new JButton("Limpar");
		bLimpar.setSize(230,60);
		bLimpar.setLocation(100,360);
		bLimpar.setForeground(Color.white);
		bLimpar.setBackground(Color.black);
		bLimpar.addMouseListener(batman);
		
		bFechar = new JButton("Fechar");
		bFechar.setSize(230,60);
		bFechar.setLocation(360,360);
		bFechar.setForeground(Color.white);
		bFechar.setBackground(Color.black);
		bFechar.addMouseListener(batman);
		
		this.add(pCliente);
		pCliente.add(lNome);
		pCliente.add(lCpfCliente);
		pCliente.add(lDataNascimento);	
		pCliente.add(tNome);
		pCliente.add(tCpfCliente);
		pCliente.add(tDataNascimento);
		
		this.add(pPlano);
		pPlano.add(lHoraSemanais);
		pPlano.add(lMensalidade);
		pPlano.add(tHoraSemanais);
		pPlano.add(tMensalidade);
		
		this.add(pFichaMedica);
		pFichaMedica.add(lPesoAtual);
		pFichaMedica.add(lAltura);
		pFichaMedica.add(lImc);	
		pFichaMedica.add(tPesoAtual);
		pFichaMedica.add(tAltura);
		pFichaMedica.add(tImc);
		
		this.add(lBuscar);
		this.add(tBuscar);
		this.add(bBuscar);
		this.add(bLimpar);
		this.add(bFechar);
	}
	
}

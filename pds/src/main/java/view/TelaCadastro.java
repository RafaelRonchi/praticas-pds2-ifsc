package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.PessoaDAO;
import model.Pessoa;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JLabel lblIdade;
    private JTextField txtId;
    private DefaultTableModel modelo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastro frame = new TelaCadastro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 50, 46, 14);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(66, 47, 86, 20);
        contentPane.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 19, 46, 14);
        contentPane.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(66, 16, 86, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);

        lblIdade = new JLabel("Idade");
        lblIdade.setBounds(10, 94, 46, 14);
        contentPane.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setColumns(10);
        txtIdade.setBounds(66, 91, 86, 20);
        contentPane.add(txtIdade);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String idade = txtIdade.getText();
                String id = txtId.getText();

                Pessoa p = new Pessoa();
                p.setPrimeiro_nome(nome);
                p.setId(Integer.valueOf(id));
                p.setIdade(Integer.valueOf(idade));

                PessoaDAO dao = new PessoaDAO();
                dao.inserir(p);

                txtNome.setText(null);
                txtId.setText(null);
                txtIdade.setText(null);

                refreshTable();
            }
        });
        btnCadastrar.setBounds(10, 122, 97, 23);
        contentPane.add(btnCadastrar);

        modelo = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Idade" });
        JTable tabela = new JTable(modelo);

        JScrollPane barraRolagem = new JScrollPane(tabela);
        barraRolagem.setBounds(10, 160, 414, 190);
        contentPane.add(barraRolagem);
        
        JButton btnUpdate = new JButton("Update nome");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nome = txtNome.getText();
                String id =txtId.getText();


                PessoaDAO dao = new PessoaDAO();
                dao.atualizar(id, nome);

                txtNome.setText(null);
                txtId.setText(null);
                txtIdade.setText(null);

                refreshTable();
        		
        	}
        });
        btnUpdate.setBounds(145, 122, 116, 23);
        contentPane.add(btnUpdate);
        
        JButton btnNewButton = new JButton("Deletar por id");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();

                PessoaDAO dao = new PessoaDAO();
                dao.excluir(id);

                txtNome.setText(null);
                txtId.setText(null);
                txtIdade.setText(null);

                refreshTable();
        		
        	}
        });
        btnNewButton.setBounds(301, 122, 123, 23);
        contentPane.add(btnNewButton);

        setSize(450, 400);
        setVisible(true);
        refreshTable();
    }

    private void refreshTable() {
        PessoaDAO dao = new PessoaDAO();
        ArrayList<Pessoa> pessoas = dao.listar();

        modelo.setRowCount(0);

        for (Pessoa pessoa : pessoas) {
            modelo.addRow(new Object[] { pessoa.getId(), pessoa.getPrimeiro_nome(), pessoa.getIdade() });
        }
    }
}

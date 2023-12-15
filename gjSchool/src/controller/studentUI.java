package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.studentDaoImpl;
import model.student;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class studentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField chi;
	private JTextField eng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentUI frame = new studentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public studentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("學員管理系統");
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(45, 10, 325, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("名子：");
		lblNewLabel_1.setBounds(10, 49, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("國文：");
		lblNewLabel_1_1.setBounds(154, 49, 46, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("英文：");
		lblNewLabel_1_1_1.setBounds(288, 49, 46, 15);
		contentPane.add(lblNewLabel_1_1_1);
		
		JTextArea output = new JTextArea();
		output.setBounds(37, 140, 372, 111);
		contentPane.add(output);
		
		name = new JTextField();
		name.setBounds(48, 46, 96, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		chi = new JTextField();
		chi.setColumns(10);
		chi.setBounds(188, 46, 96, 21);
		contentPane.add(chi);
		
		eng = new JTextField();
		eng.setColumns(10);
		eng.setBounds(328, 46, 96, 21);
		contentPane.add(eng);
		
		JButton SearchButton = new JButton("查詢(String)");
		SearchButton.setHorizontalAlignment(SwingConstants.LEFT);
		SearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.queryAll2()--->List
				 * 2.String show="";
				 */

				
				//String print
				output.setText(new studentDaoImpl().queryAll1());

			}
		});
		SearchButton.setBounds(72, 107, 105, 23);
		contentPane.add(SearchButton);
		
		JButton addButton = new JButton("新增");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.擷取 name,chi,eng getText()
				 * 2.name,chi-->轉整數
				 * 3.new student(name,chi,eng);
				 * 4.add(s);
				 */
				String NAME = name.getText();
				int CHI = Integer.parseInt(chi.getText());
				int ENG = Integer.parseInt(eng.getText());
				
				student s = new student(NAME,CHI,ENG);
				new studentDaoImpl().add(s);
				
				
			}
		});
		addButton.setBounds(180, 74, 87, 23);
		contentPane.add(addButton);
		
		JButton btnlist = new JButton("查詢(List)");
		btnlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//List print
				List<student> l = new studentDaoImpl().queryAll2();
				String show ="";
				int i = 0;
				int sum = 0;
				for(student o:l)
				{
					i++;
					sum = sum+o.getChi()+o.getEng();
					show = show+"id="+o.getId()+"\tname="+o.getName()+"\tchi="+o.getChi()+"\teng="+o.getEng()+"\t總分="+(o.getChi()+o.getEng())+"\n";
				}
				
				output.setText(show+"\t總分合計"+sum+"\t平均"+(sum/i));
				
			}
		});
		btnlist.setHorizontalAlignment(SwingConstants.LEFT);
		btnlist.setBounds(254, 107, 105, 23);
		contentPane.add(btnlist);
	}
}

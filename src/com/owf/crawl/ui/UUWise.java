package com.owf.crawl.ui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.owf.params.ConstantParams;
import com.owf.uuwise.UUAPI;
import com.owf.uuwise.VerifyImp;
import com.owf.xml.dom.XmlOperate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class UUWise {

	public JFrame frame;
	private JTextField textField_softid;
	private JTextField textField_softkey;
	private JTextField textField_dllverifykey;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_result;
	private JTextField textField_picpath;
	private JTextField textField_codetype;
	private JButton button;
	public JFrame mainWindows;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UUWise window = new UUWise();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UUWise() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 640);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SOFTID");
		lblNewLabel.setBounds(62, 33, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SOFTKEY");
		lblNewLabel_1.setBounds(62, 77, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DLLVerifyKey");
		lblNewLabel_2.setBounds(62, 121, 120, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("USERNAME");
		lblNewLabel_3.setBounds(62, 172, 72, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PASSWORD");
		lblNewLabel_4.setBounds(62, 228, 72, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_softid = new JTextField();
		textField_softid.setBounds(208, 30, 484, 24);
		frame.getContentPane().add(textField_softid);
		textField_softid.setColumns(10);
		
		textField_softkey = new JTextField();
		textField_softkey.setColumns(10);
		textField_softkey.setBounds(208, 74, 484, 24);
		frame.getContentPane().add(textField_softkey);
		
		textField_dllverifykey = new JTextField();
		textField_dllverifykey.setColumns(10);
		textField_dllverifykey.setBounds(208, 121, 484, 24);
		frame.getContentPane().add(textField_dllverifykey);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(208, 169, 484, 24);
		frame.getContentPane().add(textField_username);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(208, 222, 484, 24);
		frame.getContentPane().add(textField_password);
		
		JButton btnNewButton = new JButton("保存修改");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateUUWiseXml();
			}
		});
		btnNewButton.setBounds(62, 273, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回主页");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainWindows.setVisible(true);
				mainWindows.setBounds(frame.getBounds());
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(643, 556, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel label = new JLabel("验证结果");
		label.setBounds(62, 435, 72, 18);
		frame.getContentPane().add(label);
		
		textField_result = new JTextField();
		textField_result.setBounds(148, 432, 484, 24);
		frame.getContentPane().add(textField_result);
		textField_result.setColumns(10);
		
		JLabel label_1 = new JLabel("选择文件");
		label_1.setBounds(62, 332, 72, 18);
		frame.getContentPane().add(label_1);
		
		textField_picpath = new JTextField();
		textField_picpath.setBounds(148, 329, 484, 24);
		frame.getContentPane().add(textField_picpath);
		textField_picpath.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("打开文件");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc=new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES ); 
				jfc.showDialog(new JLabel(), "选择");
				File file=jfc.getSelectedFile();
				if(file != null)
				{
					textField_picpath.setText(file.getAbsolutePath());
				}
			}
		});
		btnNewButton_2.setBounds(643, 328, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		button = new JButton("测试");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateUUWiseXml();
				String picPath = textField_picpath.getText().trim();
				if(picPath.equals(""))
				{
					return;
				}
				String codeType = textField_codetype.getText().trim();
				if(codeType.equals(""))
				{
					return;
				}
				int nCodeType = 0;
				try {
					nCodeType = Integer.parseInt(codeType);
				} catch (Exception ep) {
					ep.printStackTrace();
					return;
				}
				
				button.setEnabled(false);
				button.setText("正在运行");
				UUwiseThread test = new UUwiseThread();
				test.picPath = picPath;
				test.codeType = nCodeType;
				test.button_test = button;
				test.textField_result = textField_result;
				test.start();
			}
		});
		button.setBounds(62, 482, 113, 27);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("代码类型");
		label_2.setBounds(62, 383, 72, 18);
		frame.getContentPane().add(label_2);
		
		textField_codetype = new JTextField();
		textField_codetype.setColumns(10);
		textField_codetype.setBounds(148, 380, 484, 24);
		frame.getContentPane().add(textField_codetype);
		
		readUUWiseXml();
	}
	
	public void updateUUWiseXml()
	{
		XmlOperate  xmlOp = new XmlOperate();
		xmlOp.updateXml(ConstantParams.UUWISEXML, "SOFTID", textField_softid.getText());
		xmlOp.updateXml(ConstantParams.UUWISEXML, "SOFTKEY", textField_softkey.getText());
		xmlOp.updateXml(ConstantParams.UUWISEXML, "DLLVerifyKey", textField_dllverifykey.getText());
		xmlOp.updateXml(ConstantParams.UUWISEXML, "USERNAME", textField_username.getText());
		xmlOp.updateXml(ConstantParams.UUWISEXML, "PASSWORD", textField_password.getText());
		readUUWiseXml();
	}
	
	public void readUUWiseXml()
	{
		XmlOperate  xmlOp = new XmlOperate();
		textField_softid.setText(xmlOp.readXml(ConstantParams.UUWISEXML, "SOFTID"));
		textField_softkey.setText(xmlOp.readXml(ConstantParams.UUWISEXML, "SOFTKEY"));
		textField_dllverifykey.setText(xmlOp.readXml(ConstantParams.UUWISEXML, "DLLVerifyKey"));
		textField_username.setText(xmlOp.readXml(ConstantParams.UUWISEXML, "USERNAME"));
		textField_password.setText(xmlOp.readXml(ConstantParams.UUWISEXML, "PASSWORD"));
	}
}

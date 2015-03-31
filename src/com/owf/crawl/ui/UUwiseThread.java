package com.owf.crawl.ui;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.owf.uuwise.VerifyImp;

public class UUwiseThread extends Thread{
  public JButton button_test;
  public JTextField textField_result;
  public String picPath;
  public int codeType;
  public void run()
  {
	  try {
		VerifyImp vi = new VerifyImp();
		String result = vi.verify(picPath, codeType);
		button_test.setEnabled(true);
		button_test.setText("测试");
		textField_result.setText(result);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}

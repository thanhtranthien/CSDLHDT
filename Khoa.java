import java.io.*;
import com.db4o.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Khoa
{
	private String maKhoa;
	private String tenKhoa;
	public Khoa(String ma, String ten)
	{
		maKhoa = ma;
		tenKhoa = ten;
	}
	public String layMaKhoa(){ return maKhoa; }
	public String layTenKhoa(){ return tenKhoa; }
	public void ganTenKhoa(String ten){ tenKhoa=ten;}
	public void ganMaKhoa(String ma){ maKhoa=ma;}	
	public String toString()
	{
		return maKhoa+" - " + tenKhoa;
	} 
}
class KhoaGUI extends JFrame
{
	private JTextField jtfMaKhoa;
	private JTextField jtfTenKhoa;
	private ObjectContainer db;
	public KhoaGUI(ObjectContainer d)
	{
		db = d;
		JLabel jlbMK = new JLabel("Ma khoa ");
		JLabel jlbTK = new JLabel("Ten khoa ");
		jtfMaKhoa = new JTextField(15);
		jtfTenKhoa = new JTextField(15);
		JButton jbtMoi = new JButton("Them moi");
		JButton jbtLuu = new JButton("Luu");
		JButton jbtDong = new JButton("Dong");
		
		Font fo = new Font("Arial",Font.PLAIN,24);
		jlbMK.setFont(fo);
		jlbTK.setFont(fo);
		jtfMaKhoa.setFont(fo);
		jtfTenKhoa.setFont(fo);
		jbtMoi.setFont(fo);	
		jbtLuu.setFont(fo);
		jbtDong.setFont(fo);
		KhoaCTRL kctrl = new KhoaCTRL(this,db);
		jbtLuu.addActionListener(kctrl);
		jbtDong.addActionListener(kctrl);
		setLayout(new FlowLayout());
		add(jlbMK); add(jtfMaKhoa);
		add(jlbTK); add(jtfTenKhoa);
		add(jbtMoi);add(jbtLuu); add(jbtDong);
		setSize(500,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setData(Khoa kh)
	{
		jtfMaKhoa.setText(kh.layMaKhoa());
		jtfTenKhoa.setText(kh.layTenKhoa());
	}
	public Khoa getData()
	{
		return new Khoa(jtfMaKhoa.getText(), jtfTenKhoa.getText());
	}
	public void dong()
	{
		setVisible(false);
	}
	public void moi()
	{
		jtfMaKhoa.setText("");
		jtfTenKhoa.setText("");
	}
}
class KhoaCTRL implements ActionListener
{
	private Khoa khoa;
	private KhoaGUI khoaGUI;
	private ObjectContainer db;
	public KhoaCTRL(KhoaGUI kg, ObjectContainer db)
	{
		khoaGUI = kg;
		this.db = db;
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Luu"))
		{
			khoa = khoaGUI.getData();
			db.store(khoa);
		}	
		else
			if(ae.getActionCommand().equals("Them moi"))
			{
				khoaGUI.moi();
			}
			else
			if(ae.getActionCommand().equals("Dong"))
			{
				khoaGUI.dong();
			}		
	}	
}
class Main extends JFrame implements ActionListener
{
	JButton jbtNhapKhoa = new JButton("Nhap Khoa");
	JButton jbtThoat = new JButton("Thoat");
	ObjectContainer db;
	public Main()
	{
		super("Quan ly sinh vien");
		setLayout(new FlowLayout());
		add(jbtNhapKhoa);add(jbtThoat);
		jbtNhapKhoa.addActionListener(this);
		jbtThoat.addActionListener(this);
		setSize(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		db = DB.openDatabase("QLSV.db4o");
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==jbtNhapKhoa)
			new KhoaGUI(db);
		else
			if(ae.getSource()==jbtThoat)
			{
				db.close();
				System.exit(0);
			}	
	}
}
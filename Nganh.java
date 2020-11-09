import java.io.*;
import java.util.*;
import com.db4o.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Nganh
{
	private String maNganh;
	private String tenNganh;
	private Khoa khoaQL;
	private Vector<MonHoc> dsMonHoc;
	public Nganh(String ma, String ten, Khoa kh)
	{
		maNganh=ma;
		tenNganh=ten;
		khoaQL = kh;
		dsMonHoc=new Vector<MonHoc>();
	}
	public Nganh(String ma, String ten, Khoa kh, Vector<MonHoc> v)
	{
		maNganh=ma;
		tenNganh=ten;
		khoaQL = kh;
		dsMonHoc=v;
	}
	public String layMaNganh()
	{
		return maNganh;
	}
	public String layTenNganh()
	{
		return tenNganh;
	}
	public void ganDSMonHoc(Vector<MonHoc> ds)
	{
		dsMonHoc = ds;
	}
	public String toString()
	{
		return maNganh+" - " + tenNganh;
	}
}
class NganhGUI extends JFrame
{
	private JTextField jtfMaNganh;
	private JTextField jtfTenNganh;
	private JComboBox jcbKhoa;
	private JList jlDSTatCaMonHoc;
	private JList jlDSMonHoc;
	private ObjectContainer db;
	public NganhGUI(ObjectContainer d)
	{
		db = d;
		ObjectSet<Khoa> dsKhoa=DB.getDSKhoa(db);
		Vector vKh=new Vector();
		for(Khoa m: dsKhoa)
			vKh.addElement(m);
		ObjectSet<MonHoc> dsMon=DB.getDSMonHoc(db);
		Vector vMon=new Vector();
		for(MonHoc mh: dsMon)
			vMon.addElement(mh);
			
		JLabel jlbMN = new JLabel("Ma nganh ");
		JLabel jlbTN = new JLabel("Ten nganh ");
		JLabel jlbKh = new JLabel("Khoa quan ly ");
		JLabel jlbDSM = new JLabel("Danh sach mon hoc ");
		jtfMaNganh = new JTextField(20);
		jtfTenNganh = new JTextField(20);
		jcbKhoa = new JComboBox(vKh);
		jlDSTatCaMonHoc = new JList(vMon);
		jlDSMonHoc = new JList();
		JButton jbtMoi = new JButton("Them moi");
		JButton jbtLuu = new JButton("Luu");
		JButton jbtDong = new JButton("Dong");
		//JButton jbtChon = new JButton("Chon mon");
		
		NganhCTRL ngctrl = new NganhCTRL(this,db);
		jbtLuu.addActionListener(ngctrl);
		jbtMoi.addActionListener(ngctrl);
		jbtDong.addActionListener(ngctrl);
		jbtChon.addActionListener(ngctrl);
		setLayout(new FlowLayout());
		add(jlbMN); add(jtfMaNganh);
		add(jlbTN); add(jtfTenNganh);
		add(jlbKh); add(jcbKhoa);
		add(jlbDSM);add(jlDSTatCaMonHoc);add(jbtChon); add(jlDSMonHoc);
		add(jbtMoi);add(jbtLuu); add(jbtDong);
		setSize(350,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public Nganh getData()
	{
		String ma=jtfMaNganh.getText();
		String ten=jtfTenNganh.getText();
		Khoa kh = ((Khoa)jcbKhoa.getSelectedItem());
		
		Vector<MonHoc> dsM = new Vector<MonHoc>();
		for(Object m:jlDSTatCaMonHoc.getSelectedValues())
			dsM.addElement((MonHoc) m);
		return new Nganh(ma, ten,kh,dsM);
	}
	public void dong()
	{
		setVisible(false);
	}
	public void moi()
	{
		jtfMaNganh.setText("");
		jtfTenNganh.setText("");
	}
	public void chonMon()
	{
		
	}
}
class NganhCTRL implements ActionListener
{
	private Nganh ng;
	private NganhGUI ngGUI;
	private ObjectContainer db;
	public NganhCTRL(NganhGUI ng, ObjectContainer db)
	{
		ngGUI = ng;
		this.db = db;
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Luu"))
		{
			ng = ngGUI.getData();
			db.store(ng);
		}	
		else
			if(ae.getActionCommand().equals("Them moi"))
			{
				ngGUI.moi();
			}
			else
			if(ae.getActionCommand().equals("Dong"))
			{
				ngGUI.dong();
			}
			else
			if(ae.getActionCommand().equals("Chon mon"))
			{
				ngGUI.chonMon();
			}				
	}	
}


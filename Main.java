import com.db4o.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Main extends JFrame implements ActionListener
{
	JButton jbtNhapKhoa = new JButton("Nhap Khoa");
	JButton jbtNhapNganh = new JButton("Nhap Nganh");
	JButton jbtNhapSV = new JButton("Nhap sinh vien");
	JButton jbtTimKhoa = new JButton("Tim khoa");
	JButton jbtTimSinhVien = new JButton("Tim sinh vien");		
	JButton jbtTimSVNganh = new JButton("Tim sinh vien theo nganh");
	JButton jbtCapNhatKhoa = new JButton("Cap nhat Khoa");		
	JButton jbtThoat = new JButton("Thoat");
	ObjectContainer db;
	public Main()
	{
		super("Quan ly sinh vien");
		setLayout(new FlowLayout());
		add(jbtNhapKhoa); add(jbtNhapNganh);add(jbtNhapSV);add(jbtTimKhoa);
		add(jbtTimSinhVien);add(jbtTimSVNganh);add(jbtCapNhatKhoa);
		add(jbtThoat);
		jbtNhapKhoa.addActionListener(this);
		jbtNhapNganh.addActionListener(this);
		jbtNhapSV.addActionListener(this);
		jbtThoat.addActionListener(this);
		jbtTimKhoa.addActionListener(this);
		jbtTimSinhVien.addActionListener(this);
		jbtTimSVNganh.addActionListener(this);
		jbtCapNhatKhoa.addActionListener(this);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		db = DB.openDatabase("QLSV.db4o");
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==jbtNhapKhoa)
			new KhoaGUI(db);
		else	
		if(ae.getSource()==jbtNhapNganh)
			new NganhGUI(db);	
		else
		if(ae.getSource()==jbtNhapSV)
			new SinhVienGUI(db);	
		else
			if(ae.getSource()==jbtTimKhoa)
				new QKhoaGUI(db);	
			else
				if(ae.getSource()==jbtTimSinhVien)
					new QSVGUI(db);	
				else
					if(ae.getSource()==jbtTimSVNganh)
						new QSVNGUI(db);	
					else
						if(ae.getSource()==jbtCapNhatKhoa)
							new UKhoaGUI(db);
						else	
							if(ae.getSource()==jbtThoat)
							{
								db.close();
								System.exit(0);
							}	
	}
}
class SD
{
	public static void main (String[] args) {
		new Main();
	}
}
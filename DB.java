import java.io.*;
import com.db4o.*;
import java.util.*;

class DB
{
	public static ObjectContainer openDatabase(String fileName)
	{
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), fileName);
		return db;
	}
	public static ObjectSet<Khoa> getDSKhoa(ObjectContainer db)
	{
		ObjectSet<Khoa> rs = db.queryByExample(Khoa.class);
		return rs;
	}
	public static ObjectSet<MonHoc> getDSMonHoc(ObjectContainer db)
	{
		ObjectSet<MonHoc> rs = db.queryByExample(MonHoc.class);
		return rs;
	}
	public static ObjectSet<Nganh> getDSNganh(ObjectContainer db)
	{
		ObjectSet<Nganh> rs = db.queryByExample(Nganh.class);
		return rs;
	}
	public static Nganh getNganh(ObjectContainer db,String ma)
	{
		ObjectSet<Nganh> rs = db.queryByExample(new Nganh(ma,null,null));
		
		return rs.next();
	}
	public static void listResult(java.util.List<?> result){
		System.out.println(result.size());
		for (Object o : result) {
			System.out.println(o);
		}
	}
}

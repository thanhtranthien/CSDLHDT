class MonHoc
{
	protected String maMonHoc;
	protected String tenMonHoc;
	protected int soTinChi;
	public MonHoc(String ma, String ten, int stc)
	{
		maMonHoc=ma; tenMonHoc=ten; soTinChi=stc;
	}
	public String layMaMonHoc()
	{
		return maMonHoc;
	}
	public String layTenMonHoc()
	{
		return tenMonHoc;
	}
	public String toString()
	{
		return maMonHoc+" - " + tenMonHoc;
	}
}
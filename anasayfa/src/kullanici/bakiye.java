package kullanici;

public class bakiye {
	private int bakiye;
	private long parkString;
	
	public int getbakiye() {
		return bakiye;
	}
	
	public long getString() {
		return parkString;
	}
	
	
	public void setString(long gün) {
		this.parkString = gün;
	}
	
	public void  addbakiye(int ek) {
		this.bakiye += ek;
	}
	
	public void setbakiye(int güncelbakiye) { 
		this.bakiye = güncelbakiye;
	}
}

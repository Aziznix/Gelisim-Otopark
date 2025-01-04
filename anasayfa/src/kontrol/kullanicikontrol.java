package kontrol;

import fonks.islem;

public class kullanicikontrol {
	
	private final islem islemYap;
	
	
	public kullanicikontrol() {
        islemYap = new islem();
    }

    public boolean giris(String username, String password) {
        return islemYap.giris(username, password);
    }

    public boolean kayit(String username, String password,String eposta) {
        return islemYap.kayit(username, password, eposta);
    }
	
    
    

}

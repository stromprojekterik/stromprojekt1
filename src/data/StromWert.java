package data;

public class StromWert {
	private Date zeitpunkt;
	private final float wert_orgi;
	private float wert_edit;
	private int status;
	
	private boolean edited;
	
	public StromWert(Date zeitpunkt, float wert_orgi, float wert_edit, int status) {
		edited = false;
		
		this.zeitpunkt = zeitpunkt;
		this.wert_orgi = wert_orgi;
		this.wert_edit = wert_edit;
		this.status = status;
		
//		System.out.println(zeitpunkt+"__"+wert_orgi+"__"+wert_edit+"__"+status);
	}
	
	public boolean isBefore(StromWert other){
		return zeitpunkt.isBefore(other.zeitpunkt);
	}
	
	public void setWert_edit(float wert_edit) {
		edited=true;
		this.wert_edit = wert_edit;
	}
	
	public Date getZeitpunkt() {
		return zeitpunkt;
	}
	
	public float getWert_orgi() {
		return wert_orgi;
	}
	
	public float getWert_edit() {
		return wert_edit;
	}
	
	public int getStatus() {
		return status;
	}
	
	public boolean isEdited() {
		return edited;
	}
}

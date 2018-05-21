package ihmis;

import javax.swing.JPanel;

import inscriptions.Inscriptions;

class ispanelnopage extends JPanel {
	private int noPage;
	private Inscriptions inscriptions;
	public ispanelnopage(int no) {
		this.noPage = no;

	}
	
	public void menuequipebis() {
		
	}
	
	public int getNoPage() {
		return noPage;
	}
	
	public void setNoPage(int a) {
		noPage = a;
	}
}

package decorator;

abstract class ShieldDecorator implements _Character{
	protected _Character shielded ;
	protected String path;
	public ShieldDecorator (_Character player) {
		shielded = player;
		path = player.getPath();
	}
	@Override
	public String getPath() {
		//Path for coming player
		return shielded.getPath();
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

}

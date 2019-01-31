package project2;

public class Switch extends Sprite {

	public static final String TYPE = "Switch";
	private static boolean switchOpen = false;

	public Switch(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		// TODO Auto-generated constructor stub
	}

	public static void setSwitchOpen(boolean isOpen) {
		switchOpen = isOpen;
	}

	public static boolean isSwitchOpen() {
		return switchOpen;
	}

	// update method

}

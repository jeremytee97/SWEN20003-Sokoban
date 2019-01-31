package project2;

public class Door extends Sprite {
	private boolean isClosed;

	public static final String TYPE = "Door";

	public Door(String image_src, float x, float y, String tag) {
		super(image_src, x, y, tag);
		this.isClosed = true;
		// TODO Auto-generated constructor stub
	}

}

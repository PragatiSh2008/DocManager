package OmniDocs;

public class DocClass {
	private String name;
	private String Image;
	private String notice;
	public DocClass(String name, String image, String notice) {
		super();
		this.name = name;
		Image = image;
		this.notice = notice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
}

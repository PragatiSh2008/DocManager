package OmniDocs;

public class RelativeClass {
	private String RelativeName;
	private String relativeType;
	public RelativeClass(String relativeName, String relativeType) {
		super();
		RelativeName = relativeName;
		this.relativeType = relativeType;
	}
	public String getRelativeName() {
		return RelativeName;
	}
	public void setRelativeName(String relativeName) {
		RelativeName = relativeName;
	}
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	
}

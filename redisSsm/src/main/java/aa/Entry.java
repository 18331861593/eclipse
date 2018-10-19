package aa;

public class Entry {
	@Override
	public String toString() {
		return "Entry [name=" + name + ", type=" + type + "]";
	}
	private String name;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

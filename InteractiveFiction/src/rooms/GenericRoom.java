package rooms;

public abstract class GenericRoom {
	String roomName;
	String roomDescription;
	
	public GenericRoom() {
		// TODO Auto-generated constructor stub
	} 
	
	public abstract void addExit(Enum<?> exit);
	public abstract void linkExitTo(GenericRoom room);
	
	public String getRoomName() { return roomName;}
	public String getRoomDescription() { return roomDescription; }
	public abstract Enum<?> getExits();
	public abstract Enum<?> getEntrances();

	public void setRoomName(String roomName) { this.roomName = roomName; }
	public void setRoomDescription(String roomDescription) { this.roomDescription = roomDescription; }
	public abstract Enum<?> setExits();
	public abstract Enum<?> setEntrances();
}

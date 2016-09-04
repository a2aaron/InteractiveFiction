package items;

public interface ILockedItem {
	public boolean isLocked();
	public void unlock(GenericItem key);
}

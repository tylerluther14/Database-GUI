import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * interface for all needed SQL statements (INSERT, UPDATE, DELETE)
 * each display implements this interface and makes their own statements
 * methods should be implemented in appropriate buttons
 * @author Rachel Johnston
 *
 */
public interface StatementCreator 
{
	
	/**
	 * creates INSERT statement
	 * @throws SQLException
	 */
	public void insert() throws SQLException;
	
	/**
	 * creates UPDATE statement
	 * @throws SQLException
	 */
	public void update() throws SQLException;
	
	/**
	 * create DELETE statement
	 * @throws SQLException
	 */
	public void delete() throws SQLException;

	void mouseClicked(MouseEvent e);

	void mousePressed(MouseEvent e);

	void mouseReleased(MouseEvent e);

	void mouseEntered(MouseEvent e);

	void mouseExited(MouseEvent e);

	//we might need select??? for displaying the entries on each of our displays...not sure tho
	
}

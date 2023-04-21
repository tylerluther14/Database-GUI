import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Puts limits on JTextFields
 * @author racheljohnston
 *
 */
public class JTextFieldCharLimiter extends PlainDocument
{
	/**
	 * the desired character limit
	 */
	private int limit;
	
	/**
	 * constructor that takes in desired character limit
	 * @param n
	 */
	public JTextFieldCharLimiter(int n)
	{
		this.limit = n;
	}
	
	/**
	 * prevents JTextField from going over the limit
	 */
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException
	{
		if (str == null)
		{
			return;
		}
		else if ((getLength() + str.length()) <= limit)
		{
			super.insertString(offset, str, set);
		}
	}
	
}

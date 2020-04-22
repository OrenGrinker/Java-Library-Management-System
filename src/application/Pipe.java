package application;

import java.io.Serializable;

/**
 * Class for sending information between server and clients with data and command to know what to do with the data.
 * @author OBL_Team13
 */
public class Pipe implements Serializable {
	
	private Object data;
	private String command;
	//private String IP; 	MIGHT BE HELPFULL LATER
	
	/**
	 * Constructor for pipe.
	 */
	public Pipe() {
		setData(null);
		setCommand(null);
	} 
	
	/**
	 * Constructor for pipe initialized with command.
	 * @param command	message as string.
	 */
	public Pipe(String command) {
		this.setCommand(command);
	}
	
	/**
	 * Constructor for pipe initialized with command and data.
	 * @param command	message as string.
	 * @param data		object type for data.
	 */
	public Pipe(String command, Object data) {
		this.setData(data);
		this.setCommand(command);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	/**
	 * Create a string for pipe with command and data as string.
	 */
	@Override
	public String toString() {
		return "\nCommand: " + command.toString() + "\nData: " + data.toString() + "\n";
	}
	
}

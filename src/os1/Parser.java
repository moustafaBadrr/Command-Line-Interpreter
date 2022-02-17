package os1;

import java.util.HashMap;

public class Parser {
	private String[] args=null; // Will be filled by arguments extracted by parse method
	private String cmd=null; // Will be filled by the command extracted by parse method
	public int flag=-1;
	private HashMap<String, Integer> cmdlist= new HashMap<String, Integer>();

	Parser() {
		cmdlist.put("clear", 0);
		cmdlist.put("cd", 1);
		cmdlist.put("ls", 1);
		cmdlist.put("cp", 2);
		cmdlist.put("mv", 2);
		cmdlist.put("rm", 1);
		cmdlist.put("mkdir", 1);
		cmdlist.put("rmdir", 1);
		cmdlist.put("cat", 2);
		cmdlist.put("more", 1);
		cmdlist.put("pwd", 0);
		cmdlist.put("help", 0);
		cmdlist.put("args", 1);
		cmdlist.put("date", 0);

	}

	public boolean parse(String input) {
		if(input.contains("  ")||input.contains("   ")) {
			return false;
		}
		String[] all=input.split(" ");
		if(cmdlist.containsKey(all[0])) {
			cmd=all[0];

			if(cmd.equals("cat")) {
				if(all[1].equals(">")) {
					flag=1;
					args = new String[all.length-1];
				}
				else if(all[1].equals(">>")) {
					flag=2;
					args = new String[all.length-1];
				}
				else {
					flag=0;
					args = new String[all.length-1];
				}
			}
			else if(cmd.equals("clear")||cmd.equals("pwd")||cmd.equals("help")||cmd.equals("date")) {
				flag=0;
				args=new String[0];
			}
			else if(all[1].equals(">")) {
				flag=1;
				args = new String[cmdlist.get(all[0])+1];
			}
			else if(all[1].equals(">>")) {
				flag=2;
				args = new String[cmdlist.get(all[0])+1];
			}
			else {
				flag=0;
				args = new String[cmdlist.get(all[0])];
			}

			if(all.length-1!=args.length) {
				flag=-1;
				cmd=null;
				args=null;
				return false;
			}
			for (int i = 1; i < args.length+1; i++) {
				args[i-1]=all[i];
			}
			return true;
		}
		return false;
	}

	public String getCmd() {
		return cmd;
	}

	public String[] getArguments() {

		return args;
	}
}

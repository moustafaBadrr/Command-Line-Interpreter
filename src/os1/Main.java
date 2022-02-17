package os1;

import java.io.IOException;
import java.util.Scanner;
import os1.Parser;
import os1.Termianl;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		Parser p = new Parser();
		Termianl t = new Termianl();

		while (true) {
			System.out.print(t.currentDirectory + "~ ");
			String c;
			c = input.nextLine();
			String[] allcommand = c.split(" \\| ");
			if (c.equals("exit") || c.equals("quit") || c.equals("q")) {
				System.out.println("exit");
				break;
			}
			for (int i = 0; i < allcommand.length; i++) {

				String command = allcommand[i];

				if (!p.parse(command)) {
					System.out.println("not a command");
					continue;
				}
				String cmd = p.getCmd();

				String[] arg = p.getArguments();
				// System.out.println(arg[0]);
				if (p.flag == 0) {
					if (cmd.equals("cat")) {
						t.cat(arg, p.flag);
					} else if (cmd.equals("cd")) {
						t.cd(arg[0], p.flag);
					} else if (cmd.equals("cp")) {
						t.cp(arg[0], arg[1], p.flag);
					} else if (cmd.equals("mv")) {
						t.mv(arg[0], arg[1], p.flag);
					} else if (cmd.equals("rm")) {
						t.rm(arg[0], p.flag);
					} else if (cmd.equals("pwd")) {
						t.pwd();
					} else if (cmd.equals("clear")) {
						t.clear();
					} else if (cmd.equals("ls")) {
						t.ls(arg[0], p.flag);
					} else if (cmd.equals("mkdir")) {
						t.mkdir(arg[0], p.flag);
					} else if (cmd.equals("rmdir")) {
						t.rmdir(arg[0], p.flag);
					} else if (cmd.equals("args")) {
						t.args(arg[0], p.flag);
					} else if (cmd.equals("date")) {
						t.Date(p.flag);
					} else if (cmd.equals("help")) {
						t.help();
					} else if (cmd.equals("more")) {
						t.more(arg[0], p.flag);
					} else {
						continue;
					}
				} else {
					if (cmd.equals("cat")) {
						t.cat(arg, p.flag);
					} else if (cmd.equals("cd")) {
						t.cd(arg[1], p.flag);
					} else if (cmd.equals("cp")) {
						t.cp(arg[1], arg[2], p.flag);
					} else if (cmd.equals("mv")) {
						t.mv(arg[1], arg[2], p.flag);
					} else if (cmd.equals("rm")) {
						t.rm(arg[1], p.flag);
					} else if (cmd.equals("pwd")) {
						t.pwd();
					} else if (cmd.equals("clear")) {
						t.clear();
					} else if (cmd.equals("ls")) {
						t.ls(arg[1], p.flag);
					} else if (cmd.equals("mkdir")) {
						t.mkdir(arg[1], p.flag);
					} else if (cmd.equals("rmdir")) {
						t.rmdir(arg[1], p.flag);
					} else if (cmd.equals("args")) {
						t.args(arg[1], p.flag);
					} else if (cmd.equals("date")) {
						t.Date(p.flag);
					} else if (cmd.equals("help")) {
						t.help();
					} else if (cmd.equals("more")) {
						t.more(arg[1], p.flag);
					} else {
						continue;
					}
				}

			}

		}

	}

}

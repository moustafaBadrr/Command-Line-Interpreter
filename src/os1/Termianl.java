package os1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Termianl {
	public String currentDirectory = "C:\\Users\\" + System.getProperty("user.name") + "\\";
	private final String outputfile = "C:\\Users\\" + System.getProperty("user.name")
			+ "\\OneDrive\\Desktop\\cmdfile.txt";

	private void writetooutputfile(String text) throws IOException {

		File f = new File(outputfile);

		if (!f.exists()) {
			f.createNewFile();

			PrintWriter pw = new PrintWriter(f);
			pw.print(text);

			pw.close();
		} else {

			PrintWriter pw = new PrintWriter(f);
			pw.println(text);

			pw.close();
		}

	}

	private void addtooutputfile(String text) throws IOException {

		File f = new File(outputfile);
		FileWriter add = new FileWriter(f, true);
		if (!f.exists()) {
			f.createNewFile();
			PrintWriter pw = new PrintWriter(add);
			pw.print(text);

			pw.close();
		} else {

			PrintWriter pw = new PrintWriter(add);
			pw.println(text);

			pw.close();
		}
	}

	public void cp(String sourcePath, String destinationPath, int flag) throws IOException {
		String spath;
		String dpath;
		if (sourcePath.contains(":")) {
			spath = sourcePath;
		} else {
			spath = currentDirectory  + sourcePath;
		}
		if (destinationPath.contains(":")) {
			dpath = destinationPath + "copyfile.txt";
		} else {
			dpath = currentDirectory +destinationPath+"\\"+ "copyfile.txt";
		}
		File f = new File(spath);
		if (!f.exists()) {
			System.out.println(spath + " not exists!");
			return;
		}
		File nf = new File(dpath);
		nf.createNewFile();
		if (!nf.exists()) {
			System.out.println(dpath + " not exists!");
			return;
		}
		rm(dpath, 0);
		try {
			if (flag == 1) {
				writetooutputfile("the file is copied successfully in path:> " + dpath);
			} else if (flag == 2) {
				addtooutputfile("the file is copied successfully in path:> " + dpath);
			}
			Files.copy(f.toPath(), nf.toPath());
			System.out.println("the file is copied successfully in path:> " + dpath);
		} catch (Exception e) {
			System.out.println("error to copy!!");
		}
	}

	public void mv(String sourcePath, String destinationPath, int flag) throws IOException {
		String spath;
		String dpath;
		if (sourcePath.contains(":")) {
			spath = sourcePath;
		} else {
			spath = currentDirectory + sourcePath;
		}
		if (destinationPath.contains(":")) {
			dpath = destinationPath + "move.txt";
		} else {
			dpath = currentDirectory +  destinationPath +"\\" + "move.txt";
		}
		File f = new File(spath);
		if (!f.exists()) {
			System.out.println(spath + " not exists!");
			return;
		}
		File nf = new File(dpath);
		nf.createNewFile();
		if (!nf.exists()) {
			System.out.println(dpath + " not exists!");
			return;
		}
		rm(dpath, 0);
		try {
			if (flag == 1) {
				writetooutputfile("the file is move successfully in path:> " + dpath );
			} else if (flag == 2) {
				addtooutputfile("the file is move successfully in path:> " + dpath );
			}
			Files.move(f.toPath(), nf.toPath());
			System.out.println("The File is moved successfully in:> " + dpath);
		} catch (Exception e) {
			System.out.println("The File didn't move!!");
		}
	}

	public void rm(String sourcePath, int flag) throws IOException {
		String path;
		if (sourcePath.contains(":")) {
			path = sourcePath;
		} else {
			path = currentDirectory +  sourcePath;
		}
		File f = new File(path);
		if (f.exists()) {
			if (f.delete()) {
				if (flag == 1) {
					writetooutputfile("the file " + sourcePath + " is deleted successfully in path:> " + path);
				} else if (flag == 2) {
					addtooutputfile("the file " + sourcePath + " is deleted successfully in path:> " + path);
				}
				System.out.println("the file " + sourcePath + " is deleted successfully in path:> " + path);
			} else {
				System.out.println("delete failed");
			}
		}

	}

	public void pwd() throws IOException {
		System.out.println("the current Directory is => "+currentDirectory);
	}

	public void clear() {
		for (int i = 0; i < 100; i++)
			System.out.println();
	}

	public void cd(String p, int flag) throws IOException {
		if (p.contains(":")) {
			File folder = new File(p);
			if (folder.exists()) {
				currentDirectory = p+"\\";
			}

		} else {
			String path;
			path = currentDirectory  +p+"\\";
			File folder = new File(path);
			if (folder.exists()) {
				currentDirectory = path;
			}
		}
		if (flag == 1) {
			writetooutputfile("the current Directory now is :" + currentDirectory);
		} else if (flag == 2) {
			addtooutputfile("the current Directory now is :" + currentDirectory);
		}

	}

	public void ls(String filepath, int flag) throws IOException {

	    if (!filepath.contains(":") && !filepath.equals(""))
			filepath = currentDirectory + filepath + "\\";
		File f;
		String[] paths;
		f = new File(filepath);
		paths = f.list();
		String all = "";
		for (String path : paths) {
			System.out.println(path);
			all += path + "\n";
		}
		if (flag == 1) {
			writetooutputfile(all);
		} else if (flag == 2) {
			addtooutputfile(all);
		}
	}

	public void mkdir(String direction, int flag) throws IOException {
		if (!direction.contains(":") && !direction.equals("")) {
			direction = currentDirectory + direction + "\\";
		}
		File s = new File(direction);
		if (s.mkdir()) {
			System.out.println("the dir "+direction+" created successfully ");
			if (flag == 1) {
				writetooutputfile("the dir "+direction+" created successfully ");
			} else if (flag == 2) {
				addtooutputfile("the dir "+direction+" created successfully ");
			}
		}
	}

	public void rmdir(String p, int flag) throws IOException {
		String path;
		if (p.contains(":")) {
			path = p;
		} else {
			path = currentDirectory + "\\" + p;
		}
		File folder = new File(path);
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] allf = folder.listFiles();
				for (File f : allf) {
					f.delete();
				}
			}
			if(folder.delete())
			{
				System.out.println("the dir deleted successfully ");
			}
			if (flag == 1) {
				writetooutputfile("the dir deleted successfully ");
			} else if (flag == 2) {
				addtooutputfile("the dir deleted successfully ");
			}
		}
		else {
			System.out.println("deleting failed!!");
		}
	}

	public void args(String cmd, int flag) throws IOException {
		String output;
		if (cmd.equals("clear"))
			output = "The Number of args is equal: 0";
		else if (cmd.equals("cp"))
			output = "The Number of args is equal: 2 and it takes (Sourcepath,destinationpath)";
		else if (cmd.equals("Date"))
			output = "The Number of args is equal: 0";
		else if (cmd.equals("args"))
			output = "The Number of args is equal: 1and it takes (String comm)";
		else if (cmd.equals("help"))
			output = "The Number of args is equal: 0";
		else if (cmd.equals("rm"))
			output = "The Number of args is equal: 1 and it takes (Sourcepath";
		else if (cmd.equals("rmdir"))
			output = "The Number of args is equal: 1 and it takes (String p)";
		else if (cmd.equals("cd"))
			output = "The Number of args is equal: 1 and it takes (Sourcepath)";
		else if (cmd.equals("is"))
			output = "The Number of args is equal: 1 and it takes (path)";
		else if (cmd.equals("mkdir"))
			output = "The Number of args is equal: 1 and it takes (name of the file)";
		else if (cmd.equals("more"))
			output = "The Number of args is equal: 2 and it takes (Sourcepath,destinationpath)";
		else if (cmd.equals("pwd"))
			output = "The Number of args is equal: 0";
		else if (cmd.equals("mv"))
			output = "The Number of args is equal: 2 and it takes (Sourcepath,destinationpath)";
		else if (cmd.equals("cat"))
			output = "The Number of args is equal: one or more and it takes (Sourcepath)";
		else
			output = "not an args !!";

		System.out.println(output);

		if (flag == 1) {
			writetooutputfile(output);
		} else if (flag == 2) {
			addtooutputfile(output);
		}
	}

	public static void Date(int flag) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
	}

	public void help() {
		System.out.println("Clear: This command can be called to clear the current terminal screen and it can be redirected to clear the screen of some other terminal.");
		System.out.println("cd: This command changes the current directory to another one.");
		System.out.println("ls: These programs list each given file or directory name. Directory contents are sorted alphabetically. For ls, files are by default listed in columns, sorted vertically, if the standard output is a terminal; otherwise, they are listed one per line.");
		System.out.println("pwd: Display current user directory.");
		System.out.println("cp: Copy the file from file to file");
		System.out.println("mv: Move file from file to file");
		System.out.println("rm: Remove a specific file");
		System.out.println("mkdir: creates a directory with each given name. By default, the mode of created directories is 0777 minus the bits set in the umask.");
		System.out.println("rmdir: removes each given empty directory. If any nonoption argument does not refer to an existing empty directory, it is an error.");
		System.out.println("date:  display or to set the date and time of the system.");
		System.out.println("cat: Concatenate files and print on the standard output.");
		System.out.println("more: Let us display and scroll down the output in one direction only. You can scroll page by page or line by line.");
		System.out.println(">: Redirect the output to be written to a file using the redirect > create/replace file operator.");
		System.out.println(">>: Redirect the output to be written to a file using the redirect >> create/append to file operator.");
		System.out.println("|: redirect the output of the previous command as in input to another command.");
	}

	public void cat(String[] paths, int flag) throws IOException {
		String data = "";

		if (flag == 1) {
			for (int i = 1; i < paths.length; i++) {
				String path;
				if (paths[i].contains(":")) {
					path = paths[i];
				} else {
					path = currentDirectory + "\\" + paths[i];
				}
				Scanner x = new Scanner(new File(path));
				while (x.hasNext()) {
					data += x.nextLine();
					data += "\n";
				}
			}
			writetooutputfile(data);
		} else if (flag == 2) {
			for (int i = 1; i < paths.length; i++) {
				String path;
				if (paths[i].contains(":")) {
					path = paths[i];
				} else {
					path = currentDirectory + "\\" + paths[i];
				}
				Scanner x = new Scanner(new File(path));
				while (x.hasNext()) {
					data += x.nextLine();
					data += "\n";
				}
			}
			addtooutputfile(data);
		} else {
			
			for (int i = 0; i < paths.length; i++) {
				String path;
				if (paths[i].contains(":")) {
					path = paths[i];
				} else {
					path = currentDirectory + "\\" + paths[i];
				}
				Scanner x = new Scanner(new File(path));
				while (x.hasNext()) {
					data += x.nextLine();
					data += "\n";
				}
			}
		}

		System.out.println(data);
	}

	public void more(String pathfile, int flag) {
		if (!pathfile.contains(":") && !pathfile.equals("")) {
			pathfile = currentDirectory + pathfile;
		}
		File f = new File(pathfile);
		try {
			FileReader fileReader = new FileReader(f);
			BufferedReader in = new BufferedReader(fileReader);
			String line, userInputString;
			int i = 0;
			int l = 1;
			while ((line = in.readLine()) != null) {
				System.out.println(l + "=> " + line);
				i++;
				l++;
				if (i == 10) {
					System.out.println("if u want more lines Press Enter..");
					System.out.println("if u don't want more lines Insert out...");
					Scanner UIS = new Scanner(System.in);
					userInputString = UIS.nextLine();
					if (UIS.hasNextLine()) {
						i = 0;
					}
					if (userInputString.equals("out")) {
						break;
					}
				}
				if (flag == 1) {
					writetooutputfile(line);
					continue;
				} else if (flag == 2) {
					addtooutputfile(line);
					continue;
				}

			}
			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(" file not found.");
		} catch (IOException ex) {
			System.out.println("input/output error.");
		}
	}

}

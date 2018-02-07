import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;

public class TasksReader {

	ArrayList<ArrayList<Integer>> forced_partial_assignments = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> forbidden_Machines = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> too_near_tasks = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> machine_penalties = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> too_near_penalties = new ArrayList<ArrayList<Integer>>();
	Map<String, Integer> machineNames = new HashMap<String, Integer>();
	Map<String, Integer> taskNames = new HashMap<String, Integer>();


	public TasksReader(String fileName) {
        
		readFile(fileName);
	}

	public void readFile(String fileName){

		machineNames.put("1", 0);
		machineNames.put("2", 1);
		machineNames.put("3", 2);
		machineNames.put("4", 3);
		machineNames.put("5", 4);
		machineNames.put("6", 5);
		machineNames.put("7", 6);
		machineNames.put("8", 7);

		taskNames.put("A", 0);
		taskNames.put("B", 1);
		taskNames.put("C", 2);
		taskNames.put("D", 3);
		taskNames.put("E", 4);
		taskNames.put("F", 5);
		taskNames.put("G", 6);
		taskNames.put("H", 7);

		BufferedReader reader = null;

		try{
			reader = new BufferedReader(new FileReader(fileName));


			//See if the first line is "Name:" as the format dictates
			if (!reader.readLine().equals("Name:")) throw new Exception("error message that needs title1");

			//See if the filename listed under name is indeed the name of the file
			if (!reader.readLine().equals(fileName)) throw new Exception("error message that needs title2");
			reader.readLine();		// reads blank line

			// Parse forced partial assigments
			if (!reader.readLine().equals("forced partial assignment:")) throw new Exception("error message that needs title3");

			String line = reader.readLine();
			while ( !line.equals("") ) {
				String [] lineArray = line.substring(1, line.length() - 1).split(",");

				forced_partial_assignments.add(new ArrayList<Integer>(Arrays.asList(
						machineNames.get(lineArray[0]), taskNames.get(lineArray[1]) ) ));

				line = reader.readLine();

			}


			if (!reader.readLine().equals("forbidden machine:")) throw new Exception("error message that needs title4");

			line = reader.readLine();
			while ( !line.equals("") ) {
				String[] lineArray = line.substring(1, line.length() - 1).split(",");

				forbidden_Machines.add(new ArrayList<Integer>(Arrays.asList(
						machineNames.get(lineArray[0]), taskNames.get(lineArray[1]) ) ));

				line = reader.readLine();

			}

			if (!reader.readLine().equals("too-near tasks:")) throw new Exception("error message that needs title5");

			line = reader.readLine();
			while ( !line.equals("") ) {
				String[] lineArray = line.substring(1, line.length() - 1).split(",");

				too_near_tasks.add(new ArrayList<Integer>(Arrays.asList(
						taskNames.get(lineArray[0]), taskNames.get(lineArray[1]) ) ));

				line = reader.readLine();

			}

			if (!reader.readLine().equals("machine penalties:")) throw new Exception("error message that needs title6");

			line = reader.readLine();
			while ( !line.equals("") ) {
				String[] lineArray = line.split(" ");
				ArrayList<Integer> intLineArray = new ArrayList<Integer>();

				for (String index : lineArray) intLineArray.add(Integer.parseInt(index));
				machine_penalties.add(intLineArray);

				if (intLineArray.size() != 8) throw new Exception("machine penalty error");

				line = reader.readLine();
			}
			if (machine_penalties.size() != 8) throw new Exception("machine penalty error");

			if (!reader.readLine().equals("too-near penalities")) throw new Exception("error message that needs title7");

			line = reader.readLine();
			while ( line != null && (!line.equals("")) ) {

				String[] lineArray = line.substring(1, line.length() - 1).split(",");

				too_near_penalties.add(new ArrayList<Integer>(Arrays.asList(
						taskNames.get(lineArray[0]), taskNames.get(lineArray[1]), Integer.parseInt(lineArray[2]) ) ));

				if (too_near_penalties.get(too_near_penalties.size() - 1).get(2) < 0)
					throw new Exception("Error while parsing input file");

				line = reader.readLine();

			}



		// Error checking
		checkForErrors(forced_partial_assignments, true, true);
		checkForErrors(forbidden_Machines, false, true);
		checkForErrors(too_near_tasks, false, true);
		checkForErrors(too_near_penalties, false, true);




		} catch (IOException e) {
			System.out.println(e.getMessage() + "\nIO Error");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Error while parsing input file");
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} finally {
			try {
				if (reader != null) reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\nIO Error");
			System.exit(0);

			}
		}


	}


	public void printValues() {
		System.out.println("\n");
		System.out.println("forced_partial_assignments");
		for (ArrayList<Integer> index : forced_partial_assignments) {
			System.out.println(index);
		}
		System.out.println("\n");
		System.out.println("forbidden_Machines");
		for (ArrayList<Integer> index : forbidden_Machines) {
			System.out.println(index);
		}
		System.out.println("\n");
		System.out.println("too_near_tasks");
		for (ArrayList<Integer> index : too_near_tasks) {
			System.out.println(index);
		}

		System.out.println("\n");
		System.out.println("machine_penalties");
		for (ArrayList<Integer> index : machine_penalties) {
			System.out.println(index);
		}

		System.out.println("\n");
		System.out.println("too_near_penalties");
		for (ArrayList<Integer> index : too_near_penalties) {
			System.out.println(index);
		}
		System.out.println("\n");

	}


	public void checkForErrors(ArrayList<ArrayList<Integer>> list, Boolean checkingForDoubles, Boolean checkingForNull)
				throws Exception {

		Set<Integer> ListIndex0 = new HashSet<Integer>();
		Set<Integer> ListIndex1 = new HashSet<Integer>();

		for (ArrayList<Integer> index: list) {
			ListIndex0.add(index.get(0));
			ListIndex1.add(index.get(1));
		}
		if (checkingForDoubles)
			if (list.size() != ListIndex0.size() ||
				list.size() != ListIndex1.size() )
				throw new Exception("partial assignment error");

		if (checkingForNull)
			if (ListIndex0.contains(null) ||
				ListIndex1.contains(null) )
				throw new Exception("invalid machine/task");
	}





}

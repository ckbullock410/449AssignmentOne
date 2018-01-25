import java.io.FileReader;
import java.io.BufferedReader;

public class 449_AssignmentOne {
    int [] partialAssignments = new int[8];     //do these with hashmaps probably
    int [] forbiddenAssignments = new int[8];
    int [] tooNearPenalties = new int[8];

    int [][] penaltiesArray = new int[8];       //will need to rearrange this into a tree for branch and bound
    for (int i = 0; i < 8; i ++){
        penaltiesArray[i] = new int[8];
    }

    public static void main(String, args[]){
        readFile(args[1]);
        assignTasks();
    }
    private String compareStr(String x, String y){
        if (x != y){
            displayInvalid();
        } else  {
            return x;
        }
    }

    private String readNewLines(BufferedReader br){
        String str = br.readLine().replaceAll("\\s", "");
        while(str == "\n"){
            str = br.readLine().replaceAll("\\s","");
        }
        return str;
    }

    private void readFile(fileName){
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
        } catch (IOException ioe){
            System.out.println("error opening file");
        }

        //See if the first line is "Name:" as the format dictates
        compareStr(br.readLine().replaceAll("\\s",""), "Name:\n");

        //See if the filename listed under name is indeed the name of the file
        compareStr(br.readLine().replaceAll("\\s",""), fileName);

        //account for 1 or more new line characters until next section is reached
        compareStr(readNewLines(br), "forcedpartialassignment:\n");
        // TO-DO ..... READ PAIRS, NOT SURE HOW FORMATTED YET
        compareStr(readNewLines(br), "forbiddenmachine:\n");
        //TO-DO ... Read pairs
        compareStr(readNewLines(br), "too-neartasks:\n");
        //TO-DO .. Read pairs
        compareStr(readNewLines(br), "machinepenalties:\n");
        //read matrix into the 2d Array penaltiesArray


    }

    private void assignTasks(){
        /*
        * branch and bound search algorithm
        *
        * make a binary search tree for the column or row of the task (loop through machines or tasks for assignment?)
        *
        * store pairs of mach, tasks
        */
    }

    private void calcPenalties(){
        /*
        * look at stored pairs and see if they violate hard constraints
        * call display function that prints invalid if appropriate
        * lookup penalties for each pair
        * if not invalid call display function
        */
    }

    private void display(){
        //print the pairs and penalty val
    }

    private void displayInvalid(){
        // print invalid etc...
        System.exit(0);
    }
}
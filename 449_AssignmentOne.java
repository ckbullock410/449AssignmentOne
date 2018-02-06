import java.io.FileReader;
import java.io.BufferedReader;

public class 449_AssignmentOne {
    int [] tasks = new int[8];
    int [] assignedTasks = new int[8];
    int [] currentSolution = new int[8];
    TaskReader tr;
    int minPenalty = 0;

    public static void main(String, args[]){  
        for (int j = 0; j < tasks.length; j++){
            //if task isn't taken make it 1, else make it 0
            tasks[j] = 0;
            //unassigned machines have value 0 in them.
            assignedTasks[j] = 0;
        }  
		/*
		When you create a TasksReader object, you need to pass in the name of the file and it will parse all of the data
		and store it in 2d arrays
		*/
		tr = new TasksReader(args[0]);

		/*
		The variables can be accessed directly
		Example:
        */
        forbid = tr.forbiden_Machines;
		forced = tr.forced_partial_assignments;

		tr.printValues();				// Use this to print all of the data to the terminal
        loopFunction(0);
    }

    public void loopFunction(machineNum){
            /*
            * loop through all 8 tasks for each 8 machines
            * if machine is already partially_assigned go to next machine
            * if task is already assigned continue to next task
        Breaks Constraints
            * if task is forbidden continue to next task
            * if task violates too-near constraint continue to next task
            * 
        calcPenalty
            * if current penalty already higher than min penalty found so far, break
            * if machineNum is 8 and it's in the inner most loop and penalty is least
            * found so far, store the paired machines and penalty value.
            *
            */
        int penalty;
        if (machineNum < 8){
            for (int i = 0; i < 8; i++){
                if (forced[machineNum] != null){
                    //put into assignTasks and currentSolution prior to first loopFunction call
                    loopFunction(machine + 1);
                }
                if (tasks[i] != 0){
                    //if no valid tasks for this machine unasssign task for last machine
                    if (i == 7){
                        tasks[assignedTasks[machineNum - 1]] == 0;
                    }
                    //continue to next task (or last machine if i == 7)
                    continue;
                }
                if (breaksConstraints()){
                    if (i == 7){
                        tasks[assignedTasks[machineNum - 1]] == 0;
                    }
                    continue;
                }
                assignedTasks[machineNum] = i;      
                tasks[i] = 1;
                penalty = calcPenalty();

                if (penalty > minPenalty){
                    assignedTasks[machineNum] = 0;
                    break;
                }
                loopFunction(machineNum + 1);
                //if this combination is a new minimum penalty, store.
                if (machineNum == 7 && penalty < minPenalty){
                    for (int j = 0; j < currentSolution.length; j++){                     
                        currentSolution[j] = assignedTasks[j];
                    }
                }
            }
        }   
     }
    private boolean tasksTaken(){
        for (int i = 0; i < tasks.length; i++){
            if (tasks[i] == 0){
                return false;
            }
            return true;
        }
    }
    private boolean breaksConstraints(){
        // see if the machine task pair breaks constraints in forbidden or too near
    }

    private void calcPenalty(){
        /*
        * look at stored pairs and see if they violate hard constraints
        * call display function that prints invalid if appropriate
        * lookup penalties for each pair
        * if not invalid call display function
        */
    }

    private int convertLetterToIndex(String letter) {
        switch (letter) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            case "E": return 4;
            case "F": return 5;
            case "G": return 6;
            case "H": return 7;
            default: return -1 // This is the case if we get an invalid String
        }
    }
}
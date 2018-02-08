import java.io.FileReader;
import java.io.BufferedReader;
import TaskReader;

public class 449_AssignmentOne {
    int [] tasks = new int[8];
    int [] assignedTasks = new int[8];
    int [] currentSolution = new int[8];
    int [] curPenalties = new int[8];
    int runningPenalty = 0;
    TaskReader tr;
    int minPenalty = 0;

    public static void main(String, args[]){  
        tr = new TasksReader(args[0]);
        449_AssignmentOne assignment = new 449_AssignmentOne();
        assignment.init()
        assignment.loopFunction(0);
        SolutionWriter = new SolutionWriter(args[1]);
    }
    private void init(){
        for (int j = 0; j < tasks.length; j++){
            //if task isn't taken make it 1, else make it 0
            tasks[j] = 0;
            //unassigned machines have value -1 in them.
            assignedTasks[j] = -1;
        }        
    }

    public void loopFunction(machineNum){
            /*
            * need to check that penalties aren't being calculated when there are unassigned machines
            *
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
                if (tr.forced_partial_assignments[machineNum] != null){
                    assignedTasks[i] == tr.forced_partial_assignments[i];
                    tasks[tr.forced_partial_assignments[i]] = 1;
                    curPenalties[machineNum] = calcPenalty();
                    runningPenalty += curPenalties[machineNum];
                    loopFunction(machine + 1);
                }
                if (tasks[i] != 0 || breaksConstraints(machineNume, i)){
                    //if no valid tasks for this machine unasssign task for last machine
                    if (i == 7){
                        assignedTasks[machineNum - 1] = -1;
                        tasks[assignedTasks[machineNum - 1]] == 0;
                        runningPenalty -= curPenalties[machineNum - 1];
                        curPenalties[machineNum - 1] = 0;
                    }
                    //continue to next task (or last machine if i == 7)
                    continue;
                }

                assignedTasks[machineNum] = i;      
                tasks[i] = 1;
                curPenalties[machineNum] = calcPenalty();
                runningPenalty += curPenalties[machineNum];


                if (runningPenalty > minPenalty){
                    assignedTasks[machineNum] = 0;
                    tasks[assignedTasks[machineNum - 1]] == 0;
                    break;
                }
                loopFunction(machineNum + 1);
                //if this combination is a new minimum penalty, store.
                if (machineNum == 7 && penalty < minPenalty && tasksTaken()){
                    for (int j = 0; j < currentSolution.length; j++){                    
                        currentSolution[j] = assignedTasks[j];
                    }
                }
            }
        }   
     }
    //before storing something as a solution check if there are unassigned tasks
    private boolean tasksTaken(){
        for (int i = 0; i < tasks.length; i++){
            if (tasks[i] == 0){
                return false;
            }
            return true;
        }
    }

    private boolean breaksConstraints(int mach, int task){
        // see if the machine task pair breaks constraints in forbidden or too near
            for (int i = 0; i < tr.forbidden_Machines.size(); i++){
                if (tr.forbidden_Machines.get(i).get(0) == mach &&
                tr.forbidden_Machines.get(i).get(1) == task){
                    return true;
                }
            } 
            if (mach == 0){
                return false;
            }
            for (int j = 0; j < tr.too_near_tasks.size(); j++){
                //see if adding this task to this machine gives you a too near penalty with previous machine
                if (task != 7 && tr.too_near_tasks.get(j).get(0) == assignedTasks[mach - 1] &&
                tr.too_near_tasks.get(j).get(1) == assignedTasks[mach]){
                    penalty += tr.too_near_penalties.get(i).get(3);
                } 
                //when you machine is the last one, check the first machine to see if it gives too near penalty
                else if(task == 7 && tr.too_near_tasks.get(j).get(0) == assignedTasks[mach] &&
                tr.too_near_tasks.get(j).get(1) == assignedTasks[0]){
                    penalty += tr.too_near_penalties.get(i).get(3)
            }            
        }
        return false;
    }

    private int calcPenalty(mach, task){
        /*
        * look at stored pairs and see if they violate hard constraints
        * call display function that prints invalid if appropriate
        * lookup penalties for each pair
        * if not invalid call display function
        */
        int penalty += tr.machine_penalties.get(task).get(mach); //might be mach first task second
        if (mach == 0){
            return penalty;
        }
        //loop through too near penalties and see if penalty val needs to be added
        for (int i = 0; i < tr.too_near_penalties.size(); i++){
            //see if adding this task to this machine gives you a too near penalty with previous machine
            if (task != 7 && tr.too_near_penalties.get(i).get(0) == assignedTasks[mach - 1] &&
            tr.too_near_penalties.get(i).get(1) == assignedTasks[mach]){
                penalty += tr.too_near_penalties.get(i).get(3);
            } 
            //when you machine is the last one, check the first machine to see if it gives too near penalty
            else if(task == 7 && tr.too_near_penalties.get(i).get(0) == assignedTasks[mach] &&
            tr.too_near_penalties.get(i).get(1) == assignedTasks[0]){
                penalty += tr.too_near_penalties.get(i).get(3)
            }
        }
        return penalty;
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
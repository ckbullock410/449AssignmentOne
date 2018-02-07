import java.io.*;

public class SolutionWriter {

  FileWriter outputFile;
  BufferedWriter writer;

  public SolutionWriter(String fileName) {
    try {
      openOutputFile(fileName);
    }
    catch (IOException e) {
      System.err.println("ERROR: Could not create file");
    }
    finally{
      // Not sure what to put in here
    }
  }

  // We will use this method to write to the output file
  public void openOutputFile(String fileName) throws IOException {
    outputFile = new FileReader(fileName);
    writer = new BufferedWriter(outputFile);
  }

  /**
  * @param String pair is used for actually inputing the correct machine/task pairs into the output file and boolean noSolution is to be unassigned
  * so that when the flag is passed through, it will only output that there is no valid solution and int penalties is to be used to output the calculated
  * penalty
  */
  public void writeToFile(String[][] pair, boolean noSolution, int penalties) throws IOException {
      if (noSolution) {
        writer.append("No valid solution possible!");
        writer.close();
        return;
      }
      writer.append("Solution:\t")
      for (int i = 0; i < pair.length; i++) {
        if (i < pair.length - 1) {
          writer.append(pair[i][1] + ",\t");
        }
        else {
          writer.append(pair[i][1]);
        }

      }

    }

}

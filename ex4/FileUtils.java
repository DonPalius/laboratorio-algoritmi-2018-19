import graph.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileUtils {
  static void GraphCSV(Graph graph, String path) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(path));
      String line = br.readLine();
      while (line != null) {
        String[] fields = line.split(",");
        graph.addEdge(new Edge<>(new Vertex<>(fields[0]), new Vertex<>(fields[1]),Double.parseDouble(fields[2])));
        line = br.readLine();
    }
      br.close();
    }
      catch (IOException e) {
        System.out.println("Error, file not found ...");
    }
  }
}

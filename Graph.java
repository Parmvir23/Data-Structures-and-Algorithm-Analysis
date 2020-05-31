import java.util.*;

public class Graph {
  private ArrayList<Vertex> vertices;
  
	public Graph(int numVertices){
		vertices = new ArrayList<Vertex>(numVertices);
      
		for(int i=0;i<numVertices;i++){
			vertices.add(new Vertex("v"+Integer.toString(i)));
		}
	}
	
	public void addEdge(int src, int dest, int weight){
		Vertex s = vertices.get(src);
		Edge new_edge = new Edge(vertices.get(dest),weight);
		s.neighbours.add(new_edge);
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(int v){
		return vertices.get(v);
	}
}
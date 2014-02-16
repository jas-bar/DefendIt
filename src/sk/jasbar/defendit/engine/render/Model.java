package sk.jasbar.defendit.engine.render;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Face> faces = new ArrayList<Face>();
    private ArrayList<Normal> normals = new ArrayList<Normal>();

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void addFace(Face face) {
        faces.add(face);
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void addNormal(Normal n) {
        normals.add(n);
    }
}

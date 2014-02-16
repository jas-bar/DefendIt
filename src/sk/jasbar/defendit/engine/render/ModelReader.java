package sk.jasbar.defendit.engine.render;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ModelReader {
	private final File file;

	public ModelReader(String path) {
		this(new File(path));
	}

	public ModelReader(File file) {
		this.file = file;
	}

	public Model read() throws Exception {
		Model result = new Model();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.length() > 0) {
				String lineType = line.substring(0, line.indexOf(" ")+1);
				line = line.replace(lineType, "");
				if (lineType.equalsIgnoreCase("v ")) {
					String[] numbers = line.split(" ");
					Vertex v = new Vertex();
					v.x = Double.parseDouble(numbers[0]);
					v.y = Double.parseDouble(numbers[1]);
					v.z = Double.parseDouble(numbers[2]);
					result.addVertex(v);
				} else if (lineType.equalsIgnoreCase("f ")) {
					// faceV/.../faceN
					String[] groups = line.split(" ");
					Face face = new Face(groups.length);
					for (int i = 0; i < groups.length; i++) {
						face.faceV[i] = Integer
								.parseInt(groups[i].split("/")[0]);
						face.faceN[i] = Integer
								.parseInt(groups[i].split("/")[2]);
					}
					result.addFace(face);
				} else if (lineType.equalsIgnoreCase("vn ")) {
					String[] numbers = line.split(" ");
					Normal n = new Normal();
					n.x = Double.parseDouble(numbers[0]);
					n.y = Double.parseDouble(numbers[1]);
					n.z = Double.parseDouble(numbers[2]);
					result.addNormal(n);	
				}
			}
		}
		br.close();
		return result;
	}
}
package sk.jasbar.defendit.engine.render;
public class Face {
    public int[] faceV,faceN;

    public Face(int groups){
        faceV = new int[groups];
        faceN = new int[groups];
    }
}

public class GraphitePencil {

    protected StringBuilder paper;

    GraphitePencil() {
    }

    public void write(StringBuilder paper, String writtenText) {
        paper.append(writtenText);
    }
}

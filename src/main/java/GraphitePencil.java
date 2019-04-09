public class GraphitePencil {

    protected StringBuilder paper;

    GraphitePencil() {
        this.paper = new StringBuilder();
    }

    public void write(String writtenText) {
        this.paper.append(writtenText);
    }
}

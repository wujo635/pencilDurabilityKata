public class GraphitePencil {

    private int pencilDurability;

    GraphitePencil() {
    }

    GraphitePencil(int pencilDurability) {
        this.pencilDurability = pencilDurability;
    }

    public void write(StringBuilder paper, String writtenText) {
        paper.append(writtenText);
    }

    public int getPencilDurability() {
        return this.pencilDurability;
    }
}

public class GraphitePencil {

    private int pointDurability;

    GraphitePencil() {
    }

    GraphitePencil(int pointDurability) {
        this.pointDurability = pointDurability;
    }

    public void write(StringBuilder paper, String writtenText) {
        paper.append(writtenText);
        this.pointDurability -= writtenText.length();
    }

    public int getPointDurability() {
        return this.pointDurability;
    }
}

public class GraphitePencil {

    private int pointDurability;

    GraphitePencil() {
    }

    GraphitePencil(int pointDurability) {
        this.pointDurability = pointDurability;
    }

    public void write(StringBuilder paper, String writtenText) {
        paper.append(writtenText);
        updatePointDurability(writtenText.replaceAll("\\s+", ""));
    }

    private void updatePointDurability(String writtenText) {
        for (char c : writtenText.toCharArray()) {
            if (Character.isUpperCase(c)) {
                this.pointDurability -= 2;
            } else {
                this.pointDurability--;
            }
        }
    }

    public int getPointDurability() {
        return this.pointDurability;
    }
}

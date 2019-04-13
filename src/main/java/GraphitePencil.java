public class GraphitePencil {

    private int pointDurability;

    GraphitePencil() {
        this.pointDurability = 0;
    }

    GraphitePencil(int pointDurability) {
        this.pointDurability = pointDurability;
    }

    public void write(StringBuilder paper, String textToWrite) {
        paper.append(textToWrite);
        updatePointDurability(textToWrite);
    }

    private void updatePointDurability(String textToWrite) {
        String charactersToWrite = textToWrite.replaceAll("\\s+", "");
        for (char c : charactersToWrite.toCharArray()) {
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

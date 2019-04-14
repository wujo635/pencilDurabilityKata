public class GraphitePencil {

    private final int maxPointDurability;
    private int pointDurability;

    GraphitePencil() {
        this.maxPointDurability = 0;
        resetPointDurability();
    }

    GraphitePencil(int pointDurability) {
        this.maxPointDurability = pointDurability;
        resetPointDurability();
    }

    public void write(StringBuilder paper, String textToWrite) {
        for (char c : textToWrite.toCharArray()) {
            if (canWriteCharacter(c)) {
                paper.append(c);
            }
        }
    }

    private boolean canWriteCharacter(char c) {
        updatePointDurability(c);
        if (this.pointDurability >= 0) {
            return true;
        }
        return false;
    }

    private void updatePointDurability(char c) {
        if (Character.isUpperCase(c)) {
            this.pointDurability -= 2;
        } else if (!Character.isWhitespace(c)) {
            this.pointDurability--;
        }
    }

    public int getPointDurability() {
        return this.pointDurability;
    }

    public void resetPointDurability() {
        this.pointDurability = this.maxPointDurability;
    }
}

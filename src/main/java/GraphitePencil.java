public class GraphitePencil {

    private int pointDurability;

    GraphitePencil() {
        this.pointDurability = 0;
    }

    GraphitePencil(int pointDurability) {
        this.pointDurability = pointDurability;
    }

    public void write(StringBuilder paper, String textToWrite) {
        for (char c: textToWrite.toCharArray()) {
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
}

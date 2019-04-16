public class GraphitePencil {

    private final int maxPointDurability;
    private int pointDurability;
    private int length;

    GraphitePencil() {
        this.maxPointDurability = 100;
        this.pointDurability = 0;
        this.length = 10;
    }

    GraphitePencil(int pointDurability, int length) {
        this.maxPointDurability = pointDurability;
        this.pointDurability = pointDurability;
        this.length = length;
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

    public void sharpen() {
        this.pointDurability = this.maxPointDurability;
        this.length--;
    }

    public int getPointDurability() {
        return this.pointDurability;
    }

    public int getLength() {
        return this.length;
    }
}

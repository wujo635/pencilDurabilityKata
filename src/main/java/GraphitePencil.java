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
        this.pointDurability = Math.max(0, pointDurability);
        this.maxPointDurability = this.pointDurability;
        this.length = Math.max(0, length);
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
        if (this.length > 0) {
            this.pointDurability = this.maxPointDurability;
            this.length--;
        }

    }

    public void erase(StringBuilder paper, String textToErase) {
        int substringStartIndex = paper.lastIndexOf(textToErase);
        if (substringStartIndex >= 0) {
            int substringEndIndex = substringStartIndex + textToErase.length();
            String emptySpaces = new String(new char[textToErase.length()]).replace("\0", " ");
            paper.replace(substringStartIndex, substringEndIndex, emptySpaces);
        }
    }

    public int getPointDurability() {
        return this.pointDurability;
    }

    public int getLength() {
        return this.length;
    }

    public int getEraserDurability() {
        return 0;
    }
}

public class GraphitePencil {

    private final int maxPointDurability;
    private int pointDurability;
    private int eraserDurability;
    private int length;

    GraphitePencil() {
        this.maxPointDurability = 0;
    }

    GraphitePencil(int pointDurability, int length, int eraserDurability) {
        this.pointDurability = Math.max(0, pointDurability);
        this.maxPointDurability = this.pointDurability;
        this.length = Math.max(0, length);
        this.eraserDurability = Math.max(0, eraserDurability);
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
            int maximumCharactersToErase = Math.min(textToErase.length(), this.eraserDurability);
            int substringEndIndex = substringStartIndex + textToErase.length();
            String emptySpaces = emptyStringSizeOf(maximumCharactersToErase);
            paper.replace(substringEndIndex - maximumCharactersToErase, substringEndIndex, emptySpaces);
            updateEraserDurability(textToErase, maximumCharactersToErase);
        }
    }

    private void updateEraserDurability(String textToErase, int charactersErased) {
        int whitespaceCharacterCount = textToErase.length() - textToErase.replaceAll("\\s+", "").length();
        this.eraserDurability -= charactersErased;
        this.eraserDurability += whitespaceCharacterCount;
    }

    private String emptyStringSizeOf(int maximumCharactersToErase) {
        return new String(new char[maximumCharactersToErase]).replace("\0", " ");
    }

    public void edit(StringBuilder paper, int i, String onion) {

    }

    public int getPointDurability() {
        return this.pointDurability;
    }

    public int getLength() {
        return this.length;
    }

    public int getEraserDurability() {
        return this.eraserDurability;
    }
}

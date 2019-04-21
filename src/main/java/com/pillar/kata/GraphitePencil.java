package com.pillar.kata;

public class GraphitePencil {

    private final int maxPointDurability;
    private int pointDurability;
    private int eraserDurability;
    private int length;

    GraphitePencil() {
        this.maxPointDurability = 0;
    }

    GraphitePencil(int pointDurability, int length, int eraserDurability) {
        this.maxPointDurability = Math.max(0, pointDurability);
        this.pointDurability = this.maxPointDurability;
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
        int index = paper.lastIndexOf(textToErase);
        if (index >= 0) {
            eraseString(paper, textToErase, index);
        }
    }

    private void eraseString(StringBuilder paper, String textToErase, int startIndex) {
        int sizeOfErase = Math.min(textToErase.length(), this.eraserDurability);
        int endIndex = startIndex + textToErase.length();
        paper.replace(endIndex - sizeOfErase, endIndex, emptySpaces(sizeOfErase));
        updateEraserDurability(textToErase, sizeOfErase);
    }

    private void updateEraserDurability(String textToErase, int sizeOfErase) {
        int whitespaceCount = textToErase.length() - textToErase.replaceAll("\\s+", "").length();
        this.eraserDurability -= sizeOfErase;
        this.eraserDurability += whitespaceCount;
    }

    private String emptySpaces(int maximumCharactersToErase) {
        return new String(new char[maximumCharactersToErase]).replace("\0", " ");
    }

    public void edit(StringBuilder paper, int index, String textToEditIn) {
        if (textToEditIn.length() > 0) {
            editNextCharacter(paper, index, textToEditIn);
            edit(paper, index + 1, textToEditIn.substring(1));
        }
    }

    private void editNextCharacter(StringBuilder paper, int index, String textToEditIn) {
        char characterToWrite = textToEditIn.charAt(0);
        allotSpace(paper, index);
        if (isCollision(paper, index)) {
            characterToWrite = '@';
        }
        if (canWriteCharacter(characterToWrite)) {
            paper.setCharAt(index, characterToWrite);
        }
    }

    private void allotSpace(StringBuilder paper, int index) {
        if (index >= paper.length()) {
            paper.append(emptySpaces(index - paper.length() + 1));
        }
    }

    private boolean isCollision(StringBuilder paper, int indexToWriteTo) {
        return !Character.isWhitespace(paper.charAt(indexToWriteTo));
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

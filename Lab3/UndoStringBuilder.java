package Lab3;

import java.util.LinkedList;
import java.util.function.Consumer;

public class UndoStringBuilder {
    private StringBuilder sb;
    private LinkedList<Consumer<StringBuilder>> undoOperations = new LinkedList<>();

    public UndoStringBuilder() {
        sb = new StringBuilder();
    }

    public UndoStringBuilder(CharSequence seq) {
        sb = new StringBuilder(seq);
    }

    public UndoStringBuilder(int capacity) {
        sb = new StringBuilder(capacity);
    }

    public UndoStringBuilder(String str) {
        sb = new StringBuilder(str);
    }

    public UndoStringBuilder append(Object obj) {
        sb.append(obj);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - obj.toString().length()));
        return this;
    }

    public UndoStringBuilder append(String str) {
        sb.append(str);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - str.length()));
        return this;
    }

    public UndoStringBuilder append(StringBuffer sbuff) {
        sb.append(sbuff);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - sbuff.length()));
        return this;
    }

    public UndoStringBuilder append(CharSequence s) {
        sb.append(s);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - s.length()));
        return this;
    }

    public UndoStringBuilder append(CharSequence s, int start, int end) {
        sb.append(s, start, end);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - (end - start)));
        return this;
    }

    public UndoStringBuilder append(char[] str) {
        sb.append(str);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - str.length));
        return this;
    }

    public UndoStringBuilder append(char[] str, int offset, int len) {
        sb.append(str, offset, len);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - len));
        return this;
    }

    public UndoStringBuilder append(boolean b) {
        sb.append(b);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - String.valueOf(b).length()));
        return this;
    }

    public UndoStringBuilder append(char c) {
        sb.append(c);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - 1));
        return this;
    }

    public UndoStringBuilder append(int i) {
        sb.append(i);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - String.valueOf(i).length()));
        return this;
    }

    public UndoStringBuilder append(long lng) {
        sb.append(lng);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - String.valueOf(lng).length()));
        return this;
    }

    public UndoStringBuilder append(float f) {
        sb.append(f);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - String.valueOf(f).length()));
        return this;
    }

    public UndoStringBuilder append(double d) {
        sb.append(d);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - String.valueOf(d).length()));
        return this;
    }

    public UndoStringBuilder appendCodePoint(int codePoint) {
        sb.appendCodePoint(codePoint);
        undoOperations.offerFirst(sb -> sb.setLength(sb.length() - Character.charCount(codePoint)));
        return this;
    }

    public UndoStringBuilder delete(int start, int end) {
        String deleted = sb.substring(start, end);
        sb.delete(start, end);
        undoOperations.offerFirst(sb -> sb.insert(start, deleted));
        return this;
    }

    public UndoStringBuilder deleteCharAt(int index) {
        char deleted = sb.charAt(index);
        sb.deleteCharAt(index);
        undoOperations.offerFirst(sb -> sb.insert(index, deleted));
        return this;
    }

    public UndoStringBuilder replace(int start, int end, String str) {
        String replaced = sb.substring(start, end);
        sb.replace(start, end, str);
        undoOperations.offerFirst(sb -> sb.replace(start, start + str.length(), replaced));
        return this;
    }

    public UndoStringBuilder insert(int index, char[] str, int offset, int len) {
        sb.insert(index, str, offset, len);
        undoOperations.offerFirst(sb -> sb.delete(index, index + len));
        return this;
    }

    public UndoStringBuilder insert(int offset, Object obj) {
        sb.insert(offset, obj);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + obj.toString().length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, String str) {
        sb.insert(offset, str);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + str.length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, char[] str) {
        sb.insert(offset, str);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + str.length));
        return this;
    }

    public UndoStringBuilder insert(int dstOffset, CharSequence s) {
        sb.insert(dstOffset, s);
        undoOperations.offerFirst(sb -> sb.delete(dstOffset, dstOffset + s.length()));
        return this;
    }

    public UndoStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        sb.insert(dstOffset, s, start, end);
        undoOperations.offerFirst(sb -> sb.delete(dstOffset, dstOffset + (end - start)));
        return this;
    }

    public UndoStringBuilder insert(int offset, boolean b) {
        sb.insert(offset, b);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + String.valueOf(b).length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, char c) {
        sb.insert(offset, c);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + 1));
        return this;
    }

    public UndoStringBuilder insert(int offset, int i) {
        sb.insert(offset, i);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + String.valueOf(i).length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, long l) {
        sb.insert(offset, l);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + String.valueOf(l).length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, float f) {
        sb.insert(offset, f);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + String.valueOf(f).length()));
        return this;
    }

    public UndoStringBuilder insert(int offset, double d) {
        sb.insert(offset, d);
        undoOperations.offerFirst(sb -> sb.delete(offset, offset + String.valueOf(d).length()));
        return this;
    }

    public int indexOf(String str) {
        return sb.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return sb.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return sb.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return sb.lastIndexOf(str, fromIndex);
    }

    public UndoStringBuilder reverse() {
        sb.reverse();
        undoOperations.offerFirst(StringBuilder::reverse);
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    public int length() {
        return sb.length();
    }

    public int capacity() {
        return sb.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        sb.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        sb.trimToSize();
    }

    public void setLength(int newLength) {
        int oldLength = sb.length();
        if (newLength >= oldLength) {
            sb.setLength(newLength);
            undoOperations.offerFirst(sb -> sb.setLength(sb.length() - (newLength - oldLength)));
        } else {
            String deleted = sb.substring(newLength);
            sb.setLength(newLength);
            undoOperations.offerFirst(sb -> sb.append(deleted));
        }
    }

    public char charAt(int index) {
        return sb.charAt(index);
    }

    public int codePointAt(int index) {
        return sb.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return sb.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return sb.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return sb.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        sb.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public void setCharAt(int index, char ch) {
        char replaced = sb.charAt(index);
        sb.setCharAt(index, ch);
        undoOperations.offerFirst(sb -> sb.setCharAt(index, replaced));
    }

    public String substring(int start) {
        return sb.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return sb.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return sb.substring(start, end);
    }

    public UndoStringBuilder undo() {
        Consumer<StringBuilder> op = undoOperations.pollFirst();
        if (op != null) {
            op.accept(sb);
        }
        return this;
    }
}

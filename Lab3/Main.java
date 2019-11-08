package Lab3;

public class Main {
    public static void main(String[] args) {
        UndoStringBuilder usb = new UndoStringBuilder("test ");

        System.out.print("After appending string: " + usb.append("appended"));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After appending substring: " + usb.append("something", 0, 4));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After appending boolean: " + usb.append(true));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After appending integer: " + usb.append(123));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After deleting substring: " + usb.delete(1, 3));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After deleting char: " + usb.deleteCharAt(2));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After inserting string: " + usb.insert(1, "insert"));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After inserting substring: " + usb.insert(1,"something", 0, 4));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After inserting boolean: " + usb.insert(1, true));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After inserting integer: " + usb.insert(1, 123));
        System.out.println("; After undoing: " + usb.undo());

        System.out.print("After replacing substring: " + usb.replace(1, 2, "oa"));
        System.out.println("; After undoing: " + usb.undo());

        usb.setCharAt(2, 'c');
        System.out.print("After setting char: " + usb);
        System.out.println("; After undoing: " + usb.undo());

        usb.setLength(10);
        System.out.print("After increasing length: " + usb);
        System.out.println("; After undoing: " + usb.undo());

        usb.setLength(2);
        System.out.print("After decreasing length: " + usb);
        System.out.println("; After undoing: " + usb.undo());
    }
}

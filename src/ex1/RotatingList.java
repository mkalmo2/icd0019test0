package ex1;

public class RotatingList {

    private int[] data;

    public int nextPos = 0;
    public int size = 0;

    public RotatingList(int capacity) {
        data = new int[capacity];
    }

    public String getInternalState() {
        String result = "";
        for (int each : data) {
            if (result.length() != 0) {
                result += ", ";
            }

            result += each;
        }

        return "[" + result + "]";
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            int index = (nextPos + i) % size;

            if (result.length() != 0) {
                result += ", ";
            }

            result += data[index];
        }

        return "[" + result + "]";
    }

    public void add(int element) {
        data[nextPos++] = element;

        nextPos %= data.length;

        if (size < data.length) {
            size++;
        }
    }
}

import java.util.Objects;

// data collected in array fields
// data: speed in the x,y
// data: is there package on x,y and what is the type
// data: what is the cost of mining the package
public class Fields {
    private final double value;
    private double valueOFPack;
    private String pack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fields)) return false;
        Fields fields = (Fields) o;
        return Double.compare(fields.getValue(), getValue()) == 0 && Double.compare(fields.getValueOFPack(), getValueOFPack()) == 0 && Objects.equals(getPack(), fields.getPack());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getValueOFPack(), getPack());
    }

    public Fields(double value){
        this.value = value;
        this.pack = "null";
        this.valueOFPack = -1;
    }

    public double getValue() {
        return value;
    }

    public double getValueOFPack() {
        return valueOFPack;
    }

    public String getPack() {
        return pack;
    }

    public void setValueOFPack(double valueOFPack) {
        this.valueOFPack = valueOFPack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }
    @Override
    public String toString() {
        return "Fields{" +
                "value=" + value +
                ", valueOFPack=" + valueOFPack +
                ", pack='" + pack + '\'' +
                '}';
    }
}

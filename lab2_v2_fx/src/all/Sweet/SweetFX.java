package all.Sweet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class SweetFX implements Comparable {
    protected DoubleProperty weight;
    protected StringProperty consist;
    protected StringProperty name;
    protected StringProperty additional;
    protected StringProperty type;

    public SweetFX() {
        this.weight = new SimpleDoubleProperty(0.0);
        this.consist = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.additional = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
    }

    public SweetFX(double weight, String name, String type,  String consist) {
        this.weight = new SimpleDoubleProperty(weight);
        this.consist = new SimpleStringProperty(consist);
        this.name = new SimpleStringProperty(name);
        this.additional = new SimpleStringProperty("");
        this.type = new SimpleStringProperty(type);
    }

    public SweetFX(double weight, String name, String type,  String consist, String additional) {
        this.weight = new SimpleDoubleProperty(weight);
        this.consist = new SimpleStringProperty(consist);
        this.name = new SimpleStringProperty(name);
        this.additional = new SimpleStringProperty(additional);
        this.type = new SimpleStringProperty(type);
    }

    public String getConsist() {
        return consist.get();
    }

    public StringProperty consistProperty() {
        return consist;
    }

    public void setConsist(String consist) {
        this.consist.set(consist);
    }

    public String getAdditional() {
        return additional.get();
    }

    public StringProperty additionalProperty() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional.set(additional);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return "Type: " + this.getClass().getName() + "\n" +
                "Name: " + this.name.get() + "\n" +
                "Weigth: " + this.weight.get() + "\n" +
                "Consist: " + this.consist.get() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SweetFX sweet = (SweetFX) o;

        if (Double.compare(sweet.weight.get(), weight.get()) != 0) return false;
        if (!consist.equals(sweet.consist)) return false;
        return name != null ? name.equals(sweet.name) : sweet.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weight.get());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + consist.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(this.weight.get() - ((SweetFX)o).weight.get());
    }
}

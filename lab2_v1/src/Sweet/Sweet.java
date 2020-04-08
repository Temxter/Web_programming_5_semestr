package Sweet;

import java.util.ArrayList;

/**
 * The type Sweet.
 */
public class Sweet implements Comparable<Sweet> {

    /**
     * The Weigth.
     */
    protected double weigth = 0.0;
    /**
     * The Consist.
     */
    protected ArrayList<String> consist = new ArrayList<>();
    /**
     * The Name.
     */
    protected String name = "";

    /**
     * Add item to consist.
     *
     * @param item the item
     */
    public void addItemToConsist(String item){
        this.consist.add(item);
    }

    /**
     * Delete item from consist boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean deleteItemFromConsist(String item){
        boolean isDeleted = false;
        for (String in: this.consist){
            if (in.compareTo(item) == 0){
                isDeleted = this.consist.remove(in);
                break;
            }
        }
        return isDeleted;
    }

    /**
     * Print consist.
     */
    public void printConsist() {
        System.out.println("Consist: " + this.consistToString());
    }

    /**
     * Instantiates a new Sweet.
     */
    public Sweet(){
        this.weigth = 0.0;
    }

    /**
     * Instantiates a new Sweet.
     *
     * @param weigth the weigth
     * @param name   the name
     */
    public Sweet(double weigth, String name){
        this.weigth = weigth;
        this.name = name;
    }

    /**
     * Instantiates a new Sweet.
     *
     * @param weigth  the weigth
     * @param name    the name
     * @param consist the consist
     */
    public Sweet(double weigth, String name, ArrayList<String> consist){
        this.consist = consist;
        this.weigth = weigth;
        this.name = name;
    }

    private String consistToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < consist.size(); i++){
            if (i < consist.size() - 1)
                stringBuilder.append(consist.get(i) + ", ");
            else
                stringBuilder.append(consist.get(i));
        }
        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Sweet o) {
        return (int)(this.weigth - o.weigth);
    }

    @Override
    public String toString() {
        return "Type: " + this.getClass().getName() + "\n" +
                "Name: " + this.name + "\n" +
                "Weigth: " + this.weigth + "\n" +
                "Consist: " + this.consistToString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sweet sweet = (Sweet) o;

        if (Double.compare(sweet.weigth, weigth) != 0) return false;
        if (!consist.equals(sweet.consist)) return false;
        return name != null ? name.equals(sweet.name) : sweet.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weigth);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + consist.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

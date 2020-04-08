package all.Present;

import all.Sweet.Sweet;

import java.util.ArrayList;
import java.util.Collections;

public class Present {
    private ArrayList<Sweet> presentList;

    public Present(){
        presentList = new ArrayList<Sweet>();
    }

    public void addSweet(Sweet sweet){
        presentList.add(sweet);
    }

    public boolean removeSweet(String sweetToDelete){
        boolean isDelete = false;
        for (Sweet sweet : presentList){
            if (sweet.getName().compareTo(sweetToDelete) == 0){
                presentList.remove(sweet);
                isDelete = true;
                break;
            }
        }
        return isDelete;
    }

    public void printPresent(){
        for (Sweet sweet : presentList){
            System.out.println(sweet + "\n");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int iter = 1;
        for (Sweet sweet : presentList){
            stringBuilder.append(iter + ") " + sweet + "\n\n");
            iter++;
        }
        return stringBuilder.toString();
    }

    public ArrayList<Sweet> getArrayListSweets(){
        return presentList;
    }


    public double getWeight(){
        double weight = 0.0;
        for (Sweet sweet : presentList){
            weight += sweet.getWeigth();
        }
        return weight;
    }

    public void sortByWeight(){
        Collections.sort(presentList);
    }

}

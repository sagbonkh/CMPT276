package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Iterable class that manages all the lenses.
public class LensManager implements Iterable <Lens>{
    private List <Lens> manager = new ArrayList<>();
    private int size = 0;
    private static LensManager instance;

    public static LensManager getInstance(){
        if (instance == null)
            instance = new LensManager();
        return instance;
    }

    public void addLens(Lens aLens){
        manager.add(aLens);
        size++;
    }

    public Lens retrieveLens(int index){
        return manager.get(index);
    }

    @Override
    public Iterator<Lens> iterator() {
        return manager.iterator();
    }

    public void display(){
        int size = manager.size();
        for(int i = 0; i < size; i++){
            System.out.print(i + ". " ); manager.get(i).show();
        }
    }
    public int getSize(){
        return size;
    }
}
// lens manager
//
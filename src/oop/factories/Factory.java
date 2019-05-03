package oop.factories;

import oop.CreatableObjects;
import java.util.HashMap;

public class Factory implements Creatable {
    private HashMap<CreatableObjects,Creatable> Makers;
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer,Object> data) {
        return Makers.get(E).createObject(E, data);
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        return Makers.get(E).setObjectFields(E,data);
    }

    public Factory() {
        Makers = new HashMap<>();
        Makers.put(CreatableObjects.Spearman, new SpearmanMaker());
        Makers.put(CreatableObjects.Swordman, new SwordmanMaker());
        Makers.put(CreatableObjects.Archer, new ArcherMaker());
        Makers.put(CreatableObjects.Catapult, new CatapultMaker());
        Makers.put(CreatableObjects.Battalion, new BattallionMaker());
    }
}

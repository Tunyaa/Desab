package tunyaa.desab.mmo.service;

import tunyaa.desab.mmo.model.Person;
import tunyaa.desab.mmo.model.ViewMap;



/**
 *
 * @author sergey
 */
public class MoveOnMapService {
    private final Person person;
    
    private final ViewMap viewMap;

    public MoveOnMapService(Person person, ViewMap viewMap) {
        this.person = person;
        this.viewMap = viewMap;
    }
    
    public void moveUp(){
        viewMap.up();
    }
}

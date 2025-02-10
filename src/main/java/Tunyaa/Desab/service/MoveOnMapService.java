package Tunyaa.Desab.service;

import Tunyaa.Desab.model.Person;
import Tunyaa.Desab.model.ViewMap;



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

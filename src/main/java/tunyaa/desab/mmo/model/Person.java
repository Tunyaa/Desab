package tunyaa.desab.mmo.model;

import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author sergey
 */
@SessionScope
public class Person {

    private int[] position;

    private int hp;
    private int atk;
    public Person() {
        this.position = new int[2];
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int y, int x) {
        this.position[0] = y;
        this.position[1] = x;
    }

    public int getX() {
        return position[1];
    }

    public int getY() {
        return position[0];
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
    
    

}

package Tunyaa.Desab.model;

/**
 *
 * @author sergey
 */
public class Monster {

    private int[] position;
    private int hp;

    private int atk;

    public Monster(int hp, int atk) {
        this.hp = hp;
        this.atk = atk;
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

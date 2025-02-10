package tunyaa.desab.mmo.model;

import java.util.ArrayList;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author sergey
 */
//Карта заполненая статическими объектами
@Component
//@SessionScope
public class Map {

    ArrayList<ArrayList<Field>> map;

    //Размер карты
    private int sizeY = 18;
    private int sizeX = 28;

    public Map() {
        newMap();
    }

    //Карта заполняется полностью травой и пустой зоной по периметру.
    private void mapGenerator() {
        for (int i = 0; i < sizeY; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < sizeX; j++) {
                if (i == 0 || j == 0 || i == sizeY - 1 || j == sizeX - 1) {
                    map.get(i).add(Field.EMPTY);
                } else {
                    map.get(i).add(Field.GRASS);
                }

            }
        }

    }

    private void rockGenerator() {
        int y = 2;
        int x = 2;
        int point;
        Random random = new Random();

        for (int i = y; i < sizeY - 2; i = y) {
            for (int j = x; j < sizeX - 2; j = x) {

                // Сколько-то клеток в сторону
                int nextInt = random.nextInt(3);
                for (int k = 0; k < nextInt; k++) {
                    point = perimeterCheck(j + k, sizeX);
                    map.get(i).set(point, Field.ROCK);
                }
                // Сколько-то клеток в сторону
                nextInt = random.nextInt(3);
                for (int k = 0; k < nextInt; k++) {
                    point = perimeterCheck(i + k, sizeY);
                    map.get(point).set(j, Field.ROCK);
                }
                // Сколько-то клеток в сторону
                nextInt = random.nextInt(3);
                for (int k = 0; k < nextInt; k++) {
                    point = perimeterCheck(j - k, sizeX);
                    map.get(i).set(point, Field.ROCK);
                }
                x += random.nextInt(3, 4);
            }
            x = 2;
            y += random.nextInt(2, 4);
        }
    }

    public void objectSpawn(int n, Field type) {
        Random random = new Random();

        for (int i = 0; i < n;) {
            int y = random.nextInt(sizeY);
            int x = random.nextInt(sizeX);
            if (map.get(y).get(x) == Field.GRASS) {
                map.get(y).set(x, type);
                i++;
            }
        }
    }

    // Проверка что указатель в периметре
    private int perimeterCheck(int point, int sizeDirection) {
        point = point < 1 ? 1 : point;
        point = point > sizeDirection - 2 ? sizeDirection - 2 : point;
        return point;
    }

    public void newMap() {
        map = new ArrayList<>();
        mapGenerator();
        rockGenerator();
        objectSpawn(5, Field.MONSTER);
        objectSpawn(4, Field.HERB);
        objectSpawn(1, Field.BOSS);
    }

    public ArrayList<ArrayList<Field>> getMap() {
        return map;
    }

    public ArrayList<Field> get(int i) {
        return map.get(i);
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

}

package tunyaa.desab.mmo.model;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import static tunyaa.desab.mmo.model.Field.*;

/**
 *
 * @author sergey
 */
@Component
@SessionScope
public class ViewMap {

//    private ArrayList<ArrayList<Field>> map;
    private String[][] viewMap;
    private Map map;
    private Person person;

    public ViewMap(Map map) {
        this.map = map;

    }

    @PostConstruct
    public void postConstruct() {
        //Создаём копию карты, которая заполнена ссылками на картинки
        viewMap = new String[map.getSizeY()][map.get(0).size()];
        //Ставим персонажа в рандомное место
        personRandomPointStart();
    }

    @Deprecated(since = "Use getBlackMap")
    public String[][] getViewMap() {
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                viewMap[i][j] = mapConverter(map.get(i).get(j));
            }
        }

        return viewMap;
    }

    public String[][] getBlackMap() {

        //Вся карта заполняется черными полями
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                viewMap[i][j] = "black.png";
            }
        }

        //Зона видимости
        int Area = 3;// обзор Area X Area вокруг персонажа

        //Если указатель выходит за пределы массива
        int y = person.getY() - Area < 0 ? 0 : person.getY() - Area;
        int x = person.getX() - Area < 0 ? 0 : person.getX() - Area;

        int yQ = person.getY() + Area > map.getSizeY() - 1 ? map.getSizeY() - 1 : person.getY() + Area;
        int xQ = person.getX() + Area > map.getSizeX() - 1 ? map.getSizeX() - 1 : person.getX() + Area;

        //Заполнение зоны видимости ссылками на картинку
        for (int i = y; i <= yQ; i++) {
            for (int j = x; j <= xQ; j++) {
                if (i == y && j == x || i == y && j == xQ || i == yQ && j == x || i == yQ && j == xQ) {
                    continue;
                }
                viewMap[i][j] = mapConverter(map.get(i).get(j));
            }
        }

        return viewMap;
    }

    //Заполнение зоны видимости ссылками на картинку
    public String mapConverter(Field field) {
        switch (field) {
            case EMPTY:
                return "grey.png";

            case GRASS:
                return "green.png";

            case PERSON:
                return "person.jpeg";

            case ROCK:
                return "rock.jpeg";

            case MONSTER:
                return "pirate.jpeg";

            case HERB:
                return "herb.jpeg";

            case BOSS:
                return "boss.png";
            default:
                throw new AssertionError();
        }
    }

    //Ставим персонажа в рандомное место
    private void personRandomPointStart() {
        Random random = new Random();
        boolean start = false;

        while (!start) {
            int x = random.nextInt(map.getSizeX());
            int y = random.nextInt(map.getSizeY());

            if (map.get(y).get(x) == GRASS) {
                map.get(y).set(x, PERSON);

                person = new Person();
                person.setPosition(y, x);

                System.out.println("Person position - " + y + " ," + x);
                start = true;
            }
        }
    }

    public void newMap() {
        map.newMap();
        //Ставим персонажа в рандомное место
        personRandomPointStart();
    }

    public void addMonster(){
        map.objectSpawn(4, MONSTER);
    }
    
    public void up() {

        if (map.get(person.getY() - 1).get(person.getX()) == GRASS) {
            map.get(person.getY() - 1).set(person.getX(), PERSON);
            map.get(person.getY()).set(person.getX(), GRASS);

            person.setPosition(person.getY() - 1, person.getX());
        }

    }

    public void down() {
        if (map.get(person.getY() + 1).get(person.getX()) == GRASS) {
            map.get(person.getY() + 1).set(person.getX(), PERSON);
            map.get(person.getY()).set(person.getX(), GRASS);
            person.setPosition(person.getY() + 1, person.getX());
        }
    }

    public void left() {
        if (map.get(person.getY()).get(person.getX() - 1) == GRASS) {
            map.get(person.getY()).set(person.getX() - 1, PERSON);
            map.get(person.getY()).set(person.getX(), GRASS);
            person.setPosition(person.getY(), person.getX() - 1);
        }
    }

    public void right() {
        if (map.get(person.getY()).get(person.getX() + 1) == GRASS) {
            map.get(person.getY()).set(person.getX() + 1, PERSON);
            map.get(person.getY()).set(person.getX(), GRASS);
            person.setPosition(person.getY(), person.getX() + 1);
        }
    }

}

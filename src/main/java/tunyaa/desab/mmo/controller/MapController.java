package tunyaa.desab.mmo.controller;

import tunyaa.desab.mmo.model.ViewMap;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author sergey
 */
@Controller

public class MapController {

    @Autowired
    private ViewMap viewMap;

//    @GetMapping("/map")
//    public String getMap(@RequestParam(required = false) String move, @RequestParam(required = false) String newMap, Model model) {
//
//        if (newMap != null) {
//            viewMap.newMap();
//        }
//        if (move != null) {
//            if (move.equals("↑")) {
//                viewMap.up();
//            } else if (move.equals("↓")) {
//                viewMap.down();
//            } else if (move.equals("←")) {
//                viewMap.left();
//            } else if (move.equals("→")) {
//                viewMap.right();
//            }
//        }
//
//        model.addAttribute("map", viewMap.getBlackMap());
//        return "map.html";
//    }
    @GetMapping("/map/table")
    public String getMapTable(@RequestParam(required = false) String move, Model model) {
        if (move != null) {
            if (move.equals("↑")) {
                viewMap.up();
            } else if (move.equals("↓")) {
                viewMap.down();
            } else if (move.equals("←")) {
                viewMap.left();
            } else if (move.equals("→")) {
                viewMap.right();
            }
        }

        model.addAttribute("map", viewMap.getBlackMap());
        return "fragments :: mapTable"; // Используем фрагмент Thymeleaf для возвращения таблицы
    }

    @GetMapping("/map")
    public String getMap(@RequestParam(required = false) String newMap, Model model) {
        if (newMap != null) {
            viewMap.newMap();
        }
        model.addAttribute("map", viewMap.getBlackMap());
        return "map.html";
    }

}

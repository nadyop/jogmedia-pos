package com.blibli.controller;

import com.blibli.model.Store;
import com.blibli.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store")
    public String dataStore(Model model){

        model.addAttribute("store",storeService.showStore());
        return "manager/show/store";
    }

    @RequestMapping(value = "/store/",method = RequestMethod.POST)
    public String updateData(Model model, Store store){
        storeService.saveOrUpdate(store);

        return "redirect:/store";
    }

    @RequestMapping(value = "/store/createStore",method = RequestMethod.GET)
    public String creStore(Model model){
      model.addAttribute("store", new Store());
        return "manager/edit/createStore";
    }

    @RequestMapping(value = "/store/edit/{id}", method = RequestMethod.GET)
    public String editDataStore(@PathVariable Integer id, Model model){
        model.addAttribute("store",storeService.getIdStore(id));
        return "manager/edit/createStore";
    }
}
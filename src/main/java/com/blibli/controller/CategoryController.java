package com.blibli.controller;

import com.blibli.model.Category;
import com.blibli.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String categoryList(Model model){
        model = categoryService.showAllCategories(model);
        return "manager/show/category";
    }

    @RequestMapping(value="/category/",method = RequestMethod.POST)
    public String simpanDataCategory(Category c){
        return categoryService.save(c);

    }
    @RequestMapping(value = "/category/createCategory", method = RequestMethod.GET)
    public String tampilFormCreateCategory(Model model){
        model = categoryService.manageFormCreateCategory(model);
        return "manager/edit/createCategory";
    }

    @RequestMapping(value = "/category/editCategory/{id}",method = RequestMethod.GET)
    public String editDataCategory(@PathVariable int id, Model model){
        model = categoryService.getIdCategory(model,id);
        return "manager/edit/createCategory";
    }
    @RequestMapping(value = "/category/search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchKey") String searchKey){
        model = categoryService.searchCategoryByTitle(model, searchKey);
        return "manager/show/category";
    }
    @RequestMapping(value = "/category/hapus/{id}",method = RequestMethod.GET)
    public String hapusDataCategory(@PathVariable Integer id,Model model){
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
    @RequestMapping(value = "/category/softDelete/{id}", method = RequestMethod.GET)
    public String softDeleteBook(@PathVariable Integer id) {
        categoryService.softDeleteCategoty(id);
        return "redirect:/category";
    }
}
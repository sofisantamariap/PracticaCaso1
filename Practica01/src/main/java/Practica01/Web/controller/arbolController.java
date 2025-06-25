/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.Web.controller;

/**
 *
 * @author sofisantamaria
 */
import Practica01.Web.models.Arbol;
import Practica01.Web.repository.ArbolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/arboles")
public class arbolController {
    @Autowired
    private ArbolRepository arbolRepository;

    @GetMapping
    public String listar(Model model) {
        List<Arbol> arboles = arbolRepository.findAll();
        model.addAttribute("arboles", arboles);
        return "arbol/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "arbol/nuevo";
    }

    @PostMapping
    public String guardar(@ModelAttribute Arbol arbol) {
        arbolRepository.save(arbol);
        return "redirect:/arboles";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
    Arbol arbol = ArbolRepository.findById(id).orElseThrow();
        model.addAttribute("arbol", arbol);
        return "arbol/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Arbol arbolActualizado) {
        arbolActualizado.setId(id);
        arbolRepository.save(arbolActualizado);
        return "redirect:/arboles";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        arbolRepository.deleteById(id);
        return "redirect:/arboles";
    }
    
}

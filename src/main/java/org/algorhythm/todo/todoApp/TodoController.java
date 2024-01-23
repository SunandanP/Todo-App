package org.algorhythm.todo.todoApp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private TodoRepository todoService;
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String getTodos(ModelMap model){
        model.put("todos", todoService.getTodos());
        return "todos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model){
        Todo todo = new Todo(0, "", "", LocalDate.now().plusYears(2), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addTodoRequest(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }
        todoService.addTodo(new Todo(todo.getId(),  todoService.getUsername()  , todo.getDescription(), todo.getTargetDate(), todo.getDone()));
        return "redirect:todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam long id){
        todoService.deleteTodo(todoService.getTodo(id));
        return "redirect:todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String updateTodo(ModelMap model, @RequestParam long id){
        if(!todoService.todoExists(id)){
            return "todos";
        }
        Todo old = todoService.getTodo(id);
//        Todo todo = new Todo(old.getId(), old.getUsername(), old.getDescription(), old.getTargetDate(), old.getDone());
        model.put("todo", old);
        return "updateTodo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodoRequest(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "updateTodo";
        }

        todo.setUsername(todoService.getUsername());
        System.out.println(todo);
        todoService.updateTodo(todo);
        return "redirect:todos";
    }
}

package org.algorhythm.todo.todoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {


    @Autowired
    TodoJpaRepository todoJpaRepository;

    public List<Todo> getTodos(){
        return todoJpaRepository.findAll().stream().filter(record -> record.getUsername().equals(getUsername())).toList();
    }

    public Todo getTodo(long id){
        return todoJpaRepository.findAll().stream().filter(record -> (record.getUsername().equals(getUsername()) && record.getId() == id)).toList().get(0);
    }

    public void addTodo(Todo todo){
        todoJpaRepository.save(todo);
    }

    public void updateTodo(Todo todo){
        todoJpaRepository.save(todo);
    }

    public void deleteTodo(Todo todo){
        todoJpaRepository.delete(todo);
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }



    public void setTodoJpaRepository(TodoJpaRepository todoJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
    }

    public boolean todoExists(long id){
        if(getTodos().stream().filter(record -> record.getId() == id).toList().isEmpty()){
            return false;
        }
        return true;
    }
}

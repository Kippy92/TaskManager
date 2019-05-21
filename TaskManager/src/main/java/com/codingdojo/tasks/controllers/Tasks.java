package com.codingdojo.tasks.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.tasks.models.Note;
import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.services.TaskService;
import com.codingdojo.tasks.services.NoteService;
import com.codingdojo.tasks.services.UserService;
import com.codingdojo.tasks.validators.TaskValidator;

@Controller
public class Tasks {
	private final TaskService taskService;
	private final NoteService noteService;
	private final UserService userService;
	private final TaskValidator taskValidator;
	public Tasks(TaskService taskService, NoteService noteService, UserService userService, TaskValidator taskValidator) {
		this.taskService = taskService;
		this.noteService = noteService;
		this.userService = userService;
		this.taskValidator = taskValidator;
	}
	@RequestMapping("/tasks/addTask")
    public String newTask(Model model, @ModelAttribute("task") Task task, HttpSession session, RedirectAttributes flash) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			HashMap<String, Object> map = new HashMap<String, Object> ();
			map.put("login", "You must log in first");
			flash.addFlashAttribute("errors", map);
			return "redirect:/";
		}
		model.addAttribute("user", user);
        return "newTask.jsp";
    }
	@RequestMapping(value="/tasks", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("task") Task task, BindingResult result, RedirectAttributes flash, HttpSession session) {
		taskValidator.validate(task, result);
		if (result.hasErrors()) {
            return "newTask.jsp";
        } 
		else {
			User u = (User) session.getAttribute("user");
            taskService.createTask(task);
            taskService.addTaskToUser(task, u);
            return "redirect:/main";
    	}
    }
	@RequestMapping("/tasks/{id}")
    public String showTask(Model model, @PathVariable("id") long id, @ModelAttribute("note") Note note, RedirectAttributes flash,  HttpSession session) {
		User u = (User) session.getAttribute("user");
		Task task = taskService.findTask(id);
		if (u.getId() != task.getUser().getId()) {
			HashMap<String, Object> map = new HashMap<String, Object> ();
			map.put("login", "You may only view your own tasks");
			flash.addFlashAttribute("errors", map);
			return "redirect:/main";
		}
    	List<Note> n = noteService.descNotes(task.getId());
    	model.addAttribute("task", task);
    	model.addAttribute("note", note);
    	model.addAttribute("n", n);
    	session.setAttribute("user", u);
        return "task.jsp";
    }
	@RequestMapping("/tasks/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Task task = taskService.findTask(id);
        model.addAttribute("task", task);
        return "editTask.jsp";
    }
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("tasks") Task task, BindingResult result) {
        taskService.updateTask(task.getId(), task.getCompleted());
        return "redirect:/main";
    }
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/main";
    }
	
	@RequestMapping(value="/notes", method=RequestMethod.POST)
	public String createNote(Model model, HttpSession session, @Valid @ModelAttribute("note") Note note, @RequestParam(value="task_id") Long task_id, BindingResult result) {
		noteService.createNote(note);
		Note n = this.noteService.findNote(note.getId());
		Task t = this.taskService.findTask(task_id);
		User u = (User) session.getAttribute("user");
		session.setAttribute("user", u);
		noteService.addNoteToTask(n, t);
		return "redirect:/main";
	}
}

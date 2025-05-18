package ch.zhaw.iwi.devops.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskController taskController;

    @BeforeEach
    public void resetTasks() throws Exception {
        Field tasksField = TaskController.class.getDeclaredField("tasks");
        tasksField.setAccessible(true);
        ((List<?>) tasksField.get(taskController)).clear();
    }

    @Test
    public void testAddAndGetTasks() throws Exception {
        Task newTask = new Task();
        newTask.setName("Testaufgabe");
        newTask.setStatus("Offen");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testaufgabe"))
                .andExpect(jsonPath("$.status").value("Offen"));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Testaufgabe"))
                .andExpect(jsonPath("$[0].status").value("Offen"));
    }

    @Test
    public void testMultipleScenarios() throws Exception {
        Task task1 = new Task();
        task1.setName("Aufgabe 1");
        task1.setStatus("Offen");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isOk());

        Task task2 = new Task();
        task2.setName("Aufgabe 2");
        task2.setStatus("Erledigt");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Aufgabe 1')].status").value(hasItem("Offen")))
                .andExpect(jsonPath("$[?(@.name == 'Aufgabe 2')].status").value(hasItem("Erledigt")));
    }
}
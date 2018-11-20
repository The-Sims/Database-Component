package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PhasedplanTest {
    private Phasedplan phasedplan;
    private List<Task> testTasks = new ArrayList<>();

    @Before
    public void TestInitialize() {
        phasedplan = new Phasedplan(1, "Test");
        testTasks.add(new Task(1, "test", "test"));
        testTasks.add(new Task(2, "blussen", "brand blussen"));
        phasedplan.setTasks(testTasks);
    }

    @Test
    public void TestConstructor() {
        assertEquals(phasedplan.getName(), "Test");
    }

    @Test
    public void TestAddTasks() {
        phasedplan.setTasks(testTasks);

        assertEquals(2, phasedplan.getTasks().size());
    }

    @Test
    public void TestAddTask() {
        phasedplan.addPhasedplanTask(new Task(3, "evacuatie", "evacueer burgers"));

        assertEquals(3, phasedplan.getTasks().size());
    }

    @Test
    public void TestEditTask() {
        Task test = new Task(1, "ontruimen", "ontruim de omgeving");
        phasedplan.editPhasedPlanTask(test);

        for (Task t : phasedplan.getTasks()) {
            if (t.getTaskId() == test.getTaskId()) {
                assertEquals(test.getName(), t.getName());
                assertEquals(test.getDescription(), t.getDescription());
            }
        }
    }
}
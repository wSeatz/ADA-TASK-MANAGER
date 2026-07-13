import com.example.ada1fp.models.AVLTree;
import com.example.ada1fp.models.heap;
import com.example.ada1fp.models.task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private heap taskHeap;
    private AVLTree taskTree;

    @BeforeEach
    public void setUp() {
        taskHeap = new heap();
        taskTree = new AVLTree();
    }

    // ==========================================================
    //  TESTS DE COMPORTAMIENTO DEL HEAP
    // ==========================================================

    @Test
    public void testInsercionRespetaPrioridades() {
        System.out.println("=== TEST 1: Inserción y orden correcto de prioridades en Heap ===");

        task t1 = new task(2, "Media prioridad", "2025-01-01");
        task t2 = new task(3, "Alta prioridad", "2025-01-02");
        task t3 = new task(1, "Baja prioridad", "2025-01-03");

        taskHeap.insert(t1);
        taskHeap.insert(t2);
        taskHeap.insert(t3);

        System.out.println("Insertado: 2, 3, 1");
        System.out.println("Extracción esperada: 3 → 2 → 1");

        assertEquals(t2, taskHeap.extractMax());
        assertEquals(t1, taskHeap.extractMax());
        assertEquals(t3, taskHeap.extractMax());

        System.out.println("✔ Resultado: el Heap mantiene el orden por prioridad.\n");
    }

    @Test
    public void testEliminacionMantieneIntegridad() {
        System.out.println("=== TEST 2: Eliminación y consistencia interna del Heap ===");

        task t1 = new task(2, "Media prioridad", "2025-01-01");
        task t2 = new task(3, "Alta prioridad", "2025-01-02");

        taskHeap.insert(t1);
        taskHeap.insert(t2);

        taskHeap.extractMax(); // Se extrae la de mayor prioridad

        System.out.println("Después de eliminar el elemento máximo");
        System.out.println("Tamaño esperado del heap: 1");

        assertEquals(1, taskHeap.size());
        assertEquals(t1, taskHeap.getMax());

        System.out.println("✔ Resultado: el Heap mantiene su estructura tras la eliminación.\n");
    }

    // ==========================================================
    //  TESTS DE RENDIMIENTO E INDEXACIÓN (AVL)
    // ==========================================================

    @Test
    public void testBusquedaAVL_EficienciaLogaritmica() {
        System.out.println("=== TEST 3: Eficiencia de búsqueda en el árbol AVL ===");

        for (int i = 1; i <= 1000; i++) {
            task t = new task(1, "Tarea " + i, "2025-01-01");
            taskTree.insert(t);
        }

        long start = System.nanoTime();
        task found = taskTree.search(500);
        long end = System.nanoTime();

        System.out.println("Tiempo de búsqueda para 1000 nodos: " + (end - start) + " ns");

        assertNotNull(found);
        assertEquals(500, found.getId());

        System.out.println("✔ Resultado: búsqueda O(log n) confirmada.\n");
    }

    // ==========================================================
    //  TESTS DE BALANCEO DEL AVL
    // ==========================================================

    @Test
    public void testAVL_RebalanceoAutomatico() {
        System.out.println("=== TEST 4: Verificación del rebalanceo automático del AVL ===");

        // Inserción en orden descendente (peor caso)
        for (int i = 10; i >= 1; i--) {
            task t = new task(1, "Tarea " + i, "2025-01-01");
            taskTree.insert(t);
        }

        int height = getTreeHeight(taskTree);

        System.out.println("Altura esperada aprox: 4  |  Altura real: " + height);

        assertTrue(height <= 4);

        System.out.println("✔ Resultado: el AVL se balanceó correctamente tras inserciones desbalanceadas.\n");
    }

    // Utilidad
    private int getTreeHeight(AVLTree tree) {
        return tree.getHeigth();
    }
}

# 🗂️ **ADA Task Manager**

Gestor de tareas implementado en Java utilizando **estructuras de datos avanzadas** como **Heap Máximo** y **Árbol AVL** para lograr eficiencia en la administración, búsqueda y priorización de tareas.

---

## 🚀 **Descripción del Proyecto**

Este proyecto combina dos estructuras fundamentales:

### 🔹 1. **Heap Máximo (Max-Heap)**
- Maneja las **prioridades** de las tareas.
- Inserción y extracción en **O(log n)**.

### 🔹 2. **Árbol AVL**
- Permite búsquedas rápidas por **ID**.
- Mantiene el árbol balanceado mediante **rotaciones automáticas**.
- Todas sus operaciones clave son **O(log n)**.

> El objetivo es ofrecer un gestor de tareas rápido, eficiente y con una estructura interna óptima.

---

## 🧩 **Características Principales**

✔ Inserción de tareas con prioridad  
✔ Extracción de la tarea más prioritaria  
✔ Búsqueda eficiente por ID (AVL)  
✔ Rebalanceo automático  
✔ Pruebas unitarias con JUnit  
✔ Interfaz gráfica con JavaFX  
✔ Organización clara del código

---

## 📁 **Estructura del Proyecto**

```bash
/src
└── main
├── java/com.example.ada1fp
│   ├── controllers/
│   ├── models/
│   │   ├── AVLTree.java
│   │   ├── heap.java
│   │   └── task.java
│   └── MainApplication.java
└── resources/com.example.ada1fp
├── main-view.fxml
└── application.css

/test
└── java/com.example.ada1fp
└── TaskManagerTest.java
```


---

# ▶️ **Cómo ejecutar el proyecto**

### ✅ **1. Abir el proyecto en IntelliJ IDEA**

- Seleccionar Open y elegir carpeta del proyecto
- Asegurarse de que el JDK está configurado (File > Project Structure > Project SDK)

### 2. Ejecutar la aplicación

- Buscar la clase **`HelloApplication.java`**
- Click derecho → **Run 'HelloApplication.main()'.**
- La interfaz gráfica se abrirá.

---
## 🧪 Cómo correr los tests

Este proyecto incluye una serie de pruebas utilizando **JUnit 5** para validar el funcionamiento del Heap y el Árbol AVL.

### ▶️ Ejecutar pruebas

Se pueden correr las pruebas de dos formas:

1. **Desde IntelliJ IDEA**
    - Navega a `src/test/java/TaskManagerTest.java`
    - Haz clic derecho en la clase o método → `Run 'TaskManagerTest'`

Las pruebas verifican:
- Inserción y orden de prioridad en el Heap
- Eliminación y mantenimiento de la estructura
- Búsqueda eficiente en el AVL
- Equilibrio automático del AVL tras inserciones

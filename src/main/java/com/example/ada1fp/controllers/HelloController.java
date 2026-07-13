    package com.example.ada1fp.controllers;

    import com.example.ada1fp.alert.AlertBox;
    import com.example.ada1fp.models.AVLTree;
    import com.example.ada1fp.models.heap;
    import com.example.ada1fp.models.task;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;

    import java.util.List;

    public class HelloController {
        private heap taskHeap = new heap();
        private final AVLTree taskTree = new AVLTree();
        private final AlertBox alertBox = new AlertBox();

        @FXML
        private TextField descriptionField;
        @FXML
        private ComboBox<Integer> priorityComboBox;
        @FXML
        private DatePicker expirationDatePicker;
        @FXML
        private TextArea outputArea;
        @FXML
        private TextField searchIdField;
        @FXML
        private TextField completeByIdField;

        @FXML
        public void initialize() {
            priorityComboBox.getItems().addAll(1, 2, 3); // 1: Baja, 2: Media, 3: Alta
        }

        @FXML
        protected void onAddTaskClick() {
            try {
                String description = descriptionField.getText();
                Integer priority = priorityComboBox.getValue();
                String expirationDate = expirationDatePicker.getValue() != null ?
                        expirationDatePicker.getValue().toString() : "";

                if (description.isEmpty() || priority == null || expirationDate.isEmpty()) {
                    alertBox.showAlert("Error", "Campos vacíos", "Por favor complete todos los campos", Alert.AlertType.ERROR);
                    return;
                }

                task newTask = new task(priority, description, expirationDate);
                taskHeap.insert(newTask);
                taskTree.insert(newTask);

                updateOutput();
                clearFields();
                alertBox.showAlert("Éxito", "Tarea agregada", "La tarea se ha agregado correctamente", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                alertBox.showAlert("Error", "Error al agregar tarea", e.getMessage(), Alert.AlertType.ERROR);
            }
        }

        @FXML
        protected void onCompleteHighestPriorityTaskClick() {
            try {
                if (taskHeap.isEmpty()) {
                    alertBox.showAlert("Error", "Heap vacío", "No hay tareas para completar", Alert.AlertType.ERROR);
                    return;
                }

                task completedTask = taskHeap.extractMax();
                taskTree.delete(completedTask.getId());

                updateOutput();
                alertBox.showAlert("Éxito", "Tarea completada",
                        "Tarea más prioritaria completada: " + completedTask.getDescription(),
                        Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                alertBox.showAlert("Error", "Error al completar tarea", e.getMessage(), Alert.AlertType.ERROR);
            }
        }

        @FXML
        protected void onCompleteTaskByIdClick() {
            try {
                int id = Integer.parseInt(completeByIdField.getText());
                task taskToComplete = taskTree.search(id);

                if (taskToComplete == null) {
                    alertBox.showAlert("Error", "Tarea no encontrada",
                            "No existe una tarea con ID: " + id, Alert.AlertType.ERROR);
                    return;
                }

                boolean removedFromHeap = removeFromHeap(taskToComplete);
                if (!removedFromHeap) {
                    alertBox.showAlert("Error", "Tarea no encontrada",
                            "La tarea no estaba en la cola de prioridad", Alert.AlertType.ERROR);
                    return;
                }

                taskTree.delete(id);

                updateOutput();
                alertBox.showAlert("Éxito", "Tarea completada",
                        "Tarea completada: " + taskToComplete.getDescription(),
                        Alert.AlertType.INFORMATION);
            } catch (NumberFormatException e) {
                alertBox.showAlert("Error", "ID inválido", "Por favor ingrese un número válido", Alert.AlertType.ERROR);
            }
        }

        @FXML
        protected void onSearchTaskClick() {
            try {
                int id = Integer.parseInt(searchIdField.getText());
                task foundTask = taskTree.search(id);

                if (foundTask != null) {
                    outputArea.setText("Tarea encontrada:\n" +
                            "ID: " + foundTask.getId() + "\n" +
                            "Descripción: " + foundTask.getDescription() + "\n" +
                            "Prioridad: " + foundTask.getPriority() + "\n" +
                            "Fecha vencimiento: " + foundTask.getExpirationDate());
                } else {
                    alertBox.showAlert("No encontrado", "Tarea no encontrada",
                            "No se encontró una tarea con ID: " + id, Alert.AlertType.INFORMATION);
                }
            } catch (NumberFormatException e) {
                alertBox.showAlert("Error", "ID inválido", "Por favor ingrese un número válido", Alert.AlertType.ERROR);
            }
        }

        @FXML
        protected void onShowHighestPriorityTaskClick() {
            if (taskHeap.isEmpty()) {
                alertBox.showAlert("Información", "No hay tareas",
                        "No hay tareas pendientes", Alert.AlertType.INFORMATION);
                return;
            }

            task highestPriorityTask = taskHeap.getMax();
            outputArea.setText("Tarea más prioritaria:\n" +
                    "ID: " + highestPriorityTask.getId() + "\n" +
                    "Descripción: " + highestPriorityTask.getDescription() + "\n" +
                    "Prioridad: " + highestPriorityTask.getPriority() + "\n" +
                    "Fecha vencimiento: " + highestPriorityTask.getExpirationDate());
        }

        @FXML
        protected void onShowAllTaskClick() {
            updateOutput();
        }

        private boolean removeFromHeap(task taskToRemove) {
            List<task> tasks = taskHeap.getHeap();
            boolean found = tasks.removeIf(t -> t.getId() == taskToRemove.getId());

            if (found) {
                heap newHeap = new heap();
                tasks.forEach(newHeap::insert);
                this.taskHeap = newHeap;
            }

            return found;
        }

        private void updateOutput() {
            StringBuilder sb = new StringBuilder();
            sb.append("Tareas pendientes (ordenadas por prioridad):\n");

            List<task> tasks = taskHeap.getHeap();
            if (tasks.isEmpty()) {
                outputArea.setText("No hay tareas registradas.");
                return;
            }

            tasks.sort((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));

            for (task t : tasks) {
                sb.append("ID: ").append(t.getId())
                        .append(" | Prioridad: ").append(t.getPriority())
                        .append(" | Descripción: ").append(t.getDescription())
                        .append(" | Fecha: ").append(t.getExpirationDate())
                        .append("\n");
            }

            outputArea.setText(sb.toString());
        }

        private void clearFields() {
            descriptionField.clear();
            priorityComboBox.getSelectionModel().clearSelection();
            expirationDatePicker.setValue(null);
        }
    }
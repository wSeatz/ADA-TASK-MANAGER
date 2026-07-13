package com.example.ada1fp.models;

public class AVLTree {
    private AVLNode root;

    private static class AVLNode {
        task task;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(task task) {
            this.task = task;
            this.height = 1;
        }
    }

    // Insertar una tarea en el árbol
    public void insert(task task) {
        root = insert(root, task);
    }

    private AVLNode insert(AVLNode node, task task) {
        if (node == null) {
            return new AVLNode(task);
        }

        if (task.getId() < node.task.getId()) {
            node.left = insert(node.left, task);
        } else if (task.getId() > node.task.getId()) {
            node.right = insert(node.right, task);
        } else {
            return node; // No se permiten duplicados
        }

        // Actualizar altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Balancear el árbol
        return balance(node);
    }

    // Buscar una tarea por ID
    public task search(int id) {
        AVLNode result = search(root, id);
        return result == null ? null : result.task;
    }

    private AVLNode search(AVLNode node, int id) {
        if (node == null || node.task.getId() == id) {
            return node;
        }

        if (id < node.task.getId()) {
            return search(node.left, id);
        } else {
            return search(node.right, id);
        }
    }

    // Eliminar una tarea por ID
    public void delete(int id) {
        root = delete(root, id);
    }

    private AVLNode delete(AVLNode node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.task.getId()) {
            node.left = delete(node.left, id);
        } else if (id > node.task.getId()) {
            node.right = delete(node.right, id);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;

                // Sin hijos
                if (temp == null) {
                    temp = node;
                    node = null;
                } else { // Un hijo
                    node = temp;
                }
            } else {
                // Nodo con dos hijos: obtener el sucesor
                AVLNode temp = minValueNode(node.right);
                node.task = temp.task;
                node.right = delete(node.right, temp.task.getId());
            }
        }

        if (node == null) {
            return null;
        }

        // Actualizar altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Balancear el árbol
        return balance(node);
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Métodos auxiliares para balanceo
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode balance(AVLNode node) {
        int balance = getBalance(node);

        // Rotación derecha
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Rotación izquierda-derecha
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotación izquierda
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Rotación derecha-izquierda
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public int getHeigth() {
        return height(root);
    }
}
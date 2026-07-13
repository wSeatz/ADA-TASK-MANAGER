package com.example.ada1fp.models;

import java.util.ArrayList;
import java.util.List;

public class heap {
    private final ArrayList<task> heap;

    public heap() {
        this.heap = new ArrayList<>();
    }
    public List<task> getHeap() {
        return new ArrayList<>(heap); // Devuelve una copia del ArrayList para evitar modificaciones externas
    }

    // Insertar un elemento en el heap
    public void insert(task value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    // Obtener el máximo (raíz del heap)
    public task getMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public task extractMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        if (heap.size() == 1) {
            return heap.remove(0); // Si solo hay un elemento, se elimina y se devuelve.
        }
        task max = heap.get(0); // Se guarda el elemento con mayor prioridad (raíz).
        heap.set(0, heap.remove(heap.size() - 1)); // Se reemplaza la raíz con el último elemento.
        heapifyDown(0); // Se restaura la propiedad del heap.
        return max; // Se devuelve el elemento eliminado.
    }

    // Heapify hacia arriba
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).getPriority() > heap.get(parentIndex).getPriority()) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Heapify hacia abajo
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap.get(leftChild).getPriority() > heap.get(largest).getPriority()) {
                largest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).getPriority() > heap.get(largest).getPriority()) {
                largest = rightChild;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }
    // Intercambiar dos elementos en el array
    private void swap(int i, int j) {
        task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Obtener el tamaño del heap
    public int size() {
        return heap.size();
    }

    // Verificar si el heap está vacío
    public boolean isEmpty() {
        return heap.isEmpty();
    }

}
package Arbol;

import common.ListaEnlazada.ListaEnlazada;

/**
 *
 * @author brand
 */

public class ArbolOrganizacional<T> {

    private NodoArbol<T> raiz;

    public ArbolOrganizacional() {
        this.raiz = null;
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    public static class NodoArbol<T> {
        private T dato;
        private ListaEnlazada<NodoArbol<T>> hijos;

        public NodoArbol(T dato) {
            this.dato = dato;
            this.hijos = new ListaEnlazada<>();
        }

        public T getDato() {
            return dato;
        }

        public ListaEnlazada<NodoArbol<T>> getHijos() {
            return hijos;
        }

        public void agregarHijo(NodoArbol<T> hijo) {
            hijos.agregarFin(hijo);
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }

    public boolean insertar(T datoPadre, T datoHijo) {
        if (raiz == null) {
            // Si no hay raíz, el primer elemento será la raíz
            raiz = new NodoArbol<>(datoPadre);
            raiz.agregarHijo(new NodoArbol<>(datoHijo));
            return true;
        }

        NodoArbol<T> nodoPadre = buscarNodo(raiz, datoPadre);
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(new NodoArbol<>(datoHijo));
            return true;
        }
        return false;
    }

    public NodoArbol<T> buscarNodo(NodoArbol<T> nodoActual, T dato) {
        if (nodoActual == null) return null;
        if (nodoActual.getDato().equals(dato)) return nodoActual;

        ListaEnlazada<NodoArbol<T>> hijos = nodoActual.getHijos();
        for (int i = 0; i < hijos.tamanno(); i++) {
            NodoArbol<T> encontrado = buscarNodo(hijos.obtener(i), dato);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    public void recorrerYMostrar() {
        recorrerYMostrar(raiz, 0);
    }

    private void recorrerYMostrar(NodoArbol<T> nodo, int nivel) {
        if (nodo == null) return;

        System.out.println("  ".repeat(nivel) + "- " + nodo.getDato());
        ListaEnlazada<NodoArbol<T>> hijos = nodo.getHijos();
        for (int i = 0; i < hijos.tamanno(); i++) {
            recorrerYMostrar(hijos.obtener(i), nivel + 1);
        }
    }

}


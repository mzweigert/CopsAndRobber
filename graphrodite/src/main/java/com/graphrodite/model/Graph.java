package com.graphrodite.model;


import com.graphrodite.exception.EdgeAlreadyExistException;
import com.graphrodite.exception.VertexAlreadyExistException;
import com.graphrodite.service.internal.GraphService;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph<E> implements Serializable {

    private List<Edge<E>> edges;
    private List<Vertex<E>> vertices;
    private GraphService<E> graphService;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        graphService = new GraphService<>(vertices, edges);
    }

    public static <E> Graph<E> newInstance() {
        return new Graph<>();
    }

    public Graph<E> addEdge(E first, E second) throws EdgeAlreadyExistException {
        graphService.addEdge(first, second);
        return this;
    }

    public Graph<E> addEdge(E first, E... neighbors) throws EdgeAlreadyExistException {
        graphService.addEdge(first, neighbors);
        return this;
    }

    public Vertex<E> addVertex(E index) throws VertexAlreadyExistException {
        return graphService.addVertex(index);
    }

    public List<Vertex<E>> addVertices(E... indexes) throws VertexAlreadyExistException {
        return graphService.addVertices(indexes);
    }

    public List<Vertex<E>> addPath(E... indexes) throws EdgeAlreadyExistException {
        return graphService.addPath(indexes);
    }

    public Optional<Edge<E>> findEdge(E first, E second) {
        return graphService.findEdge(e -> e.containsVertices(first, second));
    }

    public Optional<Vertex<E>> findVertex(E index) {
        return graphService.findVertex(v -> v.getIndex().equals(index));
    }

    public List<Vertex<E>> getVertices() {
        return new ArrayList<>(vertices);
    }

    public List<Edge<E>> getEdges() {
        return new ArrayList<>(edges);
    }

    public Graph<E> clone() {
        return (Graph<E>) SerializationUtils.clone(this);
    }

}

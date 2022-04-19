package io.szrharrison.mkveditor.models;

import java.util.*;
import java.util.stream.Stream;

public class Nodes implements Map<String, List<Node>> {
  private final Map<String, List<Node>> nodes = new HashMap<>();

  @Override
  public int size() {
    return nodes.size();
  }

  @Override
  public boolean isEmpty() {
    return nodes.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return nodes.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return nodes.containsValue(value);
  }

  @Override
  public List<Node> get(Object key) {
    return nodes.get(key);
  }

  @Override
  public List<Node> put(String key, List<Node> value) {
    return nodes.put(key, value);
  }

  public List<Node> put(String key, Node value) {
    List<Node> nodesAtKey = nodes.getOrDefault(key, new ArrayList<>());
    nodesAtKey.add(value);
    return nodes.put(key, nodesAtKey);
  }

  public List<Node> add(Node value) {
    return put(value.getKey(), value);
  }

  @Override
  public List<Node> remove(Object key) {
    return nodes.remove(key);
  }

  @Override
  public void putAll(Map<? extends String, ? extends List<Node>> m) {
    nodes.putAll(m);
  }

  public void putAll(Collection<? extends Node> nodes) {
    nodes.forEach(this::add);
  }

  @Override
  public void clear() {
    nodes.clear();
  }

  public Stream<Node> stream() {
    return nodes.values().stream().flatMap(List::stream);
  }

  public Stream<Node> parallelStream() {
    return nodes.values().parallelStream().flatMap(List::parallelStream);
  }

  @Override
  public Set<String> keySet() {
    return nodes.keySet();
  }

  @Override
  public Collection<List<Node>> values() {
    return nodes.values();
  }

  @Override
  public Set<Entry<String, List<Node>>> entrySet() {
    return nodes.entrySet();
  }
}

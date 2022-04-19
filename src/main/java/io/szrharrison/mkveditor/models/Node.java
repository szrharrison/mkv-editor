package io.szrharrison.mkveditor.models;

import lombok.Getter;

import java.util.List;

public class Node {
  @Getter
  private final String key;
  @Getter
  private final String value;
  @Getter
  private final Nodes children;
  private Integer level;
  @Getter
  private Node parent;

  public Node(String key, String value) {
    this.key = key;
    this.value = value;
    level = 0;
    children = new Nodes();
  }

  public void add(Node node) {
    children.add(node);
    node.level = level + 1;
    node.parent = this;
  }

  public Node get(String key) {
    if (children.containsKey(key)) {
      if (children.get(key).size() == 1) {
        return children.get(key).get(0);
      } else {
        if (children.get(key).listIterator().hasNext()) {
          return children.get(key).listIterator().next();
        } else {
          return null;
        }
      }
    } else {
      return null;
    }
  }

  @Override
  public String toString() {
    String thisString = ("<Node>" + ('[' + key + "]:").indent(level * 2)).trim();
    if (value != null) {
      thisString += ' ' + value;
    }

    if (!children.isEmpty()) {
      List<String> childrenStrings = children.stream().map(Node::toString).toList();
      return thisString + '\n' + String.join("\n", childrenStrings);
    } else {
      return thisString;
    }
  }
}

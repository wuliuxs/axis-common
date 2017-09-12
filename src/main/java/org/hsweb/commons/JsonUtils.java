package org.hsweb.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.io.IOException;
import java.util.*;

/**
 * Created by qingyuan on 17/9/12.
 */
public class JsonUtils {

    public static boolean jsonEquals(String json1, String json2) throws IOException {
        List<Pair<StringBuilder, ValueNode>> pvs1 = json2Pairs(json1);
        List<Pair<StringBuilder, ValueNode>> pvs2 = json2Pairs(json2);
        if (pvs1.size() != pvs2.size())
            return false;

        pvs1.sort((p1, p2) -> p1.left.toString().compareTo(p2.left.toString()));
        pvs2.sort((p1, p2) -> p1.left.toString().compareTo(p2.left.toString()));
        for (int i = 0; i < pvs1.size(); i++) {
            Pair<StringBuilder, ValueNode> v1 = pvs1.get(i);
            Pair<StringBuilder, ValueNode> v2 = pvs2.get(i);
            //System.out.println("left="+v1.left.toString().equals(v2.left.toString())+",right="+v1.right.equals(v2.right));
            if (!v1.left.toString().equals(v2.left.toString()) || !v1.right.equals(v2.right))
                return false;
        }
        return true;
    }

    private static List<Pair<StringBuilder, ValueNode>> json2Pairs(String json) throws IOException {
        List<Pair<StringBuilder, ValueNode>> pairs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        ContainerNode<?> root = mapper.readValue(json, ContainerNode.class);
        Queue<Pair<StringBuilder, JsonNode>> nodes = new LinkedList<>();
        nodes.offer(new Pair<StringBuilder, JsonNode>(new StringBuilder(), root));
        while (!nodes.isEmpty()) {
            Pair<StringBuilder, JsonNode> pair = nodes.poll();
            JsonNode node = pair.right;
            //System.out.println("visit "+pair.left);
            if (node instanceof ValueNode) {
                ValueNode vnode = (ValueNode) node;
                pairs.add(new Pair<StringBuilder, ValueNode>(pair.left, vnode));
            } else if (node instanceof ArrayNode) {
                ArrayNode arrayNode = (ArrayNode) node;
                for (int i = 0; i < arrayNode.size(); i++) {
                    StringBuilder sb = new StringBuilder(pair.left).append("/").append(i);
                    nodes.offer(new Pair<StringBuilder, JsonNode>(sb, arrayNode.get(i)));
                }
            } else if (node instanceof ObjectNode) {
                ObjectNode objectNode = (ObjectNode) node;
                Iterator<Map.Entry<String, JsonNode>> iterator = objectNode.fields();
                while (iterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = iterator.next();
                    StringBuilder sb = new StringBuilder(pair.left).append("/").append(entry.getKey());
                    nodes.offer(new Pair<StringBuilder, JsonNode>(sb, entry.getValue()));
                }
            }

        }

        return pairs;
    }
}

class Pair<Left, Right> {
    Left left;
    Right right;

    Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Pair [left=" + left + ", right=" + right + "]";
    }

}
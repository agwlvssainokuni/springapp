package cherry.spring.common.lib.navi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NavigatorImpl implements Navigator, InitializingBean {

	private Resource navigationDef;

	private ObjectMapper objectMapper;

	private Map<String, NaviNode> nodeMap;

	public void setNavigationDef(Resource navigationDef) {
		this.navigationDef = navigationDef;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		try (InputStream in = navigationDef.getInputStream()) {
			NaviNode root = objectMapper.readValue(in, NaviNode.class);
			nodeMap = toMap(root, new HashMap<String, NaviNode>());
		}
	}

	private Map<String, NaviNode> toMap(NaviNode node, Map<String, NaviNode> map) {
		map.put(node.getName(), node);
		if (node.getChildren() == null) {
			return map;
		}
		for (NaviNode child : node.getChildren()) {
			child.setParent(node);
			toMap(child, map);
		}
		return map;
	}

	@Override
	public List<Node> navigate(String name) {
		List<Node> list = new ArrayList<>();
		NaviNode node = nodeMap.get(name);
		if (node != null) {
			do {
				Node nd = new Node();
				nd.setName(node.getName());
				nd.setUri(node.getUri());
				nd.setLast(false);
				list.add(nd);
			} while (node.getParent() == null);
			list.get(0).setLast(true);
			Collections.reverse(list);
		}
		return list;
	}

	public static class NaviNode {

		@JsonProperty
		private String name;

		@JsonProperty
		private String uri;

		@JsonProperty
		private List<NaviNode> children;

		private NaviNode parent;

		@Override
		public String toString() {
			ReflectionToStringBuilder builder = new ReflectionToStringBuilder(
					this, ToStringStyle.SHORT_PREFIX_STYLE);
			builder.setExcludeFieldNames("parent");
			return builder.build();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public List<NaviNode> getChildren() {
			return children;
		}

		public void setChildren(List<NaviNode> children) {
			this.children = children;
		}

		public NaviNode getParent() {
			return parent;
		}

		public void setParent(NaviNode parent) {
			this.parent = parent;
		}
	}

}

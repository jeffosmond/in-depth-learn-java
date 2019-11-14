package com.jeffosmond.config.parser;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.sqlnode.*;
import com.jeffosmond.sqlnode.handler.NodeHandler;
import com.jeffosmond.sqlsource.DynamicSqlSource;
import com.jeffosmond.sqlsource.RawSqlSource;
import com.jeffosmond.sqlsource.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @function   : Mapper配置XML文件解析类
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 14:20
 */
public class ScriptXMLParser {

	private Configuration configuration;

	/**
	 * nodeHandler集合(系统自己初始化好的handler->initNodeHandlerMap方法,后面就只是往出取)
	 */
	private Map<String, NodeHandler> nodeHandlerMap = new HashMap<>();

	private boolean isDynamic = false;

	public ScriptXMLParser(Configuration configuration) {
		this.configuration = configuration;
		initNodeHandlerMap();
	}

	/**
	 * 初始化系统内置的Handler类
	 * todo:待扩展
	 */
	private void initNodeHandlerMap() {
		nodeHandlerMap.put("if", new IfNodeHandler());
		// nodeHandlerMap.put("where", new WhereNodeHandler());
		// nodeHandlerMap.put("foreach", new ForeachNodeHandler());
	}

	public SqlSource parseScriptNode(Element mapperElement) {
		// 首先先将sql脚本按照不同的类型，封装到不同的SqlNode
		MixedSqlNode rootSqlNode = parseDynamicTags(mapperElement);
		// 再将SqlNode集合封装到SqlSource中
		SqlSource sqlSource;
		if (isDynamic) {
			sqlSource = new DynamicSqlSource(rootSqlNode);
		} else {
			sqlSource = new RawSqlSource(rootSqlNode);
		}
		// 由于带有#{}和${}、动态标签的sql处理方式不同，所以需要封装到不同的SqlSource中
		return sqlSource;
	}

	/**
	 * 解析所有sql片段为MixedSqlNode
	 * @param mapperElement
	 * @return
	 */
	private MixedSqlNode parseDynamicTags(Element mapperElement) {
		List<SqlNode> sqlContent = new ArrayList<>();
		int nodeCount = mapperElement.nodeCount();
		for (int i = 0; i < nodeCount; i++){
			Node node = mapperElement.node(i);
			if (node instanceof Text) {
				String sqlText = node.getText().trim();
				if (sqlText == null || sqlText.equals("")) {
					continue;
				}
				TextSqlNode sqlNode = new TextSqlNode(sqlText);
				// 判断文本中是否带有${}
				if (sqlNode.isDynamic()) {
					sqlContent.add(sqlNode);
					isDynamic = true;
				} else {
					sqlContent.add(new StaticTextSqlNode(sqlText));
				}
			}
			// 则递归解析
			else if (node instanceof Element) {
				// 比如说if\where\foreach等动态sql子标签就需要在这处理
				// 根据标签名称，封装到不同的节点信息
				Element nodeToHandle = (Element) node;
				String nodeName = nodeToHandle.getName().toLowerCase();
				NodeHandler nodeHandler = nodeHandlerMap.get(nodeName);
				nodeHandler.handleNode(nodeToHandle, sqlContent);
				isDynamic = true;
			}
		}
		return new MixedSqlNode(sqlContent);
	}

	public class IfNodeHandler implements NodeHandler {
		@Override
		public void handleNode(Element nodeToHandle, List<SqlNode> targetContents) {
			// 对if标签进行解析(递归调用)
			MixedSqlNode rootSqlNode = parseDynamicTags(nodeToHandle);
			String test = nodeToHandle.attributeValue("test");
			IfSqlNode ifSqlNode = new IfSqlNode(test, rootSqlNode);
			targetContents.add(ifSqlNode);
		}
	}
}

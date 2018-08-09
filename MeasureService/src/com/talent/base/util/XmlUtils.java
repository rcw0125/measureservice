package com.talent.base.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

@SuppressWarnings("unchecked")
public class XmlUtils {
	public Document parseStringToXml(String xml) {
		try {
			return DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			return null;
		}
	}

	// 读取xml文件
	public Document parseFileToXml(String filepath) {
		SAXReader saxReader = new SAXReader();
		try {
			return saxReader.read(new File(filepath));
		} catch (DocumentException e) {
			return null;
		}
	}

	// 根据xpath 获取指定的元素
	public List<Map<String, String>> getElementByNodes(Document doc, String[] nodes) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (String node : nodes) {
			Map<String, String> element = new HashMap<String, String>();
			element.put("name", node);
			try {
				element.put("value", doc.selectSingleNode("//*[name()='" + node + "']").getStringValue());
			} catch (Exception e) {
				element.put("value", null);
			}
			result.add(element);
		}
		return result;
	}

	// 获取指定Node的xml元素
	public Element getElementByNode(Document doc, String node) {
		return getElementByXpath(doc, "//*[name()='" + node + "']");
	}

	// 根据xpath 获取指定的元素
	public Element getElementByXpath(Document doc, String xpath) {
		List<Element> parameterList = doc.selectNodes(xpath);
		if (parameterList != null && parameterList.size() > 0) {
			return (Element) parameterList.get(0);
		} else {
			return null;
		}
	}
	
	public String getPropertyValueByXpath(Document doc,String xpath){
		return doc.selectObject(xpath).toString();
	}

	// 根据xpath 获取指定的元素
	public Map<String,String> getElementMapByXpath(Document doc, String xpath) {
		Map<String,String> result = null;
		Node node = doc.selectSingleNode(xpath);
		if (node != null) {
			result = new HashMap<String,String>();
			List<Node> nodeChilds = node.getDocument().selectNodes("child::node()");
			for (Node child : nodeChilds) {
				result.put(child.getName(), child.getText());
			}
		}
		return result;
	}

	public List<Map<String, String>> getElementListByXpath(Document doc,String xpath,String childnode) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Node> nodeList = doc.selectNodes(xpath);
		for (Node node : nodeList) {
			List<Node> nodeChilds = node.getDocument().selectNodes(xpath + "/[name()='" + node + "']");
			Map<String, String> nodeItem = null;
			for (Node child : nodeChilds) {
				nodeItem = new HashMap<String, String>();
				nodeItem.put(child.getName(), child.getText());
			}
			result.add(nodeItem);
		}
		return result;
	}

	// 在指定id的元素后面插入元素
	public Document insertElementByNode(String node, Element newele, Document doc) {
		return insertElementByNode(node, newele, doc, 1);
	}

	// 在指定id的元素后面或前面插入元素
	public Document insertElementByNode(String node, Element newele, Document doc, int i) {
		Element element = getElementByNode(doc, node);// 坐标元素
		List<Element> list = element.getParent().content();// 获取坐标元素父元素下的所有元素
		list.add(list.indexOf(element) + i, newele);
		return doc;
	}

	// 获取指定id的xml元素(单个xml中id唯一的情况下使用)
	public Element getElementById(String id, Document doc) {
		return getElementByXpath(doc, "//*[@id='" + id + "']");
	}

	// 在指定id的元素后面插入元素
	public Document insertElementById(String pid, Element newele, Document doc) {
		return insertElementById(pid, newele, doc, 1);
	}

	// 在指定id的元素后面或前面插入元素
	public Document insertElementById(String pid, Element newele, Document doc, int i) {
		Element element = getElementById(pid, doc);// 坐标元素
		List<Element> list = element.getParent().content();// 获取坐标元素父元素下的所有元素
		list.add(list.indexOf(element) + i, newele);
		return doc;
	}

	// 删除文档doc的指定路径下的所有子节点（包含元素，属性等）,如果路径相同一并删除
	public boolean deleteNodes(Document doc, String xpath) {
		try {
			List<Node> nlist = doc.selectNodes(xpath);
			for (Node node : nlist) {
				node.detach();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 删除一个父元素下所有的子节点（包含元素，属性等）
	public boolean deleteChildren(Element element) {
		try {
			List<Node> nlist = element.elements();
			for (Node node : nlist) {
				node.detach();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 删除指定的元素
	public boolean deleteElement(Element ele) {
		List<Element> list = ele.getParent().content();
		list.remove(list.indexOf(ele));
		return true;
	}

	// 保存xml
	public boolean saveDocument(String filepath, Document document) {
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));
			writer.write(document);
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Map<String, Object> dom2Map(Document doc) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null) {
			return map;
		}

		Element root = doc.getRootElement();
		for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List<Element> list = e.elements();
			if (list.size() > 0) {
				map.put(e.getName(), dom2Map(e));
			} else
				map.put(e.getName(), e.getText());
		}
		return map;
	}

	public Map<String, Object> dom2Map(String filepath) {
		Document doc = parseFileToXml(filepath);
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null) {
			return map;
		}

		Element root = doc.getRootElement();
		for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List<Element> list = e.elements();
			if (list.size() > 0) {
				map.put(e.getName(), dom2Map(e));
			} else
				map.put(e.getName(), e.getText());
		}
		return map;
	}

	public Map<String, Object> dom2Map(Element e) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List<Object> mapList = new ArrayList<Object>();
				if (iter.elements().size() > 0) {
					Map<String, Object> m = dom2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof ArrayList)) {
							mapList.add(obj);
							mapList.add(m);
						} else {
							mapList = (List<Object>) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof ArrayList)) {
							mapList.add(obj);
							mapList.add(iter.getText());
						} else {
							mapList = (List<Object>) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else {
			map.put(e.getName(), e.getText());
		}
		return map;
	}
}

/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2018 - 2018
 */
package com.zhishun.zaotoutiao.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zhishun.zaotoutiao.core.model.common.ResultObj;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author 闫迎军(YanYingJun)
 * @version $Id: XMLUtil, v0.1 2018年03月01日 14:58闫迎军(YanYingJun) Exp $
 */
public class XMLUtil {

    private XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                public String encodeNode(String name) {
                    return name;
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (this.cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }

                }
            };
        }
    });
    private XStream inclueUnderlineXstream = new XStream(new DomDriver((String)null, new XmlFriendlyNameCoder("_-", "_")));

    public XMLUtil() {
    }

    public Map<String, String> parseXml(String msg) throws Exception {
        new ResultObj();
        Map<String, String> map = new HashMap();
        InputStream inputStream = new ByteArrayInputStream(msg.getBytes("UTF-8"));
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        Iterator var10 = elementList.iterator();

        while(var10.hasNext()) {
            Element e = (Element)var10.next();
            map.put(e.getName(), e.getText());
        }

        inputStream.close();
        inputStream = null;
        return map;
    }

    public XStream getXstreamInclueUnderline() {
        return this.inclueUnderlineXstream;
    }

    public XStream xstream() {
        return this.xstream;
    }
}

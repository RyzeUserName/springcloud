package com.lft.consumer.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/17 7:46
 */

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //FEIGN 不支持 get 绑定pojo json body 转成 query 即可
        if (requestTemplate.method().equals("GET") && requestTemplate.body() != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(requestTemplate.body());
                requestTemplate.body(null);

                HashMap<String, Collection<String>> hashMap = new HashMap<>();
                buildQuery(jsonNode, "", hashMap);
                requestTemplate.queries(hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        // 叶子节点
        if (!jsonNode.isContainerNode()) {
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        // 数组节点
        if (jsonNode.isArray()) {
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {  // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}

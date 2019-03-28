package com.lft.zuulserver.dao;

import com.lft.zuulserver.entity.ZuulRouteEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-28 18:48
 */
@Component
public class ZuulRouteEntityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String sql = "SELECT * FROM zuul_route WHERE enabled =TRUE";

    public Map<String, ZuulRoute> zuulRouteMap() {
        Map<String, ZuulRoute> objectObjectLinkedHashMap = new LinkedHashMap<>();
        List<ZuulRouteEntity> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ZuulRouteEntity.class));

        query.forEach(zuulRouteEntity -> {
            if (StringUtils.isEmpty(zuulRouteEntity.getPath())) {
                return;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            BeanUtils.copyProperties(zuulRouteEntity, zuulRoute);
            objectObjectLinkedHashMap.put(zuulRoute.getPath(), zuulRoute);
        });
        return objectObjectLinkedHashMap;
    }
}

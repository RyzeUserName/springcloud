package com.lft.zuulserver.config;

import com.lft.zuulserver.dao.ZuulRouteEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-28 18:57
 */
public class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    @Autowired
    private ZuulProperties properties;
    @Autowired
    private ZuulRouteEntityDao zuulRouteEntityDao;

    public DynamicZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    /**
     * Compute a map of path pattern to route. The default is just a static map from the
     * {@link ZuulProperties}, but subclasses can add dynamic calculations.
     */
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String, ZuulProperties.ZuulRoute> stringZuulRouteLinkedHashMap = new LinkedHashMap<>();
        Map<String, ZuulProperties.ZuulRoute> map = new LinkedHashMap<>();
        stringZuulRouteLinkedHashMap.putAll(super.locateRoutes());
        stringZuulRouteLinkedHashMap.putAll(zuulRouteEntityDao.zuulRouteMap());
        stringZuulRouteLinkedHashMap.forEach((s, zuulRoute) -> {
            String path = s;
            if (!path.startsWith("/")) {
                path = "/" + path;

            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;

                }
            }
            map.put(path, zuulRoute);
        });

        return map;
    }
}

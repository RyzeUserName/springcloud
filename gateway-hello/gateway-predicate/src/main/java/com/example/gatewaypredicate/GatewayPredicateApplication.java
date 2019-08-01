package com.example.gatewaypredicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
public class GatewayPredicateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayPredicateApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        ZonedDateTime zonedDateTime1 = LocalDateTime.now().minusMinutes(1).atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());

        ////After
        //RouteLocator route = routeLocatorBuilder.routes().route("after_route", r -> r.after(zonedDateTime).uri("http://jd.com:80/")).build();
        ////before
        //RouteLocator route = routeLocatorBuilder.routes().route("before_route", r -> r.before(zonedDateTime).uri("http://jd.com:80/")).build();

//        //between
//        RouteLocator route = routeLocatorBuilder.routes().route("before_route", r -> r.between(zonedDateTime1,zonedDateTime2).uri("http://jd.com:80/")).build();

        //RouteLocator route = routeLocatorBuilder.routes().route("cookie_route", r -> r.cookie("chocolate","ch.p").uri("http://jd.com:80/")).build();

        // RouteLocator route = routeLocatorBuilder.routes().route("host_route", r -> r.host( "**.jd1.com:8082").uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("method_route", r -> r.method("GET").uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("query_route", r -> r.query("q","a").uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("remoteAddr_route", r -> r.remoteAddr("127.0.0.1").uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("header_add_route",
//            r -> r.path("/test").filters(f->f.addRequestHeader("q","a"))
//                .uri("http://localhost:8081/test")).build();

//
//        RouteLocator route = routeLocatorBuilder.routes().route("header_parameter_add_route",
//            r -> r.path("/parameter").filters(f->f.addRequestParameter("parameter","gddfsdas"))
//                .uri("http://localhost:8081/parameter")).build();


//        RouteLocator route = routeLocatorBuilder.routes().route("rewrite_path_route",
//            r -> r.path("/foo/**").filters(f->f.rewritePath("/foo/(?<segment>.*)","/$\\{segment}"))
//                .uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("add_response_header_route",
//            r -> r.path("/test").filters(f->f.addResponseHeader("q","a"))
//                .uri("http://jd.com:80/")).build();

//        RouteLocator route = routeLocatorBuilder.routes().route("retry_route",
//            r -> r.path("/test/retry").filters(f->f.retry(retryConfig -> retryConfig.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
//                .uri("http://localhost:8081/retry?key=abc&count=2")).build();

        RouteLocator route = routeLocatorBuilder.routes().route("retry_route",
            r -> r.path("/test/retry").filters(f->f.retry(retryConfig -> retryConfig.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                .uri("http://localhost:8081/retry?key=abc&count=2")).build();

        return route;
    }

}

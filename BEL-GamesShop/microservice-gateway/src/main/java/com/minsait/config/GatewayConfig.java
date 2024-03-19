package com.minsait.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class GatewayConfig {

    @Value("${gateway.global}")
    private String global;
    @Value("${gateway.videogames}")
    private String videoGames;
    @Value("${gateway.promotion}")
    private String promotion;
    @Value("${gateway.categories}")
    private String categories;
    @Value("${gateway.platforms}")
    private String platforms;
    @Value("${gateway.shop}")
    private String shop;
    @Value("${gateway.stock}")
    private String stock;
    @Value("${gateway.videoGamesService}")
    private String videoGamesService;
    @Value("${gateway.categoriesService}")
    private String categoriesService;
    @Value("${gateway.shopService}")
    private String shopService;

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes(){
         return route()
                 .path(global, builder -> builder
                         .nest(GatewayRequestPredicates.path(videoGames), videoGamesBuilder ->
                                 videoGamesBuilder.route(GatewayRequestPredicates.path("/**"),
                                         this::forwardToVideoGamesService)
                         )
                         .nest(GatewayRequestPredicates.path(promotion), promotionbuilder ->
                                 promotionbuilder.route(GatewayRequestPredicates.path("/**"),
                                        this::forwardToVideoGamesService)
                         )
                         .nest(GatewayRequestPredicates.path(categories), categoriesBuilder ->
                                 categoriesBuilder.route(GatewayRequestPredicates.path("/**"),
                                         this::forwardToCategoriesService)
                         )
                         .nest(GatewayRequestPredicates.path(platforms), plataformsbuilder ->
                                 plataformsbuilder.route(GatewayRequestPredicates.path("/**"),
                                         this::forwardToCategoriesService)
                         )
                         .nest(GatewayRequestPredicates.path(shop), shopBuilder ->
                                 shopBuilder.route(GatewayRequestPredicates.path("/**"),
                                         this::forwardToShopService)
                         )
                         .nest(GatewayRequestPredicates.path(stock), stockBuilder1 ->
                                 stockBuilder1.route(GatewayRequestPredicates.path("/**"),
                                         this::forwardToShopService))).build();

    }

private ServerResponse forwardToVideoGamesService (ServerRequest serverRequest){
        return ServerResponse.permanentRedirect(URI.create(videoGamesService +
                serverRequest.uri().getPath())).build();

}

private  ServerResponse forwardToCategoriesService(ServerRequest serverRequest){
        return ServerResponse.permanentRedirect(URI.create(categoriesService +
                serverRequest.uri().getPath())).build();
}
private  ServerResponse forwardToShopService(ServerRequest serverRequest){
        return ServerResponse.permanentRedirect(URI.create(shopService +
                serverRequest.uri().getPath())).build();
}

}

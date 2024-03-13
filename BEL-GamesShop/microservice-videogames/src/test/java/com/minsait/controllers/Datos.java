package com.minsait.controllers;

import com.minsait.models.Promotion;
import com.minsait.models.VideoGame;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class Datos {


    public static Optional<VideoGame> createVideogame1(){
        return Optional.of( new VideoGame(1L, "Left 4 Dead 2", "This co-operative action horror FPS takes you and your friends " +
                "through the cities, swamps and cemeteries of the Deep South, from Savannah to New Orleans across five expansive campaigns. Youll " +
                "play as one of four new survivors armed with a wide and devastating array of classic and upgraded weapons", "2009-06-04", new BigDecimal("123.99")));
    }
    public static Optional<VideoGame> createVideogame2(){
        return Optional.of( new VideoGame(2L, "Red Dead Redemption 2", "The story is set in a fictionalized representation of the United " +
                "States in 1899 and follows the exploits of Arthur Morgan, an outlaw and member of the Van der Linde gang, who must deal with the decline " +
                "of the Wild West while attempting to survive against government forces, rival gangs, and other adversaries.", "2018-11-17", new BigDecimal("1250.50")));
    }
    public static Optional<VideoGame> createVideogame3(){
        return Optional.of( new VideoGame(3L, "Dead by Daylight", "Dead by Daylight is a multiplayer (4vs1) horror game where one player " +
                "takes on the role of the savage Killer, and the other four players play as Survivors, trying to escape the Killer and avoid being caught, " +
                "tortured and killed.", "2016-07-14", new BigDecimal("250.50")));
    }

    public static Optional<Promotion> createPromotion() {

        return Optional.of(new Promotion(1L,
                "Las ofertas de verano son el momento más esperado por los jugadores de la tienda de Valve. Hasta el próximo 13 " +
                        "de julio a las 19:00 (CEST) tendrás acceso a descuentos en juegos de todo tipo del catálogo. Además de la importante rebaja de Steam Deck, " +
                        "lo que más les importa a los jugadores son los juegos. Repasamos los descuentos más destacados de este arranque del periodo festivo",
                LocalDate.parse("2024-06-29"),
                LocalDate.parse("2024-07-13"),
                50,
                new VideoGame()));
    }
    public static Optional<Promotion> createPromotion2() {

        return Optional.of(new Promotion(1L,
                "La primavera está a la vuelta de la esquina y celebrar el cambio de estación de la mejor forma posible, ya que la tienda de Valve ha " +
                        "iniciado la promoción Rebajas de Primavera de 2024, en la que el precio de cientos de videojuegos se ha reducido considerablemente durante unos cuantos días.",
                LocalDate.parse("2024-03-06"),
                LocalDate.parse("2025-04-01"),
                50,
                new VideoGame()));
    }
}
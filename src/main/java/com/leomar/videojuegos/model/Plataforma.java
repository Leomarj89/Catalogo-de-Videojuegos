package com.leomar.videojuegos.model;

public enum Plataforma {
    PC("PC"),
    PS1("PS1"),
    PS2("PS2"),
    PS3("PS3"),
    PS4("PS4"),
    PS5("PS5"),
    PSP("PSP"),
    XBOX_360("Xbox 360"),
    NINTENDO_DS("Nintendo DS"),
    NINTENDO_3DS("Nintendo 3DS"),
    NINTENDO_SWITCH("Nintendo Switch"),
    NINTENDO_SWITCH_2("Nintendo Switch 2"),
    WII("Wii"),
    GAME_BOY("Game Boy"),
    GAME_BOY_ADVANCE("Game Boy Advance"),
    SMARTPHONE("Smartphone");

    private final String label;

    Plataforma(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
package com.Asana.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.experimental.Accessors;


import java.util.Locale;
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Accessors(chain=true)
public class Data {

    private String name;

    private boolean archived;

    private String color;

    @JsonProperty("public")
    private boolean pub;

    private String team;

    private String workspace;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int Money;




    public static Data randomProject(){

        Faker faker = new Faker(Locale.UK);

        Data data = new Data();
        data.setName(faker.name().username());
        data.setArchived(faker.random().nextBoolean());
        data.setColor("light-pink");
        data.setTeam("1205601799046677");
        data.setWorkspace("1205541665331966");


        return data;
    }

    public static  Data emptyProject(){

        Data data = new Data();
        return data;
    }
















}

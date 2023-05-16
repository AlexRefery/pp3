package ru.alex.restClient.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record User (Long id, String name, String lastName, Byte age) {}


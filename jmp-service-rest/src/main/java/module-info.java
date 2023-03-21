module jmp.service.rest {
    requires spring.web;
    requires io.swagger.v3.oas.annotations;
    requires spring.beans;
    requires jmp.service.api;
    requires jmp.dto;
    requires jmp.cloud.service.impl;
    requires swagger.annotations;
    requires spring.boot.autoconfigure;
    requires spring.boot;

    opens com.epam.learn to spring.core, spring.beans, spring.context;
    opens com.epam.learn.api to spring.core, spring.beans, spring.context, spring.web;
}

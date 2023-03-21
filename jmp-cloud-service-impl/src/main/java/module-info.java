module jmp.cloud.service.impl {
    requires spring.context;
    requires spring.webmvc;
    requires jmp.dto;
    requires spring.data.commons;
    requires spring.core;
    requires spring.aop;
    requires jmp.service.api;
    requires spring.tx;
    requires spring.beans;
    exports com.epam.learn.exception;

    opens com.epam.learn.service.user to spring.core, spring.beans, spring.context, spring.aop;
    opens com.epam.learn.service.subscription to spring.core, spring.beans, spring.context, spring.aop;
}
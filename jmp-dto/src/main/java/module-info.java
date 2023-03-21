module jmp.dto {
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    exports com.epam.learn.dto;
    exports com.epam.learn.entity;

    opens com.epam.learn.entity;
}
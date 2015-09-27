package cherry.foundation.db.gen.query;

import javax.annotation.Generated;

/**
 * BSqlExecution is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BSqlExecution {

    private Long id;

    private String login;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
         return "id = " + id + ", login = " + login + ", name = " + name;
    }

}


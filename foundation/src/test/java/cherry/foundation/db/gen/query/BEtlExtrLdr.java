package cherry.foundation.db.gen.query;

import javax.annotation.Generated;

/**
 * BEtlExtrLdr is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BEtlExtrLdr {

    private String address;

    private Long id;

    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
         return "address = " + address + ", id = " + id + ", name = " + name;
    }

}


package cherry.foundation.db.gen.query;

import javax.annotation.Generated;

/**
 * BVerifyFlag is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class BVerifyFlag {

    private Integer deletedFlg;

    private Integer flagCode;

    private Long id;

    public Integer getDeletedFlg() {
        return deletedFlg;
    }

    public void setDeletedFlg(Integer deletedFlg) {
        this.deletedFlg = deletedFlg;
    }

    public Integer getFlagCode() {
        return flagCode;
    }

    public void setFlagCode(Integer flagCode) {
        this.flagCode = flagCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
         return "deletedFlg = " + deletedFlg + ", flagCode = " + flagCode + ", id = " + id;
    }

}


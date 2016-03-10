package com.example.divyasingh.json;

/**
 * Created by divyasingh on 10/03/16.
 */
public class AnObject {
    private String more;

    private String[] anarray;

    private String whoa;

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String[] getAnarray() {
        return anarray;
    }

    public void setAnarray(String[] anarray) {
        this.anarray = anarray;
    }

    public String getWhoa() {
        return whoa;
    }

    public void setWhoa(String whoa) {
        this.whoa = whoa;
    }

    @Override
    public String toString() {
        return "ClassPojo [more = " + more + ", anarray = " + anarray + ", whoa = " + whoa + "]";
    }
}

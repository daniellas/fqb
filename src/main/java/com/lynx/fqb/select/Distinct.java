package com.lynx.fqb.select;

public class Distinct implements Sources {

    private final Select select;

    public Distinct(Select select) {
        this.select = select;
    }

    @Override
    public Select getSelect() {
        return select;
    }

}

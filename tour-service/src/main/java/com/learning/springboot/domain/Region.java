package com.learning.springboot.domain;

public enum Region {

    Sindh("Sindh"), Punjab("Punjab"), Balochistan("Balochistan"), Kpk("Kpk");

    private String label;

    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String byLabel) {
        for (Region r : Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel)) {
                return r;
            }
        }
        return null;
    }
}

package com.footpath.inventory.enums;

import java.util.EnumSet;

public enum CategoryAvlZone {

    IN_ALL, // all zone in india
    IN_WB, //west bengal
    IN_AP, //andhra pradesh
    IN_AS, //assam
    IN_BR, //bihar
    IN_DL, //delhi
    IN_TN; //tamil nadu

    public static EnumSet<CategoryAvlZone> allZoneCategory = EnumSet.of(CategoryAvlZone.IN_AP,
                                            CategoryAvlZone.IN_AS, CategoryAvlZone.IN_BR, CategoryAvlZone.IN_BR,
                                            CategoryAvlZone.IN_DL, CategoryAvlZone.IN_TN, CategoryAvlZone.IN_WB);
}

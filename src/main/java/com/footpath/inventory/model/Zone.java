package com.footpath.inventory.model;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(value = "footpath_zone")
public class Zone {

    @MongoId
    private String _id = new ObjectId().toString();

    @NonNull
    private String zoneCode;

    @NonNull
    private String zoneDetails;

}

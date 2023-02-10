package com.example.madprojectfinal;

import androidx.annotation.FloatRange;

public class FoodDetail {
    String sid, type , items , pdate,ptime, loc;
    int qty;
    double dist;
    @FloatRange
    double la,lo;
    @Override
    public String toString()
    {
        return "ID: "+this.sid+"\nType: "+this.type+"\nFood: "+this.items+"\nFood Quantity: "+this.qty+"\nPickup Date and Time: "+this.pdate+ " "+this.ptime+"\nLocation: "+this.loc+"\nDistance: "+dist+"km";
    }
}

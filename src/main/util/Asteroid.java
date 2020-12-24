package main.util;

import java.awt.*;

public class Asteroid extends Point {
    public Asteroid(int x, int y){
        super(x,y);
    }

    public Asteroid add(Asteroid b, int times){
        return new Asteroid(this.x + times*b.x,this.y+times*b.y);
    }

    public double getAngle(Asteroid target) {
        double diffX =target.x- this.x;
        double diffY = target.y - this.y;
        double angle =  Math.toDegrees(Math.atan2(diffY, diffX));
        angle += 90;
        if(angle < 0){
            angle += 360;
        }


        return angle;
    }
}

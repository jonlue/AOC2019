package main.util;

import java.util.Objects;

public class Moon {
    private int x;
    private int y;
    private int z;

    private int velocityX;
    private int velocityY;
    private int velocityZ;

    public Moon(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        velocityX = 0;
        velocityY = 0;
        velocityZ = 0;
    }

    public Moon(Moon m){
        this.x = m.x;
        this.y = m.y;
        this.z = m.z;
        velocityX = m.velocityX;
        velocityY = m.velocityY;
        velocityZ = m.velocityZ;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return x == moon.x &&
                y == moon.y &&
                z == moon.z &&
                velocityX == moon.velocityX &&
                velocityY == moon.velocityY &&
                velocityZ == moon.velocityZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public int getVelocityZ() {
        return velocityZ;
    }

    public void applyVelocity(){
        this.x += this.velocityX;
        this.y += this.velocityY;
        this.z += this.velocityZ;
    }

    public void compareMoon(Moon other){
        if(this.x == other.getX()){
            this.setVelocityX(0);
            other.setVelocityX(0);
        }else if(this.x <= other.getX()){
            this.setVelocityX(1);
            other.setVelocityX(-1);
        }else{
            this.setVelocityX(-1);
            other.setVelocityX(1);
        }

        if(this.y == other.getY()){
            this.setVelocityY(0);
            other.setVelocityY(0);
        }else if(this.y <= other.getY()){
            this.setVelocityY(1);
            other.setVelocityY(-1);
        }else{
            this.setVelocityY(-1);
            other.setVelocityY(1);
        }

        if(this.z == other.getZ()){
            this.setVelocityZ(0);
            other.setVelocityZ(0);
        }else if(this.z <= other.getZ()){
            this.setVelocityZ(1);
            other.setVelocityZ(-1);
        }else{
            this.setVelocityZ(-1);
            other.setVelocityZ(1);
        }
    }

    public void compareMoon(Moon other, int step) {

        switch (step) {
            case 0:
                if (this.x == other.getX()) {
                    this.setVelocityX(0);
                    other.setVelocityX(0);
                } else if (this.x <= other.getX()) {
                    this.setVelocityX(1);
                    other.setVelocityX(-1);
                } else {
                    this.setVelocityX(-1);
                    other.setVelocityX(1);
                }
                break;
            case 1:
                if(this.y == other.getY()){
                    this.setVelocityY(0);
                    other.setVelocityY(0);
                }else if(this.y <= other.getY()){
                    this.setVelocityY(1);
                    other.setVelocityY(-1);
                }else{
                    this.setVelocityY(-1);
                    other.setVelocityY(1);
                }
            break;

            case 2:
                if (this.z == other.getZ()) {
                    this.setVelocityZ(0);
                    other.setVelocityZ(0);
                } else if (this.z <= other.getZ()) {
                    this.setVelocityZ(1);
                    other.setVelocityZ(-1);
                } else {
                    this.setVelocityZ(-1);
                    other.setVelocityZ(1);
                }
        }
    }

    public void setVelocityY(int velocityY) {
        this.velocityY += velocityY;
    }

    public void setVelocityX(int velocity){
        this.velocityX += velocity;
    }

    public void setVelocityZ(int velocityZ) {
        this.velocityZ += velocityZ;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getPot(){
        return Math.abs(this.x)+Math.abs(this.y)+Math.abs(this.z);
    }

    public int getKin(){
        return Math.abs(this.velocityX) + Math.abs(this.velocityY) + Math.abs(this.velocityZ);
    }
}

package step3.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Car {

    private final static int START_LOCATION = 0;
    private final static int MOVE_STRATEGY = 1;
    private int currentLocation;

    public Car() {
        this.currentLocation = START_LOCATION;
    }

    public void move(Boolean isMove) {
        if (isMove) {
            this.currentLocation = this.currentLocation + MOVE_STRATEGY;
        }
    }

    public int getCurrentLocation() {
        return this.currentLocation;
    }

    public Car(int location) {
        this.currentLocation = location;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

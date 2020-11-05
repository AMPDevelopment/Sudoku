package Sudoku.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Field {
    private int value;

    public static final int DIMENSION = 9;

    public Field() {
        this.value = 0;
    }

    public Field(int value) {
        this.value = value;
    }

    /*
     * Gets the value of Field.
     */
    public int getValue() {
        return this.value;
    }

    /*
     * Sets the value of Field.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /*
     * Checks whether the object is equal to this
     * @Param object The object that wants to get checked with the current field.
     */
    public boolean equal(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (!(object instanceof Field)) {
            return false;
        }

        Field field = (Field) object;
        return new EqualsBuilder().append(field.getValue(), this.value).isEquals();
    }
}